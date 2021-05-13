package ru.lab6.server.response;

public class CountByMoodResponse implements Response{
    private String mood;
    private int count;

    CountByMoodResponse(String mood, int count){
        this.mood = mood;
        this.count = count;
    }

    public String json() {
        return null;
    }
}
