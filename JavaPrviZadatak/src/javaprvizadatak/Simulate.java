/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaprvizadatak;

/**
 *
 * @author MS
 */


import java.util.Scanner;

public class Simulate {
    public static void main(String[] args) {
        
        Championship championship = new Championship();
        championship.loadFiles();
        Scanner input = new Scanner(System.in);
        SeasonalTournaments(input, championship);
        AtpFinals atpFinals = new AtpFinals(championship.getPlayers().subList(0, 8));
        System.out.println();
        System.out.println("Enter anything to begin ATP finals");
        input.nextLine();
        atpFinals.play();
        championship.updateAtpRankAndPrint();
        System.out.println();
        System.out.println(championship.getPlayers().get(0).getAtpRank() + " => " + championship.getPlayers().get(0).getName());
    }

    private static void SeasonalTournaments(Scanner input, Championship championship) {
        boolean validInput = false;

        int numTournaments = 0;
        while (!validInput) {
            try {
                System.out.println("Enter the number of tournaments to be played in the championship:");
                numTournaments = Integer.parseInt(input.nextLine());

                if (numTournaments <= 3 || numTournaments > 13) {
                    System.out.println("Please enter a number that is greater than 3 or lower than 13.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        System.out.println("You have entered " + numTournaments + " tournaments.");


        for (int i = 0; i < numTournaments; i++) {
            System.out.println();
            System.out.println("Enter the name of tournament " + (i + 1) + ":");
            String tournamentName = input.nextLine();
            if (championship.tournamentExists(tournamentName)) {

                Tournament tournament = championship.getTournamentByTourName(tournamentName);
                if (!tournament.isPlayable()) {
                    i--;
                    System.out.println("Tournament has already been played. Please enter a valid tournament name.");
                } else {
                    tournament.setContestants(championship.getPlayers());
                    tournament.play();
                    tournament.setPlayable(false);
                    championship.updateAtpRankAndPrint();
                }

            } else {
                System.out.println("Tournament does not exist. Please enter a valid tournament name.");
                i--;
            }
        }
    }

}
