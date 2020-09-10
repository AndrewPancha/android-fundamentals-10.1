package com.example.roomwords;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll() {
        new deleteAllWordsAsyncTask(mWordDao).execute();
    }

    public void deleteWord(Word word) {
        new deleteWordAsyncTask(mWordDao).execute(word);
    }

    public void updateWord(Word word) {
        new updateWordAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncWordDao;

        public insertAsyncTask(WordDao mAsyncWordDao) {
            this.mAsyncWordDao = mAsyncWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncWordDao.insert(words[0]);
            return null;
        }
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncWordDao;

        public deleteAllWordsAsyncTask(WordDao mAsyncWordDao) {
            this.mAsyncWordDao = mAsyncWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncWordDao.deleteAll();
            return null;
        }
    }

    private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncWordDao;

        public deleteWordAsyncTask(WordDao mAsyncWordDao) {
            this.mAsyncWordDao = mAsyncWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncWordDao.deleteWord(words[0]);
            return null;
        }
    }

    private static class updateWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncWordDao;

        public updateWordAsyncTask(WordDao mAsyncWordDao) {
            this.mAsyncWordDao = mAsyncWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncWordDao.update(words[0]);
            return null;
        }
    }
}
