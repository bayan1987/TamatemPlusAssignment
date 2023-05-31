package com.tamatemplus.tamatemplusassignment.presenter.ui.dialogs

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.tamatemplus.tamatemplusassignment.R
import com.tamatemplus.tamatemplusassignment.databinding.DialogWebviewBinding

class WebViewModal : DialogFragment() {

    lateinit var binding: DialogWebviewBinding

    companion object{
        const val WEBSITE_URL = "https://tamatemplus.com"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false
        binding = DialogWebviewBinding.inflate(layoutInflater)
        initUI()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initUI() {

        updateBackButtonStatus()
        updateForwardButtonStatus()

        binding.ivBack.setOnClickListener{
            // navigate back in the web view's history.
            binding.webView.goBack()
        }

        binding.ivForward.setOnClickListener{
            //navigate forward in the web view's history.
            binding.webView.goForward()
        }

        binding.ivRefresh.setOnClickListener{
            //reload the current webpage.
            binding.webView.reload()
        }

        binding.ivClose.setOnClickListener{
            //dismiss the view
            dismiss()
        }

        binding.webView.apply {
            // improve rendering time of screen having web view
            setLayerType(if(Build.VERSION.SDK_INT>=19) View.LAYER_TYPE_HARDWARE else View.LAYER_TYPE_SOFTWARE, null)

            webViewClient = MyWebViewClient()

            loadUrl(WEBSITE_URL)
        }


        binding.webView.settings.apply {
            //Tells the WebView to enable JavaScript execution
            javaScriptEnabled = true

            //enable DOM storage API
            domStorageEnabled = true

            // allow JavaScript to open windows automatically
            javaScriptCanOpenWindowsAutomatically = true


            supportMultipleWindows()
        }
    }



    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        //Notify the application that a page has started loading
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            showLoadingProgress()
            disableRefreshButton()
            hideWebView()

        }

        //Notify the application that a page has finished loading
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            hideLoadingProgress()
            showWebView()
            updateBackButtonStatus()
            updateForwardButtonStatus()
            enableRefreshButton()
        }


        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)

            showErrorMessage("onReceivedError:  ${getString(R.string.something_went_wrong)}\t ${error?.description ?: ""} ")
            hideLoadingProgress()
            updateBackButtonStatus()
            updateForwardButtonStatus()
            enableRefreshButton()
        }


        override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
            showErrorMessage("onReceivedHttpError: ${getString(R.string.something_went_wrong)}")
            hideLoadingProgress()
            updateBackButtonStatus()
            updateForwardButtonStatus()
            enableRefreshButton()
            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            hideLoadingProgress()
            updateBackButtonStatus()
            updateForwardButtonStatus()
            enableRefreshButton()

            showErrorMessage("onReceivedSslError: ${getString(R.string.something_went_wrong)}\n" + error.toString())

            super.onReceivedSslError(view, handler, error)
        }


    }

    override fun onResume() {
        // Store access variables for window and blank point
        val window = dialog!!.window
        val size = Point()
        // Store dimensions of the screen in `size`
        val display = window!!.windowManager.defaultDisplay
        display.getSize(size)
        // Set the width of the dialog proportional to 90% of the screen width
        window.setLayout((size.x * 0.90).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
        // Call super onResume after sizing
        super.onResume()
    }

    fun updateBackButtonStatus(){

        // change the alpha for the back button if the user able to click it make it full visible, if not make the transparency to be 0.5
        binding.ivBack.alpha = if(binding.webView.canGoBack()) 1.0f else 0.5f
        binding.ivBack.isEnabled = binding.webView.canGoBack()
    }

    fun updateForwardButtonStatus(){
        // change the alpha for the forward button if the user able to click it make it full visible, if not make the transparency to be 0.5
        binding.ivForward.alpha = if(binding.webView.canGoForward()) 1.0f else 0.5f
        binding.ivForward.isEnabled = binding.webView.canGoForward()
    }

    fun showLoadingProgress(){
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideLoadingProgress(){
        binding.progressBar.visibility = View.GONE
    }

    fun showWebView(){
        binding.webView.visibility = View.VISIBLE
    }

    fun hideWebView(){
        binding.webView.visibility = View.GONE
    }

    fun enableRefreshButton(){
        // enable refresh button when the web page full loaded

        binding.ivRefresh.isEnabled = true
        binding.ivRefresh.alpha = 1.0f
    }

    fun disableRefreshButton(){
        // enable refresh button when the web page full loaded

        binding.ivRefresh.isEnabled = false
        binding.ivRefresh.alpha = 0.5f
    }

    fun showErrorMessage(message:String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}