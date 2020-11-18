package com.example.datacheck

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_password.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.math.BigInteger
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

        resultText.text = ""
        resultTextDetails.text = ""
        // Hide Keyboard
        val inputManager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)

        password = editTextPassword.text.toString()
        hashPassword =  getSHA1(password).toUpperCase()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pwnedpasswords.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val service: HIBPService = retrofit.create(HIBPService::class.java)

        val call:Call<String> = service.getPasswords(hashPassword.take(5))

        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                println("reponse: ${response.body()}")

                val result: String? = response.body()
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
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

    private fun getSHA1(input: String):String{
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