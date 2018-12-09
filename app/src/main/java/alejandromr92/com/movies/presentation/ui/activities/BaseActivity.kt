package alejandromr92.com.movies.presentation.ui.activities

import alejandromr92.com.movies.R
import alejandromr92.com.movies.presentation.presenter.BaseView
import alejandromr92.com.movies.utils.LoggerUtils
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected var TAG = ""

    protected var layout = 0

    // Butterknife
    protected var binder: Unbinder? = null

    protected var progressDialog: ProgressDialog


    /////////////////////////////////////////////
    //// LIFE CYCLE
    /////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.progressDialog = ProgressDialog(this)

        this.setContentView(this.layout)

        this.injectViews()

        this.initializePresenters()

        LoggerUtils.logMessage(TAG, "onCreate()")
    }

    override fun onDestroy() {
        super.onDestroy()

        this.freeViews()

        LoggerUtils.logMessage(TAG, "onDestroy()")

    }

    override fun onStart() {
        super.onStart()

        this.configViews()

        this.loadData()

        LoggerUtils.logMessage(TAG, "onStart()")
    }

    override fun onStop() {
        super.onStop()

        this.resetData()

        LoggerUtils.logMessage(TAG, "onStop()")
    }

    override fun onResume() {
        super.onResume()
        LoggerUtils.logMessage(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        LoggerUtils.logMessage(TAG, "onPause()")
    }

    /////////////////////////////////////////////
    //// HELPERS
    /////////////////////////////////////////////

    /**
     * Override in children to initialize presenters.
     */
    protected open fun initializePresenters() {}

    /**
     * Override in children to load necessary data.
     */
    protected open fun loadData() {

    }

    /**
     * Override in children to reset necessary data.
     */
    protected open fun resetData() {

    }

    /**
     * Override in children to customize views (colors, backgrounds, etc)
     */
    protected open fun configViews() {}

    /**
     * View injection.
     */
    private fun injectViews() {
        this.binder = ButterKnife.bind(this)
    }

    /**
     * View destruction.
     */
    private fun freeViews() {
        if (this.binder != null) {
            this.binder!!.unbind()
        }
    }

    override fun showProgress() {
        if (!this.progressDialog.isShowing) {
            this.progressDialog = ProgressDialog.show(
                this, "",
                resources.getString(R.string.loading), true
            )
        }
    }

    override fun hideProgress() {
        if (this.progressDialog.isShowing) {
            this.progressDialog.dismiss()
        }
    }

}