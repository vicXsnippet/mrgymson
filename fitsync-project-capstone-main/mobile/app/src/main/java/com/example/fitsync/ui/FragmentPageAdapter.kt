package com.example.fitsync

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitsync.ui.lanjutan.LanjutanFragment
import com.example.fitsync.ui.menengah.MenengahFragment
import com.example.fitsync.ui.pemula.PemulaFragment

class FragmentPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3 // Jumlah tab
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PemulaFragment()
            1 -> MenengahFragment()
            2 -> LanjutanFragment()
            else -> PemulaFragment()
        }
    }
}