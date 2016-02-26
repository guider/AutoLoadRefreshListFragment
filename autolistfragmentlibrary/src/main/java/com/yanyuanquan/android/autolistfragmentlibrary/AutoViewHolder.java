package com.yanyuanquan.android.autolistfragmentlibrary;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Created by apple on 15/12/25.
 * @description:
 * @projectName:YYQDYB
 */
public class AutoViewHolder {
    private SparseArray<View> views;
    private View convertView;

    private AutoViewHolder( View convertView, int layoutId,ViewGroup parent) {
        this.convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        this.convertView.setTag(this);
        views = new SparseArray<View>();
    }

    public static AutoViewHolder getInstance(ViewGroup parent, View convertView, int layoutId) {
        if (convertView==null)
            return new AutoViewHolder(convertView,layoutId,parent);
        return (AutoViewHolder) convertView.getTag();
    }

    public <T extends View>T getView(int viewId){
        View view = views.get(viewId);
        if (view==null){
            view =convertView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }
}
