package alejandromr92.com.movies.presentation.ui.activities;

import alejandromr92.com.movies.utils.LoggerUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = "";

    protected int layout = 0;

    // Butterknife
    protected Unbinder binder;


/////////////////////////////////////////////
//// LIFE CYCLE
/////////////////////////////////////////////

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        loadData();

        LoggerUtils.logMessage(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
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

    protected void showProgress(){
        //TODO
    }

    protected void hideProgress(){
        //TODO
    }

}
