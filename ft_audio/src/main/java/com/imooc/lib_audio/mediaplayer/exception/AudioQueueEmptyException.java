package com.imooc.lib_audio.mediaplayer.exception;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/26 19:05
 * Used
 *  播放队列为空异常
 */
public class AudioQueueEmptyException extends RuntimeException {
	public AudioQueueEmptyException(String error) {
		super(error);
	}
}
