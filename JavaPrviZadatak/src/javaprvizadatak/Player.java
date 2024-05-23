/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaprvizadatak;

/**
 *
 * @author MS
 */

public class Player {
    private String name;
    private String ability;
    private String preferredSurface;
    private int atpRank;
    private int atpPoints;
    private boolean injured;


    public Player(String name, String ability, String preferredSurface, int atpRank, int atpPoints, boolean injured) {
        this.name = name;
        this.ability = ability;
        this.preferredSurface = preferredSurface;
        this.atpRank = atpRank;
        this.atpPoints = atpPoints;
        this.injured = injured;
    }

    public int servePointChance(Player opponent, String surface) {
        int probability = 50;
        if (opponent.ability.equals("backhand")) {
            probability -= 8;
        }
        if (ability.equals("forehand")) {
            probability += 10;
        }
        if (ability.equals("serve")) {
            probability += 15;
        }
        if (opponent.ability.equals("serve")) {
            probability -= 5;
        }
        if (ability.equals("mentality")) {
            probability += 10;
        }
        if (opponent.ability.equals("mentality")) {
            probability += 5;
        }
        if (preferredSurface.equals(surface)) {
            probability += 5;
        }
        int rankDifference = opponent.getAtpRank() - atpRank;
        probability -= rankDifference;
        probability = Math.max(0, Math.min(100, probability));

        return probability;
    }

    public String getName() {
        return name;
    }


    public int getAtpRank() {
        return atpRank;
    }

    public void setAtpRank(int atpRank) {
        this.atpRank = atpRank;
    }

    public int getAtpPoints() {
        return atpPoints;
    }

    public void setAtpPoints(int atpPoints) {
        this.atpPoints = atpPoints;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

   

/*
    @Override
    public String toString() {
        return "model.Player{" +
                "name='" + name + '\'' +
                ", ability='" + ability + '\'' +
                ", preferredSurface='" + preferredSurface + '\'' +
                ", atpRank=" + atpRank +
                ", atpPoints=" + atpPoints +
                ", injured=" + injured +
                '}';
    }
*/
}
