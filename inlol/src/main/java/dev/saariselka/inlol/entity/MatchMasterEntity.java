package dev.saariselka.inlol.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="RIOT_MATCH_MASTER")
public class MatchMasterEntity {

    @EmbeddedId
    @Column(nullable = false,unique = true)
    private MatchMasterId matchMasterId;
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
    private Timestamp rrt;

    public MatchMasterEntity(MatchMasterId matchMasterId) {
        this.matchMasterId = matchMasterId;
    }
}
