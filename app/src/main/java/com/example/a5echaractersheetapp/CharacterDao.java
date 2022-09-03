package com.example.a5echaractersheetapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface CharacterDao {

    @Insert//(onConflict = OnConflictStrategy.IGNORE)
    void insert(Character mChar);

    @Update
    void update(Character mChar);

    @Query("DELETE FROM character_table")
    void deleteAll();

    @Query("DELETE FROM character_table WHERE CharName == :character_name")
    void deleteChar(String character_name);

    @Query("SELECT * FROM character_table WHERE CharName is not NULL ORDER BY CharName")
    LiveData<List<Character>> getAlphabetizedCharacters();

    @Query("SELECT * FROM character_table WHERE CharName = :character_name")
    Character searchCharacter(String character_name);
}
