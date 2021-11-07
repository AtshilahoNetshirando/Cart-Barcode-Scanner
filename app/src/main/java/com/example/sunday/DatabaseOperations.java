package com.example.sunday;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseOperations   extends SQLiteOpenHelper {

    public  static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+ TableData.TableInfo.PRODUCT_ID+" TEXT," + TableData.TableInfo.PRODUCT_NUMBER+" TEXT,"+TableData.TableInfo.COUNT+" TEXT );";
    public DatabaseOperations(@Nullable Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");


    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void putInformation(DatabaseOperations dop, String p_id,String prd_number, String count){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.PRODUCT_ID,p_id);
        cv.put(TableData.TableInfo.PRODUCT_NUMBER, prd_number);
        cv.put(TableData.TableInfo.COUNT,count);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null,cv);
        Log.d("Database operations", "One row inserted");
    }

    public Cursor getInformation(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.PRODUCT_ID,TableData.TableInfo.PRODUCT_NUMBER,TableData.TableInfo.COUNT};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return CR;
    }

    public boolean updateUserInfo(String p_id, String p_number,String counter){
        SQLiteDatabase SQ= this.getWritableDatabase();
        String selection = TableData.TableInfo.PRODUCT_ID + " = ?";
        String args[] = {p_id};
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.PRODUCT_ID, p_id);
        contentValues.put(TableData.TableInfo.PRODUCT_NUMBER, p_number);
        contentValues.put(TableData.TableInfo.COUNT, counter);
        SQ.update(TableData.TableInfo.TABLE_NAME,contentValues,selection,new String[] { p_id} );
        return true;
    }
}
