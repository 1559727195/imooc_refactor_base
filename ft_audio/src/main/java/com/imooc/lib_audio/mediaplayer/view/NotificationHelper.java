package com.imooc.lib_audio.mediaplayer.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.imooc.lib_audio.R;
import com.imooc.lib_audio.app.AudioHelper;
import com.imooc.lib_audio.mediaplayer.core.AudioController;
import com.imooc.lib_audio.mediaplayer.core.MusicService;
import com.imooc.lib_audio.mediaplayer.db.GreenDaoHelper;
import com.imooc.lib_audio.mediaplayer.model.AudioBean;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/26 19:08
 * Used
 * 音乐Notification帮助类
 */
public class NotificationHelper {
	public static final String CHANNEL_ID = "channel_id_audio";
	public static final String CHANNEL_NAME = "channel_name_audio";
	public static final int NOTIFICATION_ID = 0x111;

	//最终的Notification显示类
	private Notification mNotification;
	private RemoteViews mRemoteViews;
	private RemoteViews mSmallRemoteViews;
	private NotificationManager mNotificationManager;
	private NotificationHelperListener mListener;
	private String packageName;

	//当前要播放的歌曲Bean
	private AudioBean mAudioBean;

	public static NotificationHelper getInstance() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {
		private static NotificationHelper instance = new NotificationHelper();
	}

	public void init (NotificationHelperListener listener) {
		mNotificationManager = (NotificationManager) AudioHelper.getContext()
		.getSystemService(Context.NOTIFICATION_SERVICE);

		packageName = AudioHelper.getContext().getPackageName();
		mAudioBean = AudioController.getInstance().getNowPlaying();
		init

	}


	/**
	 * 创建Notification
	 */
	private void initNotification () {
		if (mNotification == null) {
			//首先创建布局
			initRemoteViews();
			//在构建NOtification
			Intent intent = new Intent(AudioHelper.getContext(), MusicPlayerActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(
					AudioHelper.getContext(),0,intent,
					PendingIntent.FLAG_UPDATE_CURRENT
			);

			//适配安卓8.0的消息渠道
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

				NotificationChannel channel =
						new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
								NotificationManager.IMPORTANCE_HIGH);
				channel.enableLights(false);
				channel.enableVibration(false);
				mNotificationManager.createNotificationChannel(channel);
			}

			NotificationCompat.Builder builder =
					new NotificationCompat.Builder(AudioHelper.getContext(),CHANNEL_ID)
					.setContentIntent(
							pendingIntent
					).setSmallIcon(R.mipmap.ic_launcher)
					.setCustomBigContentView(mRemoteViews)//大布局
					.setContent(mSmallRemoteViews);//正常布局，两个布局可以切换
			mNotification = builder.build();
			show
		}
	}

	/**
	 * 显示Notification的加载状态
	 */

	public void showLoadStatus (AudioBean bean) {
		//防止空指针crash
		mAudioBean = bean;
		if (mRemoteViews != null) {
			mRemoteViews.setImageViewResource(R.id.play_view, R.mipmap.note_btn_pause_white);
			mRemoteViews.setTextViewText(R.id.title_view, mAudioBean.name);
			mRemoteViews.setTextViewText(R.id.tip_view, mAudioBean.album);
		}
	}

	/**
	 * 创建Notification的布局,默认布局为Loading状态
	 */
	private void initRemoteViews () {
		int layoutId = R.layout.notification_big_layout;
		mRemoteViews = new RemoteViews(packageName, layoutId);
		mRemoteViews.setTextViewText(R.id.title_view, mAudioBean.name);
		mRemoteViews.setTextViewText(R.id.tip_view, mAudioBean.album);
		if (null != GreenDaoHelper.selectFavourite(mAudioBean)) {
			mRemoteViews.setImageViewResource(R.id.favourite_view, R.mipmap.note_btn_loved);
		} else {
			mRemoteViews.setImageViewResource(R.id.favourite_view, R.mipmap.note_btn_love_white);
		}

		int smalllayoutId = R.layout.notification_small_layout;
		mSmallRemoteViews = new RemoteViews(packageName, smalllayoutId);
		mSmallRemoteViews.setTextViewText(R.id.title_view, mAudioBean.name);
		mSmallRemoteViews.setTextViewText(R.id.tip_view, mAudioBean.album);

		//点击播放按钮广播
		Intent playIntent = new Intent(MusicService.NotifacationReceiver.ACTION_STATUS_BAR)
		playIntent.putExtra(MusicService.NotifacationReceiver.EXTRA,
				MusicService.NotifacationReceiver.EXTRA_PLAY);
		PendingIntent playPendingIntent = PendingIntent.getBroadcast(
				AudioHelper.getContext(),1,playIntent,
				PendingIntent.FLAG_UPDATE_CURRENT
		);

		mRemoteViews.setOnClickPendingIntent(R.id.play_view,playPendingIntent);

		mRemoteViews.setImageViewResource(R.id.play_view, R.mipmap.note_btn_play_white);
		mSmallRemoteViews.setOnClickPendingIntent(R.id.play_view, playPendingIntent);
		mSmallRemoteViews.setImageViewResource(R.id.play_view, R.mipmap.note_btn_play_white);

		//点击上一首按钮广播
		Intent previousIntent = new Intent(MusicService.NotifacationReceiver.ACTION_STATUS_BAR);
		previousIntent.putExtra(MusicService.NotifacationReceiver.EXTRA,
				MusicService.NotifacationReceiver.EXTRA_PRE);
		PendingIntent previousPendingIntent =
				PendingIntent.getBroadcast(AudioHelper.getContext(), 2, previousIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.previous_view, previousPendingIntent);
		mRemoteViews.setImageViewResource(R.id.previous_view, R.mipmap.note_btn_pre_white);

		//点击下一首按钮广播
		Intent nextIntent = new Intent(MusicService.NotifacationReceiver.ACTION_STATUS_BAR);
		nextIntent.putExtra(MusicService.NotifacationReceiver.EXTRA,
				MusicService.NotifacationReceiver.EXTRA_PRE);
		PendingIntent nextPendingIntent =
				PendingIntent.getBroadcast(AudioHelper.getContext(), 3, nextIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.next_view, nextPendingIntent);
		mRemoteViews.setImageViewResource(R.id.next_view, R.mipmap.note_btn_next_white);
		mSmallRemoteViews.setOnClickPendingIntent(R.id.next_view, nextPendingIntent);
		mSmallRemoteViews.setImageViewResource(R.id.next_view, R.mipmap.note_btn_next_white);


		//点击收藏按钮广播
		Intent favouriteIntent = new Intent(MusicService.NotifacationReceiver.ACTION_STATUS_BAR);
		favouriteIntent.putExtra(MusicService.NotifacationReceiver.EXTRA,
				MusicService.NotifacationReceiver.EXTRA_FAV);
		PendingIntent favouritePendingIntent =
				PendingIntent.getBroadcast(AudioHelper.getContext(),4,favouriteIntent
				,PendingIntent.FLAG_UPDATE_CURRENT);
		mRemoteViews.setOnClickPendingIntent(R.id.favourite_view, favouritePendingIntent);
	}

	/**
	 * 与音乐service的回调通信
	 */
	public interface NotificationHelperListener {
		void onNotificationInit();
	}
}
