package com.quran.audio.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.quran.audio.database.SqlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * author: haitao
 * create at: 2019/1/21
 */
public class SignDao {

    /**
     * 表名
     */
    private static String TABLE_NAME = "sign_info";

    protected static SQLiteOpenHelper mDBOpenHelp;

    public SignDao(SQLiteOpenHelper mDBOpenHelp) {
        this.mDBOpenHelp = mDBOpenHelp;
    }


    public void insert(String date) {
        ContentValues values = new ContentValues();
        values.put("signDate",date);
        mDBOpenHelp.getWritableDatabase().insert(TABLE_NAME,null,values);
    }

    public List<String> queryAll() {
        List<String> categoryList = new ArrayList<String>();
        SQLiteDatabase readableDatabase = mDBOpenHelp.getReadableDatabase();
        Cursor cursor = readableDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("signDate"));
                categoryList.add(name);
            }
            cursor.close();
        }

        return categoryList;
    }
    /**
     * 建表语句
     *
     * @return sql的建表语句
     */
    public static String getCreateTableSql() {

        return SqlUtils.createSqlCreate(TABLE_NAME,
                new String[]{"signDate varchar(50) primary key "});
    }
}
