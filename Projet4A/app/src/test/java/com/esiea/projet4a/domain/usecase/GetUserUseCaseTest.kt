package com.esiea.projet4a.domain.usecase

import com.esiea.projet4a.data.repository.UserRepository
import com.esiea.projet4a.domain.entity.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserUseCaseTest {
    private val userRepository: UserRepository = mockk()
    private val classUnderTest = GetUserUseCase(userRepository)

    @Test
    fun `invoke with invalid email`(){
        runBlocking {
            //Given
            val email="a@a.c"
            val expectedUser= User("a@a.c")
            coEvery { userRepository.getUser(email) } returns User("a@a.c")
            //when
           val result= classUnderTest.invoke(email)
            //then
            coVerify(exactly=1){userRepository.getUser(email)}
            assertEquals(result,expectedUser)
        }



    }
}