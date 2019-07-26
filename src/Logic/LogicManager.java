package Logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static Logic.ConstantsEnums.*;


public class LogicManager {

    private String m_ActiveUser = "Administrator";

    public String getActiveUser() { return m_ActiveUser; }

    public void setActiveUser(String i_ActiveUser) { m_ActiveUser = i_ActiveUser; }

    public String getRepositoryLocation() { return "C:/repo"; } //default directory(for testing)

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
}
