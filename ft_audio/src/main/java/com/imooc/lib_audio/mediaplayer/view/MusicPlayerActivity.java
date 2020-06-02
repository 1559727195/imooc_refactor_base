package com.imooc.lib_audio.mediaplayer.view;

import android.animation.Animator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.imooc.lib_audio.R;
import com.imooc.lib_audio.mediaplayer.core.AudioController;
import com.imooc.lib_audio.mediaplayer.model.AudioBean;
import com.imooc.lib_commin_ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.PushbackInputStream;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/26 19:08
 * Used
 * 播放音乐Activity
 */
@Route(path = "/audio/music_activity")
public class MusicPlayerActivity extends BaseActivity {
	private RelativeLayout mBgView;
	private TextView mInfoView;
	private TextView mAuthorView;

	private ImageView mFavouriteView;

	private SeekBar mProgressView;
	private TextView mStartTimeView;
	private TextView mTotalTimeView;

	private ImageView mPlayModeView;
	private ImageView mPlayView;
	private ImageView mNextView;
	private ImageView mPreViousView;

	private Animator animator;
	/**
	 * data
	 */
	private AudioBean mAudioBean; //当前正在播放歌曲
	private AudioController.PlayMode mPlayMode;

	public static void start(Activity context) {
		Intent intent = new Intent(context,MusicPlayerActivity.class);
		ActivityCompat.startActivity(context,intent,
				ActivityOptionsCompat.makeSceneTransitionAnimation(
						context
				).toBundle());
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//添加入场动画
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().setEnterTransition(
					TransitionInflater.from(this)
					.inflateTransition(R.transition.transition_bottom2top)
			);

			EventBus.getDefault().register(this);
			EventBus.getDefault().register(this);
			setContentView(R.layout.activity_music_service_layout);
			initData();
			initView();;

		}
	}
}
