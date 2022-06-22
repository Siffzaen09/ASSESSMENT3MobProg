package org.d3if4081.ass1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4081.ass1.model.BendaJajarGenjang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Siffzaen09/Assessment1MPRO/main/"
private const val BASE_URL_IMG = "https://raw.githubusercontent.com/Siffzaen09/Assessment1MPRO/main/image/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BendaJajarGenjangService {
    @GET("benda-jajar-genjang.json")
    suspend fun getResult(): List<BendaJajarGenjang>
}

object BendaJajarGenjangApi {
    val service: BendaJajarGenjangService by lazy {
        retrofit.create(BendaJajarGenjangService::class.java)
    }
    fun getBendaJajarGenjangUrl(gambar: String): String {
        return "$BASE_URL_IMG$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }