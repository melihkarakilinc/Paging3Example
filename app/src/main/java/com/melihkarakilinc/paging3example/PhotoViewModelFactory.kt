package com.melihkarakilinc.paging3example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PhotoViewModelFactory(private val apiInterface: ApiInterface):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)){
            return PhotoViewModel(apiInterface) as T
        }
        throw IllegalArgumentException("not found viewModel")
    }
}