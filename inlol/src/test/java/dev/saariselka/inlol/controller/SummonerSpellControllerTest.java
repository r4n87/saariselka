package dev.saariselka.inlol.controller;

import dev.saariselka.inlol.entity.SummonerSpellEntity;
import dev.saariselka.inlol.service.SummonerSpellService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SummonerSpellControllerTest {

    @Autowired
    private SummonerSpellController summonerSpellController;
    @Autowired
    private SummonerSpellService summonerSpellService;


    @Test
    @DisplayName("Insert Entity")
    public void insertSummonerSpell() {
        // given
        String name = "testSummonerSpellName";
        String description = "testSummonerSpellDesc";
        int spellKey = 0;
        String image = "testSummonerSpellImage";

        // when
        summonerSpellController.insertSummonerSpell(name, description, spellKey, image);

        // then
        SummonerSpellEntity summonerSpellEntitySaved = summonerSpellService.findByspellKey(spellKey).get(0);

        assertThat(spellKey).isEqualTo(summonerSpellEntitySaved.getSpellKey());
        assertThat(summonerSpellEntitySaved.getSpellKey()).isNotNull();
    }

    @Test
    @DisplayName("Insert Entities")
    public void insertAllSummonerSpell() {
        // given
        String name = "testSummonerSpellName";
        String description = "testSummonerSpellDesc";
        int spellKey = 0;
        String image = "testSummonerSpellImage";

        List<SummonerSpellEntity> summonerSpellEntities = new ArrayList<>();
        SummonerSpellEntity summonerSpellEntity = new SummonerSpellEntity(name,description,spellKey,image);
        summonerSpellEntities.add(summonerSpellEntity);

        // when
        summonerSpellController.insertAllSummonerSpell(summonerSpellEntities);

        // then
        SummonerSpellEntity summonerSpellEntitySaved = summonerSpellService.findByspellKey(spellKey).get(0);

        assertThat(spellKey).isEqualTo(summonerSpellEntitySaved.getSpellKey());
        assertThat(summonerSpellEntitySaved.getSpellKey()).isNotNull();
    }

    @Test
    @DisplayName("Find Entity By Key")
    public void getSummonerSpellByKey() {
        // given
        String summonerSpellNameBarrier = "?????????";
        String summonerSpellNameBooster = "??????";
        String summonerSpellDescBarrier = "2??? ?????? ??????????????? ?????? ????????? 115~455(????????? ????????? ?????? ??????)?????? ???????????????.";
        String summonerSpellDescBooster = "???????????? ?????? ?????? ?????? ????????? (?????? ??? ???????????? ????????? ?????? ??????) ????????? ????????? ?????? ????????? ????????? ???????????? ?????? ???????????? ?????? ?????? ???????????? ??????????????? 3?????? 65% ??????????????????.";
        int summonerSpellKeyBarrier = 21;
        int summonerSpellKeyBooster = 1;
        String summonerSpellImageBarrier = "SummonerBarrier.png";
        String summonerSpellImageBooster = "SummonerBoost.png";

        summonerSpellService.insert(summonerSpellNameBarrier, summonerSpellDescBarrier, summonerSpellKeyBarrier, summonerSpellImageBarrier);
        summonerSpellService.insert(summonerSpellNameBooster, summonerSpellDescBooster, summonerSpellKeyBooster, summonerSpellImageBooster);

        // when
        SummonerSpellEntity summonerSpellEntityBarrierFind = summonerSpellController.getSummonerSpellByKey(summonerSpellKeyBarrier).get(0);
        SummonerSpellEntity summonerSpellEntityBoosterFind = summonerSpellController.getSummonerSpellByKey(summonerSpellKeyBooster).get(0);

        // then
        assertThat(summonerSpellEntityBarrierFind.getSpellKey()).isEqualTo(21);
        assertThat(summonerSpellEntityBarrierFind.getImage()).isEqualTo("SummonerBarrier.png");
        assertThat(summonerSpellEntityBoosterFind.getSpellKey()).isEqualTo(1);
        assertThat(summonerSpellEntityBoosterFind.getImage()).isEqualTo("SummonerBoost.png");
    }
}
