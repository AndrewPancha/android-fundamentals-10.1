package com.example.roomwords;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public String getWord() {
        return mWord;
    }

    public int getId() {
        return id;
    }

    @Ignore
    public Word(int id, @NonNull String mWord) {
        this.id = id;
        this.mWord = mWord;
    }
}
