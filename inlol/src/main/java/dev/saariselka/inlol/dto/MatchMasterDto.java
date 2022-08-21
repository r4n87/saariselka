package dev.saariselka.inlol.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchMasterDto {

    private String dataVersion;
    private String matchId;
    private long gameCreation;
    private long gameDuration;
    private long gameEndTimeStamp;
    private long gameId;
    private String gameMode;
    private String gameName;
    private long gameStartTimeStamp;
    private String gameType;
    private String gameVersion;
    private int mapId;
    private String platformId;
    private int queueId;
    private String queueType;
    private int teamId1;
    private int teamId2;
    private String tournamentCode;
}
