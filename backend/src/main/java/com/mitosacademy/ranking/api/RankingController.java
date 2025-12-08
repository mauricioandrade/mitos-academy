package com.mitosacademy.ranking.api;

import com.mitosacademy.ranking.domain.PlayerRanking;
import com.mitosacademy.ranking.service.RankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/api/ranking")
    public List<PlayerRanking> getRanking() {
        return rankingService.getCurrentRanking();
    }
}
