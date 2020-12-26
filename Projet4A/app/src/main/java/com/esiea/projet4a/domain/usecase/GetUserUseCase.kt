package com.esiea.projet4a.domain.usecase

import com.esiea.projet4a.data.repository.UserRepository
import com.esiea.projet4a.domain.entity.User

class GetUserUseCase (
    private val userRepository: UserRepository
){suspend fun invoke(email:String) : User {
   return userRepository.getUser(email)
}
}