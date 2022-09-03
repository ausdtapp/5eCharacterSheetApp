package com.example.a5echaractersheetapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Character.class}, version = 1, exportSchema = false)
public abstract class CharacterRoomDatabase extends RoomDatabase {

    public abstract CharacterDao characterDao();

    private static volatile CharacterRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CharacterRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CharacterRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CharacterRoomDatabase.class, "Character_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            //deletes all characters on devices on app restart
            databaseWriteExecutor.execute(() -> {
                CharacterDao dao = INSTANCE.characterDao();
                dao.deleteAll();

                Character mCharacter = new Character("test", "1", "2", "3", "4", "5", "6");
                dao.insert(mCharacter);

            });
        }
    };


}