package com.imooc.lib_commin_ui.recyclerview.wrapper;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imooc.lib_commin_ui.recyclerview.base.ViewHolder;
import com.imooc.lib_commin_ui.recyclerview.utils.WrapperUtils;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/21 19:07
 * Used
 */
public class EmptyWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	public static final int ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1;

	private RecyclerView.Adapter mInnerAdapter;
	private View mEmptyView;
	private int mEmptyLayoutId;

	public EmptyWrapper(RecyclerView.Adapter adapter) {
		mInnerAdapter = adapter;
	}


	private boolean isEmpty() {
		return (mEmptyView != null || mEmptyLayoutId != 0) && mInnerAdapter.getItemCount() == 0;
	}


	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		if (isEmpty()) {

			ViewHolder holder;
			if (mEmptyView != null) {
				holder = ViewHolder.createViewHolder(parent.getContext(), mEmptyView);
			} else {
				holder = ViewHolder.createViewHolder(parent.getContext(), parent,
						mEmptyLayoutId);
				return holder;
			}
			return mInnerAdapter.onCreateViewHolder(parent, viewType);
		}


		return null;
	}

	@Override
	public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
		WrapperUtils.onAttachedToRecycleView(mInnerAdapter, recyclerView,
				new WrapperUtils.SpanSizeCallback() {
					@Override
					public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {

						if (isEmpty()) {
							return layoutManager.getSpanCount();
						}

						if (oldLookup != null) {
							return oldLookup.getSpanSize(position);
						}


						return 1;


					}
				});
	}

	@Override
	public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
		mInnerAdapter.onViewAttachedToWindow(holder);
	}


	@Override
	public int getItemViewType(int position) {


		if (isEmpty()) {
			return ITEM_TYPE_EMPTY;
		}
		return mInnerAdapter.getItemViewType(position);
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

		if (isEmpty()) return;

		mInnerAdapter.onBindViewHolder(holder,position);


	}

	@Override
	public int getItemCount() {

		if (isEmpty()) return 1;
		return mInnerAdapter.getItemCount();
	}


	public void setEmptyView(View emptyView) {
		mEmptyView = emptyView;
	}

	public void setEmptyView(int layoutId) {
		mEmptyLayoutId = layoutId;
	}
}
