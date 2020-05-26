package com.imooc.lib_audio.mediaplayer.core;

import android.media.MediaPlayer;

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
