package com.example.a5echaractersheetapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName= "character_table")
public class Character implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name= "CharName")
    private String mCharName;

    @NonNull
    @ColumnInfo(name = "Strength")
    private String mStr;

    @NonNull
    @ColumnInfo(name = "Dexterity")
    private String mDex;

    @NonNull
    @ColumnInfo(name = "Constitution")
    private String mCon;

    @NonNull
    @ColumnInfo(name = "Intelligence")
    private String mInt;

    @NonNull
    @ColumnInfo(name = "Wisdom")
    private String mWis;

    @NonNull
    @ColumnInfo(name = "Charisma")
    private String mCha;

    public Character(@NonNull String mCharName, @NonNull String mStr, @NonNull String mDex, @NonNull String mCon,
                     @NonNull String mInt, @NonNull String mWis, @NonNull String mCha) {
        this.mCharName = mCharName;
        this.mStr = mStr;
        this.mDex = mDex;
        this.mCon = mCon;
        this.mInt = mInt;
        this.mWis = mWis;
        this.mCha = mCha;

    }

    protected Character(Parcel in) {
        mCharName = in.readString();
        mStr = in.readString();
        mDex = in.readString();
        mCon = in.readString();
        mInt = in.readString();
        mWis = in.readString();
        mCha = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCharName);
        dest.writeString(mStr);
        dest.writeString(mDex);
        dest.writeString(mCon);
        dest.writeString(mInt);
        dest.writeString(mWis);
        dest.writeString(mCha);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public String getCharName() {return this.mCharName;}
    public String getStr() {return this.mStr;}
    public String getDex() {return this.mDex;}
    public String getCon() {return this.mCon;}
    public String getInt() {return this.mInt;}
    public String getWis() {return this.mWis;}
    public String getCha() {return this.mCha;}
    public boolean equals(Character c) {
        if (c.getStr() == this.getStr() &&
        c.getDex() == this.getDex() &&
        c.getCon() == this.getCon() &&
        c.getInt() == this.getInt() &&
        c.getWis() == this.getWis() &&
        c.getCha() == this.getCha()) {
            return true;
        } else {
            return false;
        }
    }


}
