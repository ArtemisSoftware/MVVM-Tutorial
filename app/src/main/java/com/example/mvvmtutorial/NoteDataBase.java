package com.example.mvvmtutorial;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class, Author.class}, version = 3)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDao noteDao();
    public abstract AuthorDao authorDao();
    public abstract AuthorNoteDao authorNoteDao();

    public static synchronized NoteDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDataBase.class, "note_database")
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    //.fallbackToDestructiveMigration().addCallback(roomCallback)
                    .build();
        }
        return instance;
    }



    private static final Migration MIGRATION_2_3=new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE note_table ADD COLUMN idAuthor INTEGER NOT NULL DEFAULT -1");
        }
    };


    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE author_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT NOT NULL)");

            new PopulateAuthorDbAsyncTask(instance).execute();
        }
    };




    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDataBase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d("MVVMTutorial", "Populating notes...");
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            Log.d("MVVMTutorial", "Finished Populating");
            return null;
        }
    }

/*
    private static RoomDatabase.Callback roomAuthorCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAuthorDbAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateAuthorDbAsyncTask(instance).execute();
        }
    };
*/
    private static class PopulateAuthorDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private AuthorDao authorDao;

        private PopulateAuthorDbAsyncTask(NoteDataBase db) {
            authorDao = db.authorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d("MVVMTutorial", "Populating author...");
            authorDao.insert(new Author("Aldebarab"));
            authorDao.insert(new Author("Camus"));
            authorDao.insert(new Author("Milo"));
            Log.d("MVVMTutorial", "Finished Populating");
            return null;
        }
    }

}
