package com.yanyuanquan.android.autolistfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yanyuanquan.android.autolistfragmentlibrary.AutoListFragment;
import com.yanyuanquan.android.autolistfragmentlibrary.AutoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/2/26.
 *  不用写一行xml文件 实现下拉刷新下拉加载
 *
 */
public class ContainFragment extends AutoListFragment<Entity> {
    List<Entity> entities = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.item_listview;
    }

    @Override
    protected void setListData(Entity entity, AutoViewHolder holder, Context context) {
        ((TextView)holder.getView(R.id.tv)).setText(entity.getTitle());
    }

    @Override
    protected boolean canRefresh() {
        return true;
    }

    @Override
    protected void initView() {
        // 可是定制EmptyView
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.empty,null);
        setListViewEmptyView(v);


        for (int i= 0;i<20; i++){
            entities.add(new Entity(("测试"+i),i));
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setListAdapter();
                getAdapter().setData(entities);
            }
        },1000);

    }

    @Override
    protected void refresh() {
        List<Entity> entities2 = new ArrayList<>();

        getAdapter().setData(null);
        setRefreshComplete();
    }
    List<Entity> entities3 = new ArrayList<>();
    @Override
    protected void loadMoreData() {
        new Thread(){
            @Override
            public void run() {
                super.run();

                for (int i= 0;i<20; i++){
                    entities3.add(new Entity(("测试3"+System.currentTimeMillis()/1000),i));
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getAdapter().append(entities3);
                        /**
                         *  是否还有更多数据
                         */
                        setHasMore(true);

                        getListView().onLoadMoreComplete();
                    }
                },2000);
            }
        }.start();


    }

    private android.os.Handler handler = new android.os.Handler();
    @Override
    protected boolean hasModeData() {
        setOnLoadModeListener();
        return true;
    }


}
