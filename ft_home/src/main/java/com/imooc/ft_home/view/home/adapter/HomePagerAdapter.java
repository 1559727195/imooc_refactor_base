package com.imooc.ft_home.view.home.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.imooc.ft_home.model.CHANNEL;
import com.imooc.ft_home.view.FriendFragment;
import com.imooc.ft_home.view.discory.DiscoryFragment;
import com.imooc.ft_home.view.mine.MineFragment;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/18 19:10
 * Used
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
	private CHANNEL[] mList;

	public HomePagerAdapter(FragmentManager fm, CHANNEL[] datas) {
		super(fm);
		mList = datas;
	}

	//这种方式，避免一次性创建所有的framgent
	@Override public Fragment getItem(int position) {
		int type = mList[position].getValue();
		switch (type) {
			case CHANNEL.MINE_ID:
				return MineFragment.newInstance();
			case CHANNEL.DISCORY_ID:
				return DiscoryFragment.newInstance();
			case CHANNEL.FRIEND_ID:
				return FriendFragment.newInstance();
		}
		return null;
	}

	@Override public int getCount() {
		return mList == null ? 0 : mList.length;
	}
}
