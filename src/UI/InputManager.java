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

    public String[] IOinitRepository()
    {
        String RepositoryArgs[] = new String[2];
        System.out.println("insert Repository Location:");
        RepositoryArgs[0] =  m_Scanner.nextLine();
        System.out.println("insert Repository Name:");
        RepositoryArgs[1] = m_Scanner.nextLine();
        return RepositoryArgs;
    }

    public String getInputActiveRepository()
    {
        System.out.println("insert Repository Full Path:");
        return m_Scanner.nextLine();
    }

    public void printActiveRepositoryNotFound()
    {
        System.out.println("Error! Repository Not Found.");
    }

    public String IOgetCommitMsg()
    {
        System.out.println("insert commit massage: ");
        String commit_msg = m_Scanner.nextLine();
        return(commit_msg);
    }


}
