package com.cloud.communicator.module.message;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class FolderDTO {

    @NotNull
    @Length(min = 3)
    private String name;

    private Integer label;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}