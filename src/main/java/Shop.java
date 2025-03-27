import java.util.ArrayList;

public class Shop {
    private record ShopPet(Pet pet, boolean frozen) {}
    private record ShopFood(Food food, boolean frozen) {}

    private ArrayList<ShopPet> pets;
    private ArrayList<ShopFood> foods;
    private int extraAttack;
    private int extraHealth;

    public Shop() {
        this.pets = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.extraAttack = 0;
        this.extraHealth = 0;
    }

    public void roll(int turn, Pet[][] petList, Food[][] foodList) {
        this.clearShop();

        int shopTier = Math.min(turn/2 - 1, 6);
        int numPets = Math.min(turn/2 + 3, 5) - this.pets.size();
        int numFoods = 2 - this.foods.size();

        for (int i = 0; i < numPets; i++) {
            int randomIndex = (int) (Math.random() * (shopTier * 10));
            Pet randomPet = petList[randomIndex / 10][randomIndex % 10];
            randomPet.increaseAttack(this.extraAttack); 
            randomPet.increaseHealth(this.extraHealth); 
            this.pets.add(new ShopPet(randomPet, false));
        }

        for (int i = 0; i < numFoods; i++) {
            int randomIndex = (int) (Math.random() * (shopTier * 10));
            Food randomFood = foodList[randomIndex / 10][randomIndex % 10];
            this.foods.add(new ShopFood(randomFood, false));
        }
    }

    private void clearShop() {
        for (ShopPet shopPet : this.pets) {
            if (!shopPet.frozen) {
                this.pets.remove(shopPet);
            }
        }

        for (ShopFood shopFood : this.foods) {
            if (!shopFood.frozen) {
                this.foods.remove(shopFood);
            }
        }
    }

    public void increaseShopStats(int attack, int health) {
        this.extraAttack += attack;
        this.extraHealth += health;

        for (ShopPet shopPet : this.pets) {
            shopPet.pet.increaseAttack(attack);
            shopPet.pet.increaseHealth(health);
        }
    }

    public Pet buyShopPet(int index) {
        if (index < 0 || index >= this.pets.size()) {
            throw new IndexOutOfBoundsException("Invalid pet index: " + index);
        }

        Pet pet = this.pets.remove(index).pet;
        return pet;
    }

    public Food buyShopFood(int index) {
        if (index < 0 || index >= this.foods.size()) {
            throw new IndexOutOfBoundsException("Invalid food index: " + index);
        }

        Food food = this.foods.remove(index).food;
        return food;
    }

    public void tierUp() {
        if (this.pets.size() == 0) {
            return;
        }
    }
}