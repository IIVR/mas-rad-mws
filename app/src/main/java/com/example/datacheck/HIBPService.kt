package com.example.datacheck

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface HIBPService {

    @GET("range/{password}")
    fun getPasswords(@Path("password") password: String?): Call<String>

    @Headers("hibp-api-key: c2ac5bddfeb5494fa2ccc8fa41784ddd")
    @GET("api/v3/breachedaccount/{email}?truncateResponse=false")
    fun getEmail(@Path("email") email: String?): Call<List<DataBreach>>

    @GET("api/v3/breaches")
    fun getAllBreaches(): Call<List<DataBreach>>

}