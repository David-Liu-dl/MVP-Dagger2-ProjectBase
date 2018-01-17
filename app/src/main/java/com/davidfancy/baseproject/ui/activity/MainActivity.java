package com.davidfancy.baseproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.davidfancy.baseproject.R;
import com.davidfancy.baseproject.base.UserBaseViewActivity;
import com.davidfancy.baseproject.ui.fragment.DemoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by David Liu on 25/11/17.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class MainActivity extends UserBaseViewActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_username)
    EditText etUsername;

    @OnClick(R.id.btn_login)
    public void onClick(View view){
        showWarningDialog();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpToolbar(toolbar);
        initView();
    }

    private void initView(){
        setTitle(getString(R.string.activity_toolbar_title_main));
        addFragment();
    }

    private void addFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new DemoFragment())
                .commit();
    }

    @Override
    public void onLogin(String username) {
        super.onLogin(username);
        showLongToast(username + " login successfully");
    }

    private void showWarningDialog(){
        showSimpleActionDialog("Note", getString(R.string.action_cancel)
                , "Continue"
                , (dialog, which) -> userPresenter.login(etUsername.getText().toString()));
    }

}
