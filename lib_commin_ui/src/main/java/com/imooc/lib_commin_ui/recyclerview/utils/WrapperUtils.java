package com.imooc.lib_commin_ui.recyclerview.utils;

import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/19 19:41
 * Used
 */
public class WrapperUtils {
	public interface SpanSizeCallback {
		int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup
				oldLookup, int position);

	}

	public static void onAttachedToRecycleView(RecyclerView.Adapter innerAdapter,
											   RecyclerView recyclerView,
											   final SpanSizeCallback callback) {

		innerAdapter.onAttachedToRecyclerView(recyclerView);


		RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();


		if (layoutManager instanceof GridLayoutManager) {
			final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
			final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
		 gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			 @Override
			 public int getSpanSize(int position) {
				 return callback.getSpanSize(gridLayoutManager,spanSizeLookup,position);
			 }
		 });

		 gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());

		}


	}



	public static void setFullSpan(RecyclerView.ViewHolder holder) {

		ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();


		
	}

}
