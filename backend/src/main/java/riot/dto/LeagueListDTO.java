package riot.dto;

import java.util.List;

public class LeagueListDTO {

    private String tier;
    private String queue;
    private String name;
    private List<LeagueEntryDTO> entries;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LeagueEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<LeagueEntryDTO> entries) {
        this.entries = entries;
    }
}
