package com.example.mvvmtutorial;

import androidx.room.Ignore;

public class AuthorNote {


    private int id;
    private String title;
    private String description;
    private int priority;
    private int idAuthor;
    private String author;

    public AuthorNote(String title, String description, int priority, int idAuthor, String author) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.idAuthor = idAuthor;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public String getAuthor() {
        return author;
    }
}
