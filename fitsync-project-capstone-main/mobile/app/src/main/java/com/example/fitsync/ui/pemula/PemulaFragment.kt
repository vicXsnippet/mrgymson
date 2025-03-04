package com.example.fitsync.ui.pemula

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.fitsync.databinding.FragmentPemulaBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PemulaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PemulaFragment : Fragment() {
   // TODO: Rename and change types of parameters
   private var param1: String? = null
   private var param2: String? = null

   private var _binding: FragmentPemulaBinding? = null

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
      // Inflate the layout for this fragment
      _binding = FragmentPemulaBinding.inflate(inflater, container, false)

      binding.cardPerutPemula.setOnClickListener {
         val intent = Intent(activity, LatihanOtotPerutPemulaActivity::class.java)
         startActivity(intent)
      }

      binding.cardDadaPemula.setOnClickListener {
         val intent = Intent(activity, LatihanDadaPemulaActivity::class.java)
         startActivity(intent)
      }

      binding.cardLenganPemula.setOnClickListener {
         val intent = Intent(activity, LatihanOtotLenganPemulaActivity::class.java)
         startActivity(intent)
      }

      binding.cardKakiPemula.setOnClickListener {
         val intent = Intent(activity, LatihanOtotKakiPemulaActivity::class.java)
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
       * @return A new instance of fragment FirstFragment.
       */
      // TODO: Rename and change types and number of parameters
      @JvmStatic
      fun newInstance(param1: String, param2: String) =
         PemulaFragment().apply {
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