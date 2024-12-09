package com.example.apilearning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilearning.data.api.RetrofitClient
import com.example.apilearning.data.model.DrinkDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    private val _cocktails:MutableLiveData<DrinkDetailModel> by lazy{
        MutableLiveData<DrinkDetailModel>()
    }
    val cocktails: LiveData<DrinkDetailModel> =_cocktails
    fun getDrinksList(){
            viewModelScope.launch() {
                  _cocktails.value=RetrofitClient.apiInstance.getDrinks()
      }
    }

}