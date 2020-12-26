package com.esiea.projet4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.projet4a.domain.entity.User
import com.esiea.projet4a.domain.usecase.CreateUserUseCase
import com.esiea.projet4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main as Main1
import kotlinx.coroutines.withContext as withContext1

class MainViewModel(
        val createUserUseCase: CreateUserUseCase,
        private val getUserUseCase: GetUserUseCase
) : ViewModel(){

        val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()
        init{
               // counter.value = 0
        }

        fun onClickedLogin(emailUser: String,password:String){
                viewModelScope.launch(Dispatchers.IO) {
                       val user: User? = getUserUseCase.invoke(emailUser)
                        val loginStatus= if(user!=null){
                                LoginSuccess(user.email)
                        }else{
                                LoginError
                        }
                    withContext1(Main1) {
                        loginLiveData.value = loginStatus
                    }
                       // val user:User =  getUserUseCase.invoke("test")


                }

            //    counter.value = (counter.value ?: 0) + 1
        }

}