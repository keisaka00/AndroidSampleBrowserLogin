package com.example.samplebrowserlogin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.samplebrowserlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val URL_LOGIN_PAGE = "https://ToDo:ブラウザで開くログインURLを記載する" // Todo
    private val URL_RESIRECT = "com-example-samplebrowserlogin://login-token"
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
            // URLを指定してブラウザを開く
            LoginBrowser()
        }
    }


    /**
     * 常駐したまま別のアプリから戻ってきたときにonCreate()の代わりに呼ばれる
     * 　※戻ってくる予定でも常駐が解除されてしまうこともあり、そうするとonCreate()が呼ばれるので注意
     */
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (intent == null) return

        val action = intent.action
        if (Intent.ACTION_VIEW == action) {
            // URLスキームから呼び出された
            setIntent(intent)
        }
    }


    override fun onResume() {
        super.onResume()

        val intent = intent
        val action = intent.action

        if (Intent.ACTION_VIEW == action) {
            // URLスキームから呼び出されたときのパラメータはここで処理する
            val uri: Uri? = intent.data
            if (uri == null) {
                Log.i(TAG, String.format("error: uri was null"))
            }
            else {
                val code  = uri.getQueryParameter("code")
                Log.i(TAG, String.format("code=%s", code))
            }
        }
    }


    /**
     * ブラウザを呼び出してログインする
     */
    private fun LoginBrowser() {
        Log.d(TAG, String.format("Login Browser"))
        val uri = makeAuthorizeUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        ContextCompat.startActivity(this, intent, null)
    }


    /**
     * ログイン用URL作成
     */
    private fun makeAuthorizeUri(): Uri {
        val builder = Uri.parse(URL_LOGIN_PAGE)
            .buildUpon()
            .appendQueryParameter("redirect_uri", URL_RESIRECT)
        return builder.build()
    }
}