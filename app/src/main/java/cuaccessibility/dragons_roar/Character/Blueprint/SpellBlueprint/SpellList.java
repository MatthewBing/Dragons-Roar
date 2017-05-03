package cuaccessibility.dragons_roar.Character.Blueprint.SpellBlueprint;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


//Make this class a static hashmap
//Make a new instantiation of this class using
//Have the response methods in here
public class SpellList {
    HashMap<String, SpellBlueprint> spellList;
    JSONObject SpellsJson;
    JSONObject myData;
    Gson gson;
    JsonElement element;
    @SerializedName("Spells")
    private Map<String, JsonElement> Spells;
    HashMap<String, JsonElement> spellListJson;
    AssetManager assetManager;

    public SpellList(Context daContext) throws FileNotFoundException, JSONException {

        assetManager = daContext.getAssets();
        spellList= new HashMap<String, SpellBlueprint>();
        //String x = readJSONFromAsset();
        SpellsJson = new JSONObject(readJSONFromAsset());

        myData = SpellsJson;
        gson = new Gson();
        element = gson.fromJson(myData.toString(), JsonElement.class);


        //Spells = (Map<String, JsonElement>)element;
        spellListJson = jsonToMap(element.toString());

        generateSpells();
    }
    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = assetManager.open("Spells.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public static HashMap<String, JsonElement> jsonToMap(String jsonString) {
        Type type = new TypeToken<HashMap<String, JsonElement>>(){}.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

        public void generateSpells(){
            //Set set = spellListJson.entrySet();

            for (final HashMap.Entry<String, JsonElement> entry : spellListJson.entrySet()) {


               String name = entry.getKey();
               String castingTime = entry.getValue().getAsJsonObject().get("casting_time").toString();
               String components = entry.getValue().getAsJsonObject().get("components").toString();
               String description = entry.getValue().getAsJsonObject().get("description").toString();
               String duration = entry.getValue().getAsJsonObject().get("duration").toString();
               int level = Integer.parseInt(entry.getValue().getAsJsonObject().get("level").toString());
               String range = entry.getValue().getAsJsonObject().get("range").toString();
               String school = entry.getValue().getAsJsonObject().get("school").toString();

               SpellBlueprint spellToAdd = new SpellBlueprint(name, castingTime, components,
                                                description, duration, range,
                                                school, level);
               spellList.put(name, spellToAdd);
            }

        }


    public String Spells(String command, String spellNameSearch) throws JSONException, FileNotFoundException {
        String name = spellList.get(spellNameSearch).getSpellName();
        String castTime = spellList.get(spellNameSearch).getCastingTime();
        String components = spellList.get(spellNameSearch).getComponents();
        String description = spellList.get(spellNameSearch).getDuration();
        String duration = spellList.get(spellNameSearch).getDuration();
        int spellLevel = spellList.get(spellNameSearch).getLevel();
        String range = spellList.get(spellNameSearch).getRange();
        String school = spellList.get(spellNameSearch).getSchool();

    switch(command){

        case "full description":
            return name + ": " + "Casting Time: " + castTime + "Description: " + description + "Duration: " + duration + "Level: " + spellLevel + "Range: " + range + "School: " + school;

        case "Casting time":
            return name + ": " + castTime;

        case "Components":
            return name + ": " + components;

        case "Description":
            return name + ": " + description;

        case "Duration":
            return name + ": " + duration;

        case "Level":
            return name + ": " + spellLevel;

        case "Range":
            return name + ": " + range;

        case "School":
            return name + ": " + school;
        }

        return "Not Found";

    }


}








