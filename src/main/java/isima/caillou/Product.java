package isima.caillou;
import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    public String name;
    public String codeBar;
    public Nutriments nutriments;
    public String classe;
    public int scoreNutritionnel;
    public ArrayList<String> qualites = new ArrayList<String>();
    public ArrayList<String> defauts = new ArrayList<String>();

    public Product() {
        nutriments = new Nutriments();
    }

    public void setScore() {
        scoreNutritionnel += (nutriments.energy_100g < 3350) ? (int)(nutriments.energy_100g/335) : 10;

        if((nutriments.energy_100g/335)<=3)
            qualites.add("energie");
        if((nutriments.energy_100g/335)>=7)
            defauts.add("energie");

        scoreNutritionnel += (nutriments.saturated_fat_100g < 10) ? (int)(nutriments.saturated_fat_100g) : 10;

        if(nutriments.saturated_fat_100g<=3)
            qualites.add("graisses saturées");
        if(nutriments.saturated_fat_100g>=7)
            defauts.add("graisses saturées");

        scoreNutritionnel += (nutriments.sugars_100g < 45) ? (int)(nutriments.sugars_100g/4.5) : 10;

        if((nutriments.sugars_100g/4.5)<=3)
            qualites.add("sucres");
        if((nutriments.sugars_100g/4.5)>=7)
            defauts.add("sucres");

        scoreNutritionnel += (nutriments.salt_100g < 0.9) ? (int)(nutriments.salt_100g/0.09) : 10;

        if((nutriments.salt_100g/0.09)<=3)
            qualites.add("sel");
        if((nutriments.salt_100g/0.09)>=7)
            defauts.add("sel");

        scoreNutritionnel -= (nutriments.fiber_100g < 4.7) ? (int)(nutriments.fiber_100g/0.9) : 5;

        if((nutriments.fiber_100g/0.9)>=2)
            qualites.add("fibres");
        if((nutriments.fiber_100g/0.9)<=0)
            defauts.add("fibres");

        scoreNutritionnel -= (nutriments.proteins_100g < 8) ? (int)(nutriments.proteins_100g/1.6) : 5;

        if((nutriments.proteins_100g/8)>=2)
            qualites.add("protéines");
        if((nutriments.proteins_100g/8)<=0)
            defauts.add("protéines");

        if (scoreNutritionnel < 0) {
            classe = "trop bon";
        } else if (scoreNutritionnel < 3) {
            classe = "bon";
        } else if (scoreNutritionnel < 11) {
            classe = "mangeable";
        } else if (scoreNutritionnel < 19) {
            classe = "mouai";
        } else {
            classe = "degueu";
        }
    }
}
