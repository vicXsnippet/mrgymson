package com.example.fitsync.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.YuvImage
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.camera.core.ImageProxy
import com.example.fitsync.R
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class Utils

fun extractErrorMessage(errorMessage:String, errorBody: String?): String? {
    return try {
        val json = JSONObject(errorBody)
        json.getString(errorMessage)
    } catch (e: JSONException) {
        null
    }
}

fun popupAlert(context: Context, message: String?, title: String = "FitSync", onDismiss: (() -> Unit)? = null) {
    val dialog = Dialog(context)
    dialog.setContentView(R.layout.popup_custom)

    val popupTitle: TextView = dialog.findViewById(R.id.title_popup)
    popupTitle.text = title

    val popupMessage: TextView = dialog.findViewById(R.id.deskripsi_popup)
    popupMessage.text = message

    val popupButton: Button = dialog.findViewById(R.id.button_popup)
    popupButton.text = "Oke"
    popupButton.setOnClickListener {
        dialog.dismiss()
    }

    val buttonYes: Button = dialog.findViewById(R.id.button_popup_yes)
    buttonYes.visibility = View.GONE

    val window = dialog.window
    val layoutParams = window?.attributes
    val displayMetrics = DisplayMetrics()
    val windowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as android.view.WindowManager
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val screenWidth = displayMetrics.widthPixels
    layoutParams?.width = (screenWidth * 0.9).toInt()
    window?.attributes = layoutParams

    dialog.setOnDismissListener {
        onDismiss?.invoke()
    }

    dialog.show()
}

fun popup(context: Context, message: String?, onDismiss: (() -> Unit)? = null, onClick: (() -> Unit)? = null) {
    val dialog = Dialog(context)
    dialog.setContentView(R.layout.popup_custom)

    val popupTitle: TextView = dialog.findViewById(R.id.title_popup)
    popupTitle.text = "FitSync"

    val popupMessage: TextView = dialog.findViewById(R.id.deskripsi_popup)
    popupMessage.text = message

    val popupButton: Button = dialog.findViewById(R.id.button_popup)
    popupButton.text = "Batal"
    popupButton.setOnClickListener {
        dialog.dismiss()
    }

    val buttonYes: Button = dialog.findViewById(R.id.button_popup_yes)
    buttonYes.text = "Iya"
    buttonYes.setOnClickListener {
        onClick?.invoke()
    }

    val window = dialog.window
    val layoutParams = window?.attributes
    val displayMetrics = DisplayMetrics()
    val windowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as android.view.WindowManager
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    val screenWidth = displayMetrics.widthPixels
    layoutParams?.width = (screenWidth * 0.9).toInt()
    window?.attributes = layoutParams

    dialog.setOnDismissListener {
        onDismiss?.invoke()
    }

    dialog.show()
}

fun imageProxyToBitmap(image: ImageProxy): Bitmap {
    val yBuffer = image.planes[0].buffer
    val uBuffer = image.planes[1].buffer
    val vBuffer = image.planes[2].buffer

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val nv21 = ByteArray(ySize + uSize + vSize)

    yBuffer.get(nv21, 0, ySize)
    vBuffer.get(nv21, ySize, vSize)
    uBuffer.get(nv21, ySize + vSize, uSize)

    val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(android.graphics.Rect(0, 0, image.width, image.height), 100, out)
    val imageBytes = out.toByteArray()
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}
