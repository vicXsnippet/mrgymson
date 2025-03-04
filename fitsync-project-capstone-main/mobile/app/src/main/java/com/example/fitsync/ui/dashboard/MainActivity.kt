package com.example.fitsync.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitsync.R
import com.example.fitsync.data.ResultState
import com.example.fitsync.databinding.ActivityMainBinding
import com.example.fitsync.ui.ViewModelFactory
import com.example.fitsync.ui.adapter.HistoryLatihanAdapter
import com.example.fitsync.ui.opening.Opening
import com.example.fitsync.ui.pemula.PemulaActivity
import com.example.fitsync.ui.profile.ProfileActivity
import com.example.fitsync.utils.popup
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class MainActivity : AppCompatActivity() {

   private var _binding: ActivityMainBinding? = null
   private val binding get() = _binding!!

   private val viewModel by viewModels<MainViewModel> {
      ViewModelFactory.getInstance(this)
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      _binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)
      enableEdgeToEdge()
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         insets
      }

      window.statusBarColor = getColor(R.color.dashboard_color)
      setSupportActionBar(binding.topAppBar)

      viewModel.getUserId().observe(this@MainActivity) { userId ->
         if (userId == "") {
            val intent = Intent(this@MainActivity, Opening::class.java)
            startActivity(intent)
            finish()
         }

         viewModel.getDashboardData(id = userId)
         viewModel.dashboardData.observe(this@MainActivity) { result ->
            when (result) {
               is ResultState.Loading -> {
                  showLoading(true)
               }

               is ResultState.Success -> {
                  showLoading(false)
                  val data = result.data
                  binding.apply {
                     tvHaiUser.text =
                        getString(R.string.hai_user, String.format("%s", data.lastName))
                     tvKaloriValue.text = data.kaloriPerMinggu.toString()
                     tvTimerValue.text = String.format(Locale.US,"%.1f", data.durasiPerMinggu)
                     val metrikLatihan =
                        data.metrikLatihan?.sortedByDescending { it?.tanggalBulanLatihan }
                     if (metrikLatihan!!.isNotEmpty()) {
                        rvHistoryLatihan.visibility = View.VISIBLE
                        tvHistoryKosong.visibility = View.GONE
                        rvHistoryLatihan.layoutManager =
                           LinearLayoutManager(this@MainActivity)
                        rvHistoryLatihan.adapter =
                           HistoryLatihanAdapter(metrikLatihan, this@MainActivity)
                     }
                  }
               }

               is ResultState.Error -> {
                  showLoading(false)
                  val snackbar = Snackbar.make(binding.root, result.message, Snackbar.LENGTH_LONG)
                  snackbar.setBackgroundTint(getColor(R.color.pink))
                  snackbar.setAction("Coba lagi") {
                     recreate()
                  }
                  snackbar.setActionTextColor(getColor(R.color.white))
                  snackbar.show()
               }
            }
         }
      }

      binding.apply {
         btnPemula.setOnClickListener {
            val intent = Intent(this@MainActivity, PemulaActivity::class.java).apply {
               putExtra("TAB_SELECTED", 0)
            }
            startActivity(intent)
         }

         cardPemula.setOnClickListener {
            val intent = Intent(this@MainActivity, PemulaActivity::class.java).apply {
               putExtra("TAB_SELECTED", 0)
            }
            startActivity(intent)
         }


         btnMenengah.setOnClickListener {
            val intent = Intent(this@MainActivity, PemulaActivity::class.java).apply {
               putExtra("TAB_SELECTED", 1)
            }
            startActivity(intent)
         }

         cardMenengah.setOnClickListener {
            val intent = Intent(this@MainActivity, PemulaActivity::class.java).apply {
               putExtra("TAB_SELECTED", 1)
            }
            startActivity(intent)
         }

         btnLanjutan.setOnClickListener {
            val intent = Intent(this@MainActivity, PemulaActivity::class.java).apply {
               putExtra("TAB_SELECTED", 2)
            }
            startActivity(intent)
         }

         cardLanjutan.setOnClickListener {
            val intent = Intent(this@MainActivity, PemulaActivity::class.java).apply {
               putExtra("TAB_SELECTED", 2)
            }
            startActivity(intent)
         }
      }
   }

   override fun onResume() {
      super.onResume()
      viewModel.getUserId().observe(this@MainActivity) { userId ->
         if (userId == "") {
            val intent = Intent(this@MainActivity, Opening::class.java)
            startActivity(intent)
            finish()
         }
      }
   }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
      val inflater: MenuInflater = menuInflater
      inflater.inflate(R.menu.menu, menu)
      return true
   }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
      // Handle item selection.
      return when (item.itemId) {
         R.id.profile -> {
            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(intent)
            true
         }

         R.id.keluar -> {
            popup(this, getString(R.string.apakah_anda_ingin_keluar), onClick = {
               viewModel.logout()
            })
            true
         }

         else -> super.onOptionsItemSelected(item)

      }
   }

   private fun showLoading(isLoading: Boolean) {
      binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}