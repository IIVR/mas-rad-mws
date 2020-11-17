package com.example.datacheck

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HIBPService {

    @GET("range/{password}")
    fun getPasswords(@Path("password") password: String?): Call<String>
}