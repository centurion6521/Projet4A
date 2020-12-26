package com.esiea.projet4a.data.repository

import com.esiea.projet4a.data.local.DatabaseDao
import com.esiea.projet4a.data.local.models.UserLocal
import com.esiea.projet4a.data.local.models.toData
import com.esiea.projet4a.data.local.models.toEntity
import com.esiea.projet4a.domain.entity.User

class UserRepository(private val databaseDao: DatabaseDao){
    suspend fun createUser(user: User){
    databaseDao.insert(user.toData())

    }

    fun getUser(email: String):User {
        val userLocal:UserLocal = databaseDao.findByName(email)
        return userLocal?.toEntity()
    }


}