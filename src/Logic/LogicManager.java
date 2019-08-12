package Logic;

import Logic.Objects.*;
import Zip.ZipFile;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


import static Logic.ConstantsEnums.*;


public class LogicManager {

    private String m_ActiveUser;
    private String m_ActiveRepository;
    private ZipFile m_ZipFile;
    private Map<String,String> m_CurrentCommitStateMap;

    public LogicManager()
    {
        m_ActiveUser = "Administrator";
        m_ActiveRepository = EmptyString;
        m_ZipFile = new ZipFile();
        m_CurrentCommitStateMap = new HashMap<>();
    }

    public String getM_ActiveRepository() {
        return m_ActiveRepository;
    }

    public void setM_ActiveUser(String i_ActiveUser) {m_ActiveUser = i_ActiveUser;}



    public void spreadCommitToWc(String i_BranchName) {
        File BranchFile = new File(getPathFolder("branches") + File.separator + i_BranchName + ".txt");
        if (BranchFile.exists()) {

            String sha1OfLastCommitBranch = getContentOfFile(BranchFile);
            Commit commit = new Commit(getContentOfZipFile(getPathFolder("objects"),sha1OfLastCommitBranch));
            String RootFolderSha1 = commit.getM_MainSHA1();
            String path = m_ActiveRepository +File.separator + "Repo2";
            buildingRepository(path,RootFolderSha1,FileType.FOLDER);

        }
    }
    public void buildingRepository(String path,String Sha1,FileType i_FileType) {
        if (i_FileType == FileType.FOLDER) {
            File pathFile = new File(path);
            pathFile.mkdir();
            Folder folder = new Folder(getContentOfZipFile(getPathFolder("objects"), Sha1));
            List<BlobData> BlobDataArray = folder.getLibraryFiles();
            for (BlobData blobData : BlobDataArray) {
                buildingRepository(path + File.separator + blobData.getM_Name(), blobData.getM_Sha1(),blobData.getM_Type());
            }
        } else {
            Path pathFile = Paths.get(path);
            try {
                Files.createFile(pathFile);
                Files.write(pathFile,getContentOfZipFile(getPathFolder("objects"),Sha1).getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void initRepository(String i_RepositoryArgs[]) {
        Path RootPath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1]);
        Path ObjectPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "objects");
        Path branchesPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "branches");
//        Path WcCommitPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "WcCommit");
//        Path lastCommitPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "lastCommit");
        Path activeBranchePath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "branches" + File.separator + "HEAD.txt");
        Path branchesNamesPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "branches" + File.separator + "NAMES.txt");
        Path rootFolderNamePath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "RootFolderName.txt");
        Path commitStatusPath = Paths.get(i_RepositoryArgs[0] + File.separator + ".magit" + File.separator + "CommitStatus.txt");
        //        Path repositoryWC = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1]);

        Boolean dirExists = Files.exists(ObjectPath);
        if (dirExists) {
            System.out.println("Directory Alerady Exists");
        } else {
            try {
                Files.createDirectories(RootPath);

                Files.createDirectories(ObjectPath);
                Files.createDirectories(branchesPath);

//                Files.createDirectories(repositoryWC);
                Files.createFile(activeBranchePath);
                Files.createFile(branchesNamesPath);
                Files.createFile(rootFolderNamePath);
                Files.createFile(commitStatusPath);
                Files.write(activeBranchePath, "master".getBytes());
                Files.write(branchesNamesPath, "master".getBytes());
                Files.write(rootFolderNamePath, i_RepositoryArgs[1].getBytes());

                setM_ActiveRepository(i_RepositoryArgs[0]);
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
    }

    public String getRootFolderName()
    {
        String rootFolder = EmptyString;
        rootFolder = getContentOfFile(new File(getPathFolder(".magit") + File.separator + "RootFolderName.txt"));
        return rootFolder;
    }




    private void updateBranchActiveCommit(String i_CommitSha1) {
        String activeBranchName = getBranchActiveName();
        Path activeBranchPath = Paths.get(getPathFolder("branches") + File.separator + activeBranchName + ".txt");
       try{
           if(!Files.exists(activeBranchPath))
               Files.createFile(activeBranchPath);
           Files.write(activeBranchPath,i_CommitSha1.getBytes());
       }
       catch (Exception e)
       {

       }
    }


    public String getSha1OfMainRepositoryFromLastCommit()
    {
        String branchLastCommitName = getBranchActiveName();
        Path path = Paths.get(getPathFolder("branches") + File.separator + branchLastCommitName + ".zip");
        if(Files.exists(path)) {
            m_ZipFile.unZipIt(getPathFolder("branches") + File.separator + branchLastCommitName + ".zip", getPathFolder("branches"));
            File unZippedFile = new File(getPathFolder("branches") + File.separator + branchLastCommitName + ".txt");
            String lastCommitContent = getContentOfFile(unZippedFile);
            unZippedFile.delete();
            Commit lastCommit = new Commit(lastCommitContent);
            return (lastCommit.getM_MainSHA1());
        }
        else
            return "NONE";
    }



    public void TESTING_CHANGING_BRANCH(String branchName) {
        File activeBranchFile = new File(getPathFolder("branches") + File.separator + "HEAD.txt");
        activeBranchFile.delete();
        Path path = Paths.get(getPathFolder("branches")+ File.separator + "HEAD.txt");
        try {
            Files.write(path, branchName.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /* Change username -- Start */
    /* Case 1 */
    public String getM_ActiveUser() {
        return m_ActiveUser;
    }
    /* Case 1 */
    /* Change username -- End */

    /* Switch repository -- Start */
    /* Case 3 */
    public void setM_ActiveRepository(String i_ActiveRepository) {
        m_ActiveRepository = i_ActiveRepository;
    }
    /* Case 3 */
    /* Switch repository -- End */

    /* Show current commit file system information -- Start */
    /* Case 4 */
    public List<BlobData> showLastCommit()
    {
        List<BlobData> blobDataArr= new ArrayList<>();
        String activeBranchName = getBranchActiveName();
        String commitName = getContentOfFile(new File(getPathFolder("branches") + File.separator + activeBranchName + ".txt"));
        Commit lastCommit = new Commit(getContentOfZipFile(getPathFolder("objects"),commitName));

        blobDataArr.add(new BlobData(m_ActiveRepository, lastCommit.getM_MainSHA1(), FileType.FOLDER,
                                     lastCommit.getM_CreatedBy(), lastCommit.getM_CreatedTime()));

        showRecursiveLastCommit(blobDataArr, lastCommit.getM_MainSHA1(),true,getM_ActiveRepository() + File.separator + getRootFolderName());

       return blobDataArr;
    }
    private void showRecursiveLastCommit(List<BlobData> i_BlobDataArr, String i_Sha1,Boolean isFolder,String i_FullPath) {
        String fileSha1Content = getContentOfZipFile(getPathFolder("objects"),i_Sha1);
        //BlobData fileBlob = new BlobData(fileSha1Content);
        if (isFolder) {
            String []dataFileFolder = fileSha1Content.split("~");
            for (String datafile : dataFileFolder) {

                BlobData blobData = new BlobData(datafile);
                i_BlobDataArr.add(blobData);

                if (blobData.getM_Type() == FileType.FOLDER) {
                    showRecursiveLastCommit(i_BlobDataArr, blobData.getM_Sha1(), true, i_FullPath + File.separator + blobData.getM_Name() );
                }
            }
        }
    }
    /* Case 4 */
    /* Show current commit file system information -- End */

    /* Case 5 */
    /* ShowWorkingCopyStatus -- Start */

    public WorkingCopyStatus ShowWorkingCopyStatus()
    {
        WorkingCopyStatus wcStatus = new WorkingCopyStatus();
        String rootFolderName = getRootFolderName();
        File commitStatusFile = new File(getPathFolder(".magit") + File.separator + "CommitStatus.txt");

        m_CurrentCommitStateMap.clear();
        String[] commitStatusArr = getContentOfFile(commitStatusFile).split(System.lineSeparator());
        for(String s : commitStatusArr)
        {
            String[] strings = s.split(Separator);
            m_CurrentCommitStateMap.put(strings[0],strings[1]);
        }

        wcStatus.setM_DeletedFilesList(m_CurrentCommitStateMap.keySet());

        if (!m_CurrentCommitStateMap.isEmpty())
        {
            recursiveCompareWC(m_ActiveRepository,rootFolderName,wcStatus);
        }

        return wcStatus;
    }

    private void recursiveCompareWC(String stringPath, String fName, WorkingCopyStatus i_WCstatus)
    {
        File file = new File(stringPath + File.separator + fName);

        if(file.isDirectory())
        {
            for(File f : file.listFiles())
            {
                recursiveCompareWC(file.getAbsolutePath(), f.getName(),i_WCstatus);
            }
        }
        else // isFile
        {
            String sha1InCommit = m_CurrentCommitStateMap.get(file.getAbsolutePath());
            String sha1InWC = DigestUtils.sha1Hex(getContentOfFile(file));

            i_WCstatus.getM_DeletedFilesList().remove(file.getAbsolutePath());

            if(sha1InCommit == null)
            {
                i_WCstatus.getM_NewFilesList().add(file.getAbsolutePath());
            }
             else if(!sha1InCommit.equals(sha1InWC))
            {
                i_WCstatus.getM_ChangedFilesList().add(file.getAbsolutePath());
            }

        }
    }

    /* Case 5 */
    /* ShowWorkingCopyStatus -- End */


    /* Create Commit -- Start */
    /* Case 6 */
    public void createCommit(String i_Msg)
    {
        m_CurrentCommitStateMap.clear();
        String objectFolder;
        WorkingCopyStatus workingCopyStatus= ShowWorkingCopyStatus();
        //build the commit object
        if(workingCopyStatus.isChanged()) {
            Commit newCommit = new Commit();
            newCommit.setM_Message(i_Msg);
            newCommit.setM_CreatedBy(m_ActiveUser);
            newCommit.setM_PreviousSHA1("NONE");
            newCommit.setM_PreviousSHA1merge("NONE");
            newCommit.setM_CreatedTime(dateFormat.format(new Date()));

            String rootFolderName = getRootFolderName();
            File rootFolderFile = new File(m_ActiveRepository + File.separator + rootFolderName);
            File commitStatusFile = new File(getPathFolder(".magit") + File.separator + "CommitStatus.txt");
            //File branchCommit = new File(getPathFolder("branches")+File.separator+getBranchActiveName()+".txt");

            try {
                Files.write(Paths.get(commitStatusFile.getAbsolutePath()), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
            }

            objectFolder = getPathFolder("objects");

            if (!isFirstCommit()) {
                String PreviousSHA1 = getContentOfFile(new File(getPathFolder("branches"), getBranchActiveName() + ".txt"));
                newCommit.setM_PreviousSHA1(PreviousSHA1);
            } else {
                //folderToZipInto = getPathFolder("WcCommit");
            }
            BlobData rootBlobData = recursiveTravelFolders(objectFolder, rootFolderFile,workingCopyStatus);//throw all the WC files to WcCommit folder
            newCommit.setM_MainSHA1(rootBlobData.getM_Sha1());
            String commitSha1 = DigestUtils.sha1Hex(newCommit.toString());
            m_ZipFile.zipFile(objectFolder, commitSha1, newCommit.toString());
            updateBranchActiveCommit(commitSha1);
        }
        else
        {
            System.out.println("there is nothing to update.");
        }
    }

    public BlobData recursiveTravelFolders(String i_FolderToZipInto ,File i_File,WorkingCopyStatus workingCopyStatus) {
        String sha1;
        if (i_File.isDirectory()) {
            Folder folder = new Folder();

            for (final File f : i_File.listFiles())
                folder.AddNewItem(recursiveTravelFolders(i_FolderToZipInto,f,workingCopyStatus));

            sha1 = DigestUtils.sha1Hex(folder.toString());
            BlobData directoryBlob =
                    new BlobData(i_File.getName(), sha1, ConstantsEnums.FileType.FOLDER,
                            m_ActiveUser, dateFormat.format(new Date())
                    );
            m_ZipFile.zipFile(i_FolderToZipInto,sha1, folder.printArray());
            return directoryBlob;
        }
        else { //isFile
            Blob blob = new Blob(getContentOfFile(i_File));
            sha1 = DigestUtils.sha1Hex(blob.getM_Data());

            String toFile = i_File.getAbsolutePath() + Separator + sha1 + System.lineSeparator();
            Path path = Paths.get(getPathFolder(".magit") + File.separator + "CommitStatus.txt");
            try {
                Files.write(path, toFile.getBytes() , StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

            m_ZipFile.zipFile(i_FolderToZipInto,sha1, getContentOfFile(i_File));
            BlobData newBlobData = new BlobData(i_File.getName(), sha1, ConstantsEnums.FileType.FILE,
                    m_ActiveUser, dateFormat.format(new Date()));
            return newBlobData;
        }
    }

    private boolean isFirstCommit() {
        String BranchName = getBranchActiveName();
        Path BranchCommit = Paths.get(getPathFolder("branches") + File.separator + BranchName + ".txt");
        return (!Files.exists(BranchCommit));
    }

    /* Case 6 */
    /* Create Commit -- End */

    /* Show All Branches -- Start */
    /* Case 7 */
    public List<BranchData> GetAllBranchesDetails() {
        File branchesNamesFile = new File(getPathFolder("branches") + File.separator + "NAMES.txt");
        List<BranchData> branchDataList = new ArrayList<>();
        String[] branchesNamesArray = getContentOfFile(branchesNamesFile).split(System.lineSeparator());

        for (String branchName : branchesNamesArray) {
            Path branchPath = Paths.get(getPathFolder("branches") + File.separator + branchName+".txt");

            if (Files.exists(branchPath)) {
                String commitSha1 = getContentOfFile(new File(getPathFolder("branches"), branchName+".txt"));
                Commit commit = new Commit(getContentOfZipFile(getPathFolder("objects"), commitSha1));

                branchDataList.add(new BranchData(branchName, getBranchActiveName().equals(branchName),
                        commitSha1, commit.getM_Message()));
            }
        }
        return branchDataList;
    }
    /* Case 7 */
    /* Show All Branches -- Start */

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
        String activeBranchContent = getContentOfFile(new File (getPathFolder("branches") + File.separator + activeBranchName + ".txt"));
        recursiveShowHistoryOfCommits(activeBranchContent);
    }
    private void recursiveShowHistoryOfCommits(String i_CommitSha1) {
        Commit commit = new Commit(getContentOfZipFile(getPathFolder("objects"), i_CommitSha1));
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
        m_ZipFile.unZipIt(i_Path + File.separator  + i_ZipName + ".zip", i_Path);
        File unZippedFile = new File(i_Path + File.separator + i_ZipName + ".txt");
        String contentOfFile = getContentOfFile(unZippedFile);
        unZippedFile.delete();
        return contentOfFile;
    }

    public String getContentOfFile(File i_File) {
        String fileContent = EmptyString;
        Path path = Paths.get(i_File.getAbsolutePath());

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
            case "objects":
                path =  m_ActiveRepository + File.separator + ".magit" + File.separator + "objects";
                break;
            case "WcCommit":
                path =  m_ActiveRepository + File.separator + ".magit" + File.separator + "WcCommit";
                break;
            case "branches":
                path =  m_ActiveRepository + File.separator + ".magit" + File.separator + "branches";
                break;
            case ".magit":
                path =  m_ActiveRepository + File.separator + ".magit";
                break;
            case "lastCommit":
                path =  m_ActiveRepository + File.separator + ".magit" + File.separator + "lastCommit";
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
