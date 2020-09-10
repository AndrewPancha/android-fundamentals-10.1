package com.example.roomwords;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(),
                                    WordRoomDatabase.class,
                                    "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        private final WordDao mWordDao;

        public PopulateDBAsync(WordRoomDatabase db) {
            mWordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.deleteAll();
            return null;
        }
    }
}
