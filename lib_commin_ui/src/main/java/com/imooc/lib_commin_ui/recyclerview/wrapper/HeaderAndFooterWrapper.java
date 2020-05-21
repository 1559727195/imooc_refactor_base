package com.imooc.lib_commin_ui.recyclerview.wrapper;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imooc.lib_commin_ui.recyclerview.base.ViewHolder;
import com.imooc.lib_commin_ui.recyclerview.utils.WrapperUtils;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/21 19:16
 * Used
 */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private static final int BASE_ITEM_TYPE_HEADER = 100000;
	private static final int BASE_ITEM_TYPE_FOOTER = 200000;

	private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
	private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

	private RecyclerView.Adapter mInnerAdapter;

	public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
		mInnerAdapter = adapter;
	}

	@NonNull
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		if (mHeaderViews.get(viewType) != null) {
			ViewHolder holder =
					ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));

			return holder;

		} else if (mFootViews.get(viewType) != null) {
			ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(),
					mFootViews.get(viewType));

			return holder;


		}


		return mInnerAdapter.onCreateViewHolder(parent, viewType);


	}


	@Override
	public int getItemViewType(int position) {

		if(isHeaderViewPos(position)) {
			return mHeaderViews.keyAt(position);
		} else if (isFooterViewPos(position)) {
			return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
		}

		return mInnerAdapter.getItemViewType(position -getHeadersCount());
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return getHeadersCount() + getFootersCount() + getRealItemCount();
	}

	@Override
	public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {

		WrapperUtils.onAttachedToRecycleView(mInnerAdapter, recyclerView,
				new WrapperUtils.SpanSizeCallback() {
					@Override
					public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {

						int viewType = getItemViewType(position);
						if (mHeaderViews.get(viewType) != null) {
							return layoutManager.getSpanCount();
						} else if (mFootViews.get(viewType) != null) {
							return layoutManager.getSpanCount();
						}

						if (oldLookup != null) {
							return oldLookup.getSpanSize(position);
						}


						return  1;
					}
				});
	}

	@Override
	public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
		mInnerAdapter.onViewAttachedToWindow(holder);
		int position = holder.getLayoutPosition();
		if (isHeaderViewPos(position) || isFooterViewPos(position)) {
			WrapperUtils.setFullSpan(holder);
		}
	}

	private boolean isHeaderViewPos (int position) {
		return position < getHeadersCount();
	}

	private boolean isFooterViewPos(int position) {
		return position >= getHeadersCount() + getRealItemCount();
	}



	public void addHeaderView(View view) {
		mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_FOOTER,view);
	}

	public void addFootView (View view) {
		mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER,view);
	}

	private int getRealItemCount() {
		return mInnerAdapter.getItemCount();
	}


	public int getHeadersCount() {
		return mHeaderViews.size();
	}

	public int getFootersCount() {
		return mFootViews.size();
	}
}
