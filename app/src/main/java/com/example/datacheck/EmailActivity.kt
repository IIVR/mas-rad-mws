package com.example.datacheck

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_email.*
import kotlinx.android.synthetic.main.activity_email.resultText
import kotlinx.android.synthetic.main.activity_email.resultTextDetails
import kotlinx.android.synthetic.main.activity_password.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.URLEncoder
import java.util.*
import kotlin.collections.ArrayList

class EmailActivity : AppCompatActivity() {

    lateinit var email : String
    var breachList: ArrayList<DataBreach>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

    }

    fun checkEmail(view: View) {
        // Hide Keyboard
        val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
        btnShowDetails.visibility = View.GONE;
        resultText.text = ""
        resultTextDetails.text = ""

        email =  editTextEmail.text.toString()

        if (email.isEmailValid()){

            val retrofit = Retrofit.Builder()
                .baseUrl("https://haveibeenpwned.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: HIBPService = retrofit.create(HIBPService::class.java)
            val call: Call<List<DataBreach>> = service.getEmail(email)

            call.enqueue(object: Callback<List<DataBreach>> {
                override fun onResponse(call: Call<List<DataBreach>>, response: Response<List<DataBreach>>) {

                    breachList = response?.body() as ArrayList<DataBreach>?

                    if(breachList!=null){

                        resultText.text = "Oh no — pwned!"
                        resultText.setTextColor(Color.parseColor("#db2b1f"))
                        resultTextDetails.text = "Pwned on ${breachList!!.count()} breached sites"

                        btnShowDetails.visibility = View.VISIBLE;

                    }else{

                        resultText.text = "Good news — no pwnage found!"
                        resultText.setTextColor(Color.parseColor("#0aad3f"))
                        resultTextDetails.text = "No breached accounts"

                    }

                }

                override fun onFailure(call: Call<List<DataBreach>>, t: Throwable) {
                    println("Fail "+t)
                }

            })

        }else{
            resultTextDetails.text = "Please enter a valid Email"

        }
    }

    fun showDetails(view: View) {

        val intent = Intent(this, BreachDetailsActivity::class.java)
        intent.putExtra("breachList", breachList)
        intent.putExtra("email", email)

        startActivity(intent)

    }


    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }




}