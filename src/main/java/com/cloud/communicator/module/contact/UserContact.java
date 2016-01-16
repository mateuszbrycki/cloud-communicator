package com.cloud.communicator.module.contact;

import com.cloud.communicator.module.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="user_contacts")
public class UserContact implements Serializable {

    @Id
    @NotNull
    @OneToOne
    @JoinColumn(name="fk_user_id")
    private User user;

    @Id
    @NotNull
    @OneToOne
    @JoinColumn(name="fk_person_in_book_id")
    private User personInBook;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getPersonInBook() {
        return personInBook;
    }

    public void setPersonInBook(User personInBook) {
        this.personInBook = personInBook;
    }
}
