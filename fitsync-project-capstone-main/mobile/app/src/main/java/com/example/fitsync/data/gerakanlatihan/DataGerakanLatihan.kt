package com.example.fitsync.data.gerakanlatihan

import java.time.LocalDateTime

var waktuMulaiLatihan: LocalDateTime? = null
var waktuSelesaiLatihan: LocalDateTime? = null
var kaloriLatihan: Int = 0
var bagianLatihan: String? = null
var tingkatanLatihan: String? = null



val perutPemula = """
[
   {
      "name": "SIT UP",
      "repetitions": 16,
      "image": "situp_gif"
   },
   {
      "name": "LEG RAISES",
      "repetitions": 16,
      "image": "leg_raises_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 10,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "COBRA STRETCH",
      "repetitions": "20000",
      "image": "cobra_stretch_gif"
   },
]
""".trimIndent()

val perutMenengah = """
[
   {
      "name": "SIT UP",
      "repetitions": 26,
      "image": "situp_gif"
   },
   {
      "name": "LEG RAISES",
      "repetitions": 26,
      "image": "leg_raises_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 26,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "COBRA STRETCH",
      "repetitions": "30000",
      "image": "cobra_stretch_gif"
   },
]
""".trimIndent()

val perutLanjutan = """
[
   {
      "name": "SIT UP",
      "repetitions": 36,
      "image": "situp_gif"
   },
   {
      "name": "LEG RAISES",
      "repetitions": 36,
      "image": "leg_raises_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 36,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "COBRA STRETCH",
      "repetitions": "40000",
      "image": "cobra_stretch_gif"
   },
]
""".trimIndent()

val dadaPemula = """
[
   {
      "name": "TRICEPS DIPS",
      "repetitions": 16,
      "image": "tricep_dips_gif"
   },
   {
      "name": "COBRA STRECH",
      "repetitions": "20000",
      "image": "cobra_stretch_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 16,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "PUSH UP",
      "repetitions": 16,
      "image": "push_up_gif"
   },
]
""".trimIndent()
val dadaMenengah = """
[
   {
      "name": "TRICEPS DIPS",
      "repetitions": 26,
      "image": "tricep_dips_gif"
   },
   {
      "name": "COBRA STRECH",
      "repetitions": "30000",
      "image": "cobra_stretch_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 26,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "PUSH UP",
      "repetitions": 26,
      "image": "push_up_gif"
   },
]
""".trimIndent()

val dadaLanjutan = """
[
   {
      "name": "TRICEPS DIPS",
      "repetitions": 36,
      "image": "tricep_dips_gif"
   },
   {
      "name": "COBRA STRECH",
      "repetitions": "40000",
      "image": "cobra_stretch_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 36,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "PUSH UP",
      "repetitions": 36,
      "image": "push_up_gif"
   },
]
""".trimIndent()

val lenganPemula = """
[
   {
      "name": "TRICEPS DIPS",
      "repetitions": 16,
      "image": "tricep_dips_gif"
   },
   {
      "name": "JUMPING JACK",
      "repetitions": 16,
      "image": "jumping_jacks_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 16,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "PUSH UP",
      "repetitions": 16,
      "image": "push_up_gif"
   },
]
""".trimIndent()
val lenganMenengah = """
[
   {
      "name": "TRICEPS DIPS",
      "repetitions": 26,
      "image": "tricep_dips_gif"
   },
   {
      "name": "JUMPING JACK",
      "repetitions": 26,
      "image": "jumping_jacks_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 26,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "PUSH UP",
      "repetitions": 26,
      "image": "push_up_gif"
   },
]
""".trimIndent()
val lenganLanjutan = """
[
   {
      "name": "TRICEPS DIPS",
      "repetitions": 36,
      "image": "tricep_dips_gif"
   },
   {
      "name": "JUMPING JACK",
      "repetitions": 36,
      "image": "jumping_jacks_gif"
   },
   {
      "name": "DIAMOND PUSH UP",
      "repetitions": 36,
      "image": "diamond_push_up_gif"
   },
   {
      "name": "PUSH UP",
      "repetitions": 36,
      "image": "push_up_gif"
   },
]
""".trimIndent()

val kakiPemula = """
[
   {
      "name": "SQUATS",
      "repetitions": 16,
      "image": "squats_gif"
   },
   {
      "name": "JUMPING JACK",
      "repetitions": 16,
      "image": "jumping_jacks_gif"
   },
   {
      "name": "LEG RAISES",
      "repetitions": 16,
      "image": "leg_raises_gif"
   },
   {
      "name": "JUMPING SQUATS",
      "repetitions": 16,
      "image": "jumping_squats_gif"
   },
]
""".trimIndent()

val kakiMenengah = """
[
   {
      "name": "SQUATS",
      "repetitions": 26,
      "image": "squats_gif"
   },
   {
      "name": "JUMPING JACK",
      "repetitions": 26,
      "image": "jumping_jacks_gif"
   },
   {
      "name": "LEG RAISES",
      "repetitions": 26,
      "image": "leg_raises_gif"
   },
   {
      "name": "JUMPING SQUATS",
      "repetitions": 26,
      "image": "jumping_squats_gif"
   },
]
""".trimIndent()

val kakiLanjutan = """
[
   {
      "name": "SQUATS",
      "repetitions": 36,
      "image": "squats_gif"
   },
   {
      "name": "JUMPING JACK",
      "repetitions": 36,
      "image": "jumping_jacks_gif"
   },
   {
      "name": "LEG RAISES",
      "repetitions": 36,
      "image": "leg_raises_gif"
   },
   {
      "name": "JUMPING SQUATS",
      "repetitions": 36,
      "image": "jumping_squats_gif"
   },
]
""".trimIndent()

