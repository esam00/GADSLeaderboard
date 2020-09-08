package com.essam.leaderboard.model;

public class Leader {
    private String name;
    private String country;
    private String badgeUrl;
    private String progress;
    private int hours;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getProgress() {
        if (hours != 0){
            return this.hours + " Learning hours, " + this.getCountry();
        }
        else {
            return this.score + " Skill IQ score, " + this.getCountry();
        }
    }
}
