package cuaccessibility.dragons_roar.Character.Blueprint;
import java.lang.Math;
import java.util.*;
import java.io.*;
import cuaccessibility.dragons_roar.Character.Blueprint.CharacterSheet;

public abstract class BackgroundGeneral{
  protected String name = "General";
  protected ArrayList<String> toolProficiencies = new ArrayList<String>();
  protected ArrayList<String> languages = new ArrayList<String>();
  protected ArrayList<String> skillChoices = new ArrayList<String>();
  protected ArrayList<String> startingEquipment = new ArrayList<String>();
  protected Map<String, String> backgroundFeature = new HashMap<String, String>();
  protected ArrayList<String> personalityTraits = new ArrayList<String>();
  protected ArrayList<String> ideals = new ArrayList<String>();
  protected ArrayList<String> bonds = new ArrayList<String>();
  protected ArrayList<String> flaws = new ArrayList<String>();

  protected ArrayList<String> toolOptions = new ArrayList<String>();
  protected ArrayList<ArrayList<String>> personalityOptions = new ArrayList<ArrayList<String>>();
  protected String personalityTrait = new String();
  protected String ideal = new String();
  protected String bond = new String();
  protected String flaw = new String();

  public String getName(){
    return name;
  }
  public ArrayList<String> getSkillChoices(){
    return skillChoices;
  }

  public ArrayList<String> getStartingEquipment(){
    return startingEquipment;
  }

  public ArrayList<String> getToolProficiencies(){
    return toolProficiencies;
  }

  public ArrayList<String> getLanguages(){
    return languages;
  }

  public String getPersonalityTrait(){
    return personalityTrait;
  }

  public String getIdeal(){
    return ideal;
  }

  public String getBond(){
    return bond;
  }

  public String getFlaw(){
    return flaw;
  }

  public String getBackgroundFeature(){
    Map<String, String> temp = new HashMap<String, String>();
    temp = backgroundFeature;
    return temp.toString();
  }

}
