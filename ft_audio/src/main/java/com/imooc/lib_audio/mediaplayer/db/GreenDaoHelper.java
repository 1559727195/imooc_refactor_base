package com.imooc.lib_audio.mediaplayer.db;

import android.database.sqlite.SQLiteDatabase;

import com.imooc.lib_audio.app.AudioHelper;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/26 19:04
 * Used
 * 操作greenDao数据库帮助类
 */
public class GreenDaoHelper {
	private static final String DB_BAME = "music_db";

	private static DaoMaster.DevOpenHelper mHelper;
	private static SQLiteDatabase mDb;
	//管理数据库
	private static DaoMaster mDaoMaster;
	//管理各种实体Dao,不让业务层拿到session直接去操作数据库，统一由此类提供方法
	private static DaoSession mDaoSession;


	/**
	 * 设置greenDao
	 */
	public static void initDatabase() {
		mHelper = new DaoMaster.DevOpenHelper(AudioHelper.get)
	}

}
