package model.characters;

import model.graph.IGraphPathGettable;

import java.util.ArrayList;
import java.util.Random;

public class Team<T> {
    IGraphPathGettable<T> pathGetter;
    ArrayList<Character> members;

    public Team(IGraphPathGettable<T> pPathGetter){

    }

    public void addMember(java.lang.Character pCharacter){

    }

    public void receiveDamage(int pDamage){
        Random rand = new Random();
        int damagedCharPos = rand.nextInt(members.size());
        members.get(damagedCharPos).takeDamage(pDamage);
        if(members.get(damagedCharPos).isDead()){
            members.remove(damagedCharPos);
        }
    }

    public void dealDamage(Team pOpponentTeam){
        for (Character member:members
             ) {
            pOpponentTeam.receiveDamage(member.getAttackPower());
        }
    }
}