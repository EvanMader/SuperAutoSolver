public class Pet {
    public int id;
    public String name;
    public int attack;
    public int health;
    public int cost;
    public int triggers;
    public AbilityType abilityType;

    public Pet(int id, String name, int attack, int health, int cost, int triggers, AbilityType abilityType) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.cost = cost;
        this.triggers = triggers;
        this.abilityType = abilityType;
    }

    public String toString() {
        return name;
    }
}