package com.example.fitsync.ui.menengah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.fitsync.R
import com.example.fitsync.data.gerakanlatihan.bagianLatihan
import com.example.fitsync.data.gerakanlatihan.kakiMenengah
import com.example.fitsync.data.gerakanlatihan.kaloriLatihan
import com.example.fitsync.data.gerakanlatihan.perutPemula
import com.example.fitsync.data.gerakanlatihan.tingkatanLatihan
import com.example.fitsync.databinding.ActivityLatihanOtotKakiMenengahBinding
import com.example.fitsync.ui.persiapanlatihan.PersiapanLatihanActivity

class LatihanOtotKakiMenengahActivity : AppCompatActivity() {
    private var _binding: ActivityLatihanOtotKakiMenengahBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivityLatihanOtotKakiMenengahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.navy)

        binding.btnBack.setOnClickListener { onBackPressed() }
        binding.btnStart.setOnClickListener {
            kaloriLatihan = 226
            bagianLatihan = "Kaki"
            tingkatanLatihan = "Menengah"
            val intent = Intent(
                this@LatihanOtotKakiMenengahActivity,
                PersiapanLatihanActivity::class.java
            )
            intent.putExtra("gerakan", kakiMenengah)
            startActivity(intent)
        }
        Glide.with(this)
            .load(R.drawable.squats_gif)
            .centerCrop()
            .into(binding.imageSquats)
        Glide.with(this)
            .load(R.drawable.jumping_jacks_gif)
            .centerCrop()
            .into(binding.imageJumpingJack)
        Glide.with(this)
            .load(R.drawable.leg_raises_gif)
            .centerCrop()
            .into(binding.imageLegRaises)
        Glide.with(this)
            .load(R.drawable.jumping_squats_gif)
            .centerCrop()
            .into(binding.imageJumpingSquats)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}