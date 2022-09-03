package com.example.a5echaractersheetapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


class CharacterRepository {

    private com.example.a5echaractersheetapp.CharacterDao mCharacterDao;
    private LiveData<List<Character>> mAllCharacters;
    private Character char_search;

    CharacterRepository(Application application) {
        com.example.a5echaractersheetapp.CharacterRoomDatabase db = com.example.a5echaractersheetapp.CharacterRoomDatabase.getDatabase(application);
        mCharacterDao = db.characterDao();
        mAllCharacters = mCharacterDao.getAlphabetizedCharacters();
    }


    LiveData<List<Character>> getAllCharacters() {
        return mAllCharacters;
    }

    void insert(Character character) {
        com.example.a5echaractersheetapp.CharacterRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCharacterDao.insert(character);
        });
    }

    void searchCharacter(String charName) {
        com.example.a5echaractersheetapp.CharacterRoomDatabase.databaseWriteExecutor.execute(() -> {
           char_search = mCharacterDao.searchCharacter(charName);
        });
    }

    Character getCharacter() {
        return char_search;
    }

    void delete_all_characters() {
        com.example.a5echaractersheetapp.CharacterRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCharacterDao.deleteAll();
        });
    }

    void update(Character character) {
        com.example.a5echaractersheetapp.CharacterRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCharacterDao.update(character);
        });
    }

    void delete_character(String character_name) {
        com.example.a5echaractersheetapp.CharacterRoomDatabase.databaseWriteExecutor.execute(() -> {
            mCharacterDao.deleteChar(character_name);
        });
    }
}