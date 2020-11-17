package com.example.datacheck



import android.content.Context
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_password.*
import java.io.InputStreamReader
import java.math.BigInteger
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest


class PasswordActivity : AppCompatActivity() {

    lateinit var hashPassword : String
    lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
    }


    fun checkPassword(view: View) {

        println(editTextPassword.text.toString())
        progressBar.visibility = VISIBLE


        resultText.text = ""
        resultTextDetails.text = ""
        // Hide Keyboard
        val inputManager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)



        val downloadData = Download()
        try {
            val url = "https://api.pwnedpasswords.com/range/"

            password = editTextPassword.text.toString()
            hashPassword =  getSHA1(password).toUpperCase()

            println(hashPassword)

            downloadData.execute(url + hashPassword.take(5))
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    inner class Download : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg p0: String?): String {

            var result = ""
            var url: URL
            val httpURLConnection: HttpURLConnection

            try {

                url = URL(p0[0])
                httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)

                var data = inputStreamReader.read()

                while (data > 0) {
                    val character = data.toChar()
                    result += character
                    data = inputStreamReader.read()

                }

                return result
            } catch (e: Exception) {
                e.printStackTrace()
                return result

            }

        }
        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            progressBar.visibility = INVISIBLE
            try {
                println("2 " + result?.length)
                var exposedPass = false
                var nbExpose = 0

                if (result !=  null && result.isNotEmpty()){

                    val arrayHash = result.lines()

                    for (hash in arrayHash){

                        if(hashPassword.takeLast(35) == hash.substringBefore(':')){
                            exposedPass = true
                            nbExpose = hash.substringAfter(':').toInt()
                            break
                        }
                    }

                    if (exposedPass){
                        resultText.text = "Oh no — pwned!"
                        resultText.setTextColor(Color.parseColor("#db2b1f"))
                        resultTextDetails.text = "This password has been seen $nbExpose times before"

                    }else{
                        resultText.text = "Good news — no pwnage found!"
                        resultText.setTextColor(Color.parseColor("#0aad3f"))
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getSHA1(input: String):String{
        val md: MessageDigest = MessageDigest.getInstance("SHA-1")
        val messageDigest = md.digest(input.toByteArray())

        // Convert byte array into signum representation
        val no = BigInteger(1, messageDigest)

        // Convert message digest into hex value
        var hashtext: String = no.toString(16)

        // Add preceding 0s to make it 32 bit
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        // return the HashText
        return hashtext
    }




}