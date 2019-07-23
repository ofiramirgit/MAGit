import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Path repositoryPathDirection;

        Scanner in = new Scanner(System.in);
        System.out.println("Please Select:");
        System.out.println("1. initialization");
        System.out.println("\ninsert:1");
        String s = in.nextLine();
        if(s.equals("1"))
        {
            System.out.println("please insert the name of the repository:\n");
            String repositoryName = in.nextLine();
            System.out.println("please insert path of the repository:\n");
            String repositoryPath = in.nextLine();
            System.out.println("Repository Name: "+ repositoryName);
            System.out.println("Repository Path: "+ repositoryPath);
            repositoryPathDirection = Paths.get(repositoryPath);
        }

    }
}
