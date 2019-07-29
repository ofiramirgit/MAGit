package Logic;

import GeneratedXML.MagitRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static Logic.ConstantsEnums.*;



public class LogicManager {

    private String m_ActiveUser = "Administrator";
    private String m_ActiveRepository="C:/default";

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

    public void initRepository(Path i_RepositoryPath) {
        Boolean dirExists = Files.exists(i_RepositoryPath);
        if (dirExists) {
            System.out.println("Directory Alerady Exists");
        } else {
            try {
                Files.createDirectories(i_RepositoryPath);
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }

    }

    public void readXML()
    {
        XmlReader xmlReader = new XmlReader("C:\\Users\\amira\\IdeaProjects\\MAGit2\\src\\Resourses\\ex1-small.xml");
        xmlReader.buildFromXML();


    }
}
