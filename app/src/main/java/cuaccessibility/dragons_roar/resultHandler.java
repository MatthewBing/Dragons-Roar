package cuaccessibility.dragons_roar;


import android.content.Context;

import com.google.gson.JsonElement;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import ai.api.model.Metadata;
import cuaccessibility.dragons_roar.Character.Blueprint.CharacterSheet;
import cuaccessibility.dragons_roar.Character.Blueprint.SpellBlueprint.SpellList;

import static java.lang.Integer.valueOf;


public class resultHandler{
    /*
    This class contains methods that:
    1: Take in input from API.AI's JSON results (Handled in the constructor)
    2: Using that information, determine what methods to call from charactersheet.java (This is done via getResponse).
    3: Return that output to AIButton.java to be read via TTS and logged. (getResponse calls methods that return stuff to be read)
    */

    HashMap<String, JsonElement> params;
    Metadata metadata;

    //This is where the current character should be loaded/referenced. For now it makes a new one from the constructor.
    CharacterSheet currentCharacter = new CharacterSheet();
    SpellList spellList;

    public resultHandler(Context voiceButtonContext) throws IOException, JSONException {
        spellList = new SpellList(voiceButtonContext);
    }
    //The constructor here should eventually take a CharacterSheet as an arg, setting it to currentCharacter.

    //end of constructor for resultHandler.



    /*
    getResponse() is what will be called outside of
    this class (from voiceButton) and is what will return the full string.
    This is used because something needs to determine which method to use.
    */

    public String getResponse(HashMap<String, JsonElement> paramsInput, Metadata metadatainput ) throws FileNotFoundException, JSONException {

        /*params is a HashMap and contains the params.
        Each method within this class should be able to access
        the needed parameters in "params" as needed by using params.get(NameOfParameter)
        */
        params = paramsInput;

        /*metadata contains the intent ID, which is used to determine which method to call.*/
        metadata = metadatainput;

        if(metadata != null)
        {
            String command = metadata.getIntentName();

            //This switch statement is what determines the method to call and calls it.
            switch(command){

                case "Roll Dice":
                    return rollDice();

                /*
                Character Info allows access to character information that doesn't change/doesn't change often:
                Race, Height, Age (mostly), Class, Weight, etc
                */
                case "Get Character Info":
                    return characterInfo();

                /*
                This Ability Score section deals with everything involving the basic ability scores:
                Getting and setting the basic numbers, getting the modifiers, and possibly more.
                */
                case "Get Temporary Info":
                    String TempInfoReturning = params.get("TempInfoType").toString().replace("\"","");
                    return Integer.toString(getTempValue(TempInfoReturning));

                case "Modify Temp Value":
                    String ThingToModify = params.get("TempInfoType").toString().replace("\"","");
                    String ActionTaking = params.get("ChangeWords").toString().replace("\"","");
                    String Value = params.get("number-integer").toString().replace("\"","");
                    return ModifyTempValue(ThingToModify, ActionTaking, Value);

                case "Get Ability Score":
                    String AbilityAccessing = params.get("AbilityScore").toString().replace("\"","");
                    return AccessAbilityScores(AbilityAccessing);

                case "Get Skill Proficiency":
                    //Parameters returned from API.AI: SkillName
                    String SkillReturning = params.get("Skills").toString().replace("\"","");
                    return SkillAccess(SkillReturning);


                /*Inventory and Gear section*/

                case "Get Inventory Items":
                    //Parameters returned from API.AI: TypeOfItem
                    return "Inventory Under Development!";

                case "Get Equipped Gear":
                    //Parameters returned from API.AI: SpecificGear


                /* SPELL SECTION */

                case "Cast Spell":
                    return "Spells Under Development!";
                    //Parameters returned from API.AI: SpellName

                case "Spell Lookup":
                    //Parameters returned from API.AI: SpellNames, SpellInfo
                    String lookupType = params.get("SpellInfo").toString().replace("\"","");
                    String spellName = params.get("SpellNames").toString().replace("\"","");
                    return spellInfo(lookupType, spellName);



                //Other cases go here based on the NAME OF THE INTENT in API.AI.

            }
        }
        return "Not a valid command";

    }//end getResponse

    /*This section should begin defining methods that:
    1: Access the information in params and metadata.
    2: Access the data in currentCharacter, passed in by reference from voiceButton.
    3: Generate a full response string that will be sent back to AIButton, which will read it off to the user.
    */

    public String rollDice() {

        //These two lines access params to get the die type and the number of dice to roll.
        String howmanydice = (params.get("NumberOfDice").toString()).replace("\"", "");
        int numOfDice = Integer.valueOf(howmanydice);
        String sides = (params.get("Dice").toString()).replace("\"", "");
        int numOfSides = Integer.valueOf(sides);

        //These three lines are used for the random number generation section.
        Random r = new Random();
        int diceRollTotal = 0;
        int numOfDiceUse = numOfDice;
        //int individualResults[] = new int[numOfDice];

        /*
        Roll the die (numOfDice) times, and each time
        add it to the total for usage, and also
        add it to an array (individualResults) for later accessing if requested.
         */
        int newValue = 0;
        while (numOfDiceUse > 0)
            {
                newValue = r.nextInt(valueOf(numOfSides) + 1);
                diceRollTotal += newValue;
                //individualResults[(numOfDice - numOfDiceUse)] = newValue;
                numOfDiceUse = numOfDiceUse - 1;
            }

            StringBuilder diceRollStringBuild = new StringBuilder("");
            diceRollStringBuild.append(Integer.toString(numOfDice));
            diceRollStringBuild.append("d");
            diceRollStringBuild.append(Integer.toString(numOfSides));
            diceRollStringBuild.append(" = ");
            diceRollStringBuild.append(Integer.toString(diceRollTotal));
            String str = diceRollStringBuild.toString();
            return str;


    }//ends rollDice

    public String characterInfo() {

        String InfoType = (params.get("CharacterInfo").toString()).replace("\"", "");

        if(InfoType.contains("Class"))
            return "Your class is " + currentCharacter.getCharacterClass();

        if(InfoType.contains("Race"))
            return "You are a " + currentCharacter.getRace();

        if(InfoType.contains("Size"))
            return "Your size class is " + currentCharacter.getSize();

        if(InfoType.contains("Name"))
            return "Your name is " + currentCharacter.getCharacterName();

        if(InfoType.contains("Alignment"))
            return "Your alignment is " + currentCharacter.getAlignment();

        if(InfoType.contains("Background"))
            return "Your background is " + currentCharacter.getBackground();

        //case "characterRole":
            //  return "Your job is to " + currentCharacter.getCharacterRole();

        //case "characterAge":
            //  return "You are " + currentCharacter.getCharacterAge();

        return "No info accessible.";
    }//Ends CharacterInfo


    public int getTempValue(String tempInfoType){
        return Integer.parseInt(tempInfoType);
    }

    public String ModifyTempValue(String ThingToModify, String ActionTaking, String Value){
        //In this first case, the user has specified a value. This is likely the most common case, so it goes first.
        if(Integer.parseInt(Value)!=0){
            switch(ActionTaking){
                case "Increase":
                    currentCharacter.setTempValue(ThingToModify, Integer.parseInt(currentCharacter.getTempValue(ThingToModify))+Integer.parseInt(Value));
                case "Decrease":
                    currentCharacter.setTempValue(ThingToModify, Integer.parseInt(currentCharacter.getTempValue(ThingToModify))-Integer.parseInt(Value));
                case "Reset":
                    //??? what to do with "Reset Health to 0"  ????
            }
        }

        //If the user did not say a value, default to 1, or in the case of reset, reset it to it's default value.
        else
            switch(ActionTaking){
                //Increase ThingToModify by 1.
                case "Increasing":
                    currentCharacter.setTempValue(ThingToModify, Integer.parseInt(currentCharacter.getTempValue(ThingToModify)) + 1);
                    return "Increasing " + ThingToModify + " by 1. New value is " + getTempValue(ThingToModify);

                case "Decreasing":
                    currentCharacter.setTempValue(ThingToModify, Integer.parseInt(currentCharacter.getTempValue(ThingToModify)) - 1);
                    return "Decreasing " + ThingToModify + " by 1. New value is " + getTempValue(ThingToModify);

                case "Reset":
                    //Set the ThingToModify to it's default value.
                    //currentCharacter.resetValue(ThingToModify, 0);
                    //DOESNT WORK FOR HEALTH
                    return "Re-setting " + ThingToModify + " is not currently supported!";

        }
        return "Probably shouldn't get here?";
    }

    public String AccessAbilityScores(String Ability){

        if (Ability.contains("Modifier"))
            {
                String ModAbility = Ability.replace(" Modifier", "");
                return "Your " + Ability + " equals " + currentCharacter.getAbilityScoreMod(ModAbility);
            }

        else
            { return "Your " + Ability + " equals " + currentCharacter.getAbilityScore(Ability); }

    }//Ends AccessAbilityScores method

    public String SkillAccess(String Skill){
            String SkillBonus = Skill.replace(" Bonus", "");
            return "Your " + Skill + " equals " + currentCharacter.getSkillBonus(SkillBonus);
    }

    //******************************GETITEM NEEDS IMPLEMENTATION*************
    public String InventoryAccess(String Item){
        if(Item.contains("Count")){ //"How many daggers do I have" ==> "Dagger Count"
            String ItemName = Item.replace(" Count", "");
            return "You have THIS MANY OF THIS ITEM (wip) ";
                    //currentCharacter.getItem(ItemName);
        } else if(Item.contains("Add")){    //"Add 10 gold" ==> "10 Gold Add"
            String addItem = Item.replace(" Add", "");
            String[] parts = addItem.split(" ");
            currentCharacter.addToInventory(parts[1], Integer.parseInt(parts[0]));

            return "You have added " + parts[0] + " " +  parts[1] + " to you inventory";

        } else { //"What is in my inventory" ==> "Inventory Lookup"
            return "stuff";
        }
    }

    public String SaveAccess(String Save){
        //"What is my Strength Save" ==> "Strength Save"
        String SaveAbility = Save.replace(" Save", ")");
        return "Your " + SaveAbility + " save bonus is " + currentCharacter.getSave(SaveAbility);
    }

/*
    public String HealthAccess(String Intent){
        if(Intent.contains("Add")){ //Restore 10 HP ==> Health 10 Add
            String addHealth = Intent.replace(" Add", "");
            String[] parts = addHealth.split(" ");
            currentCharacter.addHealth(Integer.parseInt(parts[1]));
            return "Your health is now at " + currentCharacter.getCurrentHP();

        } else if(Intent.contains("Remove")){ //"Take 10 Damage ==> Heath 10 Remove
            String removeHealth = Intent.replace(" Remove", "");
            String[] parts = removeHealth.split(" ");
            currentCharacter.takeDamage(Integer.parseInt(parts[1]));
            return "Your health is now at " + currentCharacter.getCurrentHP();

        } else if(Intent.contains("Temp")){ //Add 5 Temp HP ==> Heath 10 Temp
            String addTemp = Intent.replace(" Temp", "");
            String[] parts = addTemp.split(" ");
            currentCharacter.setTempHP(Integer.parseInt(parts[1]));
        }

        return "You have " + currentCharacter.getCurrentHP() + " and " + currentCharacter.getTempHP() + " temporary hit points";
    }
    */

    public String spellInfo(String spellInfo, String spellName) throws FileNotFoundException, JSONException {
        return spellList.Spells(spellInfo, spellName);
    }



    /*
    case "Get Skill Proficiency":
                case "Get Equipped Gear":
                        //Parameters returned from API.AI: SpecificGear
                        case "Cast Spell":
                        //Parameters returned from API.AI: SpellName

                        case "Spell Lookup":
                        //Parameters returned from API.AI: SpellName, ???

                        case "Get Temporary Info":
    //Parameters returned from API.AI: TempInfoType
                                */



}
