package UI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputManager {
    private Scanner m_Scanner = new Scanner(System.in);

    public String IOsetActiveUser()
    {
        System.out.println("insert new Active UserName:\n");
        return(m_Scanner.nextLine());
    }

    public Path IOinitRepository()
    {
        System.out.println("insert Repository Name:\n");
        String RepositoryName = m_Scanner.nextLine();
        System.out.println("insert Repository Location:\n");
        Path RepositoryPath = Paths.get(m_Scanner.nextLine() + "\\" + RepositoryName+ "\\.magit" );
        return RepositoryPath;
    }

}
