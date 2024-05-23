/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaprvizadatak;

/**
 *
 * @author MS
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SeasonTournament extends Tournament {
    private ArrayList<Player> roundOf16;
    private ArrayList<Player> quarterFinalists;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;

    public SeasonTournament(String name, String type, String surface) {
        super(name, type, surface);
        this.roundOf16 = this.contestants;
        this.quarterFinalists = new ArrayList<>();
        this.semiFinalists = new ArrayList<>();
        this.finalists = new ArrayList<>();
    }

    @Override
    public void play() {
        for (Player player : this.contestants) {
            player.setInjured(false);
        }
        simulateRoundOf16();
        simulateQuarterFinals();
        simulateSemiFinals();
        simulateFinal();
    }

    private void simulateRoundOf16() {
        System.out.println("========== Round of 16 ==========");
        this.roundOf16 = this.contestants;
        Collections.shuffle(this.roundOf16);
        for (int i = 0; i < contestants.size(); i += 2) {
            Player player1 = contestants.get(i);
            Player player2 = contestants.get(i + 1);
            Player winner = getWinnerIfOpponentIsInjured(player1, player2);
            if (winner == null) {
                Match match = new Match(player1, player2, this.tourSurface, this.numOfSets);
                winner = match.playMatch();
            }
            int points = (this.tourType.equals("Grand Slam")) ? 180 : 100;
            if (winner != null) {
                winner.setAtpPoints(winner.getAtpPoints() + points);
                quarterFinalists.add(winner);
            }
        }

    }

    private Player getWinnerIfOpponentIsInjured(Player player1, Player player2) {
        Random random1 = new Random();
        int randomNumber1 = random1.nextInt(100 + 1);

        if (randomNumber1 == 1) {
            player1.setInjured(true);
            System.out.println(player1.getName() + " has been injured, " + player2.getName() + "goes to the next round!");
            System.out.println(" ");
            return player2;
        }
        Random random2 = new Random();
        int randomNumber2 = random2.nextInt(100 + 1);

        if (randomNumber2 == 1) {
            player2.setInjured(false);
            System.out.println(player2.getName() + " has been injured, " + player1.getName() + "goes to the next round!");
            System.out.println(" ");
            return player1;
        }

        return null;
    }

    private void simulateQuarterFinals() {
        System.out.println("========= Quarter finals =========");
        Collections.shuffle(this.quarterFinalists);
        for (int i = 0; i < quarterFinalists.size(); i += 2) {
            Player player1 = quarterFinalists.get(i);
            Player player2 = quarterFinalists.get(i + 1);
            Player winner = getWinnerIfOpponentIsInjured(player1, player2);
            if (winner == null) {
                Match match = new Match(player1, player2, this.tourSurface, this.numOfSets);
                winner = match.playMatch();
            }
            int points = (this.tourType.equals("Grand Slam")) ? 360 : 200;
            if (winner != null) {
                winner.setAtpPoints(winner.getAtpPoints() + points);
                semiFinalists.add(winner);
            }

        }
    }

    private void simulateSemiFinals() {

        System.out.println("========== Semi finals ==========");
        Collections.shuffle(this.semiFinalists);
        for (int i = 0; i < semiFinalists.size(); i += 2) {
            Player player1 = semiFinalists.get(i);
            Player player2 = semiFinalists.get(i + 1);
            Player winner = getWinnerIfOpponentIsInjured(player1, player2);
            if (winner == null) {
                Match match = new Match(player1, player2, this.tourSurface, this.numOfSets);
                winner = match.playMatch();
            }
            int points = (this.tourType.equals("Grand Slam")) ? 720 : 400;
            if (winner != null) {
                winner.setAtpPoints(winner.getAtpPoints() + points);
                finalists.add(winner);
            }

        }
    }

    private void simulateFinal() {
        System.out.println("============ Finals ============");
        Player player1 = finalists.get(0);
        Player player2 = finalists.get(1);
        Player winner = getWinnerIfOpponentIsInjured(player1, player2);
        if (winner == null) {
            Match match = new Match(player1, player2, this.tourSurface, this.numOfSets);
            winner = match.playMatch();
        }
        int points = (this.tourType.equals("Grand Slam")) ? 2000 : 1000;
        if (winner != null) {
            winner.setAtpPoints(winner.getAtpPoints() + points);
            Player runnerUp = (winner.equals(player1)) ? player2 : player1;
            points = (this.tourType.equals("Grand Slam")) ? 1200 : 650;
            runnerUp.setAtpPoints(points);
        }

    }

}
