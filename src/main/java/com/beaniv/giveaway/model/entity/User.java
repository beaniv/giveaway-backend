package com.beaniv.giveaway.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User extends BaseEntity {

    @Email(message = "Email should be valid")
    private String email;

    private String password;

    private boolean isConfirmed;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Post> posts;
}
