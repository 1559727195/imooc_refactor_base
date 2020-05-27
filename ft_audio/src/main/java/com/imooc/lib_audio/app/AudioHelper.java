package com.imooc.lib_audio.app;

import android.content.Context;

import com.imooc.lib_audio.mediaplayer.core.MusicService;
import com.imooc.lib_audio.mediaplayer.db.GreenDaoHelper;
import com.imooc.lib_base.ft_audio.model.CommonAudioBean;

import java.util.ArrayList;

/**
 * 唯一与外界通信的帮助类
 */
public final class AudioHelper {
	//SDK全局Context, 供子模块用
	private static Context mContext;

	public static void init(Context context) {
		mContext = context;
		//初始化本地数据库
		GreenDaoHelper.initDatabase();
	}


	//外部启动MusicService方法
	public static void startMusicService(ArrayList<CommonAudioBean> audios) {
		MusicService.st
	}



	public static Context getContext() {
		return mContext;
	}

}