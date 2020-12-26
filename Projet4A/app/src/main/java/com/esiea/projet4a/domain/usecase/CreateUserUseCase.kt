package com.esiea.projet4a.domain.usecase

import com.esiea.projet4a.data.repository.UserRepository
import com.esiea.projet4a.domain.entity.User

class CreateUserUseCase( private val userRepository: UserRepository) {


    suspend fun invoke(user: User){
        userRepository.createUser(user)



    }
}