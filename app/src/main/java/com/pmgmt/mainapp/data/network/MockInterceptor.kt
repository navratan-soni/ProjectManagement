package com.pmgmt.mainapp.data.network

import android.content.Context
import com.pmgmt.mainapp.R
import com.pmgmt.mainapp.data.source.LocalJsonReader
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(private val context: Context) : Interceptor {
    /*override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // Redirect the specific login API call to local JSON
        val response = if (url.contains("architect_login.json")) {
            val json = LocalJsonReader.readJson(context, R.raw.architect_login)
            chain.proceed(request)
                .newBuilder()
                .body(json.toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        } else {
            chain.proceed(request)
        }

        return response
    }

    companion object {
        fun create(context: Context): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(MockInterceptor(context))
                .build()
        }
    }*/

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        // Map specific URLs to local JSON files in res/raw
        val jsonFileName = when {
            url.contains("login") -> "architect_login.json"
            url.contains("project_details") -> "project_details.json"
            // Add more cases as needed for different mock endpoints
            else -> null
        }

        return if (jsonFileName != null) {
            // Mock response from local file
            val jsonResponse = readJsonFromFile(jsonFileName) ?: "{}"
            Response.Builder()
                .request(request)
                .protocol(chain.connection()?.protocol() ?: Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(jsonResponse.toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        } else {
            // Fallback to proceed with a real network request if no mock data is found
            chain.proceed(request)
        }
    }

    private fun readJsonFromFile(fileName: String): String? {
        val resourceId = context.resources.getIdentifier(fileName.substringBeforeLast("."), "raw", context.packageName)
        return if (resourceId != 0) {
            context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
        } else {
            null
        }
    }
}