package com.example.apilearning.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilearning.data.ResponseState
import com.example.apilearning.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repositoryImpl: RepositoryImpl
) : ViewModel() {

    private val _cocktails: MutableLiveData<ResponseState> by lazy {
        MutableLiveData<ResponseState>()
    }
    val cocktails: LiveData<ResponseState> = _cocktails
    fun getDrinksList() {
        //loading state before coroutine is launched

        try {
            _cocktails.postValue(ResponseState.Loading)
            viewModelScope.launch() {
                val result = repositoryImpl.getDrinks()
                if (result.drinks.isNullOrEmpty()) {
                    _cocktails.postValue(ResponseState.Fail("Failed to retrieve from the API"))
                } else {
                    _cocktails.value = ResponseState.Success(result)
                }
            }
        } catch (e: SocketTimeoutException) {
            _cocktails.postValue(ResponseState.Fail(e.message.toString()))
        } catch (e: Exception) {
            _cocktails.postValue(ResponseState.Fail(e.message.toString()))
        }
    }

}