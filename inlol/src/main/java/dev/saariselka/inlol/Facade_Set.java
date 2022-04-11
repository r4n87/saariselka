package dev.saariselka.inlol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.saariselka.inlol.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class Facade_Set {

    @Autowired
    SummonerController summonerController;
    @Autowired
    MatchMasterController matchMasterController;
    @Autowired
    TeamController teamController;
    @Autowired
    MatchParticipantController matchParticipantController;
    @Autowired
    MatchBanController matchBanController;
    @Autowired
    MatchObjectivesController matchObjectivesController;

    public void setMatchInfoAtDB(HashMap<String, Object> result) throws JsonProcessingException {

        HashMap<String, Object> matchInfo;

        matchInfo = (HashMap) result.get("body");

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(matchInfo);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject)jsonParser.parse(jsonInString);
        JsonObject jsonObjectForMetadata = (JsonObject) jsonObject.get("metadata");
        JsonObject jsonObjectForInfo = (JsonObject) jsonObject.get("info");
        JsonArray jsonArrayForTeams = (JsonArray)jsonObjectForInfo.get("teams");
        JsonArray jsonArrayForParticipants = (JsonArray)jsonObjectForInfo.get("participants");

        //DB Insert
        matchMasterController.insertMatchMaster(jsonObjectForMetadata.get("dataVersion").toString(),jsonObjectForMetadata.get("matchId").toString(),
                Long.parseLong(jsonObjectForInfo.get("gameCreation").toString()),Long.parseLong(jsonObjectForInfo.get("gameDuration").toString()),
                Long.parseLong(jsonObjectForInfo.get("gameEndTimestamp").toString()),Long.parseLong(jsonObjectForInfo.get("gameId").toString()),
                jsonObjectForInfo.get("gameMode").toString(),jsonObjectForInfo.get("gameName").toString(),
                Long.parseLong(jsonObjectForInfo.get("gameStartTimestamp").toString()),jsonObjectForInfo.get("gameType").toString(),
                jsonObjectForInfo.get("gameVersion").toString(),Integer.parseInt(jsonObjectForInfo.get("mapId").toString()),

                jsonObjectForInfo.get("platformId").toString(),Integer.parseInt(jsonObjectForInfo.get("queueId").toString()),
                jsonObjectForInfo.get("tournamentCode").toString(), 100, 200
                ,Timestamp.valueOf(jsonObjectForInfo.get("rrt").toString()));
      
        for (Object teamsObj : jsonArrayForTeams) {
            JsonObject teamObj = (JsonObject)teamsObj;

            teamController.insertTeamInfo(jsonObjectForMetadata.get("matchId").toString()
                    ,Integer.parseInt(teamObj.get("teamId").toString())
                    ,Boolean.parseBoolean(teamObj.get("win").toString())
                    ,Timestamp.valueOf(teamObj.get("rrt").toString()));

            JsonArray jsonArrayForBans = (JsonArray)teamObj.get("bans");

            for (Object bansObj : jsonArrayForBans) {
                JsonObject banObj = (JsonObject)bansObj;
                matchBanController.insertBanInfo(banObj.get("matchId").toString()
                        ,Integer.parseInt(banObj.get("pickTurn").toString())
                        ,Integer.parseInt(banObj.get("teamId").toString())
                        ,Integer.parseInt(banObj.get("championId").toString())
                        ,Timestamp.valueOf(banObj.get("rrt").toString()));
            }

            JsonObject objectivesObj = (JsonObject)teamObj.get("objectives");
            matchObjectivesController.insertObjectivesInfo(objectivesObj.get("matchId").toString()
                    ,Integer.parseInt(objectivesObj.get("teamId").toString())
                    ,Boolean.parseBoolean(objectivesObj.get("baronFirst").toString())
                    ,Integer.parseInt(objectivesObj.get("baronKills").toString())
                    ,Boolean.parseBoolean(objectivesObj.get("championFirst").toString())
                    ,Integer.parseInt(objectivesObj.get("championKills").toString())
                    ,Boolean.parseBoolean(objectivesObj.get("dragonFirst").toString())
                    ,Integer.parseInt(objectivesObj.get("dragonKills").toString())
                    ,Boolean.parseBoolean(objectivesObj.get("inhibitorFirst").toString())
                    ,Integer.parseInt(objectivesObj.get("inhibitorKills").toString())
                    ,Boolean.parseBoolean(objectivesObj.get("riftheraldFirst").toString())
                    ,Integer.parseInt(objectivesObj.get("riftheraldKills").toString())
                    ,Boolean.parseBoolean(objectivesObj.get("towerFirst").toString())
                    ,Integer.parseInt(objectivesObj.get("towerKills").toString())
                    ,Timestamp.valueOf(objectivesObj.get("rrt").toString()));
        }

        for (Object obj : jsonArrayForParticipants) {
            JsonObject participantObj = (JsonObject)obj;
            matchParticipantController.insertParticipantInfo(
                    participantObj.get("puuid").toString(),
                    jsonObjectForMetadata.get("dataVersion").toString(),
                    jsonObjectForMetadata.get("matchId").toString(),
                    Integer.parseInt(participantObj.get("assists").toString()),
                    Integer.parseInt(participantObj.get("baronKills").toString()),
                    Integer.parseInt(participantObj.get("bountyLevel").toString()),
                    Integer.parseInt(participantObj.get("champExperience").toString()),
                    Integer.parseInt(participantObj.get("champLevel").toString()),
                    Integer.parseInt(participantObj.get("championId").toString()),
                    participantObj.get("championName").toString(),
                    Integer.parseInt(participantObj.get("championTransform").toString()),
                    Integer.parseInt(participantObj.get("consumablesPurchased").toString()),
                    Integer.parseInt(participantObj.get("damageDealtToBuildings").toString()),
                    Integer.parseInt(participantObj.get("damageDealtToObjectives").toString()),
                    Integer.parseInt(participantObj.get("damageDealtToTurrets").toString()),
                    Integer.parseInt(participantObj.get("damageSelfMitigated").toString()),
                    Integer.parseInt(participantObj.get("deaths").toString()),
                    Integer.parseInt(participantObj.get("detectorWardsPlaced").toString()),
                    Integer.parseInt(participantObj.get("doubleKills").toString()),
                    Integer.parseInt(participantObj.get("dragonKills").toString()),
                    Boolean.parseBoolean(participantObj.get("firstBloodAssist").toString()),
                    Boolean.parseBoolean(participantObj.get("firstBloodKill").toString()),
                    Boolean.parseBoolean(participantObj.get("firstTowerAssist").toString()),
                    Boolean.parseBoolean(participantObj.get("firstTowerKill").toString()),
                    Boolean.parseBoolean(participantObj.get("gameEndedInEarlySurrender").toString()),
                    Boolean.parseBoolean(participantObj.get("gameEndedInSurrender").toString()),
                    Integer.parseInt(participantObj.get("goldEarned").toString()),
                    Integer.parseInt(participantObj.get("goldSpent").toString()),
                    participantObj.get("individualPosition").toString(),
                    Integer.parseInt(participantObj.get("inhibitorKills").toString()),
                    Integer.parseInt(participantObj.get("inhibitorTakedowns").toString()),
                    Integer.parseInt(participantObj.get("inhibitorsLost").toString()),
                    Integer.parseInt(participantObj.get("item0").toString()),
                    Integer.parseInt(participantObj.get("item1").toString()),
                    Integer.parseInt(participantObj.get("item2").toString()),
                    Integer.parseInt(participantObj.get("item3").toString()),
                    Integer.parseInt(participantObj.get("item4").toString()),
                    Integer.parseInt(participantObj.get("item5").toString()),
                    Integer.parseInt(participantObj.get("item6").toString()),
                    Integer.parseInt(participantObj.get("itemsPurchased").toString()),
                    Integer.parseInt(participantObj.get("killingSprees").toString()),
                    Integer.parseInt(participantObj.get("kills").toString()),
                    participantObj.get("lane").toString(),
                    Integer.parseInt(participantObj.get("largestCriticalStrike").toString()),
                    Integer.parseInt(participantObj.get("largestKillingSpree").toString()),
                    Integer.parseInt(participantObj.get("largestMultiKill").toString()),
                    Integer.parseInt(participantObj.get("longestTimeSpentLiving").toString()),
                    Integer.parseInt(participantObj.get("magicDamageDealt").toString()),
                    Integer.parseInt(participantObj.get("magicDamageDealtToChampions").toString()),
                    Integer.parseInt(participantObj.get("magicDamageTaken").toString()),
                    Integer.parseInt(participantObj.get("neutralMinionsKilled").toString()),
                    Integer.parseInt(participantObj.get("nexusKills").toString()),
                    Integer.parseInt(participantObj.get("nexusLost").toString()),
                    Integer.parseInt(participantObj.get("nexusTakedowns").toString()),
                    Integer.parseInt(participantObj.get("objectivesStolen").toString()),
                    Integer.parseInt(participantObj.get("objectivesStolenAssists").toString()),
                    Integer.parseInt(participantObj.get("participantId").toString()),
                    Integer.parseInt(participantObj.get("pentaKills").toString()),
                    Integer.parseInt(participantObj.get("physicalDamageDealt").toString()),
                    Integer.parseInt(participantObj.get("physicalDamageDealtToChampions").toString()),
                    Integer.parseInt(participantObj.get("physicalDamageTaken").toString()),
                    Integer.parseInt(participantObj.get("profileIcon").toString()),
                    Integer.parseInt(participantObj.get("quadraKills").toString()),
                    participantObj.get("riotIdName").toString(),
                    participantObj.get("riotIdTagline").toString(),
                    participantObj.get("role").toString(),
                    Integer.parseInt(participantObj.get("sightWardsBoughtInGame").toString()),
                    Integer.parseInt(participantObj.get("spell1Casts").toString()),
                    Integer.parseInt(participantObj.get("spell2Casts").toString()),
                    Integer.parseInt(participantObj.get("spell3Casts").toString()),
                    Integer.parseInt(participantObj.get("spell4Casts").toString()),
                    Integer.parseInt(participantObj.get("summoner1Casts").toString()),
                    Integer.parseInt(participantObj.get("summoner1Id").toString()),
                    Integer.parseInt(participantObj.get("summoner2Casts").toString()),
                    Integer.parseInt(participantObj.get("summoner2Id").toString()),
                    participantObj.get("summonerId").toString(),
                    Integer.parseInt(participantObj.get("summonerLevel").toString()),
                    participantObj.get("summonerName").toString(),
                    Boolean.parseBoolean(participantObj.get("teamEarlySurrendered").toString()),
                    Integer.parseInt(participantObj.get("teamId").toString()),
                    participantObj.get("teamPosition").toString(),
                    Integer.parseInt(participantObj.get("timeCCingOthers").toString()),
                    Integer.parseInt(participantObj.get("timePlayed").toString()),
                    Integer.parseInt(participantObj.get("totalDamageDealt").toString()),
                    Integer.parseInt(participantObj.get("totalDamageDealtToChampions").toString()),
                    Integer.parseInt(participantObj.get("totalDamageShieldedOnTeammates").toString()),
                    Integer.parseInt(participantObj.get("totalDamageTaken").toString()),
                    Integer.parseInt(participantObj.get("totalHeal").toString()),
                    Integer.parseInt(participantObj.get("totalHealsOnTeammates").toString()),
                    Integer.parseInt(participantObj.get("totalMinionsKilled").toString()),
                    Integer.parseInt(participantObj.get("totalTimeCCDealt").toString()),
                    Integer.parseInt(participantObj.get("totalTimeSpentDead").toString()),
                    Integer.parseInt(participantObj.get("totalUnitsHealed").toString()),
                    Integer.parseInt(participantObj.get("tripleKills").toString()),
                    Integer.parseInt(participantObj.get("trueDamageDealt").toString()),
                    Integer.parseInt(participantObj.get("trueDamageDealtToChampions").toString()),
                    Integer.parseInt(participantObj.get("trueDamageTaken").toString()),
                    Integer.parseInt(participantObj.get("turretKills").toString()),
                    Integer.parseInt(participantObj.get("turretTakedowns").toString()),
                    Integer.parseInt(participantObj.get("turretsLost").toString()),
                    Integer.parseInt(participantObj.get("unrealKills").toString()),
                    Integer.parseInt(participantObj.get("visionScore").toString()),
                    Integer.parseInt(participantObj.get("visionWardsBoughtInGame").toString()),
                    Integer.parseInt(participantObj.get("wardsKilled").toString()),
                    Integer.parseInt(participantObj.get("wardsPlaced").toString()),
                    Boolean.parseBoolean(participantObj.get("win").toString()),
                    Timestamp.valueOf(participantObj.get("rrt").toString())
                    );
        }
    }

    public void setSummonerInfoAtDB(HashMap<String, Object> result) {
        // parsing
        LinkedHashMap<String, String> res = (LinkedHashMap) result.get("body");

        // DB Insert
        summonerController.insertSummoner(res.get("accountId"),Integer.parseInt(String.valueOf(res.get("profileIconId"))),
                Long.parseLong(String.valueOf(res.get("revisionDate"))),res.get("name"),res.get("id"),
                Long.parseLong(String.valueOf(res.get("summonerLevel"))),res.get("puuid"));
    }
}
