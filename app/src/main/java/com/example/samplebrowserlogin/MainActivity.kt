package com.example.samplebrowserlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.samplebrowserlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "LOGIN"

    private lateinit var binding: ActivityMainBinding

    /**
     * アプリ新規起動時に呼ばれる
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // ログインボタンの動作
        binding.btnLogin.setOnClickListener {
        }
    }
}