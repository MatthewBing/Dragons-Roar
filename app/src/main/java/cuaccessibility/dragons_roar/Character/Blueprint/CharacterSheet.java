package cuaccessibility.dragons_roar.Character.Blueprint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cuaccessibility.dragons_roar.Character.Backgrounds.Soldier;
import cuaccessibility.dragons_roar.Character.Classes.Fighter;

public class CharacterSheet
{
    private String characterName;
    private String playerName;
    private PlayerClass characterClass;
    private String race;
    private BackgroundGeneral background;
    private String size;
    private int experience;
    private String alignment;
    private Map<String, Integer> abilityScores;
    private Map<String, String> skills;

    private ArrayList<String> toolProf;

    private ArrayList<String> languages;
    private ArrayList<String> weaponProf;
    private ArrayList<String> armorProf;


    private Map<String, Integer> proficiencyMath;
    private Map<String, Integer> saves;
    private int armorClass;
    private int speed;
    private int hitDiceLeft;
    private int currentSpeed;
    private int maxHitPoints;
    private int currentHitPoints;
    private int tempHitPoints;
    private Map<String, Integer> inventory;
    private int level;
    private int proficiencyBonus;
    protected int failedDeathSaves;
    protected int passedDeathSaves;


    public CharacterSheet(){
        skills = new HashMap<String, String>();
        proficiencyMath = new HashMap<String, Integer>();
        saves = new HashMap<String, Integer>();
        abilityScores = new HashMap<String, Integer>();
        inventory = new HashMap<String, Integer>();
        toolProf = new ArrayList<String>();
        languages = new ArrayList<String>();
        weaponProf = new ArrayList<String>();
        armorProf = new ArrayList<String>();
        characterName = "Gartag";
        playerName = "Dylan";
        characterClass = new Fighter();
        race = "Golaith";
        background = new Soldier();
        size = "Medium";
        experience = 0;
        alignment = "Neutral Good";

        abilityScores.put("Strength", 16);
        abilityScores.put("Dexterity", 8);
        abilityScores.put("Constitution", 16);
        abilityScores.put("Intelligence", 10);
        abilityScores.put("Wisdom", 13);
        abilityScores.put("Charisma", 12);

        skills = CharacterEmpty.getDefaultSkills();
        proficiencyMath = CharacterEmpty.getDefaultProficiencies();

        saves = CharacterEmpty.getDefaultSaves();

        armorClass = 18;
        speed = 30;
        maxHitPoints = characterClass.getHitDiceValue();
        currentHitPoints = maxHitPoints;
        tempHitPoints = 0;
        level = characterClass.getLevel();
        proficiencyBonus = 2;

        addStartingInventory();
        addStartingProficiencies();
    }

    public String getCharacterName(){
        return characterName;
    }

    public void setCharacterName(String name){
        characterName = name;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String name){
        playerName = name;
    }

    public String getCharacterClass(){
        return characterClass.getName();
    }

    public String getBackground(){
        return background.getName();
    }

    public String getPersonalityTrait(){
        return background.getPersonalityTrait();
    }

    public String getIdeal(){
        return background.getIdeal();
    }

    public String getBond(){
        return background.getBond();
    }

    public String getFlaw(){
        return background.getFlaw();
    }

    public String getBackgroundFeature(){
        return background.getBackgroundFeature();
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
        return getAbilityScoreMod(skills.get(skill)) + (proficiencyBonus*proficiencyMath.get(skill));
    }

    public int getSkillBonus(String skill, int bonus){
        return getAbilityScoreMod(skills.get(skill)) + (proficiencyBonus*bonus);
    }

    public int getPassiveSkill(String skill){
        return getAbilityScoreMod(skills.get(skill)) + (proficiencyBonus*proficiencyMath.get(skill)) + 10;
    }


    public void addSkillProficiencies(String skill){
        proficiencyMath.put(skill, 1);
    }

    public void addSkillProficiencies(String skill, int bonus){
        proficiencyMath.put(skill, bonus);
    }

    private void addStartingProficiencies(){
        ArrayList<String> backgroundStart = background.getSkillChoices();
        ArrayList<String> classStart = characterClass.getSkillChoices();

        for(int x = 0; x < backgroundStart.size(); x = x+1){
            addSkillProficiencies(backgroundStart.get(x));
        }
        for(int x = 0; x < classStart.size(); x = x+1){
            addSkillProficiencies(classStart.get(x));
        }

        backgroundStart = background.getToolProficiencies();
        classStart = characterClass.getToolProficiencies();

        for(int x = 0; x < backgroundStart.size(); x = x+1){
            addToolProficiencies(backgroundStart.get(x));
        }
        for(int x = 0; x < classStart.size(); x = x+1){
            addToolProficiencies(classStart.get(x));
        }

        classStart = characterClass.getWeaponProficiencies();

        for(int x = 0; x < classStart.size(); x = x+1){
            addWeaponProficiencies(classStart.get(x));
        }

        classStart = characterClass.getArmorProficiencies();

        for(int x = 0; x < classStart.size(); x = x+1){
            addArmorProficiencies(classStart.get(x));
        }

        addLanguage("Common");
        addLanguage("Giant");


    }

    public void addToolProficiencies(String tool){
        toolProf.add(tool);
    }

    public ArrayList<String> getToolProficiencies(){
        return toolProf;
    }

    public void addWeaponProficiencies(String weapon){
        weaponProf.add(weapon);
    }

    public ArrayList<String> getWeaponProficiencies(){
        return weaponProf;
    }

    public void addArmorProficiencies(String armor){
        armorProf.add(armor);
    }

    public ArrayList<String> getArmorProficiencies(){
        return armorProf;
    }

    public void addLanguage(String langauge){
        languages.add(langauge);
    }

    public ArrayList<String> getLanguages(){
        return languages;
    }

    public int getSave(String ability){
        return getAbilityScoreMod(ability) + (proficiencyBonus*saves.get(ability));
    }

    public int getAC(){
        return armorClass;
    }

    public void setAC(int value){
        armorClass = value;
    }

    public void changeAC(int changeValue){
        armorClass += changeValue;
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

    public void setTempValue(String TempName, int Value){
        switch(TempName){
            case "CurrentHealth":
                currentHitPoints = Value;

            case "TempHP":
                tempHitPoints = Value;

            case "Inspiration":
                //Inspiration = Value;

            case "Hit Dice":
                hitDiceLeft = Value;

            case "DeathSaves":
                failedDeathSaves = Value;

            case "CurrentSpeed":
                currentSpeed = Value;

        }
    }
    public String getTempValue(String TempName){
        switch(TempName){
            case "CurrentHealth":
                return Integer.toString(currentHitPoints);

            case "TempHP":
                return Integer.toString(tempHitPoints);

            case "Inspiration":
                //Inspiration = Value;

            case "Hit Dice":
                return Integer.toString(hitDiceLeft);

            case "DeathSaves":
                return Integer.toString(failedDeathSaves);

            case "CurrentSpeed":
                return Integer.toString(currentSpeed);
        }
        return "Error.";
    }

    public int getCurrentHP(){
        return currentHitPoints;
    }

    public int getTempHP() { return tempHitPoints;}

    public void addHealth(int value){
        currentHitPoints = currentHitPoints + value;

        if(currentHitPoints > maxHitPoints){
            currentHitPoints = maxHitPoints;
        }
    }

    public void takeDamage(int value){
        currentHitPoints = currentHitPoints- value;
        if(currentHitPoints <= -maxHitPoints){
            System.out.println("You are dead");
        }
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

    private void addStartingInventory(){
        ArrayList<String> backgroundStart = background.getStartingEquipment();
        ArrayList<String> classStart = characterClass.getStartingEquipment();

        for(int x = 0; x < backgroundStart.size(); x = x+1){
            addToInventory(backgroundStart.get(x));
        }
        for(int x = 0; x < classStart.size(); x = x+1){
            addToInventory(classStart.get(x));
        }

    }

    public void addToInventory(String name){
        if (inventory.get(name) == null){
            inventory.put(name, 1);
        } else {
            inventory.put(name, inventory.get(name) + 1);
        }
    }

    public void addToInventory(String name, int amount){
        if (inventory.get(name) == null){
            inventory.put(name, 1);
        } else {
            inventory.put(name, inventory.get(name) + amount);
        }
    }

    public void removeFromInventory(String name, int amount){
        if (inventory.get(name) != null){
            inventory.put(name, inventory.get(name) - amount);
            if(inventory.get(name) < 0){
                inventory.put(name, 0);
            }
            System.out.println("You have " + inventory.get(name) + " " + name + " left");
        } else {
            System.out.println("Item not in inventory");
        }
    }

    public void addExperience(int value){
        experience = experience + value;
    }

    public void addDeathSave(int value){
        if(value == 1){
            failedDeathSaves = failedDeathSaves + 2;
        } else if(value < 10){
            failedDeathSaves = failedDeathSaves + 1;
        } else if(value == 20){
            failedDeathSaves = 0;
            addHealth(1);
        } else{
            passedDeathSaves = passedDeathSaves + 1;
        }

        if(failedDeathSaves >= 3){
            System.out.println("You have died");
        }

        if(passedDeathSaves >= 3){
            System.out.println("You are stable");
        }
    }


    public String toString(){
        return "I am " + getCharacterName();
    }


}
