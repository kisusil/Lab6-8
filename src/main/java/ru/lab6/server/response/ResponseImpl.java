package ru.lab6.server.response;

import ru.lab6.common.humanbeing.HumanBeing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ResponseImpl implements Response{
    private String collection_type;
    private LocalDateTime init_date;
    private int students_count;
    private String help;
    private String error_name;
    private String error_description;
    private String response;
    private String mood;
    private ArrayList<String> collection;
    
    public String json() {
        return null;
    }

    static class Builder{
        private String collection_type;
        private LocalDateTime init_date;
        private int students_count;
        private String help;
        private String error_name;
        private String error_description;
        private String response;
        private String mood;
        private ArrayList<String> collection;

        public Builder setInfoResponse(String collection_type, LocalDateTime init_date, int students_count) {
            this.collection_type = collection_type;
            this.init_date = init_date;
            this.students_count = students_count;
            return this;
        }

        public Builder setHelp(String help) {
            this.help = help;
            return this;
        }

        public Builder setErrorNameAndDescription(String error_name, String error_description) {
            this.error_name = error_name;
            this.error_description = error_description;
            return this;
        }

        public Builder setResponse() {
            this.response = "ok";
            return this;
        }

        public Builder setCollection(ArrayList<String> collection) {
            this.collection = collection;
            return this;
        }

        public Builder setCountByMoodResponse(String mood, int students_count) {
            this.mood = mood;
            this.students_count = students_count;
            return this;
        }
    }

    ResponseImpl(Builder builder){
        this.collection_type = builder.collection_type;
        this.init_date = builder.init_date;
        this.students_count = builder.students_count;
        this.help = builder.help;
        this.error_name = builder.error_name;
        this.error_description = builder.error_description;
        this.response = builder.response;
        this.mood = builder.mood;
        this.collection = builder.collection;
    }
}
