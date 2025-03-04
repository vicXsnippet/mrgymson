package com.example.fitsync.ui.hasillatihan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitsync.R
import com.example.fitsync.data.ResultState
import com.example.fitsync.data.gerakanlatihan.bagianLatihan
import com.example.fitsync.data.gerakanlatihan.kaloriLatihan
import com.example.fitsync.data.gerakanlatihan.tingkatanLatihan
import com.example.fitsync.data.gerakanlatihan.waktuMulaiLatihan
import com.example.fitsync.data.gerakanlatihan.waktuSelesaiLatihan
import com.example.fitsync.data.model.Exercise
import com.example.fitsync.data.model.LatihanRequest
import com.example.fitsync.databinding.ActivityHasilLatihanBinding
import com.example.fitsync.ui.ViewModelFactory
import com.example.fitsync.ui.dashboard.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HasilLatihanActivity : AppCompatActivity() {
   private var _binding: ActivityHasilLatihanBinding? = null
   private val binding get() = _binding!!

   private val viewModel by viewModels<HasilLatihanViewModel> {
      ViewModelFactory.getInstance(this)
   }

   @RequiresApi(Build.VERSION_CODES.O)
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      enableEdgeToEdge()
      _binding = ActivityHasilLatihanBinding.inflate(layoutInflater)
      setContentView(binding.root)
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         insets
      }

      val gerakan = intent.getStringExtra("gerakan")
      val gson = Gson()
      val exerciseListType = object : TypeToken<List<Exercise>>() {}.type
      val listGerakan: List<Exercise> = gson.fromJson(gerakan, exerciseListType)
      val jumlahLatihan = listGerakan.size - 1

      waktuSelesaiLatihan = LocalDateTime.now()
      val timerFormatter = DateTimeFormatter.ofPattern("HH:mm")
      val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM")

      val durasiLatihanSecond = Duration.between(waktuMulaiLatihan, waktuSelesaiLatihan).toSeconds()
      val durasiLatihanMinutes: Double =
         String.format(Locale.US, "%.1f", durasiLatihanSecond.toDouble() / 60).toDouble()
      val jamLatihan = waktuSelesaiLatihan?.format(timerFormatter).toString()
      val tanggalLatihan = waktuSelesaiLatihan?.format(dateFormatter).toString()

      val bodyRequest = LatihanRequest(
         durasi = durasiLatihanMinutes,
         kalori = kaloriLatihan,
         jamLatihan = jamLatihan,
         tanggalBulanLatihan = tanggalLatihan,
         bagianLatihan = bagianLatihan,
         tingkatanLatihan = tingkatanLatihan
      )

      viewModel.getUserId().observe(this@HasilLatihanActivity) { userId ->
         viewModel.addCompleteMovement(
            userId, bodyRequest
         ).observe(this@HasilLatihanActivity) { result ->
            when (result) {
               ResultState.Loading -> {

               }

               is ResultState.Success -> {
                  val snackbar =
                     Snackbar.make(binding.root, "Selamat Latihan Selesai", Snackbar.LENGTH_LONG)
                  snackbar.setBackgroundTint(getColor(R.color.purple))
                  snackbar.show()
               }

               is ResultState.Error -> {
               }
            }
         }
      }

      binding.tvNamaLatihan.text = "$bagianLatihan • $tingkatanLatihan"
      binding.tvJumlahDurasi.text =
         formatSecondsToMinutesAndSeconds(durasiLatihanSecond.toInt(), durasiLatihanMinutes.toInt())
      binding.tvJumlahGerakan.text = jumlahLatihan.toString()

      binding.btnKembaliKeBeranda.setOnClickListener {
         val intent: Intent = Intent(
            this@HasilLatihanActivity,
            MainActivity::class.java
         )
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
         startActivity(intent)
      }
   }

   @SuppressLint("MissingSuperCall")
   override fun onBackPressed() {
      val intent: Intent = Intent(
         this@HasilLatihanActivity,
         MainActivity::class.java
      )
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      startActivity(intent)
      finish()
   }

   private fun formatSecondsToMinutesAndSeconds(seconds: Int, minutes: Int): String {
      val remainingSeconds = seconds % 60
      return String.format("%02d:%02d", minutes, remainingSeconds)
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }

   companion object {
      private const val TAG = "HasilLatihanActivity"
   }

}