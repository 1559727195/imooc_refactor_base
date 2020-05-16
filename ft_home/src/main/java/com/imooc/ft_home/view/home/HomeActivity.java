package com.imooc.ft_home.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.imooc.ft_home.R;
import com.imooc.lib_commin_ui.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/16 18:28
 * Used
 * 首页Activity
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {


	public static void start(Context context) {
		Intent intent = new Intent(context, HomeActivity.class);
		context.startActivity(intent);
	}

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//EventBus.getDefault().register(this);
		setContentView(R.layout.activity_main);
;
	}

	@Override
	public void onClick(View v) {

	}
}
