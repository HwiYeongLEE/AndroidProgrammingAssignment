package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val phoneNumber = intent.data?.schemeSpecificPart // 받아온 Intent의 data를 phoneNumber에 저장
        val toastText = "You can't call to $phoneNumber !!!!" // Toast로 내보낼 텍스트 생성
        val replyIntent = Intent().apply {
            putExtra("toastText", toastText)
        }   // Intent에 toastText 전달
        setResult(Activity.RESULT_OK, replyIntent) // originating activity로 Result Code와 Intent 전달
        finish()
    }
}