package javaprvizadatak;

import java.util.*;

public class AtpFinals extends Tournament {
    private ArrayList<Player> groupA;
    private ArrayList<Player> groupB;
    private ArrayList<Player> semiFinalists;
    private ArrayList<Player> finalists;

    private int[] winsGroupA;
    private int[] winsGroupB;

    public AtpFinals(List<Player> players) {
        super("ATP Finals", "atp", "hard");
        this.groupA = new ArrayList<>();
        this.groupB = new ArrayList<>();
        this.semiFinalists = new ArrayList<>();
        this.finalists = new ArrayList<>();
        this.winsGroupA = new int[players.size()];
        this.winsGroupB = new int[players.size()];
        this.numOfSets = 2;
        for (int i = 0; i < 8; i++) {
            if ((i + 1) % 2 == 0) {
                groupB.add(players.get(i));
            } else {
                groupA.add(players.get(i));
            }
        }
    }

    @Override
    public void play() {
        simulateGroupMatches();
        simulateSemiFinals();
        simulateFinal();
    }

    private void simulateGroupMatches() {
        
        System.out.println();
        System.out.println("Group A:");
        for (Player player : groupA) {
            System.out.println(player.getName());
            player.setInjured(false);
        }
        
        System.out.println();
        System.out.println("Group B:");
        for (Player player : groupB) {
            System.out.println(player.getName());
            player.setInjured(false);
        }
        System.out.println(" ");
        System.out.println("Group A");
        System.out.println(" ");
        simulateGroup(groupA);

        System.out.println("Group B");
        System.out.println(" ");
        simulateGroup(groupB);
    }

    private List<Player> findTopPlayers(ArrayList<Player> group, int numPlayers) {
        List<Player> topPlayers = new ArrayList<>();
        List<Player> sortedPlayers = new ArrayList<>(group);
        int[] winsMap = (group == groupA) ? winsGroupA : winsGroupB;
        sortedPlayers.sort(Comparator.comparingInt(player -> -winsMap[group.indexOf(player)]));
        for (int i = 0; i < numPlayers; i++) {
            if (i < sortedPlayers.size()) {
                topPlayers.add(sortedPlayers.get(i));
            }
        }
        return topPlayers;
    }


    private void simulateGroup(ArrayList<Player> group) {
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                Player player1 = group.get(i);
                Player player2 = group.get(j);
                Player winner = getWinnerIfOpponentIsInjured(player1, player2);
                if (winner == null) {
                    Match match = new Match(player1, player2, this.tourSurface, this.numOfSets);
                    winner = match.playMatch();
                }
                int points = 200;
                if (winner != null) {
                    winner.setAtpPoints(winner.getAtpPoints() + points);
                    if (group == groupA) {
                        winsGroupA[group.indexOf(winner)]++;
                    } else {
                        winsGroupB[group.indexOf(winner)]++;
                    }
                }
            }
        }
    }

    private void simulateSemiFinals() {
        List<Player> semiFinalistsA = findTopPlayers(groupA, 2);
        List<Player> semiFinalistsB = findTopPlayers(groupB, 2);
        semiFinalists.addAll(semiFinalistsA);
        semiFinalists.addAll(semiFinalistsB);
        System.out.println("Semi finalists:");
        for (Player player : semiFinalists) {
            System.out.println(player.getName());
        }
        System.out.println(" ");

        System.out.println("======== Semi finals ========");
        Collections.shuffle(this.semiFinalists);
        for (int i = 0; i < semiFinalists.size(); i += 2) {
            Player player1 = semiFinalists.get(i);
            Player player2 = semiFinalists.get(i + 1);
            Player winner = getWinnerIfOpponentIsInjured(player1, player2);
            if (winner == null) {
                Match match = new Match(player1, player2, this.tourSurface, this.numOfSets);
                winner = match.playMatch();
            }
            int points = 400;
            if (winner != null) {
                winner.setAtpPoints(winner.getAtpPoints() + points);
                finalists.add(winner);
            }

        }

    }

    private void simulateFinal() {
        System.out.println("Finalists:");
        for (Player player : finalists) {
            System.out.println(player.getName());
        }
        System.out.println(" ");
        System.out.println("======== Finals ========");
        Player player1 = finalists.get(0);
        Player player2 = finalists.get(1);
        Player winner = getWinnerIfOpponentIsInjured(player1, player2);
        if (winner == null) {
            Match match = new Match(player1, player2, this.tourSurface, this.numOfSets);
            winner = match.playMatch();
        }
        int points = 500;
        if (winner != null) {
            winner.setAtpPoints(winner.getAtpPoints() + points);
            System.out.println(" ");
            System.out.println("Winner");
            System.out.println(winner.getName());
            System.out.println(" ");

        }


    }

    private Player getWinnerIfOpponentIsInjured(Player player1, Player player2) {
        if (player1.isInjured()) {
            player1.setInjured(true);
            System.out.println(player1.getName() + " has been injured, " + player2.getName() + " is a winner!");
            System.out.println(" ");
            return player2;
        }

        if (player2.isInjured()) {
            player2.setInjured(true);
            System.out.println(player2.getName() + " has been injured, " + player1.getName() + " is a winner!");
            System.out.println(" ");
            return player1;
        }

        Random random1 = new Random();
        int randomNumber1 = random1.nextInt(100 + 1);

        if (randomNumber1 == 1) {
            player1.setInjured(true);
            System.out.println(player1.getName() + " has been injured, " + player2.getName() + " is a winner!");
            System.out.println(" ");
            return player2;
        }
        Random random2 = new Random();
        int randomNumber2 = random2.nextInt(100 + 1);

        if (randomNumber2 == 1) {
            player2.setInjured(false);
            System.out.println(player2.getName() + " has been injured, " + player1.getName() + " is a winner!");
            System.out.println(" ");
            return player1;
        }

        return null;
    }
}
