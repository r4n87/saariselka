package dev.saariselka.inlol.service;

import dev.saariselka.inlol.entity.SummonerSpellEntity;
import dev.saariselka.inlol.repository.SummonerSpellRepository;
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
public class SummonerSpellServiceTest {

    @Autowired
    private SummonerSpellService summonerSpellService;
    @Autowired
    private SummonerSpellRepository summonerSpellRepository;

    @Test
    @DisplayName("Insert Entity")
    public void insert() {
        // given
        String name = "testSummonerSpellName";
        String description = "testSummonerSpellDesc";
        int spellKey = 0;
        String image = "testSummonerSpellImage";

        // when
        summonerSpellService.insert(name, description, spellKey, image);

        // then
        SummonerSpellEntity summonerSpellEntitySaved = summonerSpellRepository.findByspellKey(spellKey).get(0);

        assertThat(spellKey).isEqualTo(summonerSpellEntitySaved.getSpellKey());
        assertThat(summonerSpellEntitySaved.getSpellKey()).isNotNull();
        assertThat(summonerSpellRepository.count()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Insert Entities")
    public void insertAll() {
        // given
        String name = "testSummonerSpellName";
        String description = "testSummonerSpellDesc";
        int spellKey = 0;
        String image = "testSummonerSpellImage";

        List<SummonerSpellEntity> summonerSpellEntities = new ArrayList<>();
        SummonerSpellEntity summonerSpellEntity = new SummonerSpellEntity(name,description,spellKey,image);
        summonerSpellEntities.add(summonerSpellEntity);

        // when
        summonerSpellService.insertAll(summonerSpellEntities);

        // then
        SummonerSpellEntity summonerSpellEntitySaved = summonerSpellRepository.findByspellKey(spellKey).get(0);

        assertThat(spellKey).isEqualTo(summonerSpellEntitySaved.getSpellKey());
        assertThat(summonerSpellEntitySaved.getSpellKey()).isNotNull();
        assertThat(summonerSpellRepository.count()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find Entity By Key")
    public void findByKey() {
        // given
        String summonerSpellNameBarrier = "?????????";
        String summonerSpellNameBooster = "??????";
        String summonerSpellDescBarrier = "2??? ?????? ??????????????? ?????? ????????? 115~455(????????? ????????? ?????? ??????)?????? ???????????????.";
        String summonerSpellDescBooster = "???????????? ?????? ?????? ?????? ????????? (?????? ??? ???????????? ????????? ?????? ??????) ????????? ????????? ?????? ????????? ????????? ???????????? ?????? ???????????? ?????? ?????? ???????????? ??????????????? 3?????? 65% ??????????????????.";
        int summonerSpellKeyBarrier = 21;
        int summonerSpellKeyBooster = 1;
        String summonerSpellImageBarrier = "SummonerBarrier.png";
        String summonerSpellImageBooster = "SummonerBoost.png";

        SummonerSpellEntity summonerSpellEntityBarrier = new SummonerSpellEntity(summonerSpellNameBarrier, summonerSpellDescBarrier, summonerSpellKeyBarrier, summonerSpellImageBarrier);
        SummonerSpellEntity summonerSpellEntityBooster = new SummonerSpellEntity(summonerSpellNameBooster, summonerSpellDescBooster, summonerSpellKeyBooster, summonerSpellImageBooster);

        SummonerSpellEntity summonerSpellEntityBarrierSave = summonerSpellRepository.save(summonerSpellEntityBarrier);
        SummonerSpellEntity summonerSpellEntityBoosterSave = summonerSpellRepository.save(summonerSpellEntityBooster);

        // when
        SummonerSpellEntity summonerSpellEntityBarrierFind = summonerSpellService.findByspellKey(summonerSpellEntityBarrierSave.getSpellKey()).get(0);
        SummonerSpellEntity summonerSpellEntityBoosterFind = summonerSpellService.findByspellKey(summonerSpellEntityBoosterSave.getSpellKey()).get(0);

        // then
        assertThat(summonerSpellRepository.count()).isGreaterThan(0);
        assertThat(summonerSpellEntityBarrierFind.getSpellKey()).isEqualTo(21);
        assertThat(summonerSpellEntityBarrierFind.getImage()).isEqualTo("SummonerBarrier.png");
        assertThat(summonerSpellEntityBoosterFind.getSpellKey()).isEqualTo(1);
        assertThat(summonerSpellEntityBoosterFind.getImage()).isEqualTo("SummonerBoost.png");
    }
}
