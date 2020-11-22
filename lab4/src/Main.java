import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String menu = "Menu:\n";
        menu += "\t1. Show the set of states\n";
        menu += "\t2. Show the alphabet\n";
        menu += "\t3. Show the transitions\n";
        menu += "\t4. Show the set of final states\n";
        menu += "\t5. Check a sequence\n";
        menu += "\t0. Exit";
        File f = new File("E:\\Mara\\Faculta\\an III\\Semester I\\FLCD\\labs\\git\\FLCD\\lab4\\src\\FA.in");
        FA fa = new FA();
        fa.read(f);
        System.out.println(fa.getStartStates());
        System.out.println(menu);
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter your choice:");
        int choice = scn.nextInt();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    System.out.println(fa.getStates());
                    break;
                case 2:
                    System.out.println(fa.getAlphabet());
                    break;
                case 3:
                    System.out.println(fa.getTransitions());
                    break;
                case 4:
                    System.out.println(fa.getFinals());
                    break;
                case 5:
                    System.out.println("Enter the sequence:");
                    String seq = scn.next();
                    boolean isA = fa.isAccepted(seq);
                    if (isA)
                        System.out.println("The sequence is accepted");
                    else
                        System.out.println("The sequence is NOT accepted");
                    break;
                default:
                    System.out.println("The choice does not exist!");
                    break;
            }
            System.out.println("Enter your choice:");
            choice = scn.nextInt();
        }
    }
}
