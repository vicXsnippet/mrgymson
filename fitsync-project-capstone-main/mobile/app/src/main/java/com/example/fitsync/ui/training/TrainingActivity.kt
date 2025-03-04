package com.example.fitsync.ui.training

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.Surface
import android.view.TextureView
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fitsync.R
import com.example.fitsync.data.model.Exercise
import com.example.fitsync.databinding.ActivityTrainingBinding
import com.example.fitsync.ui.hasillatihan.HasilLatihanActivity
import com.example.fitsync.ui.persiapanlatihan.PersiapanLatihanActivity
import com.example.fitsync.utils.imageProxyToBitmap
import com.example.fitsync.utils.modelLegRaises
import com.example.fitsync.utils.modelMovenet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TrainingActivity : AppCompatActivity() {
    private var _binding: ActivityTrainingBinding? = null
    private val binding get() = _binding!!
    private lateinit var bitmap: Bitmap
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private var countDownTimer: CountDownTimer? = null
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var imageProcessor: ImageProcessor
    private var gerakan: String? = null
    private var holder: Boolean = false
    private var hitungan: Int = 0
    private var getPositionGerakan1: Int = 0
    lateinit var listSaja: List<Exercise>
    lateinit var handler: Handler
    lateinit var handlerThread: HandlerThread
    lateinit var cameraManager: CameraManager
    val paint = Paint()
    private var repetisi: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        _binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        imageProcessor =
            ImageProcessor.Builder().add(ResizeOp(256, 256, ResizeOp.ResizeMethod.BILINEAR)).build()
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        handlerThread = HandlerThread("videoThread")
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraPermission()
        } else {

        }

        paint.setColor(Color.YELLOW)
        cameraExecutor = Executors.newSingleThreadExecutor()


        val gerakan = intent.getStringExtra("gerakan")
        val getPositionGerakan = intent.getIntExtra("getPositionGerakan", 0)
        getPositionGerakan1 = getPositionGerakan

        val gson = Gson()
        val exerciseListType = object : TypeToken<List<Exercise>>() {}.type
        val listGerakan: List<Exercise> = gson.fromJson(gerakan, exerciseListType)
        listSaja = listGerakan

        binding.tvJenisLatihan.text = listGerakan[getPositionGerakan].name

        if (listGerakan[getPositionGerakan].repetitions is String) {
            try {
                val durasi = listGerakan[getPositionGerakan].repetitions.toString().toLong()
                countDownTimer = object : CountDownTimer(durasi, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val secondsRemaining = millisUntilFinished / 1000
                        binding.tvHitungan.text =
                            getString(R.string.timer, String.format("%02d", secondsRemaining))
                    }

                    override fun onFinish() {
                        if (getPositionGerakan == 3) {
                            val intent =
                                Intent(this@TrainingActivity, HasilLatihanActivity::class.java)
                            intent.putExtra("gerakan", gerakan)
                            startActivity(intent)
                            finish()
                        } else {
                            val intent =
                                Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                            intent.putExtra("getPositionGerakan", getPositionGerakan + 1)
                            intent.putExtra("gerakan", gerakan)
                            startActivity(intent)
                            finish()
                        }
                    }
                }.start()
                binding.btnNext.setOnClickListener {
                    if (getPositionGerakan == 3) {
                        val intent = Intent(this@TrainingActivity, HasilLatihanActivity::class.java)
                        intent.putExtra("gerakan", gerakan)
                        startActivity(intent)
                        finish()
                    } else {
                        val intent =
                            Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                        intent.putExtra("getPositionGerakan", getPositionGerakan + 1)
                        intent.putExtra("gerakan", gerakan)
                        startActivity(intent)
                        finish()
                    }
                }

                binding.btnPrevious.setOnClickListener {
                    if (getPositionGerakan == 0) {
                        val intent =
                            Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                        intent.putExtra("getPositionGerakan", getPositionGerakan - 1)
                        intent.putExtra("gerakan", gerakan)
                        startActivity(intent)
                        finish()
                    } else {
                        val intent =
                            Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                        intent.putExtra("getPositionGerakan", getPositionGerakan - 1)
                        intent.putExtra("gerakan", gerakan)
                        startActivity(intent)
                        finish()
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: ${e.message}")
            }
        } else {
            binding.tvHitungan.text = (if (listGerakan[getPositionGerakan].name == "LEG RAISES") {
                getString(
                    R.string.text_repetisi,
                    String.format("%.0f", hitungan.toFloat())
                )
            } else {
                getString(
                    R.string.text_repetisi,
                    String.format("%.0f", listGerakan[getPositionGerakan].repetitions)
                )
            }).toString()
            binding.btnNext.setOnClickListener {
                if (getPositionGerakan == 3) {
                    val intent = Intent(this@TrainingActivity, HasilLatihanActivity::class.java)
                    intent.putExtra("gerakan", gerakan)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                    intent.putExtra("getPositionGerakan", getPositionGerakan + 1)
                    intent.putExtra("gerakan", gerakan)
                    startActivity(intent)
                    finish()
                }
            }
            binding.btnPrevious.setOnClickListener {
                if (getPositionGerakan == 0) {
                    val intent = Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                    intent.putExtra("getPositionGerakan", getPositionGerakan - 1)
                    intent.putExtra("gerakan", gerakan)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                    intent.putExtra("getPositionGerakan", getPositionGerakan - 1)
                    intent.putExtra("gerakan", gerakan)
                    startActivity(intent)
                    finish()
                }
            }
        }
        fun chek() {
            if (hitungan.toDouble() == listGerakan[getPositionGerakan].repetitions) {
                if (getPositionGerakan == 3) {
                    val intent = Intent(this@TrainingActivity, HasilLatihanActivity::class.java)
                    intent.putExtra("gerakan", gerakan)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@TrainingActivity, PersiapanLatihanActivity::class.java)
                    intent.putExtra("getPositionGerakan", getPositionGerakan + 1)
                    intent.putExtra("gerakan", gerakan)
                    startActivity(intent)
                    finish()
                }
            }
        }
        binding.textureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {
                open_camera()
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int
            ) {

            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
                // TextureView is updated, process the current frame
                val bitmap = binding.textureView.bitmap ?: return
                val tensorImage = TensorImage(DataType.UINT8)
                tensorImage.load(bitmap)

                // Optionally, you can apply image processing using imageProcessor
                // Assuming imageProcessor is initialized in onCreate or elsewhere
                imageProcessor.process(tensorImage)

                // Perform model inference
                val outputTensor = modelMovenet(this@TrainingActivity, tensorImage.tensorBuffer)
                val outputFeature0 = outputTensor.floatArray

                Log.d("Outputs", "Hasil output : ${outputFeature0.contentToString()}")

                var mutable = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                var canvas = Canvas(mutable)
                var h = bitmap.height
                var w = bitmap.width
                var x = 0

                Log.d("output__", outputFeature0.size.toString())
                while (x <= 49) {
                    if (outputFeature0.get(x + 2) > 0.45) {
                        canvas.drawCircle(
                            outputFeature0.get(x + 1) * w,
                            outputFeature0.get(x) * h,
                            10f,
                            paint
                        )
                    }
                    x += 3
                }

                binding.viewFinder.setImageBitmap(mutable)

                // Update the OverlayView with the keypoints
                if (listSaja?.get(getPositionGerakan1)?.name == "LEG RAISES") {
                    val output = modelLegRaises(this@TrainingActivity, outputTensor)
                    if (output == "1" && holder) {
                        holder = false
                        binding.tvHitungan.text = "X ${hitungan.toString()}"
                        hitungan++
                    } else if (output == "0") {
                        holder = true
                    }
                    chek()
                    Log.d(
                        "Hitungan",
                        " ini hitungan : $hitungan dan ini repetisi ${
                            listSaja?.get(getPositionGerakan1)?.repetitions
                        }"
                    )

                    Log.d("Legraises", "Output model: $output")
                    Log.d("Legraises", "Output hitungan: $hitungan")
                } else {
                    Log.d("gagal", "output: NaN")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()


    }


    @SuppressLint("MissingPermission")
    fun open_camera() {
        cameraManager.openCamera(
            cameraManager.cameraIdList[0],
            object : CameraDevice.StateCallback() {
                override fun onOpened(p0: CameraDevice) {
                    var captureRequest = p0.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                    var surface = Surface(binding.textureView.surfaceTexture)
                    captureRequest.addTarget(surface)
                    p0.createCaptureSession(
                        listOf(surface),
                        object : CameraCaptureSession.StateCallback() {
                            override fun onConfigured(p0: CameraCaptureSession) {
                                p0.setRepeatingRequest(captureRequest.build(), null, null)
                            }

                            override fun onConfigureFailed(p0: CameraCaptureSession) {

                            }
                        },
                        handler
                    )
                }

                override fun onDisconnected(p0: CameraDevice) {

                }

                override fun onError(p0: CameraDevice, p1: Int) {

                }
            },
            handler
        )
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startCamera()

            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        countDownTimer?.cancel()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1001
        private const val TAG = "TrainingActivity"
    }
}
