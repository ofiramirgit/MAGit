package Logic;

import Zip.ZipFile;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Logic.ConstantsEnums.*;



public class LogicManager {

    private String m_ActiveUser = "Administrator";
    private String m_ActiveRepository = EmptyString;
    private ZipFile m_zipFile = new ZipFile();


    public String getM_ActiveUser() {
        return m_ActiveUser;
    }

    public String getM_ActiveRepository() {
        return m_ActiveRepository;
    }

    public void setM_ActiveUser(String i_ActiveUser) {
        this.m_ActiveUser = i_ActiveUser;
    }

    public Boolean setM_ActiveRepository(String i_ActiveRepository) {
        Path RepositoryPath = Paths.get(i_ActiveRepository + File.separator + ".." + File.separator + ".magit");
        if (Files.exists(RepositoryPath)) {
            m_ActiveRepository = i_ActiveRepository;
            return true;
        }
        return false;
    }

    public void initRepository(String i_RepositoryArgs[]) {
        Path repositoryPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "object");
        Path branchesPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "branches");
        Path activeBranchePath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "branches" + File.separator + "HEAD.txt");
        Path branchesNamesPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "branches" + File.separator + "NAMES.txt");
        Path repositoryWC = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1]);

        Boolean dirExists = Files.exists(repositoryPath);
        if (dirExists) {
            System.out.println("Directory Alerady Exists");
        } else {
            try {
                Files.createDirectories(repositoryPath);
                Files.createDirectories(branchesPath);
                Files.createDirectories(repositoryWC);
                Files.createFile(activeBranchePath);
                Files.createFile(branchesNamesPath);
                Files.write(activeBranchePath, "master".getBytes());
                Files.write(branchesNamesPath, "master".getBytes());

                setM_ActiveRepository(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1]);
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
    }

    public void createNewCommit(String i_Msg) {//build the commit object
        Commit newCommit = new Commit();
        newCommit.setM_Message(i_Msg);
        newCommit.setM_CreatedBy(m_ActiveUser);
        newCommit.setM_PreviousSHA1("NONE");
        newCommit.setM_CreatedTime(dateFormat.format(new Date()));
        createCommitAndUpdateBranch(newCommit);
        //getLastCommitStructure();


    }

    public void createCommitAndUpdateBranch(Commit i_Commit) {
        final File rootFolderFile = new File(m_ActiveRepository);
        String folderToZipInto = getPathFolder("WcCommit");
        BlobData rootBlobData = recursiveTravelFolders(folderToZipInto,rootFolderFile);//throw all the WC files to WcCommit folder
        i_Commit.setM_MainSHA1(rootBlobData.getM_Sha1());
        String commitSha1 = DigestUtils.sha1Hex(i_Commit.toString());
        m_zipFile.zipFile(getPathFolder("WcCommit"),commitSha1, i_Commit.toString());//in WcCommit folder we gonna have the commit zip with all the files
        updateBranchActiveCommit(i_Commit);

    }


    public BlobData recursiveTravelFolders(String i_FolderToZipInto ,File i_File) {
        String sha1 = EmptyString;
        if (i_File.isDirectory()) {
            Folder folder = new Folder();

            for (final File f : i_File.listFiles())
                folder.AddNewItem(recursiveTravelFolders(i_FolderToZipInto,f));

            sha1 = DigestUtils.sha1Hex(folder.toString());
            BlobData directoryBlob =
                    new BlobData(i_File.getName(), sha1, FileType.FOLDER,
                            m_ActiveUser, dateFormat.format(new Date())
                    );

            m_zipFile.zipFile(getPathFolder("WcCommit"),sha1, folder.printArray());
            System.out.println("\nfolder name: " + i_File.getName() + "\n" + directoryBlob.toString() + "\n"); //for testing

            return directoryBlob;
        } else {
            // check if sha 1 exists
            Blob blob = new Blob(getContentOfFile(i_File));

            sha1 = DigestUtils.sha1Hex(blob.getM_Data());
            m_zipFile.zipFile(getPathFolder("WcCommit"),sha1, getContentOfFile(i_File));
            BlobData newBlobData =
                    new BlobData(i_File.getName(), sha1, FileType.FILE,
                            m_ActiveUser, dateFormat.format(new Date())
                    );

            System.out.println("\nfile name: " + i_File.getName() + "\n" + newBlobData.toString() + "\n"); //for testing
            return newBlobData;
        }
    }
    public void WorkingCopyStatus()
    {
        final File rootFolderFile = new File(m_ActiveRepository);
        String folderToZipInto = getPathFolder("WcCommit");
        BlobData rootBlobData = recursiveTravelFolders(folderToZipInto,rootFolderFile);
        m_zipFile.zipFile(getPathFolder("WcCommit"),rootBlobData.getM_Sha1(), rootBlobData.toString());
        getLastCommitStructure();//throw all commit to file lastCommit
        //then check every one of the file if it's equals
    }
    public void getLastCommitStructure() {
        String branchLastCommitName = getBranchActiveName();
        m_zipFile.unZipIt(getPathFolder("branches") + File.separator + branchLastCommitName + ".zip", getPathFolder("branches"));
        File unZippedFile = new File(getPathFolder("branches") + File.separator + branchLastCommitName + ".txt");
        String lastCommitContent = getContentOfFile(unZippedFile);
        unZippedFile.delete();
        buildLastByRecurtion(getSha1ofCommitFormat(lastCommitContent),FileType.FOLDER);
    }

        public void buildLastByRecurtion(String i_Sha1,FileType i_FileType)
        {
            if(i_FileType == FileType.FOLDER) {
                String FileSha1ZipedContent = getContentOfFileBySha1(getPathFolder("object"), i_Sha1);
                copyPasteFile(getPathFolder("object")+File.separator + i_Sha1 + ".zip",getPathFolder("lastCommit") + File.separator + i_Sha1 + ".zip");
                List<BlobData> blobDataList =  getBlobsDataOfDirectory(FileSha1ZipedContent);
                for(BlobData blobData : blobDataList)
                    buildLastByRecurtion(blobData.getM_Sha1(),blobData.getM_Type());
            }
            else
            {
                copyPasteFile(getPathFolder("object")+File.separator + i_Sha1 + ".zip",getPathFolder("lastCommit") + File.separator + i_Sha1 + ".zip");
            }
        }

    private void copyPasteFile(String i_SourcePath, String i_DestinationPath) {
        File sourceFile = new File (i_SourcePath);
        File destinationFile = new File (i_DestinationPath);
        try {
            Files.copy(sourceFile.toPath(),destinationFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getContentOfFileBySha1(String path ,String i_Sha1) {
        m_zipFile.unZipIt(path + File.separator  + i_Sha1 + ".zip", path);
        File unZippedFile = new File(path + File.separator + i_Sha1 + ".txt");
        String contentOfFile = getContentOfFile(unZippedFile);
        unZippedFile.delete();
        return contentOfFile;
    }

    public List<BlobData> getBlobsDataOfDirectory(String i_ContentOfFile) {
        List<BlobData> directoryBlobDatas = new ArrayList<BlobData>();
        String[] seperatedContentDirectory = i_ContentOfFile.split(" ~ ");
        for (String ContentOfBlobData : seperatedContentDirectory) {
            directoryBlobDatas.add(new BlobData(ContentOfBlobData));
        }
        return directoryBlobDatas;
    }

    public String getSha1ofCommitFormat(String i_LastCommitContent) {
        String rootFolderSha1 = i_LastCommitContent.split(", ", 5)[0];
        return rootFolderSha1;
    }


    private void updateBranchActiveCommit(Commit commit) {
        String activeBranchName = getBranchActiveName();
        //if there is old commit on the same name of the active branch so update the new commit in the field of old commit
        m_zipFile.zipFileToBranchActive(getPathFolder("branches"),activeBranchName, commit.toString());
    }


    public String getContentOfFile(File i_File) {
        String fileContent = EmptyString;
        Path path = Paths.get(i_File.getAbsolutePath());
        System.out.println(path + "\n");
        try {
            fileContent = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileContent;
    }

    public String getBranchActiveName() {
        String branchActiveName = EmptyString;

        Path path = Paths.get(getPathFolder("branches") + File.separator + "HEAD.txt");
        try {
            branchActiveName = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return branchActiveName;
    }


    public Boolean createNewBranch(String i_BranchName) {
        Boolean branchNameNotExist = true;
        String stringPath = getPathFolder("branches") + File.separator + "NAMES.txt";
        File branchesNamesFile = new File(stringPath);
        String branchesNames = getContentOfFile(branchesNamesFile);
        String[] names = branchesNames.split(System.lineSeparator());
        for(String name : names) {
            if (name.equals(i_BranchName)) {
                branchNameNotExist = false;
            }
        }
        if(branchNameNotExist){
            Path path = Paths.get(stringPath);
            try {
                Files.write(path, (System.lineSeparator() + i_BranchName).getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return branchNameNotExist;
    }


public void showLastCommit()
{
    String branchLastCommitName = getBranchActiveName();
    m_zipFile.unZipIt(getPathFolder("branches") + File.separator + branchLastCommitName + ".zip",getPathFolder("branches"));
    Commit lastCommit = new Commit(getContentOfFile(new File(getPathFolder("branches") + File.separator + branchLastCommitName + ".txt")));
    String MainRepositorySha1 = lastCommit.getM_MainSHA1();
    System.out.println("Full Path:      " + getM_ActiveRepository());
    System.out.println("File Type:      " + "Folder");
    System.out.println("Sha1:           " + MainRepositorySha1);
    System.out.println("Last Change By: " + lastCommit.getM_CreatedBy());
    System.out.println("When Changed  : " + lastCommit.getM_CreatedTime());


    showRecueciveLastCommit(MainRepositorySha1,true,getM_ActiveRepository()+File.separator);

    Folder rootRepository = new Folder();
}

    private void showRecueciveLastCommit(String i_Sha1,Boolean isFolder,String i_FullPath) {
        String fileSha1Content = getContentOfFileBySha1(getPathFolder("object"),i_Sha1);
        //BlobData fileBlob = new BlobData(fileSha1Content);
        if (isFolder) {
            String dataFileFolder[] = fileSha1Content.split("~");
            for (String datafile : dataFileFolder) {
                BlobData datablob = new BlobData(datafile);

                System.out.println("Full Path:      " + i_FullPath + datablob.getM_Name());
                System.out.println("File Type:      " + datablob.getM_Type());
                System.out.println("Sha1:           " + datablob.getM_Sha1());
                System.out.println("Last Change By: " + datablob.getM_ChangedBy());
                System.out.println("When Changed  : " + datablob.getM_Date());

                if (datablob.getM_Type() == FileType.FOLDER) {
                    showRecueciveLastCommit(datablob.getM_Sha1(), true, i_FullPath + datablob.getM_Name() + File.separator);
                }
            }
        }
    }
    public String getPathFolder(String i_Folder)
    {
        String path = EmptyString;
        switch(i_Folder){
            case "object":
                path =  m_ActiveRepository + File.separator + ".." + File.separator + ".magit" + File.separator + "object";
                break;
            case "WcCommit":
                path =  m_ActiveRepository + File.separator + ".." + File.separator + ".magit" + File.separator + "WcCommit";
                break;
            case "branches":
                path =  m_ActiveRepository + File.separator + ".." + File.separator + ".magit" + File.separator + "branches";
                break;
            case ".magit":
                path =  m_ActiveRepository + File.separator + ".." + File.separator + ".magit";
                break;
            case "lastCommit":
                path =  m_ActiveRepository + File.separator + ".." + File.separator + ".magit" + File.separator + "lastCommit";
                break;
        }
        return path;
    }

}

 /*public void readXML()
    {
//        XmlReader xmlReader = new XmlReader("src\\Resourses\\ex1-small.xml");
//        xmlReader.buildFromXML();
    }
}
*/
