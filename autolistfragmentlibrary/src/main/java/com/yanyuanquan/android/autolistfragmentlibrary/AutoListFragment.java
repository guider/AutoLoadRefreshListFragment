package com.yanyuanquan.android.autolistfragmentlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * @Created by apple on 16/1/5.
 * @description:可上啦刷新，下拉加载的ListView，
 * @projectName:YYQ
 *
 * @email guider@yeah.net
 */
public abstract class AutoListFragment<T> extends AutoBaseFragment implements SwipeRefreshLayout.OnRefreshListener
        ,AutoLoadListView.onLoadMoreLinstener{
    private AutoLoadListView listView;
    private TextView tv;
    private SwipeRefreshLayoutCompat swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout root = new FrameLayout(activity);
        //---- For Empty ListVeiw
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setVisibility(View.GONE);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ProgressBar progressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyleLarge);
        linearLayout.addView(progressBar, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root.addView(linearLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //---------ListView
        FrameLayout frameLayout = new FrameLayout(activity);
        TextView tv = new TextView(activity);
        tv.setId(R.id.textview);
        tv.setGravity(Gravity.CENTER);
        frameLayout.addView(tv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        AutoLoadListView lv = new AutoLoadListView(activity);
        lv.setId(R.id.listview);
        SwipeRefreshLayoutCompat swipeRefreshLayout = new SwipeRefreshLayoutCompat(activity);
        swipeRefreshLayout.setId(R.id.swiperefreshlayout);
        swipeRefreshLayout.addView(lv, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        frameLayout.addView(swipeRefreshLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        root.addView(frameLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //------------root layoutparmas
        root.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initAdapter();
        initView();
        initSetting();
        listView.getParent().requestDisallowInterceptTouchEvent(true);

    }
    protected AutoBaseAdapter getAdapter(){
        return adapter;
    }
    private void initAdapter() {
        adapter = new AutoBaseAdapter<T>() {
            @Override
            protected void setView(T t, AutoViewHolder holder, Context context) {
                setListData(t, holder,context);
            }
        };
        adapter.setLayoutId(setlayoutId());
        setListAdapter(adapter);
    }

    protected abstract int setlayoutId();

    /**
     *
     */
    protected void initSetting(){
        setSwipeRefresLayoutCanRefresh(canRefresh());
        setHasMore(hasModeData());
    }

    protected abstract boolean hasModeData();

    AutoBaseAdapter<T> adapter ;

    protected abstract void setListData(T t, AutoViewHolder holder, Context context);


    protected abstract boolean canRefresh();

    protected abstract void initView();

    private void init() {
        listView = (AutoLoadListView) getView().findViewById(R.id.listview);
        tv = (TextView) getView().findViewById(R.id.textview);
        swipeRefreshLayout = (SwipeRefreshLayoutCompat) getView().findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        setdiverHeight(0);


    }

    public void setListAdapter(ListAdapter adapter) {
        if (listView == null) {
            throw new IllegalStateException("listview is null");
        }
        listView.setAdapter(adapter);
        tv.setText("暂无数据");
        listView.setEmptyView(tv);
    }

    /**\
     *   是否可以下拉刷新
     * @param canRefresh
     */
    protected void setSwipeRefresLayoutCanRefresh(boolean canRefresh){
        swipeRefreshLayout.setCanRefresh(canRefresh);
    }
    protected AutoLoadListView getListView(){
        return listView;
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    protected abstract void refresh();
    protected void onRefreshComplete(){
        swipeRefreshLayout.setRefreshing(false);
    }
    protected void setLoadMoreComplete(){
        listView.onLoadMoreComplete();
    }
    protected void setHasMore(boolean hasMore){
        listView.setHasMore(hasMore);
    }
    protected void setOnLoadModeListener(){
        listView.setOnLoadMoreLinstener(this);
    }
    protected void setdiverHeight(int height){
        listView.setDividerHeight(0);
    }
    protected void setDivider(int deawableId){
        listView.setDivider(getResources().getDrawable(deawableId));
    }

    @Override
    public void loadMore() {
        loadMoreData();
    }

    protected abstract void loadMoreData();

}
