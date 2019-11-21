package model;

import model.characters.Team;

import java.util.ArrayList;

public class Player {
    ArrayList<Team> teams;
    int flagNodeNum;

    public Player(ArrayList<Team> pTeams,int pFlagNodeNum){
        teams = pTeams;
        flagNodeNum = pFlagNodeNum;
    }
}
