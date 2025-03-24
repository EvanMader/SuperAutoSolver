public class Player {
    public int gold;
    public int health;
    public Team team;
    public Shop shop;

    public Player() {
        this.gold = 10;
        this.health = 6;
        this.team = new Team();
        this.shop = new Shop();
    }
}
