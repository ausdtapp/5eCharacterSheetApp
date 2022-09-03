package com.example.a5echaractersheetapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    private CharacterRepository mRepository;

    private final LiveData<List<Character>> mAllCharacters;

    private Character search_char;

    public CharacterViewModel (Application application) {
        super(application);
        mRepository = new CharacterRepository(application);
        mAllCharacters = mRepository.getAllCharacters();
    }

    LiveData<List<Character>> getAllCharacters() { return mAllCharacters; }

    public void insert(Character character) { mRepository.insert(character); }

    public void delete_all() {mRepository.delete_all_characters();}

    public void delete_character(String character_name) {
        mRepository.delete_character(character_name);
    }

    public void update (Character character) {
        mRepository.update(character);
    }

    public void getCharacter(String char_name) {
        mRepository.searchCharacter(char_name);
        search_char = mRepository.getCharacter();
    }
}