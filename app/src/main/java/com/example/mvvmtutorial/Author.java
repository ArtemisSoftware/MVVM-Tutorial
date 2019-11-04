package com.example.mvvmtutorial;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "author_table")
public class Author {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name;
    }
}
