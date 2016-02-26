package com.yanyuanquan.android.autolistfragment;

/**
 * Created by apple on 16/2/26.
 */
public class Entity  {
    private String title;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entity(String title, int id) {
        this.title = title;
        this.id = id;
    }
}
