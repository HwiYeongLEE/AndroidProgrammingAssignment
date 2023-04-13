package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //ActivityResultLauncher 타입 객체 생성
    private val callLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) { // RESULT_OK일 때 실행할 라인
            val data = result.data  //반환된 ActivityResult 객체로부터 intent 획득
            val message = data?.getStringExtra("toastText") // intent에 포함된 toastText string을 message에 저장
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show() // 메시지 Toast로 출력
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 뷰 바인딩 생성 및 적용
        val intent = Intent(Intent.ACTION_DIAL) // Implicit Intent 선언
        val chooser = Intent.createChooser(intent, "Choose a phone app") // Chooser 선언
        binding.btnSay.setOnClickListener {
            val phoneNumber = binding.editTextPhone.text.toString()
            intent.data = Uri.parse("tel:$phoneNumber")
            callLauncher.launch(chooser)    // btnSay 터치 시 입력 박스 안의 번호를 phoneNumber로 저장하고 intent에 전달, chooser 실행하여 앱 선택 화면이 나오도록 한다.
        }
    }
}