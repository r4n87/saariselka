package dev.saariselka.inlol.dto;

import dev.saariselka.inlol.controller.LeagueEntryController;
import dev.saariselka.inlol.entity.LeagueEntryEntity;
import dev.saariselka.inlol.entity.MatchBanEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LeagueEntryDto_Assembly {
    @Autowired
    LeagueEntryController leagueEntryController;

    public List<LeagueEntryDto> getLeagueEntries_BySummonerId(String summonerId, String queueType) {
        List<LeagueEntryEntity> leagueList = leagueEntryController.getLeagueEntries_ByLeagueEntryId(summonerId, queueType);
        List<LeagueEntryDto> leagueEntryDtoList = new ArrayList<>();

        for(LeagueEntryEntity leagueEntryEntity : leagueList)
        {
            LeagueEntryDto leagueEntryDto = new LeagueEntryDto();
            leagueEntryDto.setLeagueId(leagueEntryEntity.getLeagueId());
            leagueEntryDto.setSummonerId(leagueEntryEntity.getLeagueEntryId().getSummonerId());
            leagueEntryDto.setSummonerName(leagueEntryEntity.getSummonerName());
            leagueEntryDto.setQueueType(leagueEntryEntity.getLeagueEntryId().getQueueType());
            leagueEntryDto.setTier(leagueEntryEntity.getTier());
            leagueEntryDto.setRank(leagueEntryEntity.getRanks());
            leagueEntryDto.setLeaguePoints(leagueEntryEntity.getLeaguePoints());
            leagueEntryDto.setWins(leagueEntryEntity.getWins());
            leagueEntryDto.setLosses(leagueEntryEntity.getLosses());
            leagueEntryDto.setHotStreak(leagueEntryEntity.isHotStreak());
            leagueEntryDto.setVeteran(leagueEntryEntity.isVeteran());
            leagueEntryDto.setFreshBlood(leagueEntryEntity.isFreshBlood());
            leagueEntryDto.setInactive(leagueEntryEntity.isInactive());
            leagueEntryDtoList.add(leagueEntryDto);
        }

        return leagueEntryDtoList;
    }
}
