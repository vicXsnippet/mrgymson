package com.example.fitsync.ui.lanjutan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.fitsync.databinding.FragmentLanjutanBinding
import com.example.fitsync.databinding.FragmentMenengahBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LanjutanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LanjutanFragment : Fragment() {
   // TODO: Rename and change types of parameters
   private var param1: String? = null
   private var param2: String? = null

   private var _binding: FragmentLanjutanBinding? = null

   // Properti untuk mengakses binding dengan aman
   private val binding get() = _binding!!

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      arguments?.let {
         param1 = it.getString(ARG_PARAM1)
         param2 = it.getString(ARG_PARAM2)
      }
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      _binding = FragmentLanjutanBinding.inflate(inflater, container, false)

      binding.cardPerutLanjutan.setOnClickListener {
         val intent = Intent(activity, LatihanOtotPerutLanjutanActivity::class.java)
         startActivity(intent)
      }

      binding.cardDadaLanjutan.setOnClickListener {
         val intent = Intent(activity, LatihanOtotDadaLanjutanActivity::class.java)
         startActivity(intent)
      }

      binding.cardLenganLanjutan.setOnClickListener {
         val intent = Intent(activity, LatihanOtotLenganLanjutanActivity::class.java)
         startActivity(intent)
      }

      binding.cardKakiLanjutan.setOnClickListener {
         val intent = Intent(activity, LatihanOtotKakiLanjutanActivity::class.java)
         startActivity(intent)
      }
      return binding.root
   }

   companion object {
      /**
       * Use this factory method to create a new instance of
       * this fragment using the provided parameters.
       *
       * @param param1 Parameter 1.
       * @param param2 Parameter 2.
       * @return A new instance of fragment ThirdFragment.
       */
      // TODO: Rename and change types and number of parameters
      @JvmStatic
      fun newInstance(param1: String, param2: String) =
         LanjutanFragment().apply {
            arguments = Bundle().apply {
               putString(ARG_PARAM1, param1)
               putString(ARG_PARAM2, param2)
            }
         }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      // Membersihkan binding saat view dihancurkan
      _binding = null
   }
}