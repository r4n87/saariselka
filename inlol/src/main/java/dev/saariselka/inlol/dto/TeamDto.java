package dev.saariselka.inlol.dto;

import dev.saariselka.inlol.entity.TeamEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private List<MatchBanDto> bans;
    private MatchObjectivesDto objectives;
    private String teamId;
    private String win;
    private int teamKills;
    private List<ParticipantDto> participants;
}
