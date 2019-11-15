package com.example.mycatapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity
public class CatImage {

    private String id;
    @PrimaryKey
    @NonNull
    private String url;
    private String wikipedia_url;
    private String origin;
    private String description;
    private String dog_friendly;
    private String temperament;
    private String name;
    private String life_span;
    private String breed_id;

    public String getBreed_id() {
        return breed_id;
    }
    public void setBreed_id(String breed_id) {
        this.breed_id = breed_id;
    }
    public String getLife_span() {
        return life_span;
    }
    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }
    public String getId ()
    {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDog_friendly() {
        return dog_friendly;
    }
    public void setDog_friendly(String dog_friendly) {
        this.dog_friendly = dog_friendly;
    }
    public String getTemperament() {
        return temperament;
    }
    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId (String id)
    {
        this.id = id;
    }
    public String getUrl ()
    {
        return url;
    }
    public void setUrl (String url)
    {
        this.url = url;
    }
    public String getWikipedia_url() { return wikipedia_url; }
    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }



    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", url = "+url+"";
    }
}



