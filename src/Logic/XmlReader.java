package Logic;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;

import Logic.Objects.Commit;
import Logic.Objects.Folder;
import Zip.ZipFile;
import GeneratedXML.MagitRepository;
import Logic.Objects.BlobData;
import org.apache.commons.codec.digest.DigestUtils;

import static Logic.ConstantsEnums.EmptyString;


public class XmlReader {
    private static final String JAXB_XML_GAME_PACKAGE_NAME = "GeneratedXML";
    private MagitRepository magitRepository;
    private ZipFile m_ZipFile;
    private String m_Location;

    public XmlReader(String i_XMLLocation) throws XmlException{
        try {
            Path XmlFilePath = Paths.get(i_XMLLocation);
            if(i_XMLLocation.length()<4||!i_XMLLocation.substring(i_XMLLocation.length() - 4).equals(".xml"))
                throw new XmlException("File is not xml format");
            if(!Files.exists(XmlFilePath))
                throw new XmlException("Xml file not found");
            InputStream inputStream = new FileInputStream(i_XMLLocation);
            try {
                magitRepository = deserializeFrom(inputStream);
                m_ZipFile = new Zip.ZipFile();
                xmlFileIsValid_oneId32();
            } catch (JAXBException e) {
                e.printStackTrace();
            }

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private MagitRepository deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (MagitRepository)u.unmarshal(in);
    }
    public void buildFromXML() throws XmlException {
        BuildRepositoryObjects() ;
    }
    public String[] getLocation() {
        String[] RepositoryLocation = {magitRepository.getLocation(), magitRepository.getName()};
        m_Location = RepositoryLocation[0] + File.separator + RepositoryLocation[1];
        return RepositoryLocation;
    }
    public String getActiveBranch(){
        return magitRepository.getMagitBranches().getHead();
    }
    public void BuildRepositoryObjects() throws XmlException{
        checkIfActiveBranchExist(getActiveBranch());
        Path ActiveBranchFilePath = Paths.get(m_Location + File.separator + ".magit" +File.separator + "branches" +File.separator + "HEAD.txt");
        Path BranchesNamesFilePath = Paths.get(m_Location + File.separator + ".magit" +File.separator + "branches" +File.separator + "NAMES.txt");
        try {
            Files.write(BranchesNamesFilePath, EmptyString.getBytes());
            Files.write(ActiveBranchFilePath, getActiveBranch().getBytes());
            for (MagitRepository.MagitBranches.MagitSingleBranch branch : magitRepository.getMagitBranches().getMagitSingleBranch()) {
                Files.write(BranchesNamesFilePath, (branch.getName() + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
                checkIfCommitExist(branch.getPointedCommit().getId());
                String CommitSha1 = buildCommit(magitRepository.getMagitCommits().getMagitSingleCommit().get(branch.getPointedCommit().getId() - 1));
                updateBranchCommit(CommitSha1, branch.getName());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateBranchCommit(String i_CommitSha1,String i_BranchName) {
        Path BranchPath = Paths.get(m_Location + File.separator + ".magit" + File.separator + "branches" + File.separator + i_BranchName + ".txt");
        try{
            if(!Files.exists(BranchPath))
                Files.createFile(BranchPath);
            Files.write(BranchPath,i_CommitSha1.getBytes());
        }
        catch (Exception e)
        {

        }
    }
    private String buildCommit(MagitRepository.MagitCommits.MagitSingleCommit commitXmlObject) throws XmlException {
        String PreCommitSha1 = "NONE";
        if(commitXmlObject.getPrecedingCommits()!=null && commitXmlObject.getPrecedingCommits().getPrecedingCommit()!=null) {
            checkIfCommitExist(commitXmlObject.getPrecedingCommits().getPrecedingCommit().getId());
            MagitRepository.MagitCommits.MagitSingleCommit commit = getCommitXmlObject(commitXmlObject.getPrecedingCommits().getPrecedingCommit().getId());
            PreCommitSha1 = buildCommit(commit);
        }
        BlobData rootBlobData;
        Commit commitObject = new Commit();
        commitObject.setM_CreatedBy(commitXmlObject.getAuthor());
        commitObject.setM_CreatedTime(commitXmlObject.getDateOfCreation());
        commitObject.setM_Message(commitXmlObject.getMessage());
        checkIfFolderIdExist(commitXmlObject.getRootFolder().getId());
        checkIfFolderIsRootFolder(commitXmlObject.getRootFolder().getId());
        rootBlobData = buildRootFolder(commitXmlObject.getRootFolder().getId(), ConstantsEnums.FileType.FOLDER);
        commitObject.setM_MainSHA1(rootBlobData.getM_Sha1());
        commitObject.setM_PreviousSHA1(PreCommitSha1);
        m_ZipFile.zipFile(m_Location + File.separator + ".magit"+File.separator+"objects",DigestUtils.sha1Hex(commitObject.toString()),commitObject.toString());
        return DigestUtils.sha1Hex(commitObject.toString());
    }
    private BlobData buildRootFolder(Byte id, ConstantsEnums.FileType fileType) throws XmlException {
        String sha1;
        if (fileType == ConstantsEnums.FileType.FOLDER) {
            Folder folder = new Folder();
            MagitRepository.MagitFolders.MagitSingleFolder MagitFolder =  getFolderXmlObject(id);
            for (MagitRepository.MagitFolders.MagitSingleFolder.Items.Item itemFolder : MagitFolder.getItems().getItem()) {
                if (itemFolder.getType().equals("blob")) {
                    checkIfBlobIdExist(itemFolder.getId());
                    folder.AddNewItem(buildRootFolder(itemFolder.getId(), ConstantsEnums.FileType.FILE));
                } else {
                    checkIfFolderIdExist(itemFolder.getId());
                    checkIfFolderPointToHimself(itemFolder.getId(), id);
                    folder.AddNewItem(buildRootFolder(itemFolder.getId(), ConstantsEnums.FileType.FOLDER));
                }
            }
            sha1 = DigestUtils.sha1Hex(folder.toString());
            BlobData directoryBlob =
                    new BlobData(MagitFolder.getName(), sha1, ConstantsEnums.FileType.FOLDER,
                            MagitFolder.getLastUpdater(), MagitFolder.getLastUpdateDate());
            m_ZipFile.zipFile(m_Location + File.separator + ".magit" + File.separator + "objects", sha1, folder.printArray());
            return directoryBlob;
        } else {
            MagitRepository.MagitBlobs.MagitBlob blob = getBlobXmlObject(id);
            BlobData blobData = new BlobData(blob.getName(), DigestUtils.sha1Hex(blob.getContent()), ConstantsEnums.FileType.FILE, blob.getLastUpdater(), blob.getLastUpdateDate());
            m_ZipFile.zipFile(m_Location + File.separator + ".magit" + File.separator + "objects", DigestUtils.sha1Hex(blob.getContent()), blob.getContent());
            return blobData;
        }
    }
    private MagitRepository.MagitBlobs.MagitBlob getBlobXmlObject(Byte id) {
        MagitRepository.MagitBlobs.MagitBlob BlobToFind = null;
        for(MagitRepository.MagitBlobs.MagitBlob blob: magitRepository.getMagitBlobs().getMagitBlob())
        {
            if(blob.getId().equals(id))
                BlobToFind =  blob;
        }
        return BlobToFind;
    }
    private MagitRepository.MagitFolders.MagitSingleFolder getFolderXmlObject(Byte id) {
        MagitRepository.MagitFolders.MagitSingleFolder FolderToFind = null;
        for(MagitRepository.MagitFolders.MagitSingleFolder folder: magitRepository.getMagitFolders().getMagitSingleFolder())
        {
            if(folder.getId().equals(id))
                FolderToFind =  folder;
        }
        return FolderToFind;
    }
    private MagitRepository.MagitCommits.MagitSingleCommit getCommitXmlObject(Byte id) {
        MagitRepository.MagitCommits.MagitSingleCommit CommitToFind = null;
        for(MagitRepository.MagitCommits.MagitSingleCommit commit: magitRepository.getMagitCommits().getMagitSingleCommit())
        {
            if(commit.getId().equals(id))
                CommitToFind =  commit;
        }
        return CommitToFind;
    }

    //Xml Validation 3.2
    private void xmlFileIsValid_oneId32() throws XmlException{
        List<Byte>idNumbers = new ArrayList<>();
        for(MagitRepository.MagitBlobs.MagitBlob blob:magitRepository.getMagitBlobs().getMagitBlob())
        {
            if(idNumbers.contains(blob.getId())) {
                throw new XmlException("there is 2 blobs with the same id");
            }
            idNumbers.add(blob.getId());
        }
        idNumbers.clear();
        for(MagitRepository.MagitFolders.MagitSingleFolder folder:magitRepository.getMagitFolders().getMagitSingleFolder())
        {
            if(idNumbers.contains(folder.getId())) {
                throw new XmlException("there is 2 folders with the same id");
            }
            idNumbers.add(folder.getId());
        }
        idNumbers.clear();
        for(MagitRepository.MagitCommits.MagitSingleCommit commit:magitRepository.getMagitCommits().getMagitSingleCommit())
        {
            if(idNumbers.contains(commit.getId())) {
                throw new XmlException("there is 2 commits with the same id");
            }
            idNumbers.add(commit.getId());
        }
        idNumbers.clear();
    }

    //Xml Validation 3.3
    private void checkIfBlobIdExist(Byte id) throws XmlException{
        Boolean Exist=false;
        for(MagitRepository.MagitBlobs.MagitBlob blob: magitRepository.getMagitBlobs().getMagitBlob())
        {
            if(blob.getId().equals(id))
                Exist = true;
        }
        if(!Exist)
            throw new XmlException("Folder Pointing UnExist Blob");
    }

    //Xml Validation 3.4 & 3.6
    private void checkIfFolderIdExist(Byte id) throws XmlException{
        Boolean Exist=false;
        for(MagitRepository.MagitFolders.MagitSingleFolder folder: magitRepository.getMagitFolders().getMagitSingleFolder())
        {
            if(folder.getId().equals(id))
                Exist = true;
        }
        if(!Exist)
            throw new XmlException("Folder Pointing UnExist Folder");
    }

    //Xml Validation 3.5
    private void checkIfFolderPointToHimself(Byte ToId, Byte FromId) throws XmlException {
        if(ToId.equals(FromId))
            throw new XmlException("Folder Point to Same Folder");
    }

    //Xml Validation 3.7
    private void checkIfFolderIsRootFolder(Byte id) throws XmlException {
        if(getFolderXmlObject(id).getIsRoot()==null)
            throw new XmlException("Commit Pointing None Root Folder");
        if(!getFolderXmlObject(id).getIsRoot().equals("true"))
            throw new XmlException("Commit Pointing None Root Folder");
    }

    //Xml Validation 3.8
    private void checkIfCommitExist(Byte id) throws XmlException {
        Boolean Exist=false;
        for(MagitRepository.MagitCommits.MagitSingleCommit commit: magitRepository.getMagitCommits().getMagitSingleCommit())
        {
            if(commit.getId().equals(id))
                Exist = true;
        }
        if(!Exist)
            throw new XmlException("Branch Pointing UnExist Commit");
    }

    //Xml Validation 3.9
    private void checkIfActiveBranchExist(String activeBranch) throws XmlException{
        Boolean Exist=false;
        for(MagitRepository.MagitBranches.MagitSingleBranch branch: magitRepository.getMagitBranches().getMagitSingleBranch())
        {
            if(branch.getName().equals(activeBranch))
                Exist = true;
        }
        if(!Exist)
            throw new XmlException("Head Pointing UnExist Branch");
    }

}
