package com.melihkarakilinc.paging3example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PhotoViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    val photoList =Pager(PagingConfig(pageSize = 100)){
        PhotoDataSource(apiInterface)
    }.flow.cachedIn(viewModelScope)
}