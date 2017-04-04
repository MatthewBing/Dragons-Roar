package cuaccessibility.dragons_roar;
import java.util.*;

public class CharacterSheet
{
    private String characterName;
    private String playerName;
    private String characterClass;
    private String race;
    private String background;
    private String size;
    private int experience;
    private String alignment;
    private Map<String, Integer> abilityScores;
    private Map<String, Integer> skills;
    //private Map<String, Integer[]> proficiencies;
    private Map<String, Integer> saves;
    private int armorClass;
    private int speed;
    private int initiative;
    private int maxHitPoints;
    private int currentHitPoints;
    private int tempHitPoints;
    private Map<String, Integer> inventory;
    private int level;
    private int proficiencyBonus;


    public CharacterSheet(){
        skills = new HashMap<String, Integer>();
        saves = new HashMap<String, Integer>();
        abilityScores = new HashMap<String, Integer>();
        inventory = new HashMap<String, Integer>();
        characterName = "Gartag";
        playerName = "Dylan";
        characterClass = "Fighter";
        race = "Golaith";
        background = "Soldier";
        size = "Medium";
        experience = 0;
        alignment = "Neutral Good";

        abilityScores.put("Strength", 16);
        abilityScores.put("Dexterity", 8);
        abilityScores.put("Constitution", 16);
        abilityScores.put("Intelligence", 10);
        abilityScores.put("Wisdom", 13);
        abilityScores.put("Charisma", 16);

        skills.put("Stealth", -1);

        saves.put("Strength", 5);
        saves.put("Dexterity", -1);
        saves.put("Constitution", 5);
        saves.put("Intelligencen", 0);
        saves.put("Wisdom", 1);
        saves.put("Charisma", 0);

        armorClass = 18;
        speed = 30;
        initiative = -1;
        maxHitPoints = 13;
        currentHitPoints = maxHitPoints;
        tempHitPoints = 0;
        inventory.put("Chainmail", 1);
        inventory.put("Warhammer", 1);
        inventory.put("Gold", 10);
        level = 1;
        proficiencyBonus = 2;
    }

    public String getCharacterName(){
        return characterName;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getCharacterClass(){
        return characterClass;
    }

    public String getBackground(){
        return background;
    }

    public String getRace(){
        return race;
    }

    public String getAlignment(){
        return alignment;
    }

    public int getAbilityScore(String ability){
        return abilityScores.get(ability);
    }

    public int getAbilityScoreMod(String ability){
        return (abilityScores.get(ability)-10)/2;
    }

    public void setAbilityScore(String ability, int number){
        abilityScores.put(ability, number);
    }

    public int getSkillBonus(String skill){
        return skills.get(skill);
    }

    /*   public String getProficiencies(String type){
         return proficiencies.get(type);
       }
   */
    public int getSave(String ability){
        return saves.get(ability);
    }

    public int getAC(){
        return armorClass;
    }

    public int getInitiativeBonus(){
        return (abilityScores.get("Dexterity")-10)/2;
    }

    public String getSize(){
        return size;
    }

    public int getSpeed(){
        return speed;
    }

    public int getHPMax(){
        return maxHitPoints;
    }

    public int getCurrentHP(){
        return currentHitPoints;
    }

    public void addHealth(int value){
        currentHitPoints = currentHitPoints + value;

        if(currentHitPoints > maxHitPoints){
            currentHitPoints = maxHitPoints;
        }
    }

    public void takeDamage(int value){
        currentHitPoints = currentHitPoints- value;
        if(currentHitPoints < 0){
            currentHitPoints = 0;
        }
    }

    public void setTempHP(int value){
        tempHitPoints = value;
    }

    public Map<String, Integer> getInventory(){
        return inventory;
    }

    public void addToInventory(String name, int amount){
        int value = inventory.get(name);

        if (value > 0){
            inventory.put(name, inventory.get(name) + amount);
        } else {
            inventory.put(name, amount);
        }

    }

    public void addExperience(int value){
        experience = experience + value;
    }


    public String toString(){
        return "I am " + getCharacterName();
    }


}