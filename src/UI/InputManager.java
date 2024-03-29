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
                System.out.println("Repository not found. please try again: ");
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
        if(!i_BlobDataArr.isEmpty()){
            for (BlobData bd : i_BlobDataArr) {
                System.out.println("Full Path:      " + bd.getM_Name());
                System.out.println("File Type:      " + bd.getM_Type());
                System.out.println("Sha1:           " + bd.getM_Sha1());
                System.out.println("Changed By:     " + bd.getM_ChangedBy());
                System.out.println("Changed At:     " + bd.getM_Date() + "\n");
            }
        }
        else
            print("There is no branch that make commit." + System.lineSeparator());
    }
    /*case 4 - show commit details - End*/

    /*case 5 - show commit details - Start*/

    public void PrintWcStatus(WorkingCopyStatus i_WcStatus)
    {
        if(i_WcStatus.isNotChanged()){
            System.out.println("There is no changes." + System.lineSeparator());

        }
        else {
            if (!i_WcStatus.getM_NewFilesList().isEmpty()) {
                System.out.println("New Files:");
                for (String fileFullName : i_WcStatus.getM_NewFilesList())
                    System.out.println("    - " + fileFullName);
                System.out.println();
            }
            if (!i_WcStatus.getM_ChangedFilesList().isEmpty()) {
                System.out.println("Modified Files:");
                for (String fileFullName : i_WcStatus.getM_ChangedFilesList())
                    System.out.println("    - " + fileFullName);
                System.out.println();
            }
            if (!i_WcStatus.getM_DeletedFilesList().isEmpty()) {
                System.out.println("Deleted Files:");
                for (String fileFullName : i_WcStatus.getM_DeletedFilesList())
                    System.out.println("    - " + fileFullName);
                System.out.println();
            }
        }
    }

    /*case 5 - show commit details - End*/


       /*case 7 - showAllBranches - Start*/
    public  void  PrintAllBranches(List<BranchData> i_BranchDataList)
    {
        if(!i_BranchDataList.isEmpty()) {
            for (BranchData branchData : i_BranchDataList) {
                if (branchData.getM_IsActive())
                    System.out.print("(Active Branch) -> ");
                System.out.println("Branch Name:    " + branchData.getM_BranchName());
                System.out.println("------------------------------");
                System.out.println("Commit Sha1:    " + branchData.getM_CommitSha1());
                System.out.println("Commit Message: " + branchData.getM_CommitMessage() + System.lineSeparator());
            }
        }
        else
            print("There is no branch that make commit." + System.lineSeparator());
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


    public String getInputBranchNameToDelete() {
        System.out.println("insert branch name you want to delete: ");
        String branchName = m_Scanner.nextLine();
        return branchName;
    }


    public String getInputChangeBranch(String PathOfBranchesFolder) {
        Boolean bool = false;
        String branchName =EmptyString;
        System.out.println("insert branch name you want to replace: ");
        while (!bool)
        {
            branchName = m_Scanner.nextLine();
            bool = m_InputValidation.isBranchExist(PathOfBranchesFolder+ File.separator + "NAMES.txt",branchName);
            if(!bool)
                System.out.println("Error! branch name isn't Exist! Please try Again.");
        }
        return branchName;
    }

    public Boolean WcOpenChanges() {
        Boolean bool = false;
        String makeCommit = EmptyString;
        while(!bool) {
            System.out.println("there is open changes. Do You Want To Make a Commit? [Y/N]");
            makeCommit = m_Scanner.nextLine();
            bool = m_InputValidation.inputYesNo(makeCommit);
        }
        return(makeCommit.toLowerCase().equals("y"));
    }

    public Boolean Bonus2(){
        Boolean bool = false;
        String makeCommit = EmptyString;
        while(!bool) {
            System.out.println("there is open changes. Do you want to change the Sha-1 anyway? [Y/N]");
            makeCommit = m_Scanner.nextLine();
            bool = m_InputValidation.inputYesNo(makeCommit);
        }
        return (makeCommit.toLowerCase().equals("y"));
    }

    public void printBranchHistoryCommits(List<CommitData> BranchHistoryCommits) {
        if(BranchHistoryCommits.isEmpty())
            System.out.println("there is no commits to show for this branch");
        for(CommitData commit: BranchHistoryCommits)
        {
            System.out.println("Sha1        : " + commit.getM_SHA1());
            System.out.println("Message     : " + commit.getM_Message());
            System.out.println("Created Time: " + commit.getM_CreatedTime());
            System.out.println("Created By  : " + commit.getM_CreatedBy() +System.lineSeparator());
        }
    }


    public String getXmlPathFile() {
        System.out.println("insert full path of XML file: ");
        return m_Scanner.nextLine();
    }

    public String getInputSha1() {
        Boolean bool =false;
        String Sha1Input = EmptyString;
        System.out.println("insert sha1 that you want the branch to point to (40 HEX characters): ");
        while(!bool) {
            Sha1Input = m_Scanner.nextLine();
            bool = m_InputValidation.validSha1(Sha1Input);
            if(!bool)
                System.out.println("Invalid Sha1. try again (40 Hex Characters): ");
        }
        return Sha1Input;
    }

    public void print(String i_String) {
        System.out.println(i_String + System.lineSeparator());
    }

    public Integer getInputXmlRepositoryExist() {
        Boolean bool =false;
        String option = EmptyString;
        while(!bool) {
            option = m_Scanner.nextLine();
            bool = m_InputValidation.validOptionXmlRepositoryExist(option);
            if(!bool)
                System.out.println("Invalid input. try again (1,2): ");
        }
        return Integer.parseInt(option);
    }

    public Boolean getInputSwitchBranch() {
        Boolean bool = false;
        String checkOutBranch = EmptyString;
        while(!bool) {
            System.out.println("Do you want to check out active branch and load this branch? [Y/N]");
            checkOutBranch = m_Scanner.nextLine();
            bool = m_InputValidation.inputYesNo(checkOutBranch);
        }
        return(checkOutBranch.toLowerCase().equals("y"));
    }
}
