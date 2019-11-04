package com.example.mvvmtutorial;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AuthorNoteDao {

    @Query("DELETE FROM note_table WHERE id = :id ")
    void delete(int id);

    @Query("SELECT nt.id as id, title, description, priority, idAuthor, ath.name as author FROM note_table as nt LEFT OUTER JOIN (SELECT id, name FROM author_table) as ath ON nt.idAuthor = ath.id ORDER BY priority DESC")
    LiveData<List<AuthorNote>> getAuthorNote();

}
