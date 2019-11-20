package model.characters;

import model.Square;
import model.graph.IGraphPathGettable;

import java.util.ArrayList;
import java.util.Random;

public class Team {
    IGraphPathGettable<Square> pathGetter;
    ArrayList<Character> members;

    public Team(IGraphPathGettable<Square> pPathGetter){
        members = new ArrayList<Character>();
        pathGetter = pPathGetter;
    }

    public void addMember(Character pCharacter){
        members.add(pCharacter);
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

    public boolean isEmpty(){
        return members.isEmpty();
    }

}
