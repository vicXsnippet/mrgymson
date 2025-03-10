package com.example.fitsync.ui.menengah

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.fitsync.R
import com.example.fitsync.databinding.FragmentMenengahBinding
import com.example.fitsync.databinding.FragmentPemulaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [MenengahFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenengahFragment : Fragment() {
   // TODO: Rename and change types of parameters
   private var param1: String? = null
   private var param2: String? = null

   private var _binding: FragmentMenengahBinding? = null

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

      _binding = FragmentMenengahBinding.inflate(inflater, container, false)

      binding.cardPerutMenengah.setOnClickListener {
         val intent = Intent(activity, LatihanOtotPerutMenengahActivity::class.java)
         startActivity(intent)
      }

      binding.cardDadaMenengah.setOnClickListener {
         val intent = Intent(activity, LatihanOtotDadaMenengahActivity::class.java)
         startActivity(intent)
      }

      binding.cardLenganMenengah.setOnClickListener {
         val intent = Intent(activity, LatihanOtotLenganMenengahActivity::class.java)
         startActivity(intent)
      }

      binding.cardKakiMenengah.setOnClickListener {
         val intent = Intent(activity, LatihanOtotKakiMenengahActivity::class.java)
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
       * @return A new instance of fragment SecondFragment.
       */
      // TODO: Rename and change types and number of parameters
      @JvmStatic
      fun newInstance(param1: String, param2: String) =
         MenengahFragment().apply {
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