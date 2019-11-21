package model.characters;

public class Puncher extends Character {
	public Puncher() {
		super();
		super.attackPower = PUNCHER_ATTACK_POWER;
		super.attackTime = PUNCHER_ATTACK_TIME;
	}
	
	@Override
	public String toString() {
		return "Puncher: " + this.healthPoints;
	}
}
