package com.example.apple.test50;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements Runnable {
    DrawerLayout dl;
    MyHandler mHandler;
    Toolbar toolbar;
    NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dl=(DrawerLayout)findViewById(R.id.myDrawerLayout);
        nv=(NavigationView)findViewById(R.id.nav_view);


        toolbar.setTitle("Title");
        toolbar.setSubtitle("SubTitle");
        toolbar.inflateMenu(R.menu.menu_main);
        //toolbar.setLogo(R.mipmap.ic_launcher);
        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                .LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        toolbar.setLayoutParams(lp);*/
        toolbar.setTitleTextColor(Color.GREEN);
        toolbar.setSubtitleTextColor(0xffffffff);

        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        toolbar.setNavigationIcon(R.drawable.ab_android);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                if(dl.isDrawerOpen(GravityCompat.START))
                    dl.closeDrawer(GravityCompat.START);
                else
                dl.openDrawer(GravityCompat.START);
            }
        });
        nv.setItemIconTintList(null);           //恢复图标颜色
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true); // 改变item选中状态
                dl.closeDrawers(); // 关闭导航菜单
                return false;
            }
        });
        //异步关闭toolbar
        /*mHandler=new MyHandler(this);
        Thread thread=new Thread(this);
        thread.start();*/

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }
            if(!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };



    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            Log.e("ok", "111111111");
            // TODO Auto-generated method stub
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyHandler extends Handler {
        WeakReference<MainActivity> mActivity;// 弱引用
        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            MainActivity theActivity = mActivity.get();
            switch (msg.what) {
                case 1:
                    theActivity.toolbar.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
