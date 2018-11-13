package alejandromr92.com.movies.presentation.ui.activities;

import alejandromr92.com.movies.R;
import alejandromr92.com.movies.presentation.presenter.BaseView;
import alejandromr92.com.movies.utils.LoggerUtils;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected String TAG = "";

    protected int layout = 0;

    // Butterknife
    protected Unbinder binder;

    protected ProgressDialog progressDialog;


/////////////////////////////////////////////
//// LIFE CYCLE
/////////////////////////////////////////////

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.progressDialog = new ProgressDialog(this);

        this.setContentView(this.layout);

        this.injectViews();

        this.initializePresenters();

        LoggerUtils.logMessage(TAG, "onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.freeViews();

        LoggerUtils.logMessage(TAG, "onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.configViews();

        this.loadData();

        LoggerUtils.logMessage(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        this.resetData();

        LoggerUtils.logMessage(TAG, "onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoggerUtils.logMessage(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LoggerUtils.logMessage(TAG, "onPause()");
    }

/////////////////////////////////////////////
//// HELPERS
/////////////////////////////////////////////

    /**
     * Override in children to initialize presenters.
     */
    protected void initializePresenters(){}

    /**
     * Override in children to load necessary data.
     */
    protected void loadData(){

    }

    /**
     * Override in children to reset necessary data.
     */
    protected void resetData(){

    }

    /**
     * Override in children to customize views (colors, backgrounds, etc)
     */
    protected void configViews(){}

    /**
     * View injection.
     */
    private void injectViews(){
        this.binder = ButterKnife.bind(this);
    }

    /**
     * View destruction.
     */
    private void freeViews(){
        if (this.binder != null){
            this.binder.unbind();
        }
    }

    public void showProgress(){
        if (!this.progressDialog.isShowing()) {
            this.progressDialog = ProgressDialog.show(this, "",
                    getResources().getString(R.string.loading), true);
        }
    }

    public void hideProgress(){
        if(this.progressDialog.isShowing()){
            this.progressDialog.dismiss();
        }
    }

}
