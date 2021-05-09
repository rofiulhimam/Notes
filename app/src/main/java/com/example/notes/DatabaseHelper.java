package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "notes";
    private static final String TABLE_NAME = "tbl_notes";
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESC = "description";

    public DatabaseHelper (Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT, " + KEY_DESC + " TEXT)";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insert(NotesModels notesModels){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,notesModels.getTitle());
        values.put(KEY_DESC,notesModels.getDesc());
        db.insert(TABLE_NAME,null,values);
    }

    public List<NotesModels> selectUserData(){
        ArrayList<NotesModels> notesList=new ArrayList<NotesModels>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (c.moveToNext()){
            int id = c.getInt(0);
            String title=c.getString(1);
            String desc=c.getString(2);
            NotesModels notesModels = new NotesModels();
            notesModels.setId(id);
            notesModels.setTitle(title);
            notesModels.setDesc(desc);
            notesList.add(notesModels);
        }
        return notesList;
    }

    public void delete(int id){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=KEY_ID+"='"+id+"'";
        Log.d("idvalues", ""+id);
        db.delete(TABLE_NAME,whereClause,null);
    }
    public void update(NotesModels notesModels, int id){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,notesModels.getTitle());
        values.put(KEY_DESC,notesModels.getDesc());
        String whereClause=KEY_ID+"='"+notesModels.getId()+"'";
        Log.d("idvalues", ""+values+" "+whereClause);
        db.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
    }
}
