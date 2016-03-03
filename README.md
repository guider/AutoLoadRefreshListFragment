# AutoLoadRefreshListFragment
a fragment with auto load and auto refresh


/**
 *  时间匆忙，简版，周末更新，
 *
 *不用写Adapter 和XML的ListView 可替代绝大多的 ListView 和ListFragment
 *
 */

/**
 * Created by apple on 16/2/26.
 *
 *  不用写一行xml文件 实现下拉刷新下拉加载
 *
 *
 */

   只需要继承AutoListFragment 实现几个抽象方法就能实现
                    1  空列表提示加载中，
                    2 没数据时显示Emptyview
                    3 EmptyView 可定制，
                    4 无需判断状态
                    // 周末更新
                    5, 可定制 的footerView
                    6，可定制的Loading

      // 默认的EmptyView （后期会改好看一点)
     ![image](https://github.com/guider/AutoLoadRefreshListFragment/blob/master/Untitled.gif)

     //订制后的EmptyView 可做跳转<br/>
       ![image](https://github.com/guider/AutoLoadRefreshListFragment/blob/master/Untitled2.gif)
}
