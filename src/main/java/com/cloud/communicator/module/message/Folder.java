package com.cloud.communicator.module.message;

import com.cloud.communicator.module.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="folder_folder_id_seq")
    @SequenceGenerator(name="folder_folder_id_seq", sequenceName="folder_folder_id_seq", allocationSize=1)
    @Column(name="folder_id")
    private Integer id;

    @NotNull
    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="label_color")
    private String labelColor;

    @NotNull
    @OneToOne
    @JoinColumn(name="fk_owner_id")
    private User owner;

    @Column(name="is_default_user_folder")
    private Boolean isUserDefaultFolder;

    @Transient
    private Integer unreadMessages;

    public static String DEFAULT_FOLDER_NAME = "Inbox";
    public static String DEFAULT_FOLDER_DESCRIPTION = "Inbox";


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Boolean getIsUserDefaultFolder() {
        return isUserDefaultFolder;
    }

    public void setIsUserDefaultFolder(Boolean isUserDefaultFolder) {
        this.isUserDefaultFolder = isUserDefaultFolder;
    }

    public Integer getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(Integer unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    @Override
    public String toString() {
        return "Folder: {Id: " + this.getId() +
                ", name: " + this.getName() +
                ", description: " + this.getDescription() +
                ", labelColor: " + this.getLabelColor() +
                ", owner: " + this.getOwner() + "}";
    }
}
