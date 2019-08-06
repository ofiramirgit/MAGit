package Logic;

import Zip.ZipFile;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
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
        Path WcCommitPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "WcCommit");
        Path lastCommitPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "lastCommit");
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

    public void createCommit(String i_Msg) {//build the commit object
        Commit newCommit = new Commit();
        newCommit.setM_Message(i_Msg);
        newCommit.setM_CreatedBy(getM_ActiveUser());
        newCommit.setM_PreviousSHA1("NONE");
        newCommit.setM_CreatedTime(dateFormat.format(new Date()));
        final File rootFolderFile = new File(getM_ActiveRepository());
        String folderToZipInto;
        if(!isFirstCommit()) {
            String PreviousSHA1 = getContentOfZipFile(getPathFolder("branches"), getBranchActiveName());
            newCommit.setM_PreviousSHA1(PreviousSHA1);
        }
        else {
            //folderToZipInto = getPathFolder("WcCommit");
        }
        folderToZipInto = getPathFolder("object");

        BlobData rootBlobData = recursiveTravelFolders(folderToZipInto,rootFolderFile);//throw all the WC files to WcCommit folder
        newCommit.setM_MainSHA1(rootBlobData.getM_Sha1());
        String commitSha1 = DigestUtils.sha1Hex(newCommit.toString());
        m_zipFile.zipFile(folderToZipInto,commitSha1, newCommit.toString());
        updateBranchActiveCommit(commitSha1);
        //getLastCommitStructure();
    }


    public BlobData recursiveTravelFolders(String i_FolderToZipInto ,File i_File) {
        String sha1;
        if (i_File.isDirectory()) {
            Folder folder = new Folder();

            for (final File f : i_File.listFiles())
                folder.AddNewItem(recursiveTravelFolders(i_FolderToZipInto,f));

            sha1 = DigestUtils.sha1Hex(folder.toString());
            BlobData directoryBlob =
                    new BlobData(i_File.getName(), sha1, ConstantsEnums.FileType.FOLDER,
                            m_ActiveUser, dateFormat.format(new Date())
                    );

            m_zipFile.zipFile(i_FolderToZipInto,sha1, folder.printArray());
            System.out.println("\nfolder name: " + i_File.getName() + "\n" + directoryBlob.toString() + "\n"); //for testing

            return directoryBlob;
        } else {
            Blob blob = new Blob(getContentOfFile(i_File));

            sha1 = DigestUtils.sha1Hex(blob.getM_Data());
            m_zipFile.zipFile(i_FolderToZipInto,sha1, getContentOfFile(i_File));
            BlobData newBlobData =
                    new BlobData(i_File.getName(), sha1, ConstantsEnums.FileType.FILE,
                            m_ActiveUser, dateFormat.format(new Date())
                    );

            System.out.println("\nfile name: " + i_File.getName() + "\n" + newBlobData.toString() + "\n"); //for testing
            return newBlobData;
        }
    }

    private void updateBranchActiveCommit(String i_CommitSha1) {
        String activeBranchName = getBranchActiveName();
        File activeBranchNAme = new File (getPathFolder("branches") + File.separator + activeBranchName + ".zip");
        activeBranchNAme.delete();
        m_zipFile.zipFile(getPathFolder("branches"),activeBranchName, i_CommitSha1);
    }

    private boolean isFirstCommit() {
        String BranchName = getBranchActiveName();
        Path BranchCommit = Paths.get(getPathFolder("branches") + File.separator + BranchName + ".zip");
        if (Files.exists(BranchCommit))
            return false;
        else
            return true;
    }












    public String getSha1OfMainRepositoryFromLastCommit()
    {
        String branchLastCommitName = getBranchActiveName();
        Path path = Paths.get(getPathFolder("branches") + File.separator + branchLastCommitName + ".zip");
        if(Files.exists(path)) {
            m_zipFile.unZipIt(getPathFolder("branches") + File.separator + branchLastCommitName + ".zip", getPathFolder("branches"));
            File unZippedFile = new File(getPathFolder("branches") + File.separator + branchLastCommitName + ".txt");
            String lastCommitContent = getContentOfFile(unZippedFile);
            unZippedFile.delete();
            Commit lastCommit = new Commit(lastCommitContent);
            return (lastCommit.getM_MainSHA1());
        }
        else
            return "NONE";
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

}

    private void showRecueciveLastCommit(String i_Sha1,Boolean isFolder,String i_FullPath) {
        String fileSha1Content = getContentOfZipFile(getPathFolder("object"),i_Sha1);
        //BlobData fileBlob = new BlobData(fileSha1Content);
        if (isFolder) {
            String []dataFileFolder = fileSha1Content.split("~");
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










    /*public void WorkingCopyStatus()
    {
        final File rootFolderFile = new File(m_ActiveRepository);
        String folderToZipInto = getPathFolder("WcCommit");
        BlobData rootBlobData = recursiveTravelFolders(folderToZipInto,rootFolderFile);
        m_zipFile.zipFile(getPathFolder("WcCommit"),rootBlobData.getM_Sha1(), rootBlobData.toString());
        getLastCommitStructure();//throw all commit to file lastCommit
        List<ChangedStateFiles> listChangedStateFiles = getListOfChangedStateFiles(rootBlobData.getM_Sha1(),getSha1OfMainRepositoryFromLastCommit());
        listChangedStateFiles.toString();
        //then check every one of the file if it's equals
    }

    private List <ChangedStateFiles> getListOfChangedStateFiles(String i_Sha1_WC, String i_Sha1_LastCommit) {
        List <ChangedStateFiles> listChangedStateFiles = new ArrayList<ChangedStateFiles>();
        String path = m_ActiveRepository;
        recursiveTravelChanged(listChangedStateFiles,path,i_Sha1_WC,FileType.FOLDER);
        return listChangedStateFiles;
    }

    public void getLastCommitStructure() {
        String Sha1OfMainRepositoryCommit = getSha1OfMainRepositoryFromLastCommit();
        buildLastByRecursive(Sha1OfMainRepositoryCommit, ConstantsEnums.FileType.FOLDER);
    }

    public void buildLastByRecursive(String i_Sha1, ConstantsEnums.FileType i_FileType)
    {
        if(!i_Sha1.equals("NONE")) {
            if (i_FileType == ConstantsEnums.FileType.FOLDER) {
                String FileSha1ZipedContent = getContentOfZipFile(getPathFolder("object"), i_Sha1);
                copyPasteFile(getPathFolder("object") + File.separator + i_Sha1 + ".zip", getPathFolder("lastCommit") + File.separator + i_Sha1 + ".zip");
                List<BlobData> blobDataList = getBlobsDataOfDirectory(FileSha1ZipedContent);
                for (BlobData blobData : blobDataList)
                    buildLastByRecursive(blobData.getM_Sha1(), blobData.getM_Type());
            } else {
                copyPasteFile(getPathFolder("object") + File.separator + i_Sha1 + ".zip", getPathFolder("lastCommit") + File.separator + i_Sha1 + ".zip");
            }
        }
    }

        private void recursiveTravelChanged(List<ChangedStateFiles> listChangedStateFiles,
                                        String path, String i_sha1_wc, FileType typeofFile) {


        String FileSha1ZipedContent = getContentOfZipFile(getPathFolder("WcCommit"),i_sha1_wc);
        if(typeofFile == FileType.FOLDER)
        {
            List<BlobData> blobDataList =  getBlobsDataOfDirectory(FileSha1ZipedContent);
            for(BlobData blobData : blobDataList)
                recursiveTravelChanged(listChangedStateFiles,blobData.getM_Name(), blobData.getM_Sha1(),blobData.getM_Type());
        }
        else
        {
            Path inCommitFilePath = Paths.get(getPathFolder("lastCommit") + File.separator + i_sha1_wc + ".zip");
            if(!Files.exists(inCommitFilePath))
            {
                listChangedStateFiles.add(new ChangedStateFiles(path,FileState.CHANGED));
            }

        }

    }

    public List<BlobData> getBlobsDataOfDirectory(String i_ContentOfFile) {
        List<BlobData> directoryBlobDatas = new ArrayList<BlobData>();
        String[] seperatedContentDirectory = i_ContentOfFile.split(" ~ ");
        for (String ContentOfBlobData : seperatedContentDirectory) {
            directoryBlobDatas.add(new BlobData(ContentOfBlobData));
        }
        return directoryBlobDatas;
    }
    */


    public void showAllBranches()
    {
        File branchesNamesFile = new File (getPathFolder("branches")+ File.separator + "NAMES.txt");
        String[] branchesNamesArray = getContentOfFile(branchesNamesFile).split(System.lineSeparator());
        for(String branchName : branchesNamesArray)
        {
            String branchSha1 = getContentOfZipFile(getPathFolder("branches"), branchName);
            Commit branchCommit = new Commit(getContentOfZipFile(getPathFolder("object"),branchSha1));
            System.out.println("Branch Name:    " + branchName);
            if(getBranchActiveName().equals(branchName))
                System.out.print(" (Active Branch");
            System.out.println("------------------------------");
            System.out.println("Branch Sha1:    " + branchSha1);
            System.out.println("Branch Message: " + branchCommit.getM_Message() + System.lineSeparator());
        }

    }


    /* Create New Branch -- Start */
    /* Case 8 */

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

    /* Case 8 */
    /* Create New Branch -- END */

    /* Delete Branch -- Start */
    /* Case 9 */
    public Boolean deleteBranch(String i_BranchName)
    {
        Boolean bool = false;
        if(getBranchActiveName().equals(i_BranchName))
            bool = false;
        else
        {
            try {
                File branchesNamesFile = new File (getPathFolder("branches")+ File.separator + "NAMES.txt");
                String branchesNamesContent = getContentOfFile(branchesNamesFile);
                if(!branchesNamesContent.contains(i_BranchName))
                    bool = false;
                else
                {
                    branchesNamesFile.delete();
                    branchesNamesContent = branchesNamesContent.replace(System.lineSeparator()+ i_BranchName,"");
                    Path path = Paths.get(getPathFolder("branches")+ File.separator + "NAMES.txt");
                    Files.write(path, branchesNamesContent.getBytes(), StandardOpenOption.CREATE);

                    bool=true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
    /* Case 9 */
    /* Delete Branch -- END */

    /* History of Active Branch -- Start */
    /* Case 11 */
    public void historyOfActiveBranch()
    {
        String activeBranchName = getBranchActiveName();
        String activeBranchContent = getContentOfZipFile(getPathFolder("branches") ,activeBranchName);
        recursiveShowHistoryOfCommits(activeBranchContent);
    }

    private void recursiveShowHistoryOfCommits(String i_CommitSha1) {
        Commit commit = new Commit(getContentOfZipFile(getPathFolder("object"), i_CommitSha1));
        System.out.println("Sha1        : " + i_CommitSha1);
        System.out.println("Message     : " + commit.getM_Message());
        System.out.println("Created Time: " + commit.getM_CreatedTime());
        System.out.println("Created By  : " + commit.getM_CreatedBy() +System.lineSeparator());
        if(!commit.getM_PreviousSHA1().equals("NONE"))
            recursiveShowHistoryOfCommits(commit.getM_PreviousSHA1());

    }
    /* Case 11 */
    /* History of Active Branch -- END */

    public String getContentOfZipFile(String i_Path ,String i_ZipName) {
        m_zipFile.unZipIt(i_Path + File.separator  + i_ZipName + ".zip", i_Path);
        File unZippedFile = new File(i_Path + File.separator + i_ZipName + ".txt");
        String contentOfFile = getContentOfFile(unZippedFile);
        unZippedFile.delete();
        return contentOfFile;
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

    private void copyPasteFile(String i_SourcePath, String i_DestinationPath) {
        File sourceFile = new File (i_SourcePath);
        File destinationFile = new File (i_DestinationPath);
        try {
            Files.copy(sourceFile.toPath(),destinationFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
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
