package com.lecarlink.zframwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 鸿洋大神listview adapter 根据listvew的功能需求选择
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected int layoutId;

	
	public List<T> getmDatas() {
		return mDatas;
	}
	public void addMData(List<T> list)
	{
		mDatas.addAll(list);
		this.notifyDataSetChanged();
	}
	public void setmDatas(List<T> mDatas) {
		this.mDatas = mDatas;
		this.notifyDataSetChanged();
	}

	public CommonAdapter(Context ctx, List<T> lists, int layoutId) {
		this.mDatas = lists;
		mInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mContext = ctx;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CommonViewHolder vh = CommonViewHolder.getViewHolder(convertView,
				mContext, parent, layoutId, position);
		convert(vh, mDatas.get(position),position);
		return vh.getConvertView();
	}

	abstract protected void convert(CommonViewHolder vh, T item,int position);
}
