package UI;
import Logic.LogicManager;
import java.util.Scanner;


public class Engine
{
    private Boolean m_IsRunning = true;
    private LogicManager m_LogicManager = new LogicManager();
    private InputManager m_InputManager = new InputManager();
    private Menu m_Menu = new Menu();


    public void run()
    {
        while(m_IsRunning)
        {
            m_Menu.printInfoMenu(m_LogicManager.getM_ActiveUser(),m_LogicManager.getM_ActiveRepository());
            m_Menu.printMenu();
            execute(m_InputManager.GetNextMenuOption());
        }
    }

    private void execute(String i_Option)
    {
        switch(i_Option){
            case "-1":
               // m_LogicManager.TESTING_CHANGING_BRANCH(m_InputManager.IOsetActiveUser());

            case "1": //Change username - Finished
                m_LogicManager.setM_ActiveUser(m_InputManager.readActiveUserName());
                break;

            case "2"://Load from XML
                //m_LogicManager.readXML();
                break;

            case "3"://Switch repository - Finished
                m_LogicManager.setM_ActiveRepository(m_InputManager.getInputActiveRepository());
                break;

            case "4": //Show current commit file system information
                m_InputManager.printAllBlobDataDetails(m_LogicManager.showLastCommit());
                break;

            case "5": //Working copy status
                m_LogicManager.ShowWorkingCopyStatus();
                break;

            case "6"://Commit
                m_LogicManager.createCommit(m_InputManager.readCommitMsg());
                break;

            case "7": //List available branches
                m_InputManager.PrintAllBranches(m_LogicManager.GetAllBranchesDetails());

                break;

            case "8"://Create new branch
                if(!m_LogicManager.createNewBranch(m_InputManager.getInputNewBranchName()))
                    m_InputManager.printBranchNameExist();
                break;

            case "9": //Check out branch
                if(!m_LogicManager.deleteBranch(m_InputManager.getInputBranchNameToDelete()))
                    m_InputManager.printBranchNameIsActive();
                break;

            case "10": //Check out Head branch
                m_LogicManager.spreadCommitToWc("master");
                break;

            case "11": //Show current branch history
                m_LogicManager.historyOfActiveBranch();
                break;

            case "12": //Exit
                    m_IsRunning = false;//Bonus 1
                break;

            case "13": //Bonus1
                String repositoryArgs[];
                repositoryArgs = m_InputManager.IOinitRepository();
                m_LogicManager.initRepository(repositoryArgs);
                break;
            default:
                System.out.println("Invalid Input please selcet number between 1-13.");
                break;


        }
    }
}
