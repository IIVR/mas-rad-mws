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

    @Headers("hibp-api-key: 9d9fde3c47a3470980d826f628aab049")
    @GET("api/v3/breachedaccount/{email}")
    fun getEmail(@Path("email") email: String?): Call<List<DataBreach>>

}