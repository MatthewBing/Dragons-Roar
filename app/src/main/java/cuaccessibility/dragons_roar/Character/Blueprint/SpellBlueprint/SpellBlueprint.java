package cuaccessibility.dragons_roar.Character.Blueprint.SpellBlueprint;



public class SpellBlueprint {
    private String spellName;
    private String castingTime;
    private String components;
    private String description;
    private String duration;
    private String range;
    private String school;
    private int level;

    public SpellBlueprint(String name, String castingTime, String components, String description, String duration, String range, String school, int level){
        this.spellName = name;
        this.castingTime = castingTime;
        this.components = components;
        this.description = description;
        this.duration = duration;
        this.range = range;
        this.school = school;
        this.level = level;
    }


    public String getSpellName() { return spellName; }
    public String getCastingTime() { return castingTime; }
    public String getComponents() { return components; }
    public String getDescription() { return description; }
    public String getDuration() { return duration; }
    public String getRange() { return range; }
    public String getSchool() { return school; }
    public int getLevel() { return level; }
    public void setSpellName(String name){ spellName = name; }
    public void setCastingTime(String castTime){ castingTime = castTime; }
    public void setComponents(String comp){ components = comp; }
    public void setDescription(String desc){ description = desc; }
    public void setDuration(String dur){ duration = dur; }
    public void setRange(String r){ range = r; }
    public void setSchool(String s){ school = s; }
    public void setLevel(int l){level = l; }
}
