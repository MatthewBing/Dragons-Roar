package cuaccessibility.dragons_roar.Character.Blueprint;
public class CharacterDriver
{
	public static void main(String[] args)
	{
    CharacterSheet myCharacter = new CharacterSheet();

    System.out.println("My Character is "+ myCharacter.getCharacterName());
		System.out.println("My Class is " + myCharacter.getCharacterClass());
    System.out.println("Athletics Bonus: " +myCharacter.getSkillBonus("Athletics"));
		System.out.println("Animal Handling Bonus: " + myCharacter.getSkillBonus("Animal Handling"));
		System.out.println("Passive Perception: "+ myCharacter.getPassiveSkill("Perception"));
		System.out.println("Passive Investigation: "+ myCharacter.getPassiveSkill("Investigation"));
		System.out.println("Inventory: "+ myCharacter.getInventory());
		System.out.println("Tools: "+ myCharacter.getToolProficiencies());
		System.out.println("Weapons: "+ myCharacter.getWeaponProficiencies());
		System.out.println("Armor: "+ myCharacter.getArmorProficiencies());
		System.out.println("Langauges: "+ myCharacter.getLanguages());
	}
}
