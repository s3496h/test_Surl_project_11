package com.koreait.Surl_project_11;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(length = 255)
    private String title;
    @Column(columnDefinition = "text")
    private String body;
    private String author;
}

