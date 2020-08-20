package com.behavioral.chainofrepsonsibility;

class Creature {
	public String name;
	public int defense, attack;

	public Creature(String name, int defense, int attack) {
		this.name = name;
		this.defense = defense;
		this.attack = attack;
	}

	@Override
	public String toString() {
		return "Creature [name=" + name + ", defense=" + defense + ", attack=" + attack + "]";
	}
}

class CreatureModifier {
	protected Creature creature;
	protected CreatureModifier next;
	
	public CreatureModifier(Creature creature) {
		this.creature = creature;
	}
	
	public void add(CreatureModifier creatureModifier) {
		if (next != null) {
			next.add(creatureModifier);
		} else {
			next = creatureModifier;
		}
	}
	
	public void handle() {
		if (next != null) {
			next.handle();
		}
	}
}

class DoubleAttackModifier extends CreatureModifier {

	public DoubleAttackModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		System.out.println("Doubling " + creature.name + "'s attack");
		creature.attack *= 2;
		super.handle();
	}
}

class IncreaseDefenceModifier extends CreatureModifier {

	public IncreaseDefenceModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		System.out.println("Increasing " + creature.name + "'s defense");
		creature.defense += 3;
		super.handle();
	}
}
 
class NoBonusesModifier extends CreatureModifier {

	public NoBonusesModifier(Creature creature) {
		super(creature);
	}

	@Override
	public void handle() {
		System.out.println("No bonuses for you");
		// we will not call super.handle() to cut the chain of responsibilities
	}
}

public class ChainOfResponsibilityMethodChainDemo {
	public static void main(String[] args) {
		Creature goblin = new Creature("Goblin", 2, 2);
		System.out.println(goblin);
		
		CreatureModifier root = new CreatureModifier(goblin);
		
		// to terminate the chain of responsibility
//		root.add(new NoBonusesModifier(goblin)); 
		
		System.out.println("Lets double goblin's attack...");
		root.add(new DoubleAttackModifier(goblin));
		
		System.out.println("Lets increase goblin's defense...");
		root.add(new IncreaseDefenceModifier(goblin));
		
		root.handle();
		System.out.println(goblin);
	}
}
