/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseAdapter.java
 * ClassName: BaseAdapter
 * LastModified: 10/11/17 3:33 PM
 */

package me.xjliao.xjlib.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;


/**
 * @param <T>
 * @ClassName: BaseAdapter
 * @Description: 基类适配器
 * @author: xjliao lxj990@gmail.com
 * @date: 2015-3-18 下午1:24:26
 */
public abstract class BaseAdapter<T extends BaseItem> extends android.widget.BaseAdapter {

    /**
     * @fieldName: mDatas
     * @fieldType: List<T>
     * @Description: 展示的数据
     */
    private List<T> mData;
    /**
     * @fieldName: mContext
     * @fieldType: Context
     * @Description: TODO
     */
    private Context mContext;
    /**
     * @fieldName: isNotifyDataSetChanged
     * @fieldType: boolean
     * @Description: 是否可修改
     */
    private boolean isNotifyDataSetChanged = true;

    /**
     * @fieldName: mCheckedPosition
     * @fieldType: int
     * @Description: 选定的位置
     */
    private int mCheckedPosition = -1;

    public BaseAdapter() {
        // TODO Auto-generated constructor stub
    }

    public BaseAdapter(List<T> data) {
        this.mData = data;
    }

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    public BaseAdapter(Context context, List<T> data) {
        this(context);
        this.mData = data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        // TODO Auto-generated method stub
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.mData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return prepareGetView(position, convertView, parent);
    }

    public abstract int getItemLayout();

    public abstract View prepareGetView(int position, View convertView, ViewGroup parent);

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        BaseViewHolder holder = null;
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(getItemLayout(), parent, false);
//
//            holder = new BaseViewHolder(convertView);
//            convertView.setTag(holder);
//        } else {
//            holder = (BaseViewHolder) convertView.getTag();
//        }
//
//        prepareGetView(holder, position);
//        return convertView;
//    }
//
//    public abstract int getItemLayout();
//
//    public abstract void prepareGetView(BaseViewHolder viewHolder, int position);

    /**
     * @return the datas
     */
    public List<T> getData() {
        return mData;
    }

    /**
     * @param data the datas to set
     */
    public void setData(List<T> data) {
        mData = data;
    }

    /**
     * @return the isNotifyDataSetChanged
     */
    public boolean isNotifyDataSetChanged() {
        return isNotifyDataSetChanged;
    }

    /**
     * @param isNotifyDataSetChanged the isNotifyDataSetChanged to set
     */
    public void setNotifyDataSetChanged(boolean isNotifyDataSetChanged) {
        this.isNotifyDataSetChanged = isNotifyDataSetChanged;
    }

    /**
     * @return the context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * @param context the context to set
     */
    public void setContext(Context context) {
        mContext = context;
    }

    public int getCheckedPosition() {
        return mCheckedPosition;
    }

    public void setCheckedPosition(int checkedPosition) {
        this.mCheckedPosition = checkedPosition;
    }

    protected static class BaseViewHolder {
        public BaseViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
