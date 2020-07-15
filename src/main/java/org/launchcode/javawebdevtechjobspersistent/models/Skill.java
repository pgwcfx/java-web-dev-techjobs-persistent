package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    @Size(min = 20,max = 200,message = "Description must be between 20 and 200 characters.")
    private String description;

    public Skill(@Size(min = 20, max = 200,message = "Description must be between 20 and 200 characters.") String description) {
        this.description = description;
    }

    public Skill(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}