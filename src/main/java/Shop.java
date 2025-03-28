import java.util.ArrayList;

public class Shop {
    private record ShopPet(Pet pet, boolean frozen) {}
    private record ShopFood(Food food, boolean frozen) {}

    private ArrayList<ShopPet> pets;
    private ArrayList<ShopFood> foods;
    private int extraAttack;
    private int extraHealth;
    public Pet[][] petList;
    public Food[][] foodList;

    public Shop(Pet[][] petList, Food[][] foodList) {
        this.pets = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.extraAttack = 0;
        this.extraHealth = 0;
        this.petList = petList;
        this.foodList = foodList;
    }

    public void roll(int turn) {
        this.clearShop();

        int shopTier = Math.min(turn/2 - 1, 6);
        int numPets = Math.min(turn/2 + 3, 5) - this.pets.size();
        int numFoods = 2 - this.foods.size();

        for (int i = 0; i < numPets; i++) {
            int randomIndex = (int) (Math.random() * (shopTier * 10));
            Pet randomPet = Pet.copy(this.petList[randomIndex / 10][randomIndex % 10]);
            randomPet.increaseAttack(this.extraAttack); 
            randomPet.increaseHealth(this.extraHealth); 
            this.pets.add(new ShopPet(randomPet, false));
        }

        for (int i = 0; i < numFoods; i++) {
            int randomIndex = (int) (Math.random() * (shopTier * 10));
            Food randomFood = Food.copy(this.foodList[randomIndex / 10][randomIndex % 10]);
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

    public void tierUp(Pet[] tierUpPets) {
        for (int i = 0; i < 2; i++) {
            int randomIndex = (int) (Math.random() * tierUpPets.length);
            Pet newPet = Pet.copy(tierUpPets[randomIndex]);
            newPet.increaseAttack(this.extraAttack);
            newPet.increaseHealth(this.extraHealth);
            this.pets.add(new ShopPet(newPet, false));

            if (!this.shopOverFull()) break;
            for (int j = this.foods.size() - 1; j >= 0; j--) {
                if (this.foods.get(j).frozen) continue;
                this.foods.remove(j);
                break;
            }
            
            if (!this.shopOverFull()) break;
            for (int j = this.pets.size() - 1; j >= 0; j--) {
                if (this.pets.get(j).frozen) continue;
                this.pets.remove(j);
                break;
            }
        }
    }

    private boolean shopOverFull() {
        if (this.pets.size() + this.foods.size() > 9) return true;
        return false;
    }
}