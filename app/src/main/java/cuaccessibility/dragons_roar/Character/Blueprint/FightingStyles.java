package cuaccessibility.dragons_roar.Character.Blueprint;
import java.lang.Math;
import java.util.*;

public class FightingStyles{

  static Map<String,String> fightingStyles;

  public static void generateFightingStyles(){
    fightingStyles = new HashMap<String, String>();
    fightingStyles.put("Archery", "You gain a +2 bonus to attack rolls you make with ranged weapons.");
    fightingStyles.put("Defense", "While you are wearing armor, you gain a +1 bonus to AC.");
    fightingStyles.put("Dueling", "When you are wielding a melee w eapon in one hand and no other weapons, you gain a +2 bonus to damage rolls with that weapon.");
    fightingStyles.put("Great Weapon Fighting","When you roll a 1 or 2 on a damage die for an attack you make with a melee weapon that you are wielding with two hands, you can reroll the die and must use the new roll, even if the new roll is a 1 or a 2. The weapon must have the two-handed or versatile property for you to gain this benefit.");
    fightingStyles.put("Protection", "When a creature you can see attacks a target other than you that is within 5 feet of you, you can use your reaction to impose disadvantage on the attack roll. You must be wielding a shield.");
    fightingStyles.put("Two-Weapon Fighting", "When you engage in two-weapon fighting, you can add your ability modifier to the damage of the second attack.");
  }

  public static String getFightingStyleDescription(String styleName){
    generateFightingStyles();
    return (String)fightingStyles.get(styleName);
  }

  public static void addFightingStyle(String name, String description){
    fightingStyles.put(name, description);
  }

  public static String[] chooseFightingStyle(String choice){
      generateFightingStyles();
      String returnVals[] = {choice, (String)fightingStyles.get(choice)};
      return returnVals;
  }


}
