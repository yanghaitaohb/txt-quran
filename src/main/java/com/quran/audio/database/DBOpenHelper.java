package com.quran.audio.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.quran.audio.database.dao.SignDao;

/**
 * author: haitao
 * create at: 2019/1/21
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    private Context mContext;

    /**
     * 数据库版本号
     */
    private final static int DB_VERSION = 17;
    /**
     * 数据库名称
     */
    private final static String DB_NAME = "quran.db";


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SignDao.getCreateTableSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
