package dev.saariselka.inlol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.saariselka.inlol.controller.APIController;
import dev.saariselka.inlol.controller.APIKeyController;
import dev.saariselka.inlol.controller.MatchMasterController;
import dev.saariselka.inlol.controller.SummonerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Facade {

    @Autowired
    private Facade_Get facade_get;
    @Autowired
    private Facade_Set facade_set;

    private String name;

    // 테스트를 위해 주석 처리
//    public Facade(String name)
//    {
//        this.name = name;
//    }

    // DB 정보 불러오기
    public void search()
    {
        name = "Petaluma";

        // 서버에서 ppuid 얻어오기
        // 얻은 ppuid로 db조회
        // db에 데이터가 있는 경우 , 없는 경우
        // db에 데이터가 있지만 일정기간 xx일 지났으면 자동갱신
        // -> 밖에서 해야되려나?
        // Init과 search, update의 차이 명확하게

    }

    // API를 활용해서 Init
    @GetMapping("testinit")
    public void init() throws JsonProcessingException
    {
        // 테스트를 위해 하드 코딩
        name = "Petaluma";

        HashMap<String, Object> result;

        //Step 1. Get SummonerInfo By Name
        result = facade_get.getSummonerInfo(name);

        //Step 2. Set SummonerInfo At DB
        facade_set.setSummonerInfoAtDB(result);

        //Step 3. Get MatchList By puuid
        ArrayList<String> matchList = facade_get.getMatchList(name);

        for(int i = 0; i < matchList.size(); i++)
        {
            //Step 4. Get MatchInfo By MatchList
            result = facade_get.getMatchInfo(matchList, i);

            //Step 5. Set MatchInfo At DB
            facade_set.setMatchInfoAtDB(result);
        }
    }

    // API를 활용해서 DB 업데이트
    public void update()
    {
        //1. puuid 발췌
        //String puuid = summonerController.getSummner_Puuid_ByName(name);

    }

}
