package com.example.apilearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilearning.data.ResponseState
import com.example.apilearning.data.api.RetrofitClient
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class MainViewModel : ViewModel() {
    /*    //USED FOR VIEWMODEL EXAMPLE 1 --------------------
       private val _counterValue = MutableLiveData<Int>().apply {
            value = 0
        }
        val counterValue: LiveData<Int> = _counterValue

        fun addValue(valueToAdd: Int){
            _counterValue.value=_counterValue.value?.toInt()?.plus(valueToAdd)
        }

        fun subtractValue(valueToSubtract: Int){
            _counterValue.value=_counterValue.value?.toInt()?.minus(valueToSubtract)

        }
        //-------------------------*/

    private val _cocktails: MutableLiveData<ResponseState> by lazy {
        MutableLiveData<ResponseState>()
    }
    val cocktails: LiveData<ResponseState> = _cocktails
    fun getDrinksList() {
        //loading state before coroutine is launched

        try {
            _cocktails.postValue(ResponseState.Loading)
            viewModelScope.launch() {
                val result = RetrofitClient.apiInstance.getDrinks()
                if (result.drinks.isNullOrEmpty()) {
                    _cocktails.postValue(ResponseState.Fail("Failed to retrieve from the API"))
                } else {
                    _cocktails.value = ResponseState.Success(result)
                }
            }
        } catch (e: SocketTimeoutException) {
            _cocktails.postValue(ResponseState.Fail(e.message.toString()))
        } catch (e: Exception){
            _cocktails.postValue(ResponseState.Fail(e.message.toString()))
        }
    }

}