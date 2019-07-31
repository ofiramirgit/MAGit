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
    private String m_ActiveRepository="C:/Hector/f";

    private ZipFile m_zipFile = new ZipFile("C:/Hector/.magit/object");

    public String getActiveUser() { return m_ActiveUser; }

    public void setActiveUser(String i_ActiveUser) { m_ActiveUser = i_ActiveUser; }

    public String getActiveRepository(){ return m_ActiveRepository; }
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
        Path RepositoryWC = Paths.get(i_RepositoryArgs[0] + "\\" + i_RepositoryArgs[1] );

        Boolean dirExists = Files.exists(RepositoryPath);
        if (dirExists) {
            System.out.println("Directory Alerady Exists");
        } else {
            try {
                Files.createDirectories(RepositoryPath);
                Files.createDirectories(RepositoryWC);
                setActiveRepository(i_RepositoryArgs[0]);
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

        //newCommit.setM_CreatedTime();
    }
     public void start(Commit commit)
     {
         final File rootFolderFile = new File(m_ActiveRepository);
         BlobData rootBlobData = recursiveTravelFolders(rootFolderFile);
         commit.setM_MainSHA1(rootBlobData.getM_Sha1());

         String commitSha1 = DigestUtils.sha1Hex(commit.toString());

         m_zipFile.zipFile(commitSha1,commit.toString());

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