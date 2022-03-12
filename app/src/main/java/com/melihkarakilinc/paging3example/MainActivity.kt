package com.melihkarakilinc.paging3example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PhotoViewModel
    lateinit var adapter: PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            PhotoViewModelFactory(ApiInterface.getApi())
        )[PhotoViewModel::class.java]

        adapter= PhotoAdapter()

        lifecycleScope.launch {
            viewModel.photoList.collect {
                Log.e("DATA",it.toString())
                adapter.submitData(it)
            }
        }
        bindUi()
    }
    private fun bindUi(){
        val rv=findViewById<RecyclerView>(R.id.rv)
        rv.apply {
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=adapter
        }
    }
}