package com.instant.hospital.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.instant.hospital.R

/**
     * This file is responsible for creating functions of a special type and shape to
     * facilitate and shorten the functions of some things,
     * such as the toast message and the Navigation Action
     */


    fun Activity.changeStatusBarColor(color: Int, isLight: Boolean) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
    }

    fun Fragment.showToastShort(message: Any){
        Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
    }

    fun Fragment.showToastLong(message: Any){
        Toast.makeText(requireContext(), "$message", Toast.LENGTH_LONG).show()
    }


    fun Fragment.setNavigation(action: Int){
        findNavController().navigate(action)
    }


    fun Fragment.setNavigationWithData(fragmentDirection: ActionOnlyNavDirections){
        findNavController().navigate(fragmentDirection)
    }

    fun ImageView.onBackPressed(){
        findNavController().popBackStack()
    }


    fun Fragment.copyToClipboard(text: String) {
        var clipboard: ClipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        Toast.makeText(requireContext(), "$text", Toast.LENGTH_LONG).show()
        clipboard.setPrimaryClip(clip)
    }


/** Hides the soft input keyboard from the screen */
    @SuppressLint("ServiceCast")
    fun View.hideKeyboard(context: Context?) {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }


/** Controll in View Visibility  */
    fun View.makeVisible() {
        visibility = View.VISIBLE
    }

    fun View.makeGone() {
        visibility = View.GONE
    }

    fun View.makeInvisible() {
        visibility = View.INVISIBLE
    }


/** Transprant Status Bar */
fun Activity.setTransparentStatusBar() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = Color.TRANSPARENT

    }

//    var dialog: AlertDialog
//    fun Fragment.showProgressAlertDialog(): AlertDialog{
//        var builder = AlertDialog.Builder(requireContext())
//        var inflater = requireActivity().layoutInflater
//        builder.setView(inflater.inflate(R.layout.custom_progress_loading_alert_dialog, null))
//        builder.setCancelable(false)
//        dialog = builder.create()
////        dialog.show()
//        return dialog
//    }
//
//    fun Fragment.dismissProgressAlertDialog(){
//        var builder = AlertDialog.Builder(requireContext())
//        var inflater = requireActivity().layoutInflater
//        builder.setView(inflater.inflate(R.layout.custom_progress_loading_alert_dialog, null))
//        builder.setCancelable(false)
//        dialog = builder.create()
//        dialog.dismiss()
//    }
}

/**
 * Loads URL into an ImageView using Glide
 * @param url URL to be loaded
 */

    fun ImageView.loadImgFromUrl(context: Context ,url: String, imageViewId: ImageView) {
        Glide.with(context).load(url).into(imageViewId)
    }


/**
 * Texts And Strings*/
    fun TextView.underline() {
        paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    fun String.gText(editText: EditText): String?{
        return editText.text.toString().trim()
    }

    fun SearchView.setTextSize(size: Float) {
        findViewById<EditText>(R.id.search_src_text).textSize = size
    }

/** Margin and Sizes */

    fun View.setMargins(start: Int? = null, top: Int? = null, end: Int? = null, bottom: Int? = null
    ) {
        val lp = layoutParams as? ViewGroup.MarginLayoutParams
            ?: return

    lp.setMargins(
        start ?: lp.marginStart,
        top ?: lp.topMargin,
        end ?: lp.marginEnd,
        bottom ?: lp.bottomMargin
    )

    layoutParams = lp
}




