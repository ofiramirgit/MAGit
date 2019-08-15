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



    private String m_ActiveRepositoryName;
    private ZipFile m_ZipFile;
    private Map<String, String> m_CurrentCommitStateMap;
    private InputValidation m_InputValidation = new InputValidation();

    public LogicManager() {
        m_ActiveUser = "Administrator";
        m_ActiveRepository = EmptyString;
        m_ZipFile = new ZipFile();
        m_CurrentCommitStateMap = new HashMap<>();
    }

    public String getM_ActiveRepository() {
        return m_ActiveRepository;
    }

    public void setM_ActiveUser(String i_ActiveUser) {
        m_ActiveUser = i_ActiveUser;
    }

    public void setM_ActiveRepositoryName(String m_ActiveRepositoryName) {
        this.m_ActiveRepositoryName = m_ActiveRepositoryName;
    }

    public String getM_ActiveRepositoryName() {
        return m_ActiveRepositoryName;
    }

    /* Change username -- Start */
    /* Case 1 */
    public String getM_ActiveUser()
    {
        return m_ActiveUser;
    }
    /* Case 1 */
    /* Change username -- End */

    /* read Xml -- Start */
    /* Case 2 */
    public void readXML(String i_XmlFilePath) throws XmlException {
        XmlReader xmlReader = new XmlReader(i_XmlFilePath);

        String[] RepositoryLocation = xmlReader.getLocation();
        setM_ActiveRepository(RepositoryLocation[0] + File.separator + RepositoryLocation[1]);
        setM_ActiveRepositoryName(RepositoryLocation[1]);
        if (!m_InputValidation.checkInputActiveRepository(RepositoryLocation[0] + File.separator + RepositoryLocation[1]))
            initRepository(RepositoryLocation);
        else
            throw new XmlException("Repository Already Exist.",RepositoryLocation[0] + File.separator + RepositoryLocation[1]);
        xmlReader.buildFromXML();
        spreadCommitToWc(xmlReader.getActiveBranch());
    }
    /* Case 2 */
    /* read Xml -- End */

    /* Switch repository -- Start */
    /* Case 3 */
    public void setM_ActiveRepository(String i_ActiveRepository)
    {
        m_ActiveRepository = i_ActiveRepository;
    }
    /* Case 3 */
    /* Switch repository -- End */

    /* Show current commit file system information -- Start */
    /* Case 4 */
    public List<BlobData> showLastCommit() {
        List<BlobData> blobDataArr = new ArrayList<>();
        String activeBranchName = getBranchActiveName();
        if(!getContentOfFile(new File(getPathFolder("branches") + File.separator + activeBranchName + ".txt")).equals(EmptyString)){
            String commitName = getContentOfFile(new File(getPathFolder("branches") + File.separator + activeBranchName + ".txt"));
            Commit lastCommit = new Commit(getContentOfZipFile(getPathFolder("objects"), commitName));
            blobDataArr.add(new BlobData(m_ActiveRepository, lastCommit.getM_MainSHA1(), FileType.FOLDER,
                    lastCommit.getM_CreatedBy(), lastCommit.getM_CreatedTime()));

            showRecursiveLastCommit(blobDataArr, lastCommit.getM_MainSHA1(),  getM_ActiveRepository() + File.separator + getRootFolderName());
        }

        return blobDataArr;
    }
    private void showRecursiveLastCommit(List<BlobData> i_BlobDataArr, String i_Sha1, String i_FullPath) {
        String fileSha1Content = getContentOfZipFile(getPathFolder("objects"), i_Sha1);
        String[] dataFileFolder = fileSha1Content.split("~");
        for (String datafile : dataFileFolder) {
            BlobData blobData = new BlobData(datafile);
            i_BlobDataArr.add(blobData);

            if (blobData.getM_Type() == FileType.FOLDER)
                showRecursiveLastCommit(i_BlobDataArr, blobData.getM_Sha1(),  i_FullPath + File.separator + blobData.getM_Name());
        }
    }
    /* Case 4 */
    /* Show current commit file system information -- End */

    /* Case 5 */
    /* ShowWorkingCopyStatus -- Start */
    public WorkingCopyStatus ShowWorkingCopyStatus() {
        WorkingCopyStatus wcStatus = new WorkingCopyStatus();
        String rootFolderName = getRootFolderName();
        File commitStatusFile = new File(getPathFolder(".magit") + File.separator + "CommitStatus.txt");
        m_CurrentCommitStateMap.clear();

        if(!getContentOfFile(commitStatusFile).isEmpty()) {
            String[] commitStatusArr = getContentOfFile(commitStatusFile).split(System.lineSeparator());
            if (commitStatusArr != null) {
                for (String s : commitStatusArr) {
                    String[] strings = s.split(Separator);
                    m_CurrentCommitStateMap.put(strings[0], strings[1]);
                }
            }
        }
        wcStatus.setM_DeletedFilesList(m_CurrentCommitStateMap.keySet());
        recursiveCompareWC(m_ActiveRepository, rootFolderName, wcStatus);

        return wcStatus;
    }
    private void recursiveCompareWC(String stringPath, String fName, WorkingCopyStatus i_WcStatus) {
        File file = new File(stringPath + File.separator + fName);

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveCompareWC(file.getAbsolutePath(), f.getName(), i_WcStatus);
            }
        } else // isFile
        {
            String sha1InCommit = m_CurrentCommitStateMap.get(file.getAbsolutePath());
            String sha1InWC = DigestUtils.sha1Hex(getContentOfFile(file));

            i_WcStatus.getM_DeletedFilesList().remove(file.getAbsolutePath());

            if (sha1InCommit == null) {
                i_WcStatus.getM_NewFilesList().add(file.getAbsolutePath());
            } else if (!sha1InCommit.equals(sha1InWC)) {
                i_WcStatus.getM_ChangedFilesList().add(file.getAbsolutePath());
            }

        }
    }
    /* Case 5 */
    /* ShowWorkingCopyStatus -- End */

    /* Create Commit -- Start */
    /* Case 6 */

    public WorkingCopyStatus createCommit(String i_Msg)
    {
        WorkingCopyStatus wcStatus = ShowWorkingCopyStatus();
        String objectFolder;
        if(!wcStatus.IsEmpty()) {
            Commit newCommit = new Commit();
            newCommit.setM_Message(i_Msg);
            newCommit.setM_CreatedBy(m_ActiveUser);
            newCommit.setM_PreviousSHA1("NONE");
            newCommit.setM_PreviousSHA1merge("NONE");
            newCommit.setM_CreatedTime(dateFormat.format(new Date()));

            String rootFolderName = getRootFolderName();
            File rootFolderFile = new File(m_ActiveRepository + File.separator + rootFolderName);
            File commitStatusFile = new File(getPathFolder(".magit") + File.separator + "CommitStatus.txt");

            try {
                Files.write(Paths.get(commitStatusFile.getAbsolutePath()), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
            }

            objectFolder = getPathFolder("objects");

            if (!isFirstCommit()) {
                String PreviousSHA1 = getContentOfFile(new File(getPathFolder("branches"), getBranchActiveName() + ".txt"));
                newCommit.setM_PreviousSHA1(PreviousSHA1);
            }

            BlobData rootBlobData = recursiveTravelFolders(objectFolder, rootFolderFile, wcStatus);//throw all the WC files to WcCommit folder
            newCommit.setM_MainSHA1(rootBlobData.getM_Sha1());
            String commitSha1 = DigestUtils.sha1Hex(newCommit.toString());
            m_ZipFile.zipFile(objectFolder, commitSha1, newCommit.toString());
            updateBranchActiveCommit(commitSha1);
        }
        return wcStatus;
    }

    public BlobData recursiveTravelFolders(String i_FolderToZipInto ,File i_File, WorkingCopyStatus i_WCstatus) {
        String sha1;
        BlobData newBlobData;
        if (i_File.isDirectory()) {
            Folder folder = new Folder();
            for (final File f : i_File.listFiles())
                folder.AddNewItem(recursiveTravelFolders(i_FolderToZipInto, f, i_WCstatus));

            sha1 = DigestUtils.sha1Hex(folder.toString());
            BlobData directoryBlob =
                    new BlobData(i_File.getName(), sha1, ConstantsEnums.FileType.FOLDER,
                            m_ActiveUser, dateFormat.format(new Date())
                    );
            m_ZipFile.zipFile(i_FolderToZipInto, sha1, folder.printArray());

            return directoryBlob;
        }
        else { //isFile
                Boolean fileChanged = false;

                Blob blob = new Blob(getContentOfFile(i_File));
                sha1 = DigestUtils.sha1Hex(blob.getM_Data());

                fileChanged = isFileNeedCommit(i_File.getAbsolutePath(),i_WCstatus);

                if(fileChanged)
                {
                    m_ZipFile.zipFile(i_FolderToZipInto, sha1, getContentOfFile(i_File));
                }

            String toFile = i_File.getAbsolutePath() + Separator + sha1 + System.lineSeparator();
            Path path = Paths.get(getPathFolder(".magit") + File.separator + "CommitStatus.txt");
            try {
                Files.write(path, toFile.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

                newBlobData = new BlobData(i_File.getName(), sha1, ConstantsEnums.FileType.FILE,
                        m_ActiveUser, dateFormat.format(new Date()));
            }
            return newBlobData;
        }


    private boolean isFirstCommit() {
        String BranchName = getBranchActiveName();
        return (getContentOfFile(new File (getPathFolder("branches") + File.separator + BranchName + ".txt")).equals(EmptyString));
    }

    private Boolean isFileNeedCommit(String i_Path, WorkingCopyStatus i_WCstatus)
    {
        return i_WCstatus.getM_NewFilesList().contains(i_Path) || i_WCstatus.getM_ChangedFilesList().contains(i_Path);
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
            Path branchPath = Paths.get(getPathFolder("branches") + File.separator + branchName + ".txt");

            if (!getContentOfFile(new File(getPathFolder("branches"), branchName + ".txt")).equals(EmptyString)) {
                String commitSha1 = getContentOfFile(new File(getPathFolder("branches"), branchName + ".txt"));
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
        for (String name : names) {
            if (name.equals(i_BranchName))
                branchNameNotExist = false;

        }
        if (branchNameNotExist) {
            String Sha1CurrentBranch = EmptyString;
            Path path = Paths.get(stringPath);
            Path BranchPath = Paths.get(getPathFolder("branches") + File.separator + i_BranchName + ".txt");
            Sha1CurrentBranch = getContentOfFile(new File(getPathFolder("branches") + File.separator + getBranchActiveName() + ".txt"));
            try {
                Files.createFile(BranchPath);
                Files.write(BranchPath, Sha1CurrentBranch.getBytes());
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
    public Boolean deleteBranch(String i_BranchName) {
        Boolean bool = false;
        if (getBranchActiveName().equals(i_BranchName))
            bool = false;
        else {
            try {
                File branchesNamesFile = new File(getPathFolder("branches") + File.separator + "NAMES.txt");
                String branchesNamesContent = getContentOfFile(branchesNamesFile);
                if (!branchesNamesContent.contains(i_BranchName))
                    bool = false;
                else {
                    branchesNamesFile.delete();
                    branchesNamesContent = branchesNamesContent.replace(System.lineSeparator() + i_BranchName, "");
                    Path path = Paths.get(getPathFolder("branches") + File.separator + "NAMES.txt");
                    Files.write(path, branchesNamesContent.getBytes(), StandardOpenOption.CREATE);

                    bool = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bool;
    }
    /* Case 9 */
    /* Delete Branch -- END */

    /* Check out Head branch -- Start */
    /* Case 10 */
    public void CheckOutHeadBranch(String i_BranchName, Boolean i_toCommit,String Msg)
    {
        if(i_toCommit)
            createCommit(Msg);

        File rootFolder = new File(m_ActiveRepository + File.separator + getRootFolderName());
        deleteFolder(rootFolder);
        spreadCommitToWc(i_BranchName);
        Path HeadFile = Paths.get(getPathFolder("branches") + File.separator + "HEAD.txt");
        try {
            Files.write(HeadFile,i_BranchName.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteFolder(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles())
                deleteFolder(f);
            if (!file.getName().equals(getRootFolderName()))
                file.delete();
        } else
            file.delete();
    }
    /* Case 10 */
    /* Check out Head branch -- END */

    /* History of Active Branch -- Start */
    /* Case 11 */
    public List<CommitData> historyOfActiveBranch() {
        String activeBranchName = getBranchActiveName();
        List<CommitData> listOfCommits = new ArrayList<>();
        if(!getContentOfFile(new File(getPathFolder("branches") + File.separator + activeBranchName + ".txt")).equals(EmptyString)) {
            String activeBranchContent = getContentOfFile(new File(getPathFolder("branches") + File.separator + activeBranchName + ".txt"));
            recursiveShowHistoryOfCommits(activeBranchContent, listOfCommits);
        }
        return listOfCommits;
    }
    private void recursiveShowHistoryOfCommits(String i_CommitSha1, List<CommitData> listOfCommits) {
        Commit commit = new Commit(getContentOfZipFile(getPathFolder("objects"), i_CommitSha1));
        CommitData commitData = new CommitData(i_CommitSha1, commit.getM_Message(), commit.getM_CreatedTime(), commit.getM_CreatedBy());
        listOfCommits.add(commitData);
        if (!commit.getM_PreviousSHA1().equals("NONE"))
            recursiveShowHistoryOfCommits(commit.getM_PreviousSHA1(), listOfCommits);
    }
    /* Case 11 */
    /* History of Active Branch -- END */

    /* Bonus1 -- Start */
    /* Case 13 */
    public void initRepository(String []i_RepositoryArgs) {
        Path RepositoryPath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1]);
        Path RootFolderPath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + i_RepositoryArgs[1]);
        Path ObjectPath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + ".magit" + File.separator + "objects");
        Path branchesPath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + ".magit" + File.separator + "branches");
        Path activeBranchePath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + ".magit" + File.separator + "branches" + File.separator + "HEAD.txt");
        Path branchesNamesPath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + ".magit" + File.separator + "branches" + File.separator + "NAMES.txt");
        Path rootFolderNamePath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + ".magit" + File.separator + "RootFolderName.txt");
        Path commitStatusPath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + ".magit" + File.separator + "CommitStatus.txt");
        Path materFilePath = Paths.get(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1] + File.separator + ".magit" + File.separator + "branches" +File.separator +"mater.txt");
        Boolean dirExists = Files.exists(ObjectPath);
        if (dirExists) {
            System.out.println("Directory Alerady Exists");
        } else {
            try {
                Files.createDirectories(RepositoryPath);
                Files.createDirectories(RootFolderPath);
                Files.createDirectories(ObjectPath);
                Files.createDirectories(branchesPath);
                Files.createFile(activeBranchePath);
                Files.createFile(branchesNamesPath);
                Files.createFile(rootFolderNamePath);
                Files.createFile(commitStatusPath);
                Files.createFile(materFilePath);
                Files.write(activeBranchePath, "master".getBytes());
                Files.write(branchesNamesPath, "master".getBytes());
                Files.write(materFilePath, EmptyString.getBytes());
                Files.write(rootFolderNamePath, i_RepositoryArgs[1].getBytes());

                setM_ActiveRepository(i_RepositoryArgs[0] + File.separator + i_RepositoryArgs[1]);
                setM_ActiveRepositoryName(i_RepositoryArgs[1]);
            } catch (IOException ioExceptionObj) {
            }
        }
    }
    /* Case 13 */
    /* Bonus1 -- End */

    /* Bonus2 -- Start */
    /* Case 14 */
    public void zeroingBranch(String i_Sha1) {
        String ActiveBranch = getBranchActiveName();
        updateBranchActiveCommit(i_Sha1);
        File rootFolder = new File(m_ActiveRepository + File.separator + getRootFolderName());
        deleteFolder(rootFolder);
        spreadCommitToWc(ActiveBranch);
    }
    /* Case 14 */
    /* Bonus2 -- End */


    private String getContentOfZipFile(String i_Path, String i_ZipName) {
        m_ZipFile.unZipIt(i_Path + File.separator + i_ZipName + ".zip", i_Path);
        File unZippedFile = new File(i_Path + File.separator + i_ZipName + ".txt");
        String contentOfFile = getContentOfFile(unZippedFile);
        unZippedFile.delete();
        return contentOfFile;
    }
    private String getContentOfFile(File i_File) {
        String fileContent = EmptyString;
        Path path = Paths.get(i_File.getAbsolutePath());

        try {
            fileContent=EmptyString;
            if(path.toFile().length()>0)
                fileContent = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileContent;
    }
    private String getBranchActiveName() {
        String branchActiveName = EmptyString;

        Path path = Paths.get(getPathFolder("branches") + File.separator + "HEAD.txt");
        try {
            branchActiveName = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return branchActiveName;
    }
    private void spreadCommitToWc(String i_BranchName) {
        File BranchFile = new File(getPathFolder("branches") + File.separator + i_BranchName + ".txt");

        if (!getContentOfFile(BranchFile).equals(EmptyString)) {
            String sha1OfLastCommitBranch = getContentOfFile(BranchFile);
            Commit commit = new Commit(getContentOfZipFile(getPathFolder("objects"), sha1OfLastCommitBranch));
            String RootFolderSha1 = commit.getM_MainSHA1();
            String path = m_ActiveRepository + File.separator + getRootFolderName();
            buildingRepository(path, RootFolderSha1, FileType.FOLDER);
        }
    }
    private void buildingRepository(String path, String Sha1, FileType i_FileType) {
        if (i_FileType == FileType.FOLDER) {
            File pathFile = new File(path);
            pathFile.mkdir();
            Folder folder = new Folder(getContentOfZipFile(getPathFolder("objects"), Sha1));
            List<BlobData> BlobDataArray = folder.getLibraryFiles();
            for (BlobData blobData : BlobDataArray) {
                buildingRepository(path + File.separator + blobData.getM_Name(), blobData.getM_Sha1(), blobData.getM_Type());
            }
        } else {
            Path pathFile = Paths.get(path);
            try {
                Files.createFile(pathFile);
                Files.write(pathFile, getContentOfZipFile(getPathFolder("objects"), Sha1).getBytes());

                String toFile = pathFile + Separator + Sha1 + System.lineSeparator();
                Path pathToFile = Paths.get(getPathFolder(".magit") + File.separator + "CommitStatus.txt");
                try {
                    Files.write(pathToFile, toFile.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private String getRootFolderName() {
        Path RootFolderName = Paths.get(getPathFolder(".magit") + File.separator + "RootFolderName.txt");
        if(Files.exists(RootFolderName))
            return getContentOfFile(new File(getPathFolder(".magit") + File.separator + "RootFolderName.txt"));
        else
            return getM_ActiveRepositoryName();
    }
    private void updateBranchActiveCommit(String i_CommitSha1) {
        String activeBranchName = getBranchActiveName();
        Path activeBranchPath = Paths.get(getPathFolder("branches") + File.separator + activeBranchName + ".txt");
        try {
            if (!Files.exists(activeBranchPath))
                Files.createFile(activeBranchPath);
            Files.write(activeBranchPath, i_CommitSha1.getBytes());
        } catch (Exception e) {

        }
    }
    public boolean WcNotChanged() {
        WorkingCopyStatus workingCopyStatus = ShowWorkingCopyStatus();
        return workingCopyStatus.isNotChanged();
    }

    public String getPathFolder(String i_Folder) {
        String path = EmptyString;
        switch (i_Folder) {
            case "objects":
                path = m_ActiveRepository + File.separator + ".magit" + File.separator + "objects";
                break;
            case "branches":
                path = m_ActiveRepository + File.separator + ".magit" + File.separator + "branches";
                break;
            case ".magit":
                path = m_ActiveRepository + File.separator + ".magit";
                break;
        }
        return path;
    }
}