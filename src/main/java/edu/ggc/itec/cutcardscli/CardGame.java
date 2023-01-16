package edu.ggc.itec.cutcardscli;

import edu.ggc.itec.cutcardscli.cardcontent.Card;
import edu.ggc.itec.cutcardscli.cardcontent.Deck;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static edu.ggc.itec.cutcardscli.GameController.State.*;


public class CardGame extends Application {

    //what I created all within reach
    public static final String TOTAL_WINS_FORMAT = "%s Total Wins : %d";
    public static final String WINNER_FMT = "%s Wins";
    public static final String TIE = "Tie";

    //Setting up Back card images
    ImageView p1BackCArd = new ImageView(new Image("C:\\Users\\Saif4\\Downloads\\CutCardsCLI\\src\\main\\java\\edu\\ggc\\itec\\cutcardscli\\card_back.png", 330, 530, false, false));
    ImageView p2BackCArd = new ImageView(new Image("C:\\Users\\Saif4\\Downloads\\CutCardsCLI\\src\\main\\java\\edu\\ggc\\itec\\cutcardscli\\card_back.png", 330, 530, false, false));

    //Set names
    String name1 = "Saif";
    String name2 = "Machine";

    //Objects made for reachable methods
    GameController game = new GameController(name1, name2);
    Deck deck = new Deck();
//    CardValue cardValue = new CardValue();

    //setting my buttons
    Button playrButton = new Button(name1 + " Cuts");
    Button startOverButton = new Button("Start Over");

    //setting my labels
    Label whoWins = new Label(String.format(TOTAL_WINS_FORMAT, game.p1.name, game.p1.numWins));
    Label whoWins2pl = new Label(String.format(TOTAL_WINS_FORMAT, game.p2.name, game.p2.numWins));


    @Override
    public void start(Stage stage) throws Exception {

        //using BorderPane with three Hbox
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(5, 5, 5, 5));

        pane.getChildren().add(getGridPain());
        pane.getChildren().add(getHboxCardsImage());

        pane.setTop(getWinTrackLabel());
        pane.setCenter(getHboxCardsImage());
        pane.setBottom(getGridPain());

        //This displays who wins
        Text text = new Text("Empty ");
        text.setText("");
        text.setFont(new Font("Ariel", 15));
        text.setX(20);
        text.setY(700);
        pane.getChildren().add(text);

        //Joker card Text display
        Text jokerTesxt = new Text();
        jokerTesxt.setFont(new Font("Ariel", 30));
        jokerTesxt.setX(120);
        jokerTesxt.setY(170);
        pane.getChildren().add(jokerTesxt);

        Text jokerTesxt2 = new Text();
        jokerTesxt2.setFont(new Font("Ariel", 30));
        jokerTesxt2.setX(460);
        jokerTesxt2.setY(170);
        pane.getChildren().add(jokerTesxt2);


        //controls for button 1
        playrButton.setOnAction(actionEvent -> {

            //random card is drawn for players
            Card draw = game.takeTurn();


            if (game.state == PLAYER_1_COMPLETED) {

                Image player1CArdDraw = new Image(draw.image().getUrl(), 330, 530, false, false);

                p1BackCArd.setImage(player1CArdDraw);

                //check if either joker pops up and prints custom text
                //I FIXED THIS
                if (draw.image().getUrl().equals("file:images/cards/red_joker_ggc.png") || draw.image().getUrl().equals("file:images/cards/black_joker_ggc.png") ){

                    jokerTesxt.setText("Created by " + "\n" + "\n" +"\n" +"\n" + "\n" + "\n" + "\n" +"\n" +"\n" +       "      Saif");

                }

                playrButton.setText(name2 + " Cut");


            } else if (game.state == PLAYER_2_COMPLETED) {


                Image player2CArdDraw = new Image(draw.image().getUrl(), 330, 530, false, false);

                p2BackCArd.setImage(player2CArdDraw);

                //check if either joker pops up and prints custom text
                //I FIXED THIS
                if (draw.image().getUrl().equals("file:images/cards/red_joker_ggc.png") || draw.image().getUrl().equals("file:images/cards/black_joker_ggc.png")){

                    jokerTesxt2.setText("Created by " + "\n" + "\n" +"\n" +"\n" + "\n" + "\n" + "\n" +"\n" +"\n" +       "      Saif");

                }

                //checks who wins or tie
                if (game.winner != null) {

                    text.setText(String.format(WINNER_FMT, game.winner.name) + "\n");


                } else {

                    text.setText(TIE);

                }

                game.state = NOT_STARTED;

                //If cards = 27 deck get shuffled and message displays
                if(game.p1.numWins + game.p2.numWins == 27){

                    deck.shuffle();

                }

                whoWins.setText(String.format(TOTAL_WINS_FORMAT, game.p1.name, game.p1.numWins));
                whoWins2pl.setText(String.format(TOTAL_WINS_FORMAT, game.p2.name, game.p2.numWins));

                playrButton.setDisable(true);
                startOverButton.setDisable(false);


            }
         });


        //controls for button 2
        startOverButton.setOnAction(actionEvent -> {
            Image image3 = new Image("C:\\Users\\Saif4\\Downloads\\CutCardsCLI\\src\\main\\java\\edu\\ggc\\itec\\cutcardscli\\card_back.png", 330, 530, false, false);
            p1BackCArd.setImage(image3);
            p2BackCArd.setImage(image3);
            startOverButton.setDisable(true);
            playrButton.setDisable(false);
            playrButton.setText(name1 + " Cut");
            text.setText("");

            jokerTesxt.setText("");
            jokerTesxt2.setText("");

        });


        //Create a scene
        Scene scene = new Scene(pane);
        stage.setTitle("Cut Cards!"); // set Title
        stage.setScene(scene); // places the scene in the stage
        stage.show(); // Display the stage


        //listens is user types C or S to correlate to button
        pane.setOnKeyPressed(keyEvent -> {
                    switch (keyEvent.getCode()) {
                        case C:
                            playrButton.fire();
                                break;
                        case S:
                            startOverButton.fire();
                                break;
                        case ESCAPE:
                            System.exit(0);
                                break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + keyEvent.getCode());

                    }
                }
                );


    }


    public static void main(String[] args) {
        launch(args);
    }

    private HBox getGridPain() {

        //setting my buttons
        HBox GRpain = new HBox(550);
        GRpain.setPadding(new Insets(40, 40, 40, 40));

        playrButton.setDisable(false);
        startOverButton.setDisable(true);

        GRpain.getChildren().add(playrButton);
        playrButton.setAlignment(Pos.BOTTOM_LEFT);

        //setting up for next turn
        GRpain.getChildren().add(startOverButton);



        return GRpain;
    }


    private HBox getHboxCardsImage() {

        //setting my back images
        HBox gridPane = new HBox(20);

        gridPane.setPadding(new Insets(20, 5, 5, 20));

        gridPane.getChildren().add(p1BackCArd);
        gridPane.getChildren().add(p2BackCArd);

        return gridPane;

    }


    private HBox getWinTrackLabel() {

        //who wins or tie label, under buttons
        HBox hBoxLabel = new HBox(245);
        hBoxLabel.setPadding(new Insets(20, 5, 5, 20));

        hBoxLabel.getChildren().add(whoWins);
        whoWins.setFont(new Font("Ariel", 15));

        hBoxLabel.getChildren().add(whoWins2pl);
        whoWins2pl.setFont(new Font("Ariel", 15));

        return hBoxLabel;

    }

}
