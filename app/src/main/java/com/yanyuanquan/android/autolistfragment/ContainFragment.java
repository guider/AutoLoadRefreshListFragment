package com.yanyuanquan.android.autolistfragment;

import android.app.ListFragment;
import android.content.Context;
import android.widget.TextView;

import com.yanyuanquan.android.autolistfragmentlibrary.AutoBaseAdapter;
import com.yanyuanquan.android.autolistfragmentlibrary.AutoListFragment;
import com.yanyuanquan.android.autolistfragmentlibrary.AutoViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.zip.Adler32;

/**
 * Created by apple on 16/2/26.
 *
 *  不用写一行xml文件 实现下拉刷新下拉加载
 *
 *
 */
public class ContainFragment extends AutoListFragment<Entity> {
    List<Entity> entities = new ArrayList<>();

    @Override
    protected int setlayoutId() {
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
        for (int i= 0;i<20; i++){
            entities.add(new Entity(("测试"+i),i));
        }
        getAdapter().setData(entities);
    }

    @Override
    protected void refresh() {
        List<Entity> entities2 = new ArrayList<>();
        for (int i= 0;i<20; i++){
            entities2.add(new Entity(("测试----2"+i),i));
        }
        getAdapter().setData(entities2);
        onRefreshComplete();
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
