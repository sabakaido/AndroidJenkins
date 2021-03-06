package com.example.anikaido.jenkins.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.anikaido.jenkins.R;
import com.example.anikaido.jenkins.ui.helper.SettingActivityHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anikaido on 2017/02/25.
 */
public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.setting_root)
    LinearLayout mRoot;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    @BindView(R.id.setting_host)
    EditText mEditTextHost;

    @BindView(R.id.setting_column)
    EditText mEditTextColumn;

    @BindView(R.id.setting_like_filter)
    EditText mEditTextLike;

    @BindView(R.id.switch_switch)
    SwitchCompat mSwitchCompat;

    private InputMethodManager mInputMethodManager;

    private SettingActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settting);

        ButterKnife.bind(this);

        setupToolBar();

        init();
    }

    /**
     * 初期化処理
     */
    private void init() {
        mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mHelper = new SettingActivityHelper(this);

        mEditTextHost.setText(mHelper.getHost());
        mEditTextLike.setText(mHelper.getLike());
        mEditTextColumn.setText(String.valueOf(mHelper.getColumn()));
        mSwitchCompat.setChecked(mHelper.isChecked());
    }

    /**
     * toolbarのセットアップ
     */
    private void setupToolBar() {
        mToolbar.setTitle("Setting");
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                }
        );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mInputMethodManager.hideSoftInputFromWindow(mRoot.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        mRoot.requestFocus();

        return false;
    }

    @Override
    public void onBackPressed() {
        String text = mEditTextHost.getText().toString();
        String like = mEditTextLike.getText().toString();
        Boolean checked = mSwitchCompat.isChecked();
        Integer column = Integer.valueOf(mEditTextColumn.getText().toString());
        mHelper.saveHost(text);
        mHelper.saveLike(like);
        mHelper.saveCheck(checked);

        if (column > 0) {
            mHelper.saveColumn(column);
        }

        super.onBackPressed();
    }
}
