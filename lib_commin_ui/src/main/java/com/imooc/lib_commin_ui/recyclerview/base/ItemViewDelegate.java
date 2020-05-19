package com.imooc.lib_commin_ui.recyclerview.base;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ZhuPengFei
 * Create Date 2020/5/18 19:49
 * Used
 */
public interface ItemViewDelegate<T> {

	int getItemViewLayoutId();

	boolean isForViewType(T item, int position);

	void convert(ViewHolder holder, T t, int position);

}
