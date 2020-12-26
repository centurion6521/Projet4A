package com.esiea.projet4a.domain.usecase

import com.esiea.projet4a.data.repository.UserRepository
import com.esiea.projet4a.domain.entity.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CreateUserUseCaseTest {
    private val userRepository: UserRepository = mockk()
    private val classUnderTest = CreateUserUseCase(userRepository)

    @Test
    fun resendMailSuccess(){
        runBlocking {
            //Given
            val user=User("")
            coEvery { userRepository.createUser(user) } returns Unit
            //when
            classUnderTest.invoke(user)
            //then
            coVerify(exactly=1){userRepository.createUser(user)}
        }



    }
}