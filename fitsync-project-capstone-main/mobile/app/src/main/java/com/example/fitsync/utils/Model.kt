package com.example.fitsync.utils

import android.content.Context
import android.util.Log
import com.example.fitsync.ml.LegRaises
import com.example.fitsync.ml.Movenet16float
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class Model

fun modelMovenet(context: Context, inputFeature0: TensorBuffer): TensorBuffer {
    val model = Movenet16float.newInstance(context)
    val inputFeature1 =
        TensorBuffer.createFixedSize(intArrayOf(1, 256, 256, 3), DataType.UINT8)
    inputFeature1.loadBuffer(inputFeature0.buffer)

    val outputs = model.process(inputFeature1)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

    return outputs.outputFeature0AsTensorBuffer
}

fun modelLegRaises(context: Context, inputFeature0: TensorBuffer): String {
    val model = LegRaises.newInstance(context)
    val inputFeature1 = TensorBuffer.createFixedSize(intArrayOf(1,51),DataType.FLOAT32)
//    inputFeature0.loadBuffer(inputFeature0.buffer)

    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

    Log.d("Legraises", "output Leg: ${outputFeature0.contentToString()}")

    if (outputFeature0[0] == 1.0f ) {
        return "1"
    } else {
        return  "0"
    }

}