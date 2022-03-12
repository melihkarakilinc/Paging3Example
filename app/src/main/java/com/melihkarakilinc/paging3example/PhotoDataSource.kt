package com.melihkarakilinc.paging3example

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class PhotoDataSource(private val apiInterface: ApiInterface) : PagingSource<Int, Hit>() {
    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        try {
            val currentPageList: Int = params.key ?: 1
            val response = apiInterface.getPhotoApiData(currentPageList)
            val responseList = mutableListOf<Hit>()

            val data = response.body()?.hits ?: emptyList()
            Log.e("DATA",data.toString())
            responseList.addAll(data)

            val prevKey = if (currentPageList == 1)
                null else currentPageList - 1

            return LoadResult.Page(
                responseList,
                prevKey,
                currentPageList.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}