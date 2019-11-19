package model.characters;

import common.IConstants;

public class Character implements IConstants, IMovementType{
	protected int healthPoints;
	protected int attackPower;
	protected int attackTime;
	
	protected Character() {
		this.healthPoints = CHARACTER_HP;
	}

	protected int getAttackPower() {
		return this.attackPower;
	}

	protected void takeDamage(int pDamage) {
		this.healthPoints -= pDamage;
	}
	
	protected int getAttackTime() {
		return this.attackTime;
	}
	
	protected void setMovementType() {}
	
	public void move() {}
}
