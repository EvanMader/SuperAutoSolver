public class Food {
    public int id;
    public String name;
    public int cost;
    public boolean perk;

    public Food(int id, String name, int cost, boolean perk) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.perk = perk;
    } 

    public String toString() {
        return name;
    }
}
