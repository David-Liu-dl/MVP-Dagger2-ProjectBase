package com.davidfancy.baseproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.davidfancy.baseproject.R;
import com.davidfancy.baseproject.base.UserBaseViewFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by David Liu on 17/1/18.
 * NowBoarding Ltd
 * lyhmelbourne@gmail.com
 */

public class DemoFragment extends UserBaseViewFragment{

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.tv_msg)
    TextView tvMessage;

    @OnClick(R.id.btn_login)
    public void onClick(View view){
        userPresenterImp.fragmentLogin(etUsername.getText().toString(), R.id.fragment_container);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_demo, container,false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onFragmentLogin(String username, int fragmentId) {
        super.onFragmentLogin(username, fragmentId);
        tvMessage.setText(getString(R.string.str_format_success, username, String.valueOf(fragmentId)));
    }
}
