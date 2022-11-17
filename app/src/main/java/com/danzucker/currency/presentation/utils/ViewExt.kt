package com.danzucker.currency.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.danzucker.currency.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.showErrorSnackBar(view: View, message: String) {
    Snackbar.make(
        view,
        message,
        5000
    ).setActionTextColor(ContextCompat.getColor(requireContext(), R.color.background_entire))
        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.error_bg_color))
        .setAction("ok") {
        }.show()
}

fun Fragment.showSnackBar (view : View, message : String) {
    Snackbar.make(
        view,
        message,
        Snackbar.LENGTH_LONG
    ).setAction("ok") {

    }.show()
}

fun Fragment.populateSpinner (context : Context, view : View, list: List<String>) {
    val arrayAdapter = ArrayAdapter(context, R.layout.spinner_list_item, list)
    view.findViewById<MaterialAutoCompleteTextView>(view.id).setAdapter(arrayAdapter)
}


fun EditText.afterTextChangedDelayed(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        var timer: CountDownTimer? = null

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            timer?.cancel()
            timer = object : CountDownTimer(1000, 1500) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    afterTextChanged.invoke(editable.toString())
                }
            }.start()
        }
    })
}

fun Fragment.enableButtonValidation (editText: EditText,
                                            button: Button
) {

    editText.afterTextChangedDelayed {
        button.isEnabled = it.isNotEmpty()
    }
}


fun Fragment.getDate(cal: Calendar) : String {
    return "${cal[Calendar.YEAR]}-${cal[Calendar.MONTH] + 1}-${cal[Calendar.DATE]}"
}

fun Fragment.getCurrentDate () : String {
    val calender = Calendar.getInstance()
    calender.timeZone = TimeZone.getTimeZone("UTC+1")

    return  getDate(calender)

}

@SuppressLint("SimpleDateFormat")
fun Fragment.getThreeDaysAgo () : String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val calender = Calendar.getInstance()
    simpleDateFormat.format(calender.time)
    calender.add(Calendar.DATE, -2)
    return getDate(calender)

}