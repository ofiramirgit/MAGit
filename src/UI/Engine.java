package UI;
import Logic.LogicManager;
import java.util.Scanner;

public class Engine
{
    private Boolean m_IsRunning = true;
    private Scanner m_Scanner = new Scanner(System.in);
    private LogicManager LM = new LogicManager();


    public void run()
    {
        while(m_IsRunning)
        {
            Menu.printMenu();
            execute(m_Scanner.nextInt());
        }
    }

    private void execute(Integer i_Option)
    {
        switch(i_Option){
            case -1: //Change username
                break;
            case 0: //Load from XML
                break;
            case 1: //Working copy status
                break;
            case 2: //Show current branch history
                break;
            case 3: //Show current commit file system information
                break;
            case 4: //Commit
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
                break;
            case 10: //Merge with branch
                break;
            case 11: //Exit
                m_IsRunning = false;
                break;

        }
    }
}
