public class Food {
    private int id;
    private String name;
    private int cost;
    private boolean perk;

    public Food(int id, String name, int cost, boolean perk) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.perk = perk;
    } 

    public String toString() {
        return name;
    }

    public boolean isPerk() {
        return perk;
    }
}
