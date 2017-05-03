package cuaccessibility.dragons_roar.Character.Backgrounds;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import cuaccessibility.dragons_roar.Character.Blueprint.BackgroundGeneral;

public class Soldier extends BackgroundGeneral
{
    public Soldier(){
      this.name = "Soldier";
      this.toolOptions.addAll(Arrays.asList("Gamingset: Cards", "Gamingset: Dice"));
      this.toolProficiencies.addAll(Arrays.asList("Vehicles(Land)", "Gamingset: Dice"/*chooseTool()*/ ));
      //this.languages.addAll(Arrays.asList(""));
      this.skillChoices.addAll(Arrays.asList("Athletics", "Intimidation"));
      this.startingEquipment.addAll(Arrays.asList("Insignia of rank", "Trophy taken from a fallen enemy", (toolProficiencies.get(1) == "Gamingset: Cards" ) ? "Deck of Cards" : "Set of Bone Dice", "Common Clothes", "Pouch of 10 gold"));
      this.backgroundFeature.put("Military Rank", "You have a military rank from your career as a soldier. Soldiers loyal to your former military organization still recognize your authority and influence, and they defer to you if they are of a lower rank. You can invoke your rank to exert influence over other soldiers and requisition simple equipment or horses for temporary use. You can also usually gain access to friendly military encampments and fortresses where your rank is recognized.");
      this.personalityTraits.addAll(Arrays.asList("I'm always polite and respectful.",
                                                  "I’m haunted by memories of war. I can’t get the images of violence out of my mind.",
                                                  "I’ve lost too many friends, and I’m slow to make new ones.",
                                                  "I’m full of inspiring and cautionary tales from my military experience relevant to almost every combat situation.",
                                                  "I can stare down a hell hound without flinching.",
                                                  "I enjoy being strong and like breaking things.",
                                                  "I have a crude sense of humor.",
                                                  "I face problems head-on. A simple, direct solution is the best path to success."));
      this.ideals.addAll(Arrays.asList("Greater Good. Our lot is to lay down our lives in defense of others. (Good)",
                                        "Responsibility. I do what I must and obey just authority. (Lawful)",
                                        "Independence. When people follow orders blindly, they embrace a kind of tyranny. (Chaotic)",
                                        "Might. In life as in war, the stronger force wins. (Evil)",
                                        "Live and Let Live. Ideals aren’t worth killing over or going to war for. (Neutral)",
                                        "Nation. My city, nation, or people are all that matter.(Any)"));
      this.bonds.addAll(Arrays.asList("I would still lay down my life for the people I served with.",
                                      "Someone saved my life on the battlefield. To this day, I will never leave a friend behind.",
                                      "My honor is my life.",
                                      "I’ll never forget the crushing defeat my company suffered or the enemies who dealt it.",
                                      "Those who fight beside me are those worth dying for.",
                                      "I fight for those who cannot fight for themselves."));
      this.flaws.addAll(Arrays.asList("The monstrous enemy we faced in battle still leaves me quivering with fear.",
                                      "I have little respect for anyone who is not a proven warrior.",
                                      "I made a terrible mistake in battle cost many lives and I would do anything to keep that mistake secret.",
                                      "My hatred of my enemies is blind and unreasoning.",
                                      "I obey the law, even if the law causes misery.",
                                      "I’d rather eat my armor than admit when I’m wrong."));
      this.personalityOptions.add(personalityTraits);
      this.personalityOptions.add(ideals);
      this.personalityOptions.add(bonds);
      this.personalityOptions.add(flaws);

      //setPersonality();
      this.personalityTrait = "I can stare down a hell hound without flinching.";
      this.ideal = "Greater Good. Our lot is to lay down our lives in defense of others. (Good)";
      this.bond = "Those who fight beside me are those worth dying for.";
      this.flaw = "I have little respect for anyone who is not a proven warrior.";
    }

    private String chooseTool(){
      System.out.println("Select an Option: ");

      for(int x = 0; x < toolOptions.size(); x = x + 1){
        System.out.println(x+1 + ") " + toolOptions.get(x));
      }

      String choice = "";
      String n = "";

      while(true){
      Scanner reader = new Scanner(System.in);  // Reading from System.in
      n = reader.nextLine(); // Scans the next token of the input as an int.
      try{
        int index = Integer.parseInt(n)-1;
        System.out.println("You have choosen " + toolOptions.get(index));
        choice = toolOptions.get(index);

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

    private String[] choosePersonality(){
      System.out.println("Select an one for each section:");
      String choices[] = new String[4];
      for(int x = 0; x < personalityOptions.size(); x = x + 1){
        int extra = 0;
        for(int y = 0; y < personalityOptions.get(x).size(); y = y+1){
          System.out.println(y+1 + ") " + personalityOptions.get(x).get(y));
          extra = extra + 1;
        }
        System.out.println(extra+1 + ") Random Option");
        String n = "";
        while(true){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        n = reader.nextLine(); // Scans the next token of the input as an int.
        try{
          int index = Integer.parseInt(n)-1;
          if(index == personalityOptions.get(x).size()){
            Random r = new Random();
            int randVal= r.nextInt(Integer.parseInt(String.valueOf(personalityOptions.get(x).size())));
            System.out.println("You have choosen " + personalityOptions.get(x).get(randVal));
            choices[x] = personalityOptions.get(x).get(randVal);
          } else {
          System.out.println("You have choosen " + personalityOptions.get(x).get(index));
          choices[x] = personalityOptions.get(x).get(index);
        }
          break;
        } catch(IndexOutOfBoundsException e){
          System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        } catch (NumberFormatException nfe) {
            System.err.println("Incorrect Input: " + nfe.getMessage());
        } catch (Exception e){
            System.err.println("Incorrect Input: " + e.getMessage());
        }
      }
      }
      return choices;
    }

    private void setPersonality(){
      String choosen[] = new String[4];

      choosen = choosePersonality();
      personalityTrait = choosen[0];
      ideal = choosen[1];
      bond = choosen[2];
      flaw = choosen[3];
    }

    public static void main(String[] args)
  	{

      Soldier me = new Soldier();
      //System.out.println(me.getAbilities());
      System.out.println(me.getName());
      System.out.println(me.getSkillChoices());
      System.out.println(me.getStartingEquipment());
      System.out.println(me.getToolProficiencies());
      System.out.println(me.getLanguages());
      System.out.println(me.getPersonalityTrait());
      System.out.println(me.getIdeal());
      System.out.println(me.getBond());
      System.out.println(me.getFlaw());


    }
}
