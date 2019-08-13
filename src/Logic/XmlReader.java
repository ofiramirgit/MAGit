package Logic;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;

import Logic.Objects.Folder;
import Zip.ZipFile;
import java.util.zip.ZipOutputStream;

import GeneratedXML.MagitRepository;
import Logic.Objects.BlobData;
import org.apache.commons.codec.digest.DigestUtils;

import static Logic.ConstantsEnums.dateFormat;


public class XmlReader {
    private static final String JAXB_XML_GAME_PACKAGE_NAME = "GeneratedXML";


    private InputStream inputStream = null;
    private MagitRepository magitRepository;
    private ZipFile m_ZipFile;
    private String m_Location;

    public XmlReader(String i_XMLLocation){
        try {
            inputStream = new FileInputStream(i_XMLLocation);
            try {
                magitRepository = deserializeFrom(inputStream);
                m_ZipFile = new Zip.ZipFile();
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
    public void buildFromXML()
    {
            getActiveBranch();


        //buildFromMagitRepository();

        // BuildRepository(magitRepository);
    }
    public String[] getLocation() {

        String[] RepositoryLocation = {magitRepository.getLocation(), magitRepository.getName()};
        m_Location = RepositoryLocation[0] + File.separator + RepositoryLocation[1];
        return RepositoryLocation;
    }
    public void getActiveBranch(){
        for(MagitRepository.MagitBranches.MagitSingleBranch branch:magitRepository.getMagitBranches().getMagitSingleBranch()) {
            //System.out.println(branch.getName() +" ~ " + branch.getPointedCommit().getId());
            MagitRepository.MagitCommits.MagitSingleCommit commit = magitRepository.getMagitCommits().getMagitSingleCommit().get(branch.getPointedCommit().getId()-1);
            System.out.println(commit.getRootFolder().getId());
            buildRootFolder(commit.getRootFolder().getId(), ConstantsEnums.FileType.FOLDER);

        }
    }

    private BlobData buildRootFolder(Byte id, ConstantsEnums.FileType fileType) {
        String sha1;
        if (fileType == ConstantsEnums.FileType.FOLDER) {
            Folder folder = new Folder();
            MagitRepository.MagitFolders.MagitSingleFolder MagitFolder = magitRepository.getMagitFolders().getMagitSingleFolder().get(id - 1);
            for(MagitRepository.MagitFolders.MagitSingleFolder.Items.Item itemFolder : MagitFolder.getItems().getItem())
            {
                if(itemFolder.getType().equals("blob"))
                    folder.AddNewItem(buildRootFolder(itemFolder.getId(), ConstantsEnums.FileType.FILE));
                else
                    folder.AddNewItem(buildRootFolder(itemFolder.getId(), ConstantsEnums.FileType.FOLDER));
            }
            sha1 = DigestUtils.sha1Hex(folder.toString());
            BlobData directoryBlob =
                    new BlobData(MagitFolder.getName(), sha1, ConstantsEnums.FileType.FOLDER,
                            MagitFolder.getLastUpdater(), MagitFolder.getLastUpdateDate());
            m_ZipFile.zipFile(m_Location + File.separator + ".magit" + File.separator + "objects",sha1, folder.printArray());
            return directoryBlob;
        }
        else
        {
            MagitRepository.MagitBlobs.MagitBlob blob = magitRepository.getMagitBlobs().getMagitBlob().get(id - 1);
            BlobData blobData = new BlobData(blob.getName(),DigestUtils.sha1Hex(blob.getContent()), ConstantsEnums.FileType.FILE,blob.getLastUpdater(),blob.getLastUpdateDate());
            m_ZipFile.zipFile(m_Location + File.separator + ".magit" + File.separator + "objects",DigestUtils.sha1Hex(blob.getContent()),blob.getContent());
            return blobData;
        }
    }
//
//    public void buildFromMagitRepository()
//    {
//        buildBlobs(magitRepository.getMagitBlobs());
//    }
//
//
//
//    private void foldersBuilder()
//    {
//        Folder mainRepository = new Folder();
//        //mainRepository.setRootFolder(true);
//
//        for(MagitRepository.MagitFolders.MagitSingleFolder folder : magitRepository.getMagitFolders().getMagitSingleFolder())
//        {
//            if(folder.getIsRoot())
//            {
//
//            }
//        }
//
//            for(MagitRepository.MagitFolders.MagitSingleFolder folder : magitRepository.getMagitFolders().getMagitSingleFolder())
//      {
//          mainRepository.AddNewItem(rec(folder));
//      }
//    }
//
//    private BlobData rec(Object object)
//    {
//        switch (object)
//        {
//            case (instanceof MagitRepository.MagitCommits.MagitSingleCommit)
//                break;
//
//        }
//
//
//
//
//        if(isFolderExist(folder))
//        {
//
//        }
//        else
//        {
//            for(MagitRepository.MagitFolders.MagitSingleFolder.Items.Item item : folder.getItems().getItem())
//            {
//                if(item.getType().equals("blob"))
//                {
//                     if(isBlobExist(item.getId().toString()))
//                     {
//
//                     }
//                    else
//                     {
//                         folder.createBlob();
//                     }
//                }
//                else if(item.getType().equals("folder"))
//                {
//
//                }
//            }
//        }
//    }
//
//    private  Boolean isFolderExist(MagitRepository.MagitFolders.MagitSingleFolder folder)
//    {
//        return false;
//    }
//
//    private  Boolean isBlobExist(String blobId)
//    {
//        return false;
//    }
//
//
//    private void buildBlobs(MagitRepository.MagitBlobs blobs)
//    {
//        File file;
//        String sha1;
//        InputStream inputStreamBlob;
//        for(MagitRepository.MagitBlobs.MagitBlob blob : magitRepository.getMagitBlobs().getMagitBlob()) {
//            try {
//                inputStreamBlob = new ByteArrayInputStream(blob.getContent().getBytes(Charset.forName("UTF-8")));
//                String outputFile = "C:\\magit\\" +DigestUtils.sha1Hex(blob.getContent());
//                Files.copy(inputStreamBlob, Paths.get(outputFile));
//                file = new File(outputFile);
//
//            }
//            catch (IOException e){
//
//            }
//        }
//             //zip
//      //       sha1 = DigestUtils.sha1Hex(blob.getContent());
//     //        file = new File(sha1);
//    }
//
//

//
//    private void printRep(MagitRepository i_MagitRepository)
//    {
//        System.out.println(i_MagitRepository.getLocation());
//
//        System.out.println(i_MagitRepository.getName());
//
//        for(MagitRepository.MagitBlobs.MagitBlob b : i_MagitRepository.getMagitBlobs().getMagitBlob())
//        {
//            System.out.println(b.getId());
//            System.out.println(b.getName());
//            System.out.println(b.getLastUpdater());
//            System.out.println(b.getLastUpdateDate());
//            System.out.println(b.getContent());
//            System.out.println("SHA-1: " + DigestUtils.sha1Hex(b.getContent()));
//        }
//    }
//    private void BuildRepository(MagitRepository i_MagitRepository) {
//        for(MagitRepository.MagitFolders.MagitSingleFolder F : i_MagitRepository.getMagitFolders().getMagitSingleFolder())
//        {
//
//            System.out.println("-------Folder "+F.getId()+"--------");
//            System.out.println("Id:               " + F.getId());
//            System.out.println("Last Update Date: " + F.getLastUpdateDate());
//            System.out.println("Last Updater:     " + F.getLastUpdater());
//            System.out.println("Name:             " + F.getName());
//            for(MagitRepository.MagitFolders.MagitSingleFolder.Items.Item item : F.getItems().getItem()) {
//                System.out.println("    ---item "+item.getId()+"---------");
//                System.out.println("    Type:                " + item.getType());
//                if(item.getType().equals("blob"))
//                {
//                    printBlob(i_MagitRepository.getMagitBlobs(),item.getId());
//                }
//            }
//            System.out.print("\n");
//        }
//    }
//
//    private void printBlob(MagitRepository.MagitBlobs blobs,Byte id)
//    {
//        //MagitRepository.MagitBlobs.MagitBlob blob = blobs.getMagitBlob().get(id);
//        for(MagitRepository.MagitBlobs.MagitBlob blob : blobs.getMagitBlob())
//        {
//            if(blob.getId().equals(id))
//            {
//                System.out.println("    Blob Id:             " + blob.getId());
//                System.out.println("    Blob Name:           " + blob.getName());
//                System.out.println("    Blob Last Updater:   " + blob.getLastUpdater());
//                System.out.println("    Blob Date Update:    " + blob.getLastUpdateDate());
//                System.out.println("    Blob Content:        " + blob.getContent());
//                System.out.println("    SHA-1:               " + DigestUtils.sha1Hex(blob.getContent()));
//            }
//        }
//    }
//

//        Boolean dirExists = Files.exists(location);
//        if (dirExists) {
//            System.out.println("Repository Already Exists");
//            System.out.println("Do you want to delete the the current and To Create new One by this File? or to continue with old repository?");
//        } else {
//            try {
//
//            } catch (IOException ioExceptionObj) {
//                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
//            }
//        }


}