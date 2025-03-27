public class Pet {
    private int id;
    private String name;
    private int attack;
    private int health;
    private int experience;
    private int cost;
    private int triggers;
    private AbilityType abilityType;
    private Food perk;
    private int tempAttack;
    private int tempHealth;

    public Pet(int id, String name, int attack, int health, int cost, int triggers, AbilityType abilityType) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.cost = cost;
        this.triggers = triggers;
        this.abilityType = abilityType;
        this.perk = null;
        this.tempAttack = 0;
        this.tempHealth = 0;
    }

    public String toString() {
        return name;
    }

    public void consumeFood(Food food) {
        if (food.isPerk()) {
            this.perk = food;
            return;
        } 
    }

    public void increaseAttack(int attack) {
        this.attack += attack;
    }

    public void increaseHealth(int health) {
        this.health += health;
    }

    public void resetTempStats() {
        this.tempAttack = 0;
        this.tempHealth = 0;
    }

    public void increaseTempAttack(int attack) {
        this.tempAttack += attack;
    }

    public void increaseTempHealth(int health) {
        this.tempHealth += health;
    }

    public int getAttack() {
        return attack + tempAttack;
    }

    public int getHealth() {
        return health + tempHealth;
    }

    public Food getPerk() {
        return perk;
    }

    public void hurt(int damage) {
        this.health -= damage;
    }

    public int getId() {
        return id;
    }   

    public int getExperience() {
        return experience;
    }

    public boolean combine(Pet pet) {
        this.attack = Math.max(this.attack, pet.getAttack());
        this.health = Math.max(this.health, pet.getHealth());
        this.attack += 1;
        this.health += 1;
        if (this.experience < 3 || pet.getExperience() < 3 && this.experience + pet.getExperience() >= 3) {
            this.experience += pet.experience;
            return true;
        }

        this.experience += pet.experience;
        return false;
    }
}