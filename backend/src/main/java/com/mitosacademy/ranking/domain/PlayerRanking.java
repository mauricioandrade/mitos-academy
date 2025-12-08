package com.mitosacademy.ranking.domain;

import java.time.OffsetDateTime;

public class PlayerRanking {

    private String summonerName;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private double winRate;
    private int profileIconId;
    private OffsetDateTime lastUpdated;

    public PlayerRanking() {
    }

    public PlayerRanking(String summonerName,
                         String tier,
                         String rank,
                         int leaguePoints,
                         int wins,
                         int losses,
                         double winRate,
                         int profileIconId,
                         OffsetDateTime lastUpdated) {
        this.summonerName = summonerName;
        this.tier = tier;
        this.rank = rank;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
        this.profileIconId = profileIconId;
        this.lastUpdated = lastUpdated;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
