package com.example.mvvmtutorial;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private AuthorDao authorDao;
    private AuthorNoteDao authorNoteDao;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Author>> allAuthors;

    public NoteRepository(Application application) {
        NoteDataBase database = NoteDataBase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();

        authorDao = database.authorDao();
        authorNoteDao = database.authorNoteDao();
        //allAuthors = authorDao.getAllAuthors();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(int id) {
        new DeleteAuthorNoteAsyncTask(authorNoteDao).execute(id);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    public LiveData<List<Author>> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    public LiveData<List<AuthorNote>> getAllAuthorNotes() {
        return authorNoteDao.getAuthorNote();
    }


    public void insertAuthor(Author author) {
        new InsertAuthorNoteAsyncTask(authorDao).execute(author);
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class InsertAuthorNoteAsyncTask extends AsyncTask<Author, Void, Void> {
        private AuthorDao authorDao;

        private InsertAuthorNoteAsyncTask(AuthorDao authorDao) {
            this.authorDao = authorDao;
        }

        @Override
        protected Void doInBackground(Author... authors) {
            authorDao.insert(authors[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }




    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAuthorNoteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private AuthorNoteDao authorNoteDao;

        private DeleteAuthorNoteAsyncTask(AuthorNoteDao authorNoteDao) {
            this.authorNoteDao = authorNoteDao;
        }

        @Override
        protected Void doInBackground(Integer... id) {
            authorNoteDao.delete(id[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
