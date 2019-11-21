package model.characters;

import common.GameManager;
import model.Square;
import model.graph.Graph;
import model.graph.GraphNode;
import model.graph.IGraphPathGettable;

import java.util.ArrayList;
import java.util.Random;

public class Team {
    private int player;
    private IGraphPathGettable<Square> pathGetter;
    private ArrayList<Character> members;
    private ArrayList<GraphNode<Square>> path;
    private int actualSquareNum;
    private Team currentObjective;
    private boolean onBattle;
    public Team(IGraphPathGettable<Square> pPathGetter){
        members = new ArrayList<Character>();
        pathGetter = pPathGetter;
        path = null;
        actualSquareNum = 0;
        currentObjective = null;
        onBattle = false;
    }
    public Team(ArrayList<Character> pCharacters,int pActualSquareNum){
        members = pCharacters;
        pathGetter = null;
        path = null;
        actualSquareNum =pActualSquareNum;
        currentObjective = null;
        onBattle = false;
    }

    public GraphNode<Square> getNextMove(){
        try{
            GraphNode<Square> next = path.remove(0);
            actualSquareNum = next.getContents().getId();
            return next;
        }
        catch (Exception e){
            return null;
        }

    }
    /*public void calcPath(Graph<GraphNode<Square>> pGraph,Square pStartPoint,Square pEndPoint){
        path = pathGetter.getPath(pGraph,pStartPoint,pEndPoint);
    }*/
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

    public void setPathGetter(IGraphPathGettable<Square> pathGetter) {
        this.pathGetter = pathGetter;
    }

    public int getActualSquareNum() {
        return actualSquareNum;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public Team getCurrentObjective() {
        return currentObjective;
    }

    public void setCurrentObjective(Team currentObjective) {
        this.currentObjective = currentObjective;
    }

    public boolean isOnBattle() {
        return onBattle;
    }

    public void setOnBattle(boolean onBattle) {
        this.onBattle = onBattle;
    }
}
