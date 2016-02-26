package com.yanyuanquan.android.autolistfragmentlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by apple on 16/2/24.
 * 在footerLayout的外层”套“一个父容器。代码如下：
 * <p>
 * [java] view plain copy
 * <p>
 * // footer
 * footerLayout = new PullLoadingLayout(context, MODE_PULL_DOWN_TO_REFRESH, releaseLabel,
 * pullLabel, refreshingLabel, isShowHeader);
 * footerLayoutHolder = new FrameLayout(getContext());
 * footerLayoutHolder.addView(footerLayout, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
 * FrameLayout.LayoutParams.WRAP_CONTENT));
 * // add footer
 * refreshableView.addFooterView(footerLayoutHolder);
 * <p>
 * 从上面的代码可以看出，在创建了footerLayout对象之后，又创建了一个FrameLayout的对象，
 * 叫footerLayoutHolder，然后用footerLayoutHolder对象去”包裹“footerLayout。
 * <p>
 * 这样，如果想要控制FooterView的显示状态，直接调用footerLayout的setVisibility()方法即可。
 * <p>
 * [java] view plain copy
 * <p>
 * // 显示
 * footerLayout.setVisibility(View.VISIBLE);
 * // 隐藏
 * footerLayout.setVisibility(View.GONE);
 */
public class AutoLoadListView extends ListView implements AbsListView.OnScrollListener {

    private boolean isLoading;
    private View footerContent;
    private TextView textView;
    private ProgressBar progressBar;
    private boolean hasMore = true;

    public AutoLoadListView(Context context) {
        super(context);
//        init();
    }

    private void init() {
        footerContent = LayoutInflater.from(getContext()).inflate(R.layout.listview_footer, this, false);
        textView = (TextView) footerContent.findViewById(R.id.loading);
        progressBar = (ProgressBar) footerContent.findViewById(R.id.progressbar);
        this.setOnScrollListener(this);
        // 隐藏
        FrameLayout footer = new FrameLayout(getContext());
        footer.addView(footerContent, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        this.addFooterView(footer,null,false);
        footerContent.setVisibility(View.VISIBLE);

    }



    public AutoLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    public AutoLoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.i("zjw",(listener==null)+" --- "+!isLoading+" --- "+hasMore+" --- "+(scrollState ==SCROLL_STATE_IDLE&&isLastItemVisible));
        if (listener!=null&&!isLoading&&hasMore&&scrollState ==SCROLL_STATE_IDLE&&isLastItemVisible) {
            listener.loadMore();
            isLoading=true;
            footerContent.setVisibility(View.VISIBLE);
        }

    }
    private  boolean isLastItemVisible;
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (listener!=null){
            isLastItemVisible = (totalItemCount>0)&&view.getLastVisiblePosition()==totalItemCount-1;
        }

    }

    public interface onLoadMoreLinstener {
        void loadMore();
    }

    private onLoadMoreLinstener listener;

    public void setOnLoadMoreLinstener(onLoadMoreLinstener linstener) {
        this.listener = linstener;
        init();
    }

    public void onLoadMoreComplete() {
        isLoading = false;
        footerContent.setVisibility(View.GONE);
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
        if (!hasMore) {
            textView.setText("没有更多了");
            footerContent.setVisibility(View.VISIBLE);
            progressBar.setVisibility(GONE);
        } else {
            textView.setText("加载中...");
            progressBar.setVisibility(VISIBLE);
        }
    }
}
