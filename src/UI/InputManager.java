package UI;

import Logic.InputValidation;
import Logic.Objects.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
        Boolean bool = false;
        String userName = EmptyString;
        System.out.println("Please insert user name:");
        while (!bool)
        {
           userName = m_Scanner.nextLine();
           bool = m_InputValidation.checkInputStringLen(userName);
           if(!bool)
               System.out.println("Please insert user name up to 50 characters:");
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


    /*case 4 - show commit details - Start*/

    public void printAllBlobDataDetails(List<BlobData> i_BlobDataArr)
    {
        for(BlobData bd : i_BlobDataArr)
        {
            System.out.println("Full Path:      " + bd.getM_Name());
            System.out.println("File Type:      " + bd.getM_Type());
            System.out.println("Sha1:           " + bd.getM_Sha1());
            System.out.println("Changed By: " + bd.getM_ChangedBy());
            System.out.println("Changed At: " + bd.getM_Date());
            System.out.println();
        }
    }
    /*case 4 - show commit details - End*/

    /*case 5 - show commit details - Start*/

    public void PrintWcStatus(WorkingCopyStatus i_WcStatus)
    {
        System.out.println("New Files:");
        for(String fileFullName : i_WcStatus.getM_NewFilesList())
            System.out.println(fileFullName);

        System.out.println("Modified Files:");
        for(String fileFullName : i_WcStatus.getM_ChangedFilesList())
            System.out.println(fileFullName);

        System.out.println("Deleted Files:");

        for(String fileFullName : i_WcStatus.getM_DeletedFilesList())
            System.out.println(fileFullName);
    }

    /*case 5 - show commit details - End*/

    /*case 6 - commit*/
       public  void  printNoChangesNotCommited()
       {
           System.out.println("No changes detected, commit canceled");
       }

    /*case 6 - commit*/

       /*case 7 - showAllBranches - Start*/
    public  void  PrintAllBranches(List<BranchData> i_BranchDataList) {

        for(BranchData branchData : i_BranchDataList)
        {
            System.out.println("Branch Name:    " + branchData.getM_BranchName());
            if (branchData.getM_IsActive())
                System.out.print(" (Active Branch");
            System.out.println("------------------------------");
            System.out.println("Commit Sha1:    " + branchData.getM_CommitSha1());
            System.out.println("Commit Message: " + branchData.getM_CommitMessage());
            System.out.println();
        }

    }

    /*case 7- showAllBranches - End*/

    public void printActiveRepositoryNotFound()
    {
        System.out.println("Error! Repository Not Found.");
    }

    public String readCommitMsg()
    {
        Boolean bool = false;
        String commitMsg = EmptyString;
        System.out.println("insert commit massage: ");
        while (!bool)
        {
            commitMsg = m_Scanner.nextLine();
            bool = m_InputValidation.checkInputStringLen(commitMsg);
            if (!bool)
                System.out.println("Please insert message up to 50 characters:");
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

    public String getInputChangeBranch(String PathOfBranchesFolder) {
        Boolean bool = false;
        String branchName =EmptyString;
        System.out.println("insert branch name you want to replace: ");
        while (!bool)
        {
            branchName = m_Scanner.nextLine();
            bool = m_InputValidation.isBranchExist(branchName,PathOfBranchesFolder+ File.separator + branchName + ".txt");
            if(!bool)
                System.out.println("Error! branch name isn't Exist! Please try Again.");
        }
        return branchName;
    }

    public Boolean WcOpenChanges() {
        Boolean bool = false;
        String makeCommit = EmptyString;
        System.out.println("there is open changes. Do You Want To Make a Commit? [Y/N]");
        while(!bool) {
            makeCommit = m_Scanner.nextLine();
            bool = m_InputValidation.makeCommitToOpenChanges(makeCommit);
        }
        if(makeCommit.toLowerCase().equals("y"))
            return true;
        else
            return false;
    }

    public void printBranchHistoryCommits(List<CommitData> BranchHistoryCommits) {
        for(CommitData commit: BranchHistoryCommits)
        {
            System.out.println("Sha1        : " + commit.getM_SHA1());
            System.out.println("Message     : " + commit.getM_Message());
            System.out.println("Created Time: " + commit.getM_CreatedTime());
            System.out.println("Created By  : " + commit.getM_CreatedBy() +System.lineSeparator());
        }
    }

    public void printInsertRepository() {
        System.out.println("Invalid! you have to select repository location (3).");
    }

    public String getXmlPathFile() {
        System.out.println("insert full path of XML file: ");
        return m_Scanner.nextLine();
    }

    public String getInputSha1() {
        Boolean bool =false;
        String Sha1Input =EmptyString;
        System.out.println("insert sha1 that you want the branch to point to (40 HEX characters): ");
        while(!bool) {
            Sha1Input = m_Scanner.nextLine();
            if (!m_InputValidation.validSha1(Sha1Input))
                System.out.println("Invalid Sha1. (40 Hex Characters. try again: ");
            else
                bool=true;
        }
        return Sha1Input;
    }

}
