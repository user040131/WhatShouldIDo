package seungjub270.whatshouldido.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import seungjub270.whatshouldido.domain.FoodDB;

import java.util.Objects;

@Service
@AllArgsConstructor
public class FoodService {

    public String randomFood(String time) {
        String food = "아무거나 먹어";
        if(Objects.equals(time, "morning")){
            int num = randomNumber(0, 49);
            food = FoodDB.morning.get(num);
        }
        else if(Objects.equals(time, "lunch")){
            int num = randomNumber(0, 49);
            food = FoodDB.lunch.get(num);
        }
        else if(Objects.equals(time, "dinner")){
            int num = randomNumber(0, 49);
            food = FoodDB.dinner.get(num);
        }
        else if(Objects.equals(time, "midnight")){
            int num = randomNumber(0, 49);
            food = FoodDB.midnight.get(num);
        }
        else if(time == null||time.isEmpty()){
            int num = randomNumber(0, 49);
            int num2 = randomNumber(0, 3);
            if(num2 == 0){
                food = FoodDB.morning.get(num);
            } else if(num2 == 1){
                food = FoodDB.lunch.get(num);
            } else if(num2 == 2){
                food = FoodDB.dinner.get(num);
            } else if(num2 == 3){
                food = FoodDB.midnight.get(num);
            }
        }
        return food;
    }

    private int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
