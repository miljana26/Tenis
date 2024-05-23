/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaprvizadatak;

/**
 *
 * @author MS
 */


import java.util.Random;

public class Match {
    private final Player p1;
    private final Player p2;
    private final String matchSurface;
    private final int winSetNum;
    private final int[] p1ScorePerSet;
    private final int[] p2ScorePerSet;
    private int p1Sets;
    private int p1Gems;
    private int p2Sets;
    private int p2Gems;
    private Random rng;
    private int setNumber;
    private boolean isFirstPlayerTurn;

    public Match(Player player1, Player player2, String surface, int winSets) {
        this.p1 = player1;
        this.p2 = player2;
        this.matchSurface = surface;
        this.winSetNum = winSets;
        this.p1Sets = 0;
        this.p2Sets = 0;
        this.p1Gems = 0;
        this.p2Gems = 0;
        this.p1ScorePerSet = new int[winSets * 2 - 1];//obezbedjivanje dovoljno mesta za belezenje rezultata setova
        this.p2ScorePerSet = new int[winSets * 2 - 1];
        this.setNumber = 0;
        this.rng = new Random();
        this.isFirstPlayerTurn = false;
    }

    private Player getCurrentPlayer() {
        if (this.isFirstPlayerTurn) {
            return p1;
        }
        return p2;
    }

    private Player getAlternatePlayer() {
        if (this.isFirstPlayerTurn) {
            return p2;
        }
        return p1;
    }

    public Player playMatch() {

        while (p1Sets < winSetNum && p2Sets < winSetNum) {
            playSet();
            setNumber++;
        }

        printMatchResult();
        return (p1Sets > p2Sets) ? p1 : p2;
    }

    private void playSet() {
        p1Gems = 0;
        p2Gems = 0;
        while (Math.abs(p1Gems - p2Gems) < 2 || (p1Gems < 6 && p2Gems < 6)) {
            playGame();
            if (p1Gems == 6 && p2Gems == 6) {
                break;
            }
        }
        if (p1Gems == 6 && p2Gems == 6) {
            playTieBreak();
        }
        if (p1Gems > p2Gems) {
            p1Sets++;
        } else {
            p2Sets++;
        }
    }

    private void playGame() {
        this.isFirstPlayerTurn = !this.isFirstPlayerTurn;
        int p1gemScore = 0;
        int p2gemScore = 0;
        boolean isGameOver = false;
        while (!isGameOver) {
            int playerChance = getCurrentPlayer().servePointChance(getAlternatePlayer(), this.matchSurface);
            if (chanceEvent(playerChance)) {
                if (getCurrentPlayer().getName().equals(p1.getName())) {
                    p1gemScore++;
                }
                if (getCurrentPlayer().getName().equals(p2.getName())) {
                    p2gemScore++;
                }
            } else {
                if (getAlternatePlayer().getName().equals(p1.getName())) {
                    p1gemScore++;
                }
                if (getAlternatePlayer().getName().equals(p2.getName())) {
                    p2gemScore++;
                }
            }
            isGameOver = checkIfIsGameOver(p1gemScore, p2gemScore);
        }
        if (p1gemScore > p2gemScore) {
            p1Gems += 1;
            p1ScorePerSet[setNumber] += 1;
        } else {
            p2Gems += 1;
            p2ScorePerSet[setNumber] += 1;
        }
    }

    private boolean checkIfIsGameOver(int p1gemScore, int p2gemScore) {

        if (p1gemScore == 4 && p2gemScore < 3)
            return true;

        if (p2gemScore == 4 && p1gemScore < 3)
            return true;

        if (p1gemScore - p2gemScore == 2 && (p1gemScore >= 3 || p2gemScore >= 3))
            return true;

        if (p2gemScore - p1gemScore == 2 && (p1gemScore >= 3 || p2gemScore >= 3))
            return true;
        
            
        if (p1gemScore == 3 && p2gemScore == 3)
            return false; 

        
        if (p1gemScore >= 4 && p2gemScore >= 4) {
            if (p1gemScore - p2gemScore >= 2 || p2gemScore - p1gemScore >= 2) {
                return true;
            }
        }

        return false;
    }

    private void playTieBreak() {
        int p1TieBreakScore = 0;
        int p2TieBreakScore = 0;
        boolean isTieBreakOver = false;
        while (!isTieBreakOver) {
            this.isFirstPlayerTurn = !this.isFirstPlayerTurn;
            int playerChance = getCurrentPlayer().servePointChance(getAlternatePlayer(), this.matchSurface);
            if (chanceEvent(playerChance)) {
                if (getCurrentPlayer().getName().equals(p1.getName())) {
                    p1TieBreakScore++;
                }
                if (getCurrentPlayer().getName().equals(p2.getName())) {
                    p2TieBreakScore++;
                }
            } else {
                if (getAlternatePlayer().getName().equals(p1.getName())) {
                    p1TieBreakScore++;
                }
                if (getAlternatePlayer().getName().equals(p2.getName())) {
                    p2TieBreakScore++;
                }
            }

            isTieBreakOver = checkIfIsTieBreakOver(p1TieBreakScore, p2TieBreakScore);

        }
        if (p1TieBreakScore > p2TieBreakScore) {
            p1Gems += 1;
            p1ScorePerSet[setNumber] += 1;
        } else {
            p2Gems += 1;
            p2ScorePerSet[setNumber] += 1;
        }

    }

    private boolean checkIfIsTieBreakOver(int p1TieBreakScore, int p2TieBreakScore) {
        if (p1TieBreakScore == 7 && p2TieBreakScore < 6)
            return true;

        if (p2TieBreakScore == 7 && p1TieBreakScore < 6)
            return true;

        if (p1TieBreakScore - p2TieBreakScore == 2 && (p1TieBreakScore >= 6 || p2TieBreakScore >= 6))
            return true;

        if (p2TieBreakScore - p1TieBreakScore == 2 && (p1TieBreakScore >= 6 || p2TieBreakScore >= 6))
            return true;

        return false;
    }

    private boolean chanceEvent(int probability) {
        rng = new Random();
        return rng.nextInt(100) + 1 <= probability;
    }

    public void printMatchResult() {
        StringBuilder gemResultsFirstPlayer = new StringBuilder();
        StringBuilder gemResultsSecondPlayer = new StringBuilder();
        for (int i = 0; i < setNumber; i++) {
            gemResultsFirstPlayer.append(p1ScorePerSet[i]).append(" ");
            gemResultsSecondPlayer.append(p2ScorePerSet[i]).append(" ");
        }
        System.out.printf("%-20s %-10s %-2s%s%n", p1.getName(), gemResultsFirstPlayer, p1Sets, (p1Sets > p2Sets) ? " (Winner)" : "");
        System.out.printf("%-20s %-10s %-2s%s%n", p2.getName(), gemResultsSecondPlayer, p2Sets, (p2Sets > p1Sets) ? " (Winner)" : "");
        System.out.println();
    }
}
