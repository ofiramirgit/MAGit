package UI;

import Logic.InputValidation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static Logic.ConstantsEnums.EmptyString;
import static Logic.ConstantsEnums.NameLength;
import static UI.Menu.MENU_OPTIONS;

public class InputManager {

    private Scanner m_Scanner = new Scanner(System.in);
    private InputValidation m_InputValidation = new InputValidation();

    public String GetNextMenuOption()
    {
        return m_Scanner.nextLine();
    }


    /*case 1 - change user name - Start*/
    public String readActiveUserName()
    {
        Boolean loop = true;
        String userName = EmptyString;
        System.out.println("Please insert user name:");

       while (loop)
       {
           loop = false;
           userName = m_Scanner.nextLine();

           if(!m_InputValidation.checkInputStringLen(userName))
           {
               loop = true;
               System.out.println("Please insert user name up to 50 characters:");
           }
       }
        return userName;
    }

    /*case 1 - change user name - End*/

    /*case 3 - switch repository - Start*/
    public String getInputActiveRepository()
    {
        Boolean loop = true;
        String activeRepository = EmptyString;
        System.out.println("insert Repository Full Path: ");
        while(loop) {
            loop = false;
            activeRepository = m_Scanner.nextLine();
            if (!m_InputValidation.checkInputActiveRepository(activeRepository)) {
                loop = true;
                System.out.println("Repository Not Found");
            }
        }
        return activeRepository;
    }
    /*case 3 - switch repository - End*/

    public String[] IOinitRepository()
    {
        String repositoryArgs[] = new String[2];
        System.out.println("insert Repository Location:");
        repositoryArgs[0] =  m_Scanner.nextLine();
        System.out.println("insert Repository Name:");
        repositoryArgs[1] = m_Scanner.nextLine();
        return repositoryArgs;
    }

    public void printActiveRepositoryNotFound()
    {
        System.out.println("Error! Repository Not Found.");
    }

    public String readCommitMsg()
    {
        Boolean loop = true;
        System.out.println("insert commit massage: ");
        String commitMsg = EmptyString;

        while (loop)
        {
            loop = false;
            commitMsg = m_Scanner.nextLine();

            if(!m_InputValidation.checkInputStringLen(commitMsg))
            {
                loop = true;
                System.out.println("Please insert message up to 50 characters:");
            }
        }
        return commitMsg ;
    }


    public String getInputNewBranchName() {
        System.out.println("insert new branch name: ");
        String branchName = m_Scanner.nextLine();
        return(branchName);
    }

    public void printBranchNameExist() {
        System.out.println("Error! Branch Name Exist!");
    }

    public String getInputBranchNameToDelete() {
        System.out.println("insert branch name you want to delete: ");
        String branchName = m_Scanner.nextLine();
        return branchName;
    }

    public void printBranchNameIsActive() {
        System.out.println("Error! Branch is Active!");
    }
}
