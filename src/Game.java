import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        //deciding how many players phase;
        boolean keeploop;
        int numPlayers = 0;
        do {
            try {
                do {
                keeploop = false;
                Scanner input = new Scanner(System.in);
                System.out.print("Welcome to Mall of Horror, Please select how many players (4~6)?");
                numPlayers = input.nextInt();
                if (numPlayers > 6 || numPlayers < 4) {
                    System.out.println("Please enter a number between 4 to 6");
                }
                }
                while (numPlayers> 6 || numPlayers < 4);

            } catch (Exception e) {
                System.out.println("Please enter a number.");
                keeploop = true;
            }
        }
        while (keeploop);


        //lauching the a new game broad with decided palyers
        GameBroad gameBroad = new GameBroad(numPlayers);
        System.out.println("There will be " + numPlayers +  " players in this game.");

        System.out.println();

    }
}
