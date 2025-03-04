package com.example.fitsync.ui.pemula

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager2.widget.ViewPager2
import com.example.fitsync.FragmentPageAdapter
import com.example.fitsync.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.fitsync.databinding.ActivityPemulaBinding

class PemulaActivity : AppCompatActivity() {

   private lateinit var tabLayout: TabLayout
   private lateinit var viewPager2: ViewPager2
   private lateinit var adapter: FragmentPageAdapter

//   private lateinit var btnBack2: ImageView
//   private lateinit var btnBack: ImageView

   private var _binding: ActivityPemulaBinding? = null
   private val binding get() = _binding!!


   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      _binding = ActivityPemulaBinding.inflate(layoutInflater)
      setContentView(binding.root)

      tabLayout = findViewById(R.id.tabLayout)
      viewPager2 = findViewById(R.id.viewPager2)

      adapter = FragmentPageAdapter(this)

      viewPager2.adapter = adapter

      binding.backBtn.setOnClickListener {
         onBackPressed()
      }
      
      TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
         when (position) {
            0 -> {
               tab.text = "Pemula"
            }

            1 -> {
               tab.text = "Menengah"
            }

            2 -> {
               tab.text = "Lanjutan"
            }
         }
      }.attach()

      val tabSelected = intent.getIntExtra("TAB_SELECTED", 0)
      binding.tabLayout.selectTab(tabLayout.getTabAt(tabSelected)) // Mengatur tab kedua (index 1) sebagai tab awal

      when (tabSelected) {
         0 -> {
            window.statusBarColor =
               getColor(R.color.purple)
         }

         1 -> {
            window.statusBarColor =
               getColor(R.color.pink)
         }

         2 -> {
            window.statusBarColor =
               getColor(R.color.lanjutan_color)
         }
      }

      binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
         override fun onTabSelected(tab: TabLayout.Tab) {
            // Mengubah latar belakang tab yang aktif
            when (tab.position) {
               0 -> {
                  window.statusBarColor =
                     getColor(R.color.purple)
               }

               1 -> {
                  window.statusBarColor =
                     getColor(R.color.pink)
               }

               2 -> {
                  window.statusBarColor =
                     getColor(R.color.lanjutan_color)
               }
            }
         }

         override fun onTabUnselected(tab: TabLayout.Tab) {
            // Mengembalikan latar belakang tab yang tidak aktif
            tab.view.setBackgroundColor(Color.TRANSPARENT) // Ganti dengan warna yang diinginkan
         }

         override fun onTabReselected(tab: TabLayout.Tab) {
            // Tidak diperlukan perubahan untuk tab yang dipilih kembali
         }
      })
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}