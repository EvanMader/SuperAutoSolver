public class Player {
    private int gold;
    private int health;
    private Team team;
    private Shop shop;

    public Player() {
        this.gold = 10;
        this.health = 6;
        this.team = new Team();
        this.shop = new Shop();
    }
}
