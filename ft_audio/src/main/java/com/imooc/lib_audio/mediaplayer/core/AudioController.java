package com.imooc.lib_audio.mediaplayer.core;

import com.imooc.lib_audio.mediaplayer.exception.AudioQueueEmptyException;
import com.imooc.lib_audio.mediaplayer.model.AudioBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/26 19:03
 * Used
 * 控制播放逻辑类，注意添加一些控制方法时，要考虑是否需要增加Event,来更新UI
 */
public class AudioController {

	/**
	 * 播放方式
	 */
	public enum PlayMode {
		/**
		 * 列表循环
		 */
		LOOP,
		/**
		 * 随机
		 */
		RANDOM,
		/**
		 * 单曲循环
		 */
		REPEAT
	}

	private AudioPlayer mAudioPlayer;
	//播放队列,不能为空,不设置主动抛错
	private ArrayList<AudioBean> mQueue = new ArrayList<>();
	private int mQueueIndex = 0;
	private PlayMode mPlayMode = PlayMode.LOOP;

	private AudioController() {
		EventBus.getDefault().register(this);
		mAudioPlayer = new AudioPlayer();
	}

	private void addCustomAudio(int index, AudioBean bean) {
		if (mQueue == null) {
			throw new AudioQueueEmptyException("当前播放队列为空,请先设置播放队列.");
		}

		mQueue.add(index,bean);
	}

	private int queryAudio(AudioBean bean) {
		return mQueue.indexOf(bean);
	}


	private void load(AudioBean bean) {
		mAudioPlayer.load(bean);
	}


}
