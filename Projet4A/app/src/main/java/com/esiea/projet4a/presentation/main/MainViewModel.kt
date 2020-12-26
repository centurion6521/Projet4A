package com.esiea.projet4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.projet4a.domain.entity.User
import com.esiea.projet4a.domain.usecase.CreateUserUseCase
import com.esiea.projet4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
        val createUserUseCase: CreateUserUseCase,
        private val getUserUseCase: GetUserUseCase
) : ViewModel(){




        val counter: MutableLiveData<Int> = MutableLiveData()
        init{
                counter.value = 0
        }

        fun onClickedIncrement(emailUser: String){
                viewModelScope.launch(Dispatchers.IO) {
                        createUserUseCase.invoke(User("test"))
                        val user:User =  getUserUseCase.invoke("test")
                        val debug = "debug"
                }

            //    counter.value = (counter.value ?: 0) + 1
        }

}