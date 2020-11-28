package com.example;

import java.util.Random;

public class Deck {
    public static final int NUMBEROFCARDS = 52;
    private final Card[] deck;
    public int currentCard;
    private Random random;

    public Deck(long seed) {
        deck = new Card[NUMBEROFCARDS];
        int i = 0;

        for (int suit = Card.DIAMOND; suit <= Card.SPADE; suit++)
            for (int rank = 1; rank <= 13; rank++)
                deck[i++] = new Card(suit, rank);

        currentCard = 0;
        random = new Random(seed);
    }
    public Card deal()
    {
        if ( currentCard < NUMBEROFCARDS )
        {
            return ( deck[ currentCard++ ] );
        }
        else
        {
            System.out.println("Out of cards");
            return ( null );
        }
    }
    public boolean canDeal(){
        return currentCard < NUMBEROFCARDS;
    }


    public void shuffle() {
        int i, j, k;
        for (k = 0; k < NUMBEROFCARDS; k++) {
//            i = (int) (NUMBEROFCARDS * Math.random());
//            j = (int) (NUMBEROFCARDS * Math.random());
            i = random.nextInt(NUMBEROFCARDS);
            j = random.nextInt(NUMBEROFCARDS);
            Card tmp = deck[i];
            deck[i] = deck[j];
            deck[j] = tmp;
        }
        currentCard = 0;
    }
}
