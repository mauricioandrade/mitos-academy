package com.mitosacademy.ranking.service;

import com.mitosacademy.ranking.domain.PlayerRanking;
import com.mitosacademy.ranking.riot.RiotApiClient;
import com.mitosacademy.ranking.riot.dto.LeagueEntryDTO;
import com.mitosacademy.ranking.riot.dto.LeagueListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService {

    private static final Logger log = LoggerFactory.getLogger(RankingService.class);

    private final RiotApiClient riotApiClient;

    // cache em memória
    private volatile List<PlayerRanking> cachedRanking = Collections.emptyList();

    public RankingService(RiotApiClient riotApiClient) {
        this.riotApiClient = riotApiClient;
    }

    public List<PlayerRanking> getCurrentRanking() {
        if (cachedRanking == null || cachedRanking.isEmpty()) {
            refreshRankingInternal();
        }
        return cachedRanking;
    }

    // Atualiza a cada 15 minutos
    @Scheduled(fixedRate = 15 * 60 * 1000L)
    public void scheduledRefresh() {
        refreshRankingInternal();
    }

    private void refreshRankingInternal() {
        try {
            LeagueListDTO challengerLeague = riotApiClient.getChallengerSoloQueue();
            OffsetDateTime now = OffsetDateTime.now();

            List<PlayerRanking> ranking = challengerLeague.getEntries().stream()
                    .sorted(Comparator.comparingInt(LeagueEntryDTO::getLeaguePoints).reversed())
                    .limit(10) // TOP 10
                    .map(entry -> {
                        int wins = entry.getWins();
                        int losses = entry.getLosses();
                        int total = wins + losses;
                        double winRate = total == 0 ? 0.0 : (wins * 100.0) / total;

                        return new PlayerRanking(
                                entry.getSummonerName(),
                                challengerLeague.getTier(),
                                entry.getRank(),
                                entry.getLeaguePoints(),
                                wins,
                                losses,
                                winRate,
                                0, // profileIconId – podemos implementar depois
                                now
                        );
                    })
                    .collect(Collectors.toList());

            this.cachedRanking = ranking;
        } catch (RuntimeException ex) {
            log.error("Falha ao atualizar o ranking a partir da API da Riot", ex);
            // mantém o cache vazio ou com o último valor conhecido, evitando falhas no endpoint
            if (this.cachedRanking == null) {
                this.cachedRanking = Collections.emptyList();
            }
        }
    }
}
