package UI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static Logic.ConstantsEnums.EmptyString;
import static Logic.ConstantsEnums.NameLength;
import static UI.Menu.MENU_OPTIONS;

public class InputManager {
    private Scanner m_Scanner = new Scanner(System.in);

    public Integer GetNextMenuOption()
    {
        String stringOption = EmptyString;
       Integer option = 0;
       Boolean loop = true;

       while(loop)
       {
           loop = false;
           stringOption = m_Scanner.nextLine();

           try
           {
               option = Integer.parseInt(stringOption);

               if((option < -1 || option >= MENU_OPTIONS) || option == 0)
               {
                   loop = true;
                   System.out.println("Illegal Choice.");
                   System.out.println("Please choose option from the menu:");
               }
           }
           catch (Exception e)
           {
               loop = true;
               System.out.println("Please enter a number from the menu:");
           }

       }

       return option;
    }

    public String readActiveUserName()
    {
        Boolean loop = true;
        String userName = EmptyString;
        System.out.println("Please insert user name:");

       while (loop)
       {
           loop = false;
           userName = m_Scanner.nextLine();

           if(!checkInputStringLen(userName))
           {
               loop = true;
               System.out.println(" Please insert user name up to 50 characters:");
           }
       }

        return userName;
    }

    private Boolean checkInputStringLen(String i_String)
    {
        if(i_String.length() == 0 || i_String.length() > 50)
        {
         return false;
        }
        return true;
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

    public String readCommitMsg()
    {
        Boolean loop = true;
        System.out.println("insert commit massage: ");
        String commitMsg = EmptyString;

        while (loop)
        {
            loop = false;
            commitMsg = m_Scanner.nextLine();

            if(!checkInputStringLen(commitMsg))
            {
                loop = true;
                System.out.println(" Please insert message up to 50 characters:");
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
