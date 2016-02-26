package com.yanyuanquan.android.autolistfragmentlibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


public class AutoBaseFragment extends Fragment {
    protected Activity activity;

    @Override
    public void onAttach(Activity activity) {
        this.activity =activity;
        super.onAttach(activity);
    }


    protected void intent2Activity(Class<? extends Activity> cls) {
        intent2Activity(cls, "");
    }

    protected void intent2Activity(Class<? extends Activity> cls, String params) {
        Intent intent = new Intent(activity, cls);
        if (!TextUtils.isEmpty(params)) {
            intent.putExtra(cls.getName(), params);
        }
        startActivity(intent);
    }

    protected void intent2Activity(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}
