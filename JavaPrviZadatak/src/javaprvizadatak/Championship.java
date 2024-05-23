/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaprvizadatak;

/**
 *
 * @author MS
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Championship {
    private ArrayList<Player> players;
    private ArrayList<Tournament> tournaments;

    public Championship() {
        this.players = new ArrayList<>();
        this.tournaments = new ArrayList<>();
    }


    public void sortPlayersByAtpPoints() {
        players.sort(new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return Integer.compare(player2.getAtpPoints(), player1.getAtpPoints());
            }
        });
    }

    public void updateAtpRankAndPrint() {
        sortPlayersByAtpPoints();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setAtpRank(i + 1);
        }
        for (Player player : players) {
            System.out.printf("%-2d - %-18s - %d%n", player.getAtpRank(), player.getName(), player.getAtpPoints());
        }
    }

    public void loadFiles() {
        loadPlayers();
        loadTournaments();
    }

    private void loadPlayers() {
        try {
            File file = new File("C:\\Users\\User\\Documents\\razvoj\\Java_zadatak1\\players.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                int atpRank = Integer.parseInt(data[0]);
                String name = data[1];
                String ability = data[2];
                String preferredSurface = data[3];
                int atpPoints = Integer.parseInt(data[4]);
                players.add(new Player(name, ability, preferredSurface, atpRank, atpPoints, false));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private void loadTournaments() {
        try {
            File file = new File("C:\\Users\\User\\Documents\\razvoj\\Java_zadatak1\\tournaments.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                String name = data[0];
                String surface = data[1];
                String type = data[2];
                tournaments.add(new SeasonTournament(name, type, surface));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


    public Tournament getTournamentByTourName(String name) {
        for (Tournament tournament : tournaments) {
            if (tournament.getTourName().equals(name)) {
                return tournament;
            }
        }
        return null; // If tournament with the given name is not found
    }

    // Method to check if a tournament exists
    public boolean tournamentExists(String name) {
        for (Tournament tournament : tournaments) {
            if (tournament.getTourName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
