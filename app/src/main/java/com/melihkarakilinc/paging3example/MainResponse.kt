package com.melihkarakilinc.paging3example

data class MainResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)