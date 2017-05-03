package cuaccessibility.dragons_roar.Character.Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cuaccessibility.dragons_roar.Character.Blueprint.FightingStyles;
import cuaccessibility.dragons_roar.Character.Blueprint.PlayerClass;


public class Fighter extends PlayerClass
{
    private Map<Integer, Map<String, String>> classAbilities; //{Level: {"Ability": "Description"}}

    private ArrayList<String> fightingStyles;

    public Fighter()
    {
      this.name = "Fighter";
      this.level = 1;
      this.hitDiceValue = 10;
      this.armorProficiencies.addAll(Arrays.asList("Light", "Medium", "Heavy", "Shields"));
      this.weaponProficiencies.addAll(Arrays.asList("Simple", "Martial"));
      this.savingThrows.addAll(Arrays.asList("Strength", "Constitution"));
      this.skillChoices.addAll(Arrays.asList("Acrobatics", "Animal Handling", "Athletics", "History", "Insight", "Intimidation", "Perception", "Survival"));

      this.startingEquipment.addAll(Arrays.asList("Chain mail", "Warhammer", "Shield", "Handaxe", "Handaxe", "Explorer's Pack"));

      this.fightingStyles = new ArrayList<String>();
      this.fightingStyles.addAll(Arrays.asList("Archery", "Defense", "Dueling", "Great Weapon Fighting", "Protection", "Two-Weapon Fighting"));

      this.classAbilities = new HashMap<Integer, Map<String, String>>();
      this.classAbilities = generateClassAbilities();

      this.numberOfSkills = 2;
      //chooseSkills();
      this.skillChoices.add("Insight");
      this.skillChoices.add("Perception");
    }


    private Map<Integer, Map<String, String>> generateClassAbilities(){
      Map<Integer, Map<String, String>> abilities = new HashMap<Integer, Map<String, String>>();
      Map<String, String> levelOne = new HashMap<String, String>();
      Map<String, String> levelTwo = new HashMap<String, String>();
      //Level 1
      /*
      String arr[] = chooseFightingStyle();
      levelOne.put("Fighting Style", arr[0] + ": " + arr[1]);
      */
      levelOne.put("Fighting Style", "Protection" + ": " + FightingStyles.getFightingStyleDescription("Protection"));

      levelOne.put("Second Wind", "You have a limited well of stamina that you can draw on to protect yourself from harm. On your turn, you can use a bonus action to regain hit points equal to 1d 10 + your fighter level. Once you use this feature, you must finish a short or long rest before you can use it again.");
      abilities.put(1, levelOne);

      levelTwo.put("Action Surge", "Starting at 2nd level, you can push yourself beyond your  normal limits for a moment. On your turn, you can take  one additional action on top of your regular action and a  possible bonus action. Once you use this feature, you must finish a short or  long rest before you can use it again. Starting at 17th level, you can use it twice before a rest, but only once on the same turn.");
      abilities.put(2, levelTwo);
      return abilities;
  }

  public Map<Integer, Map<String, String>> getAbilities(){
    return classAbilities;
  }


    public String[] chooseFightingStyle(){
        System.out.println("Select an Option: ");

        for(int x = 0; x < fightingStyles.size(); x = x + 1){
          System.out.println(x+1 + ") " + fightingStyles.get(x));
        }

        String choice[] = new String[2];
        String n = "";

        while(true){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        n = reader.nextLine(); // Scans the next token of the input as an int.
        try{
          System.out.println("You have choosen " + fightingStyles.get(Integer.parseInt(n)-1));
          choice = FightingStyles.chooseFightingStyle(fightingStyles.get(Integer.parseInt(n)-1));
          break;
        } catch(IndexOutOfBoundsException e){
          System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        } catch (NumberFormatException nfe) {
            System.err.println("Incorrect Input: " + nfe.getMessage());
        } catch (Exception e){
            System.err.println("Incorrect Input: " + e.getMessage());
        }
      }
        return choice;
    }

    public String getAbilityDecription(String abilityName){
      String description = "";
      for(int i = 1; i <= this.level; i = i + 1){
        Map<String, String> current = classAbilities.get(i);

        if(current.get(abilityName) != null){
          description = current.get(abilityName);
          break;
        }
      }

      if(description == ""){
        System.out.println("Ability not found");
      }
      return description;
    }


    public static void main(String[] args)
  	{

      Fighter me = new Fighter();
      //System.out.println(me.getAbilities());
      System.out.println("Fighting Style: " + me.getAbilityDecription("Fighting Style"));
      System.out.println("Second Wind: "+ me.getAbilityDecription("Second Wind"));
      System.out.println(me.getName());
      System.out.println(me.getSaves());
      System.out.println(me.getArmorProficiencies());
      System.out.println(me.getWeaponProficiencies());
      System.out.println(me.getSkillChoices());
      System.out.println(me.getStartingEquipment());
      System.out.println(me.getToolProficiencies());

      System.out.println(me.chooseSkills());

    }
}
