package com.example;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Card[] dealtCards;
    private static Deck deck;

    public static void main(String[] args) {
            game(1L); //Simulating Game Lost Condition
            game(3L); //Simulating Game Win Condition
            game(34L); //Simulating Game Lose Condition When remaining deck is empty
    }

    public static void game(long seed) {
        dealtCards = new Card[9];
        deck = new Deck(seed);
        deck.shuffle();
        for (int i = 0; i < dealtCards.length; i++) {
            dealtCards[i] = deck.deal();
        }

        int flag = removeMatches();
        while (flag == 1) {
            flag = removeMatches();
        }
        if (flag == 0) {
            System.out.println("Game Lost: Stalemate");
        } else {
            System.out.println("No Cards Remaining");
            System.out.println("Trying to match cards in hand.");
            int f = tryFinalMatch();
            while (f == 1) {
                f = tryFinalMatch();
            }
            int nullCount = 0;
            for(Card c: dealtCards){
                if(c == null){
                    nullCount++;
                }
            }
            if(nullCount == 9){
                System.out.println("Congratulations!!! Game Won");
            } else {
                System.out.println("Game Lost: Stalemate");
            }
        }
        System.out.println("Game Finished");
        System.out.println("---------------------------------------------------------");
    }

    public static int findBreakIndex() {
        for (int i = 0; i < dealtCards.length; i++) {
            if (dealtCards[i] == null || dealtCards[i].isPictureCard()) {
                return i;
            }
        }
        return dealtCards.length;
    }

    public static int removeMatches() {
        Arrays.sort(dealtCards, Comparator.comparingInt(card -> card.rank));
//        System.out.println(Arrays.toString(dealtCards));
        int breakIndex = findBreakIndex();
        int startCard = deck.currentCard;
        for (int i = 0; i < breakIndex; i++) {
            for (int j = i + 1; j < breakIndex; j++) {
                int sum = dealtCards[i].rank + dealtCards[j].rank;
                if (sum == 11) {
                    Card[] toRemove = new Card[2];
                    if (deck.canDeal()) {
                        toRemove[0] = dealtCards[i];
                        dealtCards[i] = deck.deal();
                    } else {
                        return -1;
                    }
                    if (deck.canDeal()) {
                        toRemove[1] = dealtCards[j];
                        dealtCards[j] = deck.deal();
                        System.out.println(toRemove[0].rank + " and " + toRemove[1].rank + " removed");
                    } else {
                        return -1;
                    }
                }
            }
        }
        for (int i = breakIndex; i < dealtCards.length; i++) {
            for (int j = breakIndex + 1; j < dealtCards.length; j++) {
                for (int k = j + 1; k < dealtCards.length; k++) {
//                    if(dealtCards[i] == null || dealtCards[j] == null || dealtCards[k] == null){
//                        System.out.println("No Cards Remaining");
//                        return -1;
//                    }
                    int sum = dealtCards[i].rank + dealtCards[j].rank + dealtCards[k].rank;
                    if (sum == 36) {
                        if (deck.canDeal()) {
                            dealtCards[i] = deck.deal();
                        } else {
                            return -1;
                        }
                        if (deck.canDeal()) {
                            dealtCards[j] = deck.deal();
                        } else {
                            return -1;
                        }
                        if (deck.canDeal()) {
                            dealtCards[k] = deck.deal();
                            System.out.println("Jack, Queen and King removed");
                        } else {
                            return -1;
                        }
                    }
                }
            }
        }
        if (startCard == deck.currentCard) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int tryFinalMatch() {
        Arrays.sort(dealtCards, Comparator.comparingInt(card -> {
            if (card == null)
                return 52;
            return card.rank;
        }));
        int breakIndex = findBreakIndex();
        int count = 0;
        for (int i = 0; i < breakIndex; i++) {
            for (int j = i + 1; j < breakIndex; j++) {
                if (dealtCards[i] == null || dealtCards[j] == null) {
                    break;
                }
                int sum = dealtCards[i].rank + dealtCards[j].rank;
                if (sum == 11) {
                    Card[] toRemove = new Card[2];
                    toRemove[0] = dealtCards[i];
                    dealtCards[i] = null;
                    toRemove[1] = dealtCards[j];
                    dealtCards[j] = null;
                    count = 1;
                    System.out.println(toRemove[0].rank + " and " + toRemove[1].rank + " removed");
                }
            }
        }
        for (int i = breakIndex; i < dealtCards.length; i++) {
            for (int j = breakIndex + 1; j < dealtCards.length; j++) {
                for (int k = j + 1; k < dealtCards.length; k++) {
                    if (dealtCards[i] == null || dealtCards[j] == null || dealtCards[k] == null) {
                        break;
                    }
                    int sum = dealtCards[i].rank + dealtCards[j].rank + dealtCards[k].rank;
                    if (sum == 36) {
                        dealtCards[i] = null;
                        dealtCards[j] = null;
                        dealtCards[k] = null;
                        count = 1;
                        System.out.println("Jack, Queen and King removed");
                    }
                }
            }
        }
        return count;
    }
}