package com.app.hiltsample.data.api

import com.app.hiltsample.data.model.JokesModel
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<JokesModel> = apiService.getUsers("json")

}