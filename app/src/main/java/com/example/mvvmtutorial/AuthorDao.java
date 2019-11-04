package com.example.mvvmtutorial;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AuthorDao {

    @Insert
    void insert(Author author);

    @Query("SELECT * FROM author_table ORDER BY name DESC")
    LiveData<List<Author>> getAllAuthors();
}
