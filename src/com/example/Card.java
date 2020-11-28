package com.example;

public class Card {
    public static int DIAMOND = 1;
    public static int CLUB = 2;
    public static int HEART = 3;
    public static int SPADE = 4;

    public int suit;
    public int rank;

    public Card(int suit, int rank){
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isPictureCard(){
        return this.rank > 10;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
