package dev.saariselka.inlol.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.saariselka.inlol.controller.SummonerSpellController;
import dev.saariselka.inlol.entity.ChampionEntity;
import dev.saariselka.inlol.entity.SummonerSpellEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

// TODO : exception 부분 리팩토링 필요
public class JsonParserForLOL {

    @Autowired
    SummonerSpellController summonerSpellController;

    public static List<ChampionEntity> getChampionEntities() {
        ClassPathResource resource = new ClassPathResource("json/champions.json");
        JsonObject jsonObject = null;

        try {
            jsonObject = JsonParser.parseReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ChampionEntity> championEntities = new ArrayList<>();

        JsonObject jsonChampions = (JsonObject) jsonObject.get("data");
        Set<String> keys = jsonChampions.keySet();

        for(String key : keys) {
            JsonObject champion = (JsonObject) jsonChampions.get(key);

            int id = champion.get("key").getAsInt();
            String nameEng = champion.get("id").getAsString();
            String nameKo = champion.get("name").getAsString();

            JsonObject image = (JsonObject) champion.get("image");
            String imagePath = image.get("full").getAsString();

            championEntities.add(new ChampionEntity(id, nameEng, nameKo, imagePath));
        }

        return championEntities;
    }

    public static String getSpellImageBySpellId(int spellId) {
        if(spellId == 0) return "bot";

        ClassPathResource summonerResource = new ClassPathResource("json/summoner.json");
        JsonObject summonerJson = null;

        try {
            summonerJson = JsonParser.parseReader(new InputStreamReader(summonerResource.getInputStream(), StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject summonerJsonObject = (JsonObject) summonerJson.get("data");
        Set<String> keys = summonerJsonObject.keySet();

        for(String key : keys) {
            JsonObject summonerSpell = (JsonObject) summonerJsonObject.get(key);

            if(spellId == summonerSpell.get("key").getAsInt()) {
                return ((JsonObject) summonerSpell.get("image")).get("full").getAsString();
            }
        }

        return null;
    }
    public List<SummonerSpellEntity> getSummonerSpellEntities() {
        ClassPathResource summonerResource = new ClassPathResource("json/summoner.json");
        JsonObject summonerJson = null;

        try {
            summonerJson = JsonParser.parseReader(new InputStreamReader(summonerResource.getInputStream(), StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject summonerJsonObject = (JsonObject) summonerJson.get("data");
        Set<String> keys = summonerJsonObject.keySet();

        List<SummonerSpellEntity> summonerSpellEntities = new ArrayList<>();

        for(String key : keys) {
            JsonObject summonerSpell = (JsonObject) summonerJsonObject.get(key);

            String name = summonerSpell.get("name").getAsString();
            String description = summonerSpell.get("description").getAsString();
            int spellKey = summonerSpell.get("key").getAsInt();
            String image = ((JsonObject) summonerSpell.get("image")).get("full").getAsString();

            SummonerSpellEntity summonerSpellEntity = new SummonerSpellEntity(name, description, spellKey, image);

            summonerSpellEntities.add(summonerSpellEntity);
        }

        return summonerSpellEntities;
    }

    public static String getRuneIconImageByPerkStyle(String type, int styleId, int perkId) {
        ClassPathResource runeImages = new ClassPathResource("json/runes.json");
        JsonArray runeJsonArray = null;
        String iconPath = "";

        try {
            runeJsonArray = JsonParser.parseReader(new InputStreamReader(runeImages.getInputStream(), StandardCharsets.UTF_8)).getAsJsonArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(runeJsonArray == null) return "";

        for(Object obj : runeJsonArray) {
            JsonObject runeObj = (JsonObject) obj;
            if(runeObj.get("id").getAsInt() == styleId) {
                if("sub".equals(type)) {
                    iconPath = runeObj.get("icon").getAsString();
                    break;
                }

                JsonArray detailRuneJsonArray = runeObj.get("slots").getAsJsonArray();
                for(Object detailObj : detailRuneJsonArray) {
                    JsonObject detailRuneObj = (JsonObject) detailObj;
                    JsonArray lastDepthRuneJsonArray = detailRuneObj.get("runes").getAsJsonArray();

                    for(Object lastDepthObj : lastDepthRuneJsonArray) {
                        JsonObject lastDepthRuneObj = (JsonObject) lastDepthObj;
                        if(lastDepthRuneObj.get("id").getAsInt() == perkId) {
                            iconPath =  lastDepthRuneObj.get("icon").getAsString();
                        }
                    }

                }
            }
        }

        return iconPath;
    }
}
