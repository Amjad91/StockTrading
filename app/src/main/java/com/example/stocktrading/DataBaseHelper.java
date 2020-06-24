package com.example.stocktrading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Stock.db";
    public static final String Table_Name = "stock_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EXCHANGE";
    public static final String COL_4 = "CLOSE";
    public static final String COL_5 = "OPEN";
    public static final String COL_6 = "PRICE";
    public static final String COL_7 = "BID";
    public static final String COL_8 = "ASK";
    public static final String COL_9 = "SECTOR";
    public static final String COL_10 = "FULLTIMEEMP";
    public static final String COL_11 = "SUMMARY";
    public static final String COL_12 = "CITY";
    public static final String COL_13 = "COUNTRY";
    public static final String COL_14 = "WEBSITE";
    public static final String COL_15 = "DVOLUME";
    public static final String COL_16 = "CAP";
    public static final String COL_17 = "CHANGE";
    public static final String COL_18 = "VOLUME";
    public static final String COL_19 = "SYMBOL";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EXCHANGE TEXT," +
                "CLOSE FLOAT,OPEN FLOAT,PRICE FLOAT,BID TEXT DEFAULT NULL,ASK TEXT DEFAULT NULL,SECTOR TEXT DEFAULT NULL,FULLTIMEEMP INTEGER DEFAULT 0," +
                " SUMMARY TEXT DEFAULT NULL,CITY TEXT DEFAULT NULL,COUNTRY TEXT DEFAULT NULL,WEBSITE TEXT DEFAULT NULL,DVOLUME TEXT DEFAULT NULL,CAP TEXT DEFAULT NULL," +
                "CHANGE TEXT DEFAULT NULL,VOLUME TEXT DEFAULT NULL,SYMBOL TEXT DEFAULT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);

    }

    public boolean insertData(String name, String exchange, Float close, Float open, Float price,
                              String bid, String ask, String sector, Integer fullTimeEmp,
                              String summary, String city, String country,
                              String website, String averageDailyVolume10Day, String cap,
                              String change, String volume, String symbol) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, exchange);
        contentValues.put(COL_4, close);
        contentValues.put(COL_5, open);
        contentValues.put(COL_6, price);
        contentValues.put(COL_7, bid);
        contentValues.put(COL_8, ask);
        contentValues.put(COL_9, sector);
        contentValues.put(COL_10, fullTimeEmp);
        contentValues.put(COL_11, summary);
        contentValues.put(COL_12, city);
        contentValues.put(COL_13, country);
        contentValues.put(COL_14, website);
        contentValues.put(COL_15, averageDailyVolume10Day);
        contentValues.put(COL_16, cap);
        contentValues.put(COL_17, change);
        contentValues.put(COL_18, volume);
        contentValues.put(COL_19, symbol);


        long result = db.insert(Table_Name, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;
    }

    public Integer deleteData(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name, "SYMBOL" + "=?", new String[]{s});
    }
}
