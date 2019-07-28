package UI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputManager {
    private Scanner m_Scanner = new Scanner(System.in);

    public String IOsetActiveUser()
    {
        System.out.println("insert new Active UserName:");
        String user_name = m_Scanner.nextLine();
        return(user_name);
    }

    public Path IOinitRepository()
    {
        System.out.println("insert Repository Name:");
        String RepositoryName = m_Scanner.nextLine();
        System.out.println("insert Repository Location:");
        Path RepositoryPath = Paths.get(m_Scanner.nextLine() + "\\" + RepositoryName+ "\\.magit" );
        return RepositoryPath;
    }


}
