package com.imooc.lib_audio.mediaplayer.core;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.imooc.lib_audio.app.AudioHelper;
import com.imooc.lib_audio.mediaplayer.model.AudioBean;
import com.imooc.lib_audio.mediaplayer.view.NotificationHelper;

import java.util.ArrayList;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/26 19:03
 * Used
 * 音乐后台服务，并更新notification状态
 */
public class MusicService extends Service implements NotificationHelper.NotificationHelperListener {

	private static String DATA_AUDIOS = "AUDIOS";
	//actions
	private static String ACTION_START = "ACTION_START";

	private ArrayList<AudioBean> mAudioBeans;

	private NotificationReceiver mReceiver;

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onNotificationInit() {

	}

	/**
	 * 接收Notification发送的广播
	 */
	public static class NotifacationReceiver extends BroadcastReceiver {

		public static final String ACTION_STATUS_BAR =
				AudioHelper.getContext().getPackageName() + ".NOTIFICATION_ACTIONS";

		public static final String EXTRA = "extra";
		public static final String EXTRA_PLAY = "play_pause";
		public static final String EXTRA_NEXT = "play_next";
		public static final String EXTRA_PRE = "play_previous";
		public static final String EXTRA_FAV = "play_favourite";

		@Override
		public void onReceive(Context context, Intent intent) {
   			 if (intent == null || TextUtils.isEmpty(intent.getAction())) {
   			 	return;
			 }
   			 String extra = intent.getStringExtra(EXTRA);
   			 switch (extra) {
				 case EXTRA_PLAY:
					 //处理播放暂停事件,可以封到AudioController中
					 AudioController.getInstance().playOrPause();
					 break;
				 case EXTRA_PRE:
					 AudioController.getInstance().previous(); //不管当前状态，直接播放
					 break;
				 case EXTRA_NEXT:
					 AudioController.getInstance().next();
					 break;
				 case EXTRA_FAV:
					 AudioController.getInstance().changeFavourite();
					 break;
			 }
		}
	}
}
