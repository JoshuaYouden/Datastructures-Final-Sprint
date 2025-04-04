package com.keyin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity


public class TreeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 5000)
    private String treeJson;

    private String userInputs;

    public TreeStructure(String treeJson, String userInputs) {

        this.treeJson = treeJson;
        this.userInputs = userInputs;
    }

    public TreeStructure() {

    }

    public String getTreeJson() {
        return treeJson;
    }

    public void setTreeJson(String treeJson) {
        this.treeJson = treeJson;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUserInputs() {
        return userInputs;
    }

    public void setUserInputs(String userInputs) {
        this.userInputs = userInputs;
    }
}
