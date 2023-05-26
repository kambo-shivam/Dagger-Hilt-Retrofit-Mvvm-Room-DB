package com.app.hiltsample.data.api

import com.app.hiltsample.data.model.JokesModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<JokesModel>
}