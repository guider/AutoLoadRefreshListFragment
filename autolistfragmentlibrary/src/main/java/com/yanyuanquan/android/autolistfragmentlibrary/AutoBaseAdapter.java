package com.yanyuanquan.android.autolistfragmentlibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


public abstract class AutoBaseAdapter<T> extends BaseAdapter {
    protected List<T> mDatas;
    protected int layoutId;

    public AutoBaseAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    public AutoBaseAdapter() {
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public AutoBaseAdapter(List<T> mList, int layoutId) {
        this.mDatas = mList;
        this.layoutId = layoutId;
    }

    public void setData(List<T> datas) {
//        if (datas == null) {
//            return;
//        }

        mDatas = datas;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mDatas;
    }


    public void append(List<T> datas) {
        if (mDatas == null)
            mDatas = new ArrayList<>();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void append(T t) {
        if (mDatas == null)
            mDatas = new ArrayList<>();
        mDatas.add(t);
        notifyDataSetChanged();
    }

    public void insert(T data, int position) {
        if (mDatas == null || mDatas.size() < position) {
            return;
        }
        mDatas.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mDatas == null || mDatas.size() < position) {
            return;
        }
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void appendBefore(List<T> datas) {
        if (datas == null)
            return;
        List<T> list = new ArrayList<>();
        list.addAll(datas);
        if (this.mDatas != null)
            list.addAll(mDatas);
        this.mDatas = list;
    }

    public void appendBefore(T t) {
        if (t == null)
            return;
        List<T> list = new ArrayList<>();
        list.add(t);
        if (mDatas != null)
            list.addAll(mDatas);

        this.mDatas = list;

    }

    public T getData(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AutoViewHolder holder = AutoViewHolder.getInstance(parent, convertView, layoutId);
        setView(mDatas.get(position), holder, parent.getContext());
        setView(mDatas.get(position), holder, parent.getContext(),position);
        return holder.getConvertView();
    }

    protected void setView(T t, AutoViewHolder holder, Context context, int position) {
    }


    protected abstract void setView(T t, AutoViewHolder holder, Context context);
}
