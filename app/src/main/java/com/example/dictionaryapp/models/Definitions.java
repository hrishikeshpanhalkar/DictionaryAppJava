package com.example.dictionaryapp.models;

import java.util.List;

public class Definitions {
    private String definition = "";
    private String example = "";
    private List<String> synonyms = null;
    private List<String> antonyms = null;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }

    @Override
    public String toString() {
        return "Definitions{" +
                "definition='" + definition + '\'' +
                ", example='" + example + '\'' +
                ", synonyms=" + synonyms +
                ", antonyms=" + antonyms +
                '}';
    }
}
