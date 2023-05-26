package com.app.hiltsample.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hiltsample.R
import com.app.hiltsample.data.model.JokesModel
import com.app.hiltsample.databinding.ActivityMainBinding
import com.app.hiltsample.main.adapter.UnlimintAdapter
import com.app.hiltsample.main.viewmodel.UnlimintViewModel
import com.app.hiltsample.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnlimintActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: UnlimintViewModel by viewModels()
    private lateinit var adapter: UnlimintAdapter
    private var onLaunch = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.visibility = View.VISIBLE
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UnlimintAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.jokesLiveData.observe(this) {
            if (onLaunch) {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                for (item in it) {
                    renderList(item)
                }
                onLaunch = false
            }
        }

    }

    private fun renderList(users: JokesModel) {
        adapter.updateData(users)
    }
}
