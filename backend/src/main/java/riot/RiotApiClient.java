package riot;

import config.RiotApiProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import riot.dto.LeagueListDTO;

@Component
public class RiotApiClient {

    private final RiotApiProperties properties;
    private final RestClient restClient;

    public RiotApiClient(RiotApiProperties properties) {
        this.properties = properties;
        this.restClient = RestClient.builder()
                .baseUrl("https://" + properties.getRegion() + ".api.riotgames.com")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public LeagueListDTO getChallengerSoloQueue() {
        String path = "/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5";

        return restClient.get()
                .uri(path)
                .header("X-Riot-Token", properties.getApiKey())
                .retrieve()
                .body(LeagueListDTO.class);
    }
}