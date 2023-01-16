package edu.ggc.itec.cutcardscli;

import edu.ggc.itec.cutcardscli.cardcontent.Card;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Scanner;

import static edu.ggc.itec.cutcardscli.GameController.State.*;

// extending Application is a bit of a hack, see comment in the start method below
public class CutCardsCLI extends Application {

    public static final String TOTAL_WINS_FORMAT = "%s Total Wins : %d";
    public static final String WINNER_FMT = "%s Wins";
    public static final String TIE = "Tie";

    String temp;
    //what I created
    private Button playrButton = new Button(temp + " Cuts");
    private Button startOverButton = new Button("Start Over");



    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the two player card cutter!\n");

        System.out.print("Please enter the name of player #1: ");
        String name1 = scanner.nextLine();
        System.out.print("Please enter the name of player #2: ");
        String name2 = scanner.nextLine();

        GameController game = new GameController(name1, name2);
        System.out.print("Enter [C]ut, [Q]uit: ");


        while (scanner.hasNext()) {
            String response = scanner.nextLine();
            switch (response) {
                case "q":
                    System.exit(0);
                    break;
                case "c":
                    Card draw = game.takeTurn();
                    if (game.state == PLAYER_1_COMPLETED) {
                        System.out.println(name1 + " draws " + draw.toString());
                    } else if (game.state == PLAYER_2_COMPLETED) {
                        System.out.println(name2 + " draws " + draw.toString());
                        if (game.winner != null) {
                            System.out.println(String.format(WINNER_FMT, game.winner.name) + "\n");
                        } else {
                            System.out.println(TIE);
                        }
                        game.state = NOT_STARTED;

                        System.out.println(String.format(TOTAL_WINS_FORMAT, game.p1.name, game.p1.numWins));
                        System.out.println(String.format(TOTAL_WINS_FORMAT, game.p2.name, game.p2.numWins));
                    }
                    System.out.print("Enter [C]ut, [Q]uit: ");
            }
        }


    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}

