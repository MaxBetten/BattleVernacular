package com.example.battleofvernacular;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, int score, String cats)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MYSCORE, score);
        contentValues.put(myDbHelper.MYCategories, cats);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }
    public void clearData()
    {
        String masters = "";
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.MYCategories,masters);
        db.update(myDbHelper.TABLE_NAME,contentValues,"_id="+myDbHelper.UID, null);
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MYSCORE,myDbHelper.MYCategories};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            int score = cursor.getInt(cursor.getColumnIndex(myDbHelper.MYSCORE));

            String categories = cursor.getString(cursor.getColumnIndex(myDbHelper.MYCategories));

            buffer.append(cid + ":" + name + ":" + score+":"+categories);
        }
        return buffer.toString();
    }
    public String getMastered()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MYSCORE,myDbHelper.MYCategories};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            int score = cursor.getInt(cursor.getColumnIndex(myDbHelper.MYSCORE));
            String categories = cursor.getString(cursor.getColumnIndex(myDbHelper.MYCategories));
            buffer.append(categories);
        }
        return buffer.toString();
    }
    public void updateMasterCat(String cat)
    {
        String masters = this.getMastered() + cat;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.MYCategories,masters);
        db.update(myDbHelper.TABLE_NAME,contentValues,"_id="+myDbHelper.UID, null);
    }
    public void updateScore(int oldScore , int newScore)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.MYSCORE,Integer.toString(newScore));
        //String oldscore = Integer.toString((oldScore));
        //String[] whereArgs= {Integer.toString(oldScore)};
        //db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        db.update(myDbHelper.TABLE_NAME,contentValues,"_id="+myDbHelper.UID, null);
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String MYSCORE = "Score";   // Column III
        private static final String  MYCategories = "Categories";

        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MYSCORE+" VARCHAR(225), "+MYCategories+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {

                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}