package com.example.fitsync.ui.persiapanlatihan

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.fitsync.R
import com.example.fitsync.data.gerakanlatihan.waktuMulaiLatihan
import com.example.fitsync.data.model.Exercise
import com.example.fitsync.databinding.ActivityPersiapanLatihanBinding
import com.example.fitsync.ui.training.TrainingActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime

class PersiapanLatihanActivity : AppCompatActivity() {
   private var _binding: ActivityPersiapanLatihanBinding? = null
   private val binding get() = _binding!!
   private var countDownTimer: CountDownTimer? = null

   @RequiresApi(Build.VERSION_CODES.O)
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      enableEdgeToEdge()
      _binding = ActivityPersiapanLatihanBinding.inflate(layoutInflater)
      setContentView(binding.root)
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         insets
      }

      window.navigationBarColor = getColor(R.color.white)

      val gerakan = intent.getStringExtra("gerakan")
      val getPositionGerakan = intent.getIntExtra("getPositionGerakan", 0)

      val gson = Gson()
      val exerciseListType = object : TypeToken<List<Exercise>>() {}.type
      val listGerakan: List<Exercise> = gson.fromJson(gerakan, exerciseListType)
      countDownTimer = object : CountDownTimer(20000, 1000) {
         override fun onTick(millisUntilFinished: Long) {
            val secondsRemaining = millisUntilFinished / 1000
            binding.tvTimer.text =
               getString(R.string.timer, String.format("%02d", secondsRemaining))
         }

         override fun onFinish() {
            val intent = Intent(this@PersiapanLatihanActivity, TrainingActivity::class.java)
            intent.putExtra("gerakan", gerakan)
            intent.putExtra("getPositionGerakan", getPositionGerakan)
            startActivity(intent)
            finish()
         }
      }.start()

      binding.tvNamaLatihan.text = listGerakan[getPositionGerakan].name
      val imageResId = resources.getIdentifier(
         listGerakan.get(getPositionGerakan).image, "drawable",
         packageName
      )

      Glide.with(this@PersiapanLatihanActivity).load(imageResId).fitCenter()
         .into(binding.imageNextStep)

      if (getPositionGerakan != 0) {
         binding.tvTitleHalaman.text = getString(R.string.istirahat)
      } else {
         waktuMulaiLatihan = LocalDateTime.now()
      }
      binding.btnLewatkan.setOnClickListener {
         try {
            val intent = Intent(this@PersiapanLatihanActivity, TrainingActivity::class.java)
            intent.putExtra("gerakan", gerakan)
            intent.putExtra("getPositionGerakan", getPositionGerakan)
            startActivity(intent)
            finish()
         } catch (e: Exception) {
            Log.d(TAG, "onCreate: ${e.message}")
         }
      }
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
      countDownTimer?.cancel()
   }

   companion object {
      private val TAG = "PersiapanLatihanActivity"
   }
}