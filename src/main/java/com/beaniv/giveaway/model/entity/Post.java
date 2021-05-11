package com.beaniv.giveaway.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "posts")
@Setter
@Getter
public class Post extends BaseEntity {
    private int creatorId;

    private String title;

    private String description;

    private String conditions;

    private String prize;

    @Column(length = 2000)
    private String imageUrl;

    @ManyToMany(mappedBy = "posts")
    private Set<User> users;
}