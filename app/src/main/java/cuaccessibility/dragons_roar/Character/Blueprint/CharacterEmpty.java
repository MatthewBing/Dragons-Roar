package cuaccessibility.dragons_roar.Character.Blueprint;
import java.util.HashMap;
import java.util.Map;

public class CharacterEmpty{

  static Map<String, String> skills;
  static Map<String, Integer> proficiencyMath;
  static Map<String, Integer> saves;

  public static Map<String, Integer> getDefaultSaves(){
    saves = new HashMap<String, Integer>();
    saves.put("Strength", 0);
    saves.put("Dexterity", 0);
    saves.put("Constitution", 0);
    saves.put("Intelligence", 0);
    saves.put("Wisdom", 0);
    saves.put("Charisma", 0);

    return saves;
  }

  public static Map<String, String> getDefaultSkills(){
    skills = new HashMap<String, String>();
    skills.put("Acrobatics", "Dexterity");
    skills.put("Animal Handling", "Wisdom");
    skills.put("Arcana", "Intelligence");
    skills.put("Athletics", "Strength");
    skills.put("Deception", "Charisma");
    skills.put("History", "Intelligence");
    skills.put("Insight", "Wisdom");
    skills.put("Intimidation", "Charisma");
    skills.put("Investigation", "Intelligence");
    skills.put("Medicine", "Wisdom");
    skills.put("Nature", "Intelligence");
    skills.put("Perception", "Wisdom");
    skills.put("Performance", "Charisma");
    skills.put("Persuasion", "Charisma");
    skills.put("Religion", "Intelligence");
    skills.put("Sleight of Hand", "Dexterity");
    skills.put("Stealth", "Dexterity");
    skills.put("Survival", "Wisdom");
    skills.put("Strength", "Strength");
    skills.put("Dexterity", "Dexterity");
    skills.put("Constitution", "Constitution");
    skills.put("Intelligence", "Intelligence");
    skills.put("Wisdom", "Wisdom");
    skills.put("Charisma", "Charisma");

    return skills;
  }

  public static Map<String, Integer> getDefaultProficiencies(){
    proficiencyMath = new HashMap<String, Integer>();
    proficiencyMath.put("Acrobatics", 0);
    proficiencyMath.put("Animal Handling", 0);
    proficiencyMath.put("Arcana", 0);
    proficiencyMath.put("Athletics", 0);
    proficiencyMath.put("Deception", 0);
    proficiencyMath.put("History", 0);
    proficiencyMath.put("Insight", 0);
    proficiencyMath.put("Intimidation", 0);
    proficiencyMath.put("Investigation", 0);
    proficiencyMath.put("Medicine", 0);
    proficiencyMath.put("Nature", 0);
    proficiencyMath.put("Perception", 0);
    proficiencyMath.put("Performance", 0);
    proficiencyMath.put("Persuasion", 0);
    proficiencyMath.put("Religion", 0);
    proficiencyMath.put("Sleight of Hand", 0);
    proficiencyMath.put("Stealth", 0);
    proficiencyMath.put("Survival", 0);

    return proficiencyMath;
  }

}
