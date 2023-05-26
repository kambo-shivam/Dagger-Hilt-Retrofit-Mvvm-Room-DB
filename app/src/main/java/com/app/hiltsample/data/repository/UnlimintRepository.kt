package com.app.hiltsample.data.repository

import com.app.hiltsample.data.api.ApiHelper
import com.app.hiltsample.data.model.JokesModel
import com.app.hiltsample.database.JokesDao
import javax.inject.Inject

class UnlimintRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val jokesDao: JokesDao? = null
) {

    suspend fun getUsers() = apiHelper.getUsers()

    fun insertJoke(user: JokesModel) {
        jokesDao!!.insertJoke(user)
    }

    fun getAllJokes(): List<JokesModel> {
        return jokesDao!!.getAllJokes()
    }

    fun deleteFirstJoke(joke: JokesModel?) {
        jokesDao?.deleteJoke(joke)
    }
}