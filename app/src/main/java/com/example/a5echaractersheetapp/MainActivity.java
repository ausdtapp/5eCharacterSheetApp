package com.example.a5echaractersheetapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements CharacterViewHolder.OnCharacterListener {

    public static final int NEW_CHARACTER_ACTIVITY_REQUEST_CODE = 1;
    public static final int EXISTING_CHARACTER_ACTIVITY_REQUEST_CODE = 2;

    Character temp;

    private CharacterViewModel mCharacterViewModel;

    private CharacterListAdapter adapter;

    public CharacterViewHolder.OnCharacterListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        mCharacterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);

        adapter = new CharacterListAdapter(new CharacterListAdapter.CharacterDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCharacterViewModel.getAllCharacters().observe(this, characters -> {
            //update cached copy of adapter whenever main activity is opened
            adapter.submitList(characters);
        });


        //Takes user to new activity that creates character sheet who's intent returns
        //character information to place in viewmodel and database
        Button new_char_sheet = findViewById(R.id.character_button);
        new_char_sheet.setOnClickListener(view -> {
            Intent i = new Intent(this, CharacterSheetActivity.class);
            startActivityForResult(i, NEW_CHARACTER_ACTIVITY_REQUEST_CODE);
        });

        Button delete_characters = findViewById(R.id.delete_all_button);
        delete_characters.setOnClickListener(view -> {
            //delete all database entries which will be reflected in viewmodel
            mCharacterViewModel.delete_all();
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CHARACTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            //logging if character insertion into database works
            Log.d("Main", "Inserting character");
            Character character = (Character) data.getParcelableExtra("character_info");
            Log.d("Main", "New CharName is " + character.getCharName());

            mCharacterViewModel.insert(character);
            Log.d("main", "character insertion done");
        } else if (requestCode == EXISTING_CHARACTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Character character = (Character) data.getParcelableExtra("character_info");
            Log.d("Main", "Recieved " + character.getCharName());


            if (temp.getCharName().equals(character.getCharName())) {
                mCharacterViewModel.update(character);
                Log.d("Main", character.getCharName() + " Updated");
            } else {
                mCharacterViewModel.delete_character(temp.getCharName());
                Log.d("Main", temp.getCharName() + " Deleted");
                mCharacterViewModel.insert(character);
                Log.d("Main", character.getCharName() + " Inserted");
            }

        }
    }


    //Overrides interface which allows intent to be passed containing information of character clicked
    @Override
    public void onCharacterClick(int position) {
        Log.d("Main", "Position is " + position);
        Character clicked_char = adapter.getCurrentList().get(position);
        temp = clicked_char;
        //Toast.makeText(this, "Position clicked is " + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ExistingCharacterSheetActivity.class);
        i.putExtra("character_info", clicked_char);
        startActivityForResult(i, EXISTING_CHARACTER_ACTIVITY_REQUEST_CODE);
    }



}