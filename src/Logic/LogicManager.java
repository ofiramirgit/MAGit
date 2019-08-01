package Logic;

import Zip.ZipFile;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static Logic.ConstantsEnums.*;



public class LogicManager {

    private String m_ActiveUser = "Administrator";
    private String m_ActiveRepository = EmptyString;
    private String m_ActiveRepositoryName = EmptyString;
    private ZipFile m_zipFile;

    public void setM_ActiveUser(String m_ActiveUser) {this.m_ActiveUser = m_ActiveUser;}

    public String getM_ActiveUser() { return m_ActiveUser; }

    public String getM_ActiveRepository() { return m_ActiveRepository; }

    public void setM_ActiveRepository(String m_ActiveRepository) {
        this.m_ActiveRepository = m_ActiveRepository;
    }

    public String getM_ActiveRepositoryName() { return m_ActiveRepositoryName; }

    public void setM_ActiveRepositoryName(String m_ActiveRepositoryName) {this.m_ActiveRepositoryName = m_ActiveRepositoryName; }

    public Boolean setActiveRepository(String i_ActiveRepository)
    {
        Path RepositoryPath = Paths.get(i_ActiveRepository + "\\.magit" );
        if(Files.exists(RepositoryPath)) {
            m_ActiveRepository = i_ActiveRepository;
            return true;
        }
        return false;
    }

    public void initRepository(String i_RepositoryArgs[]) {
        Path RepositoryPath = Paths.get(i_RepositoryArgs[0] + "\\.magit" + "\\object" );
        Path BranchesPath = Paths.get(i_RepositoryArgs[0] + "\\.magit" + "\\branches" );
        Path ActiveBranchePath = Paths.get(i_RepositoryArgs[0] + "\\.magit" + "\\branches\\HEAD.txt" );
        Path BranchesNamesPath = Paths.get(i_RepositoryArgs[0] + "\\.magit" + "\\branches\\NAMES.txt" );
        Path RepositoryWC = Paths.get(i_RepositoryArgs[0] + "\\" + i_RepositoryArgs[1] );

        Boolean dirExists = Files.exists(RepositoryPath);
        if (dirExists) {
            System.out.println("Directory Alerady Exists");
        } else {
            try {
                Files.createDirectories(RepositoryPath);
                Files.createDirectories(BranchesPath);
                Files.createDirectories(RepositoryWC);
                Files.createFile(ActiveBranchePath);
                Files.createFile(BranchesNamesPath);
                Files.write(ActiveBranchePath, "master".getBytes());
                Files.write(BranchesNamesPath, "master".getBytes());

                setActiveRepository(i_RepositoryArgs[0]);
                m_ActiveRepositoryName = i_RepositoryArgs[1];
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
    }

    public void makeNewCommit(String i_Msg)
    {
        Commit newCommit = new Commit();
        newCommit.setM_Message(i_Msg);
        newCommit.setM_CreatedBy(m_ActiveUser);
        newCommit.setM_PreviousSHA1("NONE");
        newCommit.setM_CreatedTime(dateFormat.format(new Date()));
        start(newCommit);

    }
     public void start(Commit commit)
     {
         m_zipFile = new ZipFile(m_ActiveRepository +"/.magit/object");
         final File rootFolderFile = new File(m_ActiveRepository + "/" + m_ActiveRepositoryName);
         BlobData rootBlobData = recursiveTravelFolders(rootFolderFile);
         commit.setM_MainSHA1(rootBlobData.getM_Sha1());

         String commitSha1 = DigestUtils.sha1Hex(commit.toString());
         m_zipFile.zipFile(commitSha1,commit.toString());
         updateBranchActiveCommit(commit);

     }

    private void updateBranchActiveCommit(Commit commit) {
        String ActiveBranchName = getBranchActiveName();
        //if there is old commit on the same name of the active branch so update the new commit in the field of old commit
        m_zipFile.zipFileToBranchActive(ActiveBranchName,commit.toString());
    }

    public BlobData recursiveTravelFolders(File file){
        String sha1 = EmptyString;
       if(file.isDirectory())
       {
           Folder folder = new Folder();

           for (final File f : file.listFiles())
           {
               folder.AddNewItem(recursiveTravelFolders(f));
           }

           sha1 = DigestUtils.sha1Hex(folder.toString());

           BlobData DirectoryBlob = new BlobData(file.getName(),
                   sha1,
                   FileType.FOLDER, m_ActiveUser, dateFormat.format(new Date()));

           m_zipFile.zipFile(sha1,folder.printArray());

           System.out.println("\nfolder name: " +file.getName()+"\n" + DirectoryBlob.toString() + "\n");


           return DirectoryBlob;

       }
       else // text file
       {
           // check if sha 1 exists
           Blob blob = new Blob(getContentOfFile(file));

           sha1 =DigestUtils.sha1Hex(blob.getM_Data());

            m_zipFile.zipFile(sha1,getContentOfFile(file));

           BlobData newBlobData = new BlobData(file.getName(),sha1,
                    FileType.FILE, m_ActiveUser, dateFormat.format(new Date()));

           System.out.println("\nfile name: " +file.getName()+"\n" + newBlobData.toString() + "\n");

          return newBlobData;
       }


    }

    public String getContentOfFile(File i_File)
    {
        String content = EmptyString;
        Path path =  Paths.get(i_File.getAbsolutePath());
        try{
            content = new String(Files.readAllBytes(path));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return content;
    }

    public String getBranchActiveName()
    {
        String BranchActiveName = EmptyString;

       // Path path =  Paths.get(m_ActiveRepository + File.separator +".."+ File.separator +".magit"+File.separator +"HEAD");
        Path path =  Paths.get(getM_ActiveRepository() + "/.magit/branches/HEAD.txt");
        try{
            BranchActiveName = new String(Files.readAllBytes(path));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return BranchActiveName;
    }

    public void readXML()
    {
//        XmlReader xmlReader = new XmlReader("src\\Resourses\\ex1-small.xml");
//        xmlReader.buildFromXML();
    }
}


/*
public class JavaExample {

    public static void main(String[] args) {

        final File folder = new File("C:\\projects");

        List<String> result = new ArrayList<>();

        search(".*\\.java", folder, result);

        for (String s : result) {
            System.out.println(s);
        }

    }

    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }

}
 */