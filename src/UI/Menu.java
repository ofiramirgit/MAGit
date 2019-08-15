package UI;

public class Menu {

    public static final int MENU_OPTIONS = 14;

    public void printMenu() {
        System.out.print(
                "1.  Change username\n" +
                        "2.  Load from XML\n" +
                        "3.  Switch repository\n" +
                        "4.  Show current commit file system information\n" +
                        "5.  Working copy status\n" +
                        "6.  Commit\n" +
                        "7.  List available branches\n" +
                        "8.  Create new branch\n" +
                        "9.  Delete branch\n" +
                        "10. Check out head branch\n" +
                        "11. Show current branch history\n" +
                        "12. Exit\n" +
                        "13. Bonus1\n" +
                        "14. Bonus2\n");
    }

    public void printInfoMenu(String i_CurrentUser, String i_RepositoryLocation) {
        System.out.println("Magit Menu      " + "Current User: " + i_CurrentUser + "       Current repository location: " + i_RepositoryLocation);
    }
}
