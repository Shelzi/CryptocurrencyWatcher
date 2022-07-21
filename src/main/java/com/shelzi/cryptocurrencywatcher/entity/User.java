package com.shelzi.cryptocurrencywatcher.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private long id;
    private String name;
    @JsonProperty("track_id")
    private Cryptocurrency savedCrypto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Cryptocurrency getSavedCrypto() {
        return savedCrypto;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withId(long id){
            user.id = id;
            return this;
        }

        public Builder withName(String name){
            user.name = name;
            return this;
        }

        public Builder withSavedCrypto(Cryptocurrency savedCrypto){
            user.savedCrypto = savedCrypto;
            return this;
        }

        public User build(){
            return user;
        }

    }

    @Override
    public String toString() {
        return "Cryptocurrency{" +
                "id=" + id +
                ", symbol='" + name + '\'' +
                ", saved crypto: '" + savedCrypto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(savedCrypto, user.savedCrypto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, savedCrypto);
    }
}