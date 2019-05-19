package com.example.zutshidk.bmi_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, "deeps", null, 1);
        //SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table deeps1(id INTEGER PRIMARY KEY AUTOINCREMENT,msg VARCHAR(12))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS deeps1");
        onCreate(sqLiteDatabase);

    }
    public boolean isinsert(String msg){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put("ht",ht);
        //values.put("wt",wt);
        values.put("msg",msg);
        long res=sqLiteDatabase.insert("deeps1",null,values);
        if(res==-1)
            //Log.d("DB456","insert issue");
            return  false;
        else
            return true;
            //Log.d("DB456","Inserted");
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select * from  deeps1",null);
        // if(res!=null){
        //res.moveToFirst();}
        return res;

    }
}
