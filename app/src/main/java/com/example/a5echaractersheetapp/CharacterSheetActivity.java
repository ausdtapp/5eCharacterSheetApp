package com.example.a5echaractersheetapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CharacterSheetActivity extends AppCompatActivity {

    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Character_sheet", "Char sheet started");

        setContentView(R.layout.activity_character_sheet);

        //Sends character sheet data back through intent
        Button save_button = findViewById(R.id.save_char_button);
        save_button.setOnClickListener(view -> {

            Intent replyIntent = new Intent();

            //Pulls character data from views
            String new_name = ((EditText) findViewById(R.id.name_input)).getText().toString();
            String new_str = ((TextView) findViewById(R.id.str_input)).getText().toString();
            String new_dex = ((TextView) findViewById(R.id.dex_input)).getText().toString();
            String new_con = ((TextView) findViewById(R.id.con_input)).getText().toString();
            String new_int = ((TextView) findViewById(R.id.int_input)).getText().toString();
            String new_wis = ((TextView) findViewById(R.id.wis_input)).getText().toString();
            String new_cha = ((TextView) findViewById(R.id.cha_input)).getText().toString();

            //New character object to be passed through intent
            Character new_character = new Character(new_name, new_str, new_dex, new_con, new_int, new_wis, new_cha);
            Log.d("Character_sheet", new_character.getCharName());
            Log.d("Character_sheet", "stats are " + new_character.getStr() + " Str");
            //Pass back to main activity for viewmodel

            replyIntent.putExtra("character_info", new_character);
            setResult(RESULT_OK, replyIntent);

            //finish sheet activity
            finish();
        });

    }


    public void fill_text (View v) {
        Button new_text = (Button) v;
        String s = (String) new_text.getText();
        //String s = new_text.setText("input");
        if (Integer.parseInt(s) <= 99) {
            new_text.setText(s);
        } else {
            new_text.setText("0");
        }
    }

    public void name_alert_box (View v) {
        TextView output = (TextView) v;
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Name");

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                text = input.getText().toString();
            }
        });

    }

    public void stat_num_alert_box (View v) {
        TextView output = (TextView) v;
        final EditText input = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter int < 100");

        //accept input in alert box
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        //Sets Alert box to have onClick effects for "okay" button and "cancel" button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                text = input.getText().toString();
                if (Integer.parseInt(text) < 100 && Integer.parseInt(text) > 0) {
                    output.setText(text);
                } else {
                    text = "0";
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           public void onClick (DialogInterface dialog, int which) {
               dialog.cancel();
           }
        });
        builder.create();
        builder.show();
    }
}