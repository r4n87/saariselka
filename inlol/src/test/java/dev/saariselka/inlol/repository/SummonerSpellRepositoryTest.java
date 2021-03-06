package dev.saariselka.inlol.repository;

import dev.saariselka.inlol.entity.SummonerSpellEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SummonerSpellRepositoryTest {

    @Autowired
    private SummonerSpellRepository summonerSpellRepository;

    @Test
    @DisplayName("Save Entity")
    public void saveSummonerSpellEntity() {
        // given
        String name = "testSummonerSpellName";
        String description = "testSummonerSpellDesc";
        int spellKey = 0;
        String image = "testSummonerSpellImage";

        SummonerSpellEntity summonerSpellEntity = new SummonerSpellEntity(name, description, spellKey, image);

        // when
        SummonerSpellEntity summonerSpellEntitySaved = summonerSpellRepository.save(summonerSpellEntity);

        // then
        assertThat(summonerSpellEntity.getSpellKey()).isEqualTo(summonerSpellEntitySaved.getSpellKey());
        assertThat(summonerSpellEntitySaved.getSpellKey()).isNotNull();
        assertThat(summonerSpellRepository.count()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find Entity By Key")
    public void findByKey() {
        // given
        String summonerSpellNameBarrier = "방어막";
        String summonerSpellNameBooster = "점멸";
        String summonerSpellDescBarrier = "2초 동안 방어막으로 감싸 피해를 115~455(챔피언 레벨에 따라 변동)만큼 흡수합니다.";
        String summonerSpellDescBooster = "챔피언에 걸린 모든 이동 불가와 (제압 및 공중으로 띄우는 효과 제외) 소환사 주문에 의한 해로운 효과를 제거하고 새로 적용되는 이동 불가 효과들의 지속시간을 3초간 65% 감소시킵니다.";
        int summonerSpellKeyBarrier = 21;
        int summonerSpellKeyBooster = 1;
        String summonerSpellImageBarrier = "SummonerBarrier.png";
        String summonerSpellImageBooster = "SummonerBoost.png";

        SummonerSpellEntity summonerSpellEntityBarrier = new SummonerSpellEntity(summonerSpellNameBarrier, summonerSpellDescBarrier, summonerSpellKeyBarrier, summonerSpellImageBarrier);
        SummonerSpellEntity summonerSpellEntityBooster = new SummonerSpellEntity(summonerSpellNameBooster, summonerSpellDescBooster, summonerSpellKeyBooster, summonerSpellImageBooster);

        SummonerSpellEntity summonerSpellEntityBarrierSave = summonerSpellRepository.save(summonerSpellEntityBarrier);
        SummonerSpellEntity summonerSpellEntityBoosterSave = summonerSpellRepository.save(summonerSpellEntityBooster);

        // when
        SummonerSpellEntity summonerSpellEntityBarrierFind = summonerSpellRepository.findByspellKey(summonerSpellEntityBarrierSave.getSpellKey()).get(0);
        SummonerSpellEntity summonerSpellEntityBoosterFind = summonerSpellRepository.findByspellKey(summonerSpellEntityBoosterSave.getSpellKey()).get(0);

        // then
        assertThat(summonerSpellRepository.count()).isGreaterThan(0);
        assertThat(summonerSpellEntityBarrierFind.getSpellKey()).isEqualTo(21);
        assertThat(summonerSpellEntityBarrierFind.getImage()).isEqualTo("SummonerBarrier.png");
        assertThat(summonerSpellEntityBoosterFind.getSpellKey()).isEqualTo(1);
        assertThat(summonerSpellEntityBoosterFind.getImage()).isEqualTo("SummonerBoost.png");
    }
}
