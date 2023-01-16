package edu.ggc.itec.cutcardscli;

import edu.ggc.itec.cutcardscli.cardcontent.Card;

public class Player {

    Card lastCard;
    public int numWins;
    public String name;

    public Player(String name) {
        this.numWins = 0;
        this.lastCard = null;
        this.name = name;
    }

    public void incrementWins() {
        numWins++;
    }
}
