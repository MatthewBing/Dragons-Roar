package cuaccessibility.dragons_roar.Character.Blueprint;

import java.lang.Math;
import java.util.*;
public abstract class PlayerClass
{
  protected String name;
  protected int level;
  protected int hitDiceValue;
  protected ArrayList<String> armorProficiencies = new ArrayList<String>();
  protected ArrayList<String> weaponProficiencies = new ArrayList<String>();
  protected ArrayList<String> toolProficiencies = new ArrayList<String>();
  protected ArrayList<String> savingThrows = new ArrayList<String>();
  protected ArrayList<String> skillChoices = new ArrayList<String>();
  protected ArrayList<String> startingEquipment = new ArrayList<String>();
  protected int numberOfSkills;

  public String getName(){return name;}

  public int getHitDiceValue(){return hitDiceValue;}

  public int getAverageHitDice(){
    double temp = (double)hitDiceValue;
    return (int)Math.ceil(temp/2);
  }


  public ArrayList<String> getSaves(){
    return savingThrows;
  }

  public ArrayList<String> getArmorProficiencies(){
    return armorProficiencies;
  }

  public ArrayList<String> getWeaponProficiencies(){
    return weaponProficiencies;
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

  public ArrayList<String> chooseSkills(){

    ArrayList<String> tempArray = new ArrayList<String>();
    ArrayList<String> submitArray = new ArrayList<String>();
    tempArray = skillChoices;
    int i = numberOfSkills;
    String n = "";
    while(i > 0){
    System.out.println("Choose "+ i + " of the following unselected skills: ");

    for(int x = 0; x < tempArray.size(); x = x + 1){
      System.out.println(x+1 + ") " + tempArray.get(x));
    }

    while(true){
    Scanner reader = new Scanner(System.in);  // Reading from System.in
    n = reader.nextLine(); // Scans the next token of the input as an int.
    try{
      int index = Integer.parseInt(n)-1;
      System.out.println("You have choosen " + tempArray.get(index));
      submitArray.add(tempArray.get(index));
      tempArray.remove(index);
      //tempArray.set(index, tempArray.get(index) + "[SELECTED]");
      break;
    } catch(IndexOutOfBoundsException e){
      System.err.println("IndexOutOfBoundsException: " + e.getMessage());
    } catch (NumberFormatException nfe) {
        System.err.println("Incorrect Input: " + nfe.getMessage());
    } catch (Exception e){
        System.err.println("Incorrect Input: " + e.getMessage());
    }
  }
  i--;
}

  return submitArray;

  }

  public int getLevel(){
    return level;
  }


}
