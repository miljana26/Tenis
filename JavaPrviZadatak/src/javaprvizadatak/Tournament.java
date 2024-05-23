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

public abstract class Tournament {
    protected String tourName;
    protected String tourType;
    protected String tourSurface;
    protected boolean playable;
    protected int numOfSets;
    protected ArrayList<Player> contestants;

    public Tournament(String name, String type, String surface) {
        this.tourName = name;
        this.tourType = type;
        this.tourSurface = surface;
        this.playable = true;
        this.numOfSets = (type.equals("Grand Slam")) ? 3 : 2;

    }

    public boolean isPlayable() {
        return playable;
    }


    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public String getTourName() {
        return tourName;
    }

    public void setContestants(ArrayList<Player> contestants) {
        this.contestants = contestants;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tourName='" + tourName + '\'' +
                ", tourType='" + tourType + '\'' +
                ", tourSurface='" + tourSurface + '\'' +
                ", playable=" + playable +
                ", numOfSets=" + numOfSets +
                ", contestants=" + contestants +
                '}';
    }

    public abstract void play();
}