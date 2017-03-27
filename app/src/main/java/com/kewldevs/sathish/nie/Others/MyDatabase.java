package com.kewldevs.sathish.nie.Others;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Gokul on 26-Mar-17.
 */

public class MyDatabase extends SQLiteAssetHelper {
    //Let this be a singleton

    public static final String DATABASE_NAME = "address_list.db";
    public static final int DATABASE_VERSION = 1;

    static final String DISTRICT_TABLE = "districts";
    static final String TALUK_TABLE = "taluks";
    static final String VILLAGE_TABLE = "villages";

    static final String DISTRICT_NAME_KEY = "district_name";
    static final String DISTRICT_CODE_KEY = "district_code";
    static final String TALUK_NAME_KEY = "taluk_name";
    static final String TALUK_CODE_KEY = "taluk_code";
    static final String VILLAGE_NAME_KEY = "village_name";
    static final String VILLAGE_CODE_KEY = "village_code";

    private static MyDatabase db_file;

    private MyDatabase(Context context, String name, String storageDirectory, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, storageDirectory, factory, version);
    }

    private MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyDatabase getInstance(Context context) {
        if (db_file == null) db_file = new MyDatabase(context);
        return db_file;
    }

    public ArrayList<String> getDistricts() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(DISTRICT_TABLE, new String[]{DISTRICT_NAME_KEY, DISTRICT_CODE_KEY}, null, null, null, null, null);

        if (cursor == null) return null;
        /*ResultSet set = new ResultSet();*/
        ArrayList<String> names = new ArrayList<String>();

        int nameIndex = cursor.getColumnIndex(DISTRICT_NAME_KEY);
        int codeIndex = cursor.getColumnIndex(DISTRICT_CODE_KEY);
        /*String temp;*/
        while (cursor.moveToNext()) {
            /*temp = cursor.getString(nameIndex).trim();
            set.map.put(cursor.getInt(codeIndex), temp);
            set.names.add(temp);*/
            names.add(cursor.getString(nameIndex).trim() + ":" + cursor.getInt(codeIndex));
        }

        cursor.close();
        /*return set;*/
        return names;
    }

    public ArrayList<String> getTaluks(int districtCode) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TALUK_TABLE, new String[]{TALUK_NAME_KEY, TALUK_CODE_KEY},
                DISTRICT_CODE_KEY + " = ?", new String[]{String.valueOf(districtCode)}, null, null, null);
        if (cursor == null) return null;
        /*ResultSet set = new ResultSet();*/
        ArrayList<String> names = new ArrayList<String>();

        int nameIndex = cursor.getColumnIndex(TALUK_NAME_KEY);
        int codeIndex = cursor.getColumnIndex(TALUK_CODE_KEY);
        /*String temp;*/
        while (cursor.moveToNext()) {
            /*temp = cursor.getString(nameIndex).trim();
            set.map.put(cursor.getInt(codeIndex), temp);
            set.names.add(temp);*/
            names.add(cursor.getString(nameIndex) + ":" + cursor.getInt(codeIndex));
        }

        cursor.close();
        /*return set;*/
        return names;
    }

    public ArrayList<String> getVillages(int districtCode, int talukCode) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(VILLAGE_TABLE, new String[]{VILLAGE_NAME_KEY, VILLAGE_CODE_KEY},
                DISTRICT_CODE_KEY + " = ? AND " + TALUK_CODE_KEY + " = ?", new String[]{String.valueOf(districtCode), String.valueOf(talukCode)}, null, null, null);
        if (cursor == null) return null;
        /*ResultSet set = new ResultSet();*/
        ArrayList<String> names = new ArrayList<String>();

        int nameIndex = cursor.getColumnIndex(VILLAGE_NAME_KEY);
        int codeIndex = cursor.getColumnIndex(VILLAGE_CODE_KEY);
        /*String temp;*/
        while (cursor.moveToNext()) {
            /*temp = cursor.getString(nameIndex).trim();
            set.map.put(cursor.getInt(codeIndex), temp);
            set.names.add(temp);*/
            names.add(cursor.getString(nameIndex) + ":" + cursor.getInt(codeIndex));
        }

        cursor.close();
        /*return set;*/
        return names;
    }


    /*public class ResultSet {
        public SparseArray<String> map;
        public ArrayList<String> names;
        public ResultSet(SparseArray<String> map, ArrayList<String> names) {
            this.map = map;
            this.names = names;
        }

        public ResultSet() {
            map = new SparseArray<String>();
            names = new ArrayList<String>();

        }
    }*/

}
