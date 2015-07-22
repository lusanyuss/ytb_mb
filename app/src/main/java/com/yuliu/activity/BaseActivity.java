package com.yuliu.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.hellonobase.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.lang.reflect.Field;

public class BaseActivity extends Activity {

    public final static String TAG = "APP";
    public final static String APP_NAME = "CMM";
    public DatabaseHelper databaseHelper = null;
    // 665
    public int width;
    public int height;
    /**
     * ***************************** 【Activity LifeCycle For Debug】 ******************************************
     */
    // 是否允许全屏
    public boolean allowFullScreen = true;

    // 是否允许销毁
    public boolean allowDestroy = true;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allowFullScreen = true;
        // 添加Activity到堆栈
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        databaseHelper = getHelper();
    }

    public DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    public void openActivity(String pAction) {
        openActivity(pAction, null);
    }

    public void openActivity(String pAction, Bundle pBundle) {
        Intent intent = new Intent(pAction);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    /**
     * 通过反射来设置对话框是否要关闭，在表单校验时很管用， 因为在用户填写出错时点确定时默认Dialog会消失， 所以达不到校验的效果
     * 而mShowing字段就是用来控制是否要消失的，而它在Dialog中是私有变量， 所有只有通过反射去解决此问题
     *
     * @param pDialog
     * @param pIsClose
     */
    public void setAlertDialogIsClose(DialogInterface pDialog, Boolean pIsClose) {
        try {
            Field field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(pDialog, pIsClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
