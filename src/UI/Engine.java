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
            m_Menu.printInfoMenu(m_LogicManager.getActiveUser(),m_LogicManager.getActiveRepository());
            m_Menu.printMenu();
            execute(m_Scanner.nextInt());
        }
    }

    private void execute(Integer i_Option)
    {
        switch(i_Option){
            case -1: //Change username
                m_LogicManager.setActiveUser(m_InputManager.IOsetActiveUser());
                break;
            case 0: //Load from XML
                m_LogicManager.readXML();
                break;
            case 1: //Working copy status
                break;
            case 2: //Show current branch history
                break;
            case 3: //Show current commit file system information
                break;
            case 4: //Commit
                m_LogicManager.makeNewCommit(m_InputManager.IOgetCommitMsg());
                break;
            case 5: //Create new branch
                break;
            case 6: //List available branches
                break;
            case 7: //Check out branch
                break;
            case 8: //Export repository to XML
                break;
            case 9: //Switch repository
                if(!m_LogicManager.setActiveRepository(m_InputManager.getInputActiveRepository()))
                    m_InputManager.printActiveRepositoryNotFound();
                break;
            case 10: //Merge with branch
                break;
            case 11: //Exit
                m_IsRunning = false;
                break;
            case 12: //Bonus 1
                String RepositoryName = EmptyString;
                String RepositoryLocation = EmptyString;
                String RepositoryArgs[];
                RepositoryArgs = m_InputManager.IOinitRepository();

                m_LogicManager.initRepository(RepositoryArgs);
                break;
        }
    }
}
