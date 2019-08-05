package UI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputManager {
    private Scanner m_Scanner = new Scanner(System.in);

    public String IOsetActiveUser()
    {
        System.out.println("insert new Active UserName:");
        String userName = m_Scanner.nextLine();
        return(userName);
    }

    public String[] IOinitRepository()
    {
        String repositoryArgs[] = new String[2];
        System.out.println("insert Repository Location:");
        repositoryArgs[0] =  m_Scanner.nextLine();
        System.out.println("insert Repository Name:");
        repositoryArgs[1] = m_Scanner.nextLine();
        return repositoryArgs;
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
        String commitMsg = m_Scanner.nextLine();
        return(commitMsg);
    }


    public String getInputNewBranchName() {
        System.out.println("insert new branch name: ");
        String branchName = m_Scanner.nextLine();
        return(branchName);
    }

    public void printBranchNameExist() {
        System.out.println("Error! Branch Name Exist!");
    }
}
