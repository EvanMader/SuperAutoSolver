import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Game {
    public Player player1;
    public Player player2;
    public int round;
    public Pet[][] pets;
    public Food[][] foods;
    
    public static void main(String[] args) {
        Game game = new Game();

        game.startGame();
    }

    public void startGame() {
        player1 = new Player();
        player2 = new Player();
        round = 1;

        this.pets = getPets();
        this.foods = getFoods();

        for (Pet[] petTier : this.pets) {
            System.out.println();
            for (Pet pet : petTier) {
                System.out.println(pet);
            }
        }
        for (Food[] foodTier : this.foods) {
            System.out.println();
            for (Food food : foodTier) {
                System.out.println(food);
            }
        }
    }

    private String readJsonFromFile() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Data.json");
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private Pet[][] getPets() {
        JSONObject json = new JSONObject(readJsonFromFile());
        JSONArray petsArray = json.getJSONArray("Pets");
        
        Pet[][] result = new Pet[petsArray.length()][];
        
        for (int i = 0; i < petsArray.length(); i++) {
            JSONArray tierArray = petsArray.getJSONArray(i);
            result[i] = new Pet[tierArray.length()];
            
            for (int j = 0; j < tierArray.length(); j++) {
                JSONObject petObj = tierArray.getJSONObject(j);
                
                result[i][j] = new Pet(
                    petObj.getInt("id"),
                    petObj.getString("name"),
                    petObj.getInt("attack"),
                    petObj.getInt("health"),
                    petObj.getInt("cost"),
                    petObj.getInt("triggers"),
                    AbilityType.valueOf(petObj.getString("abilityType"))
                );
            }
        }
        
        return result;
    }

    private Food[][] getFoods() {
        JSONObject json = new JSONObject(readJsonFromFile());
        JSONArray foodsArray = json.getJSONArray("Foods");
        
        Food[][] result = new Food[foodsArray.length()][];
        
        for (int i = 0; i < foodsArray.length(); i++) {
            JSONArray tierArray = foodsArray.getJSONArray(i);
            result[i] = new Food[tierArray.length()];
            
            for (int j = 0; j < tierArray.length(); j++) {
                JSONObject foodObj = tierArray.getJSONObject(j);
                
                result[i][j] = new Food(
                    foodObj.getInt("id"),
                    foodObj.getString("name"),
                    foodObj.getInt("cost"),
                    foodObj.getBoolean("perk")
                );
            }
        }
        
        return result;
    }
}