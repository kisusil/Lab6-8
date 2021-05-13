package ru.lab6.server.response;

import com.google.gson.Gson;

public class ResponseImpl implements Response {

    private String status;
    private String result;

    ResponseImpl(Builder builder) {
        this.status = builder.status;
        this.result = builder.result;
    }

    public String json() {
        return new Gson().toJson(this);
    }

    static class Builder {
        private String status;
        private String result;

        public Builder setSuccessfulResponse(String result) {
            status = "ok";
            this.result = result;
            return this;
        }

        public Builder setErrorResponse(String errorName, String description) {
            status = errorName;
            result = description;
            return this;
        }
    }
}
