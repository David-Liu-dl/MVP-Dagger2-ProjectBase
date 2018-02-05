# MVP Dagger2 Project-Base

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![MinSdk](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![Methods](https://img.shields.io/badge/author-David-blue.svg)](https://github.com/DavidFancy)

An Android project base with MVP design pattern to help you build a project fast and clean. 

| Structure | UML|
| --- | ------ |
| <img src="https://github.com/DavidFancy/MVP-Dagger2-ProjectBase/blob/master/app/imgs/package.jpeg" width="400"> | ![](https://github.com/DavidFancy/MVP-Dagger2-ProjectBase/blob/master/app/imgs/mvpdagger.png)|

## Table of Contents

  * [Introduction](#introduction)
  * [Features](#features)
  * [How to use](#how-to-use)
  
## Introduction
MVP-Dagger2 base project helps you to build an Android project from scratch through a quicker and more formal way. This project contains several common and necessary libraries, and all the frameworks are already built. As a developer, you just need to focus on implementing UI and business logics.      

The following libraries are included:

| Name | Version | Category |
| ------ | ------ | ------ |
| Tray | 0.12.0 | SharePreference |
| Retrofit2:adapter-rxjava | 2.3.0 | Network |
| Retrofit2:converter-gson | 2.3.0 | Network |
| Okhttp3:okhttp | 3.9.0 | Network |
| Okhttp3:logging-interceptor | 3.9.0 | Network |
| Dagger | 2.11 | Dependency Injection |
| Butterknife | 8.8.1 | Dependency Injection |
| RxAndroid | 1.2.1 | Multiple Threads |
|Material Dialogs:core| 0.9.4.3 | Material Design Widget |

## Features

  - BaseActivity & BaseFragment
  
    The Base-Activities and Base-Fragments include many useful base methods, saving lots of time from repeated works. 

    ```java
    public class BaseActivity extends AppCompatActivity {
    
    protected void setUpToolbar(Toolbar toolbar)

    protected void showSimpleAlertDialog(String title, String message, String buttonText)

    protected void showSimpleActionDialog(String title, String message, String buttonText, MaterialDialog.SingleButtonCallback onPositiveCallback) 
    
    protected MaterialDialog.Builder getBaseDialogBuilder()

    protected void showLongToast(String msg)
    
    protected void showLongToast(int strResId)

    protected void showShortToast(String msg)

    protected void showShortToast(int strResId)
    
    }
    ```
  - AppBaseActivity
  
    Abstract method setupActivityComponent provides the information to inject activity (fragment) to dagger.
    ```java
    public abstract class AppBaseActivity extends BaseActivity{
    
    /**
     * Injecting the Activity to Dagger 
     */ 
    protected abstract void setupActivityComponent();
    
    }
    ```
  - TaskBaseActivity & TaskBaseFragment
  
    This class handles all the Api Calls from Start to End, including task failure handling. It will return the taskId, so you can identify which task is failed or successful. 
    ``` java
    public abstract class TaskBaseActivity extends AppBaseActivity implements TaskBaseView{
    
    protected boolean showProgressBar = false;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    
    @Override
    public void onTaskStart(int taskId, boolean showProgressBar) 
    
    @Override
    public void onTaskSuccess(int taskId, @Nullable Object data)

    @Override
    public void onTaskFailure(int taskId, @Nullable Object data, @Nullable String msg)

    public void showProgressDialog()

    public void hideProgressDialog()
    
    }
    ```
  - HttpResultInterface
  
    This interface formulates the form of response when interacting with server, you should customize it according to requirements. 
    ``` java
    public interface HttpResultInterface<T> {
    /**
     * Response data from Api call
     * @return T
     */
    T getData();

    void setData(T data);

    /**
     * Return the status of Api Call (Successful or Failed)
     * @return boolean
     */
    boolean isStatus();

    void setStatus(boolean status);

    /**
     * Get error with message when failure on Api call
     * @return Error
     */
    Error getError();

    void setError(Error error);
    }
    ```
  - TaskBaseSubscriber
  
    The TaskBaseSubscriber provides you two important methods, 'onTaskSuccessForUI' and 'onTaskSuccessForData'. The onTaskSuccessForData will be called no matter if the View is destroied, however, onTaskSuccessForUI will be called only if the View is still alive. 
    (When calling the Api, you should create a subscriber through TaskBaseSubscriber instead of Subscriber)

    ``` java 
    @Override
    public void login(String email) {
        Observable<HttpResult<Void>> observable = noAuthUserApi.userLogin();
        Subscriber<HttpResult<Void>> subscriber = new TaskBaseSubscriber<HttpResult<Void>>(this, TASK_LOGIN) {
            /**
             * onTaskSuccessForData always be called even view is GC
             * this method for store the data
             * @param userLoginResponseHttpResult
             */
            @Override
            protected void onTaskSuccessForData(HttpResult<Void> userLoginResponseHttpResult) {
                // TODO: 17/1/18 Store the data & Operate the DB
            }

            @Override
            public void onTaskSuccessForUI(HttpResult<Void> userLoginResponseHttpResult) {
                getView().onLogin(email);
            }
        };

        HttpUtil.subscribe(observable, subscriber);
    }
    ```

    ``` java
    public abstract class TaskBaseSubscriber<T extends HttpResultInterface> extends Subscriber<T> {

    private MvpView<? extends TaskBaseView> mvpView;
    private int taskId;

    public TaskBaseSubscriber(MvpView<? extends TaskBaseView> weakReferenceTaskBaseView, int taskId) {
        this.mvpView = weakReferenceTaskBaseView;
        this.taskId = taskId;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mvpView.getView() == null){
            return;
        }
        TaskBaseView view = mvpView.getView();
        if (view != null){
            view.onTaskStart(taskId, false);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mvpView.getView() == null){
            return;
        }
        TaskBaseView view = mvpView.getView();
        if (view != null){
            view.onTaskFailure(taskId, null, e.getMessage());
        }
    }

    @Override
    public void onNext(T t) {
        // update data when success
        if (t.isStatus()){
            onTaskSuccessForData(t);
        }

        // update UI
        if (mvpView.getView() == null){
            return;
        }

        TaskBaseView view = mvpView.getView();
        if (view != null){
            if (t.isStatus()){
                view.onTaskSuccess(taskId, t);
                onTaskSuccessForUI(t);
            }else {
                view.onTaskFailure(taskId, t, t.getError().getMessage());
            }
        }
    }

    @Override
    public void onCompleted() {}

    public abstract void onTaskSuccessForUI(T t);

    protected void onTaskSuccessForData(T t){}
    
    }
    ```

## How to use?

If you are not familiar with [MVP][mvp] and [Dagger2][dagger2]. Please click the hypers link to get more information.

To make an specific activity work, there are several things you need to do. I will use the 
```java
public class MainActivity extends UserBaseViewActivity 
```
to demonstrate how it works.

### Step1 
Create a contract for a specific view.
``` java
public interface UserBaseContract {
    interface View extends TaskBaseContract.View{
        void onLogin(String username);
        void onFragmentLogin(String username, int fragmentId);
    }

    interface Presenter extends TaskBaseContract.Presenter{
        void login(String username);
        void fragmentLogin(String username, int fragmentId);
    }
}
```
### Step2 
Create a Presenter extends BasePresenterImp and implements contract presenter.
Please note, this presenter has a @Inject constructor, which means the dagger2 will create it for us, and ensure 'noAuthUserApi' & 'userApi' are Singleton.
``` java
public class UserPresenterImp extends BasePresenterImp<UserBaseContract.View>
        implements UserBaseContract.Presenter {

    public static final int TASK_LOGIN = 100;

    private final Context context;
    private final UserApi noAuthUserApi;
    private final UserApi userApi;

    @Inject
    public UserPresenterImp(UserBaseContract.View view, Context context, @Named("noAuth") UserApi noAuthUserApi, @Named("default") UserApi userApi) {
        this.attachView(view);
        this.context = context;
        this.noAuthUserApi = noAuthUserApi;
        this.userApi = userApi;
    }

    @Override
    public void login(String email) 

    @Override
    public void fragmentLogin(String email, int fragmentId)
}
```

### Step3 
Create a base activity extends AppBaseActivity and implements the contract view.
```java 
public abstract class UserBaseViewActivity extends TaskBaseActivity implements UserBaseContract.View{

    protected UserPresenterImp userPresenter;

    @Inject
    public void setUserPresenter(UserPresenterImp userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    protected void setupActivityComponent() {
        MyApplication.get(this)
                .getAppComponent()
                .addSub(new UserBaseViewModule(this))
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.detachView();
    }

    @Override
    public void onLogin(String username) {

    }

    @Override
    public void onFragmentLogin(String username, int fragmentId) {

    }
}
```
Please note, the Presenter is injected in this view base activity. 
``` java 
    @Override
    protected void setupActivityComponent() {
        MyApplication.get(this)
                .getAppComponent()
                .addSub(new UserBaseViewModule(this))
                .inject(this);
    }
```
```java 
    protected UserPresenterImp userPresenter;

    @Inject
    public void setUserPresenter(UserPresenterImp userPresenter) {
        this.userPresenter = userPresenter;
    }
```

### Step4 
Make the MainActivity extend the view base activity.
```java
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
```

Now, you can see how convenient and clean it is. You can implement any UserApi callback in this Activity by overriding the method, like 
``` java 
    @Override
    public void onLogin(String username) {
        super.onLogin(username);
        showLongToast(username + " login successfully");
    }
```

### You can also:
  - Customize Api actions in different stages by
 ``` java 
    @Override
    public void onTaskStart(int taskId, boolean showProgressBar) {
        super.onTaskStart(taskId, showProgressBar);
    }

    @Override
    public void onTaskSuccess(int taskId, @Nullable Object data) {
        super.onTaskSuccess(taskId, data);
    }

    @Override
    public void onTaskFailure(int taskId, @Nullable Object data, @Nullable String msg) {
        super.onTaskFailure(taskId, data, msg);
    }
 ```
  - Show customized Material Design Dialog by
``` java 
    private void showCustomizedDialog(){
        getBaseDialogBuilder()
                .title("Customized")
                .positiveText("OK")
                .onPositive((dialog, which) -> dialog.dismiss())
                .show();
    }
```
## License

Copyright 2017 Yuhao Liu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[mvp]: <https://github.com/googlesamples/android-architecture>

[dagger2]: <https://github.com/google/dagger>
