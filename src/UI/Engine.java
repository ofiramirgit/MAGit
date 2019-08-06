package UI;
import Logic.LogicManager;

import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Logic.ConstantsEnums.EmptyString;

public class Engine
{
    private Scanner m_Scanner = new Scanner(System.in);
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
            execute(m_Scanner.nextInt());
        }
    }

    private void execute(Integer i_Option)
    {
        switch(i_Option){
            case -1:
                m_LogicManager.TESTING_CHANGING_BRANCH(m_InputManager.IOsetActiveUser());

            case 1: //Change username
                m_LogicManager.setM_ActiveUser(m_InputManager.IOsetActiveUser());
                break;

            case 2://Load from XML
                //m_LogicManager.readXML();
                break;

            case 3://Switch repository
                if(!m_LogicManager.setM_ActiveRepository(m_InputManager.getInputActiveRepository()))
                    m_InputManager.printActiveRepositoryNotFound();
                break;

            case 4: //Show current commit file system information
                m_LogicManager.showLastCommit();
                break;

            case 5: //Working copy status
                //m_LogicManager.WorkingCopyStatus();
                break;

            case 6://Commit
                m_LogicManager.createCommit(m_InputManager.IOgetCommitMsg());
                break;

            case 7: //List available branches
                m_LogicManager.showAllBranches();

                break;

            case 8://Create new branch
                if(!m_LogicManager.createNewBranch(m_InputManager.getInputNewBranchName()))
                    m_InputManager.printBranchNameExist();
                break;

            case 9: //Check out branch
                if(!m_LogicManager.deleteBranch(m_InputManager.getInputBranchNameToDelete()))
                    m_InputManager.printBranchNameIsActive();
                break;

            case 10: //Check out Head branch
                break;

            case 11: //Show current branch history
                m_LogicManager.historyOfActiveBranch();
                break;

            case 12: //Exit
                    m_IsRunning = false;//Bonus 1
                break;

            case 13: //Bonus1
                String repositoryArgs[];
                repositoryArgs = m_InputManager.IOinitRepository();
                m_LogicManager.initRepository(repositoryArgs);
                break;
        }
    }
}
