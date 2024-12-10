package com.example.apilearning.data

import com.example.apilearning.data.model.DrinkDetailModel

sealed class ResponseState {
    //loading
    object Loading: ResponseState()

    //success
    //ideally, this success response should be generic for dealing with a variety of API calls,
    //but since we only have one API call for now we can refer to the model by name
    data class Success(val result: DrinkDetailModel): ResponseState()

    //failure
    data class Fail(val failureString: String): ResponseState()
}