package UI;
import Logic.LogicManager;
import java.util.Scanner;

import static Logic.ConstantsEnums.EmptyString;


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
            case "1": //Change username - Finished
                m_LogicManager.setM_ActiveUser(m_InputManager.readActiveUserName());
                break;

            case "2"://Load from XML
                m_LogicManager.readXML();
                break;

            case "3"://Switch repository - Finished
                m_LogicManager.setM_ActiveRepository(m_InputManager.getInputActiveRepository());
                break;

            case "4": //Show current commit file system information
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString))
                    m_InputManager.printAllBlobDataDetails(m_LogicManager.showLastCommit());
                else
                    m_InputManager.printInsertRepository();
                break;

            case "5": //Working copy status
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString))
                    m_InputManager.PrintWcStatus(m_LogicManager.ShowWorkingCopyStatus());
                else
                    m_InputManager.printInsertRepository();
                break;

            case "6"://Commit
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString))
                    m_LogicManager.createCommit(m_InputManager.readCommitMsg());
                else
                    m_InputManager.printInsertRepository();
                break;

            case "7": //List available branches
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString))
                    m_InputManager.PrintAllBranches(m_LogicManager.GetAllBranchesDetails());
                else
                    m_InputManager.printInsertRepository();
                break;

            case "8"://Create new branch
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString)) {
                    if (!m_LogicManager.createNewBranch(m_InputManager.getInputNewBranchName()))
                        m_InputManager.printBranchNameExist();
                }
                else
                    m_InputManager.printInsertRepository();
                break;

            case "9": //Check out branch
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString)) {
                    if (!m_LogicManager.deleteBranch(m_InputManager.getInputBranchNameToDelete()))
                        m_InputManager.printBranchNameIsActive();
                }
                else
                    m_InputManager.printInsertRepository();
                break;

            case "10": //Check out Head branch
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString)) {
                    Boolean bool = false;
                    String Msg = EmptyString;
                    String BranchName = m_InputManager.getInputChangeBranch(m_LogicManager.getPathFolder("branches"));
                    if (m_LogicManager.WcChanged()) {
                        bool = m_InputManager.WcOpenChanges();
                        if (bool)
                            Msg = m_InputManager.readCommitMsg();
                    }
                    m_LogicManager.CheckOutHeadBranch(BranchName, bool, Msg);
                }
                else
                    m_InputManager.printInsertRepository();
                break;

            case "11": //Show current branch history
                if(!m_LogicManager.getM_ActiveRepository().equals(EmptyString))
                    m_InputManager.printBranchHistoryCommits(m_LogicManager.historyOfActiveBranch());
                else
                    m_InputManager.printInsertRepository();
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
