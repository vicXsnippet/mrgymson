package com.example.fitsync.data.model

data class Exercise(
   val name: String,
   val repetitions: Any, // Menggunakan tipe Any untuk dapat menerima string atau integer
   val image: String
)
