package com.example.mvvmtutorial;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        repository = new NoteRepository(application);

    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        allNotes = repository.getAllNotes();
        return allNotes;
    }

    public LiveData<List<AuthorNote>> getAllAuthorsNotes() {
        return repository.getAllAuthorNotes();
    }

    public void insertAuthor(Author author) {
        repository.insertAuthor(author);
    }


    public LiveData<List<Author>> getAllAuthors() {
        return repository.getAllAuthors();
    }

}
