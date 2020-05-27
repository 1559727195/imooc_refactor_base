package com.imooc.lib_audio.mediaplayer.core;

import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.imooc.lib_audio.mediaplayer.events.AudioProgressEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/26 19:03
 * Used
 * 播放器事件源
 */
public class AudioPlayer  implements MediaPlayer.OnCompletionListener,
MediaPlayer.OnBufferingUpdateListener,
MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener,
AudioFocusManager.AudioFocusListener{

	private static final String TAG = "AudioPlayer";
	private static final int TIME_MSG = 0x01;
	private static final int TIME_INVAL = 100;
	//真正负责播放的核心MediaPlayer子类
	private CustomMediaPlayer mMediaPlayer;
	private WifiManager.WifiLock mWifiLock;
	//音频焦点监听器
	private AudioFocusManager mAudioFocusManager;
	private boolean isPausedByFocusLossTransient;



	//播放进度更新handler
	private Handler mHandler = new Handler(Looper.getMainLooper()) {
		@Override public void handleMessage(Message msg) {
			switch (msg.what) {
				case TIME_MSG:
					//暂停也要更新进度，防止UI不同步，只不过进度一直一样
					if (getStatus() == CustomMediaPlayer.Status.STARTED
							|| getStatus() == CustomMediaPlayer.Status.PAUSED) {
						//UI类型处理事件
						EventBus.getDefault()
								.post(new AudioProgressEvent(getStatus(), getCurrentPosition(), getDuration()));
						sendEmptyMessageDelayed(TIME_MSG, TIME_INVAL);
					}
					break;
			}
		}
	};

	/**
	 * 完成唯一的mediaplayer初始化
	 * @return
	 */
	public AudioPlayer() {
		init();
	}


	//初始化播放器相关对象
	private void init() {
		mMediaPlayer = new CustomMediaPlayer();

		//使用唤醒锁
		mMediaPlayer.setWakeMode();
	}

	public int getCurrentPosition() {
		if (getStatus() == CustomMediaPlayer.Status.STARTED
				|| getStatus() == CustomMediaPlayer.Status.PAUSED) {
			return mMediaPlayer.getCurrentPosition();
		}
		return 0;
	}

	public void setVolumn(float left, float right) {
		if (mMediaPlayer != null) mMediaPlayer.setVolume(left, right);
	}


	/**
	 * 获取当前音乐总时长,更新进度用
	 */
	public int getDuration() {
		if (getStatus() == CustomMediaPlayer.Status.STARTED
				|| getStatus() == CustomMediaPlayer.Status.PAUSED) {
			return mMediaPlayer.getDuration();
		}
		return 0;
	}


	//获取播放器状态
	public CustomMediaPlayer.Status getStatus() {
		if (mMediaPlayer != null) {
			return mMediaPlayer.getState();
		} else {
			return CustomMediaPlayer.Status.STOPPED;
		}
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {

	}

	@Override
	public void onCompletion(MediaPlayer mp) {

	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {

	}

	@Override
	public void audioFocusGrant() {

	}

	@Override
	public void audioFocusLoss() {

	}

	@Override
	public void audioFocusLossTransient() {

	}

	@Override
	public void audioFocusLossDuck() {

	}
}
