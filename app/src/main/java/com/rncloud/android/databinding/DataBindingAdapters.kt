package com.primehealthcare.telehealth.databinding

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by KondalRao Tirumalasetty on 8/23/2019.
 */

/**
 * To set visibility
 *
 * @param view
 * @param show
 */

@BindingAdapter("visible")
fun changeVisibility(view: View, isVisible:Boolean){

    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("error")
fun setError(view: TextInputLayout, sequence: CharSequence?) {
    if (sequence == null || sequence.isEmpty()) {
        view.error = null
    } else {
        view.error = sequence
    }
}

///**
// * To show image from url
// *
// * @param imageView
// * @param imageUrl
// * @param placeholder
// */
//@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
//fun setImageUrl(
//    imageView: ImageView,
//    imageUrl: String,
//    placeholder: Drawable
//) {
//    if (!TextUtils.isEmpty(imageUrl)) {
//        val builder = Glide.with(imageView.context)
//            .asBitmap()
//        val options = RequestOptions().placeholder(placeholder)
//            .error(placeholder)
//            .circleCrop()
//        builder.apply(options)
//            .load(imageUrl)
//            .into(imageView)
//    }
//}