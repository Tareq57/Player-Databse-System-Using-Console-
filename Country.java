package com.company;

import java.util.ArrayList;

public class Country {
    private ArrayList<Player> players=new ArrayList<>();
    private String name;
    public Country() {
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addPlayer(Player player) {
        players.add(player);
    }
}
