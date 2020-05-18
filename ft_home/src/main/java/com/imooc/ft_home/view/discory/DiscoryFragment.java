package com.imooc.ft_home.view.discory;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.imooc.ft_home.R;

import okhttp3.internal.http.RetryableSink;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/18 19:19
 * Used
 */
public class DiscoryFragment extends Fragment {

	private Context mContext;

	/**
	 * UI
	 * @return
	 */

	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerView mRecyclerView;





	public static Fragment newInstance() {
		DiscoryFragment fragment = new DiscoryFragment();
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_discory_layout,null);
		return view;


	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
}
