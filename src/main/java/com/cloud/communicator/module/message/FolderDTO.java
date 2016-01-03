package com.cloud.communicator.module.message;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class FolderDTO {

    private Integer id;

    @NotNull
    @Length(min = 3)
    private String name;

    private String label;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}