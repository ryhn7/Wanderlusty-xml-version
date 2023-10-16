package com.example.wanderlusty.feature_explore_tourism.presentation.explore

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wanderlusty.R
import com.example.wanderlusty.databinding.ActivityAddTourismBinding

class AddTourismActivity : AppCompatActivity() {

    private var _binding: ActivityAddTourismBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val btnSubmit = binding.btnAddTourism
        btnSubmit.setOnClickListener {
            val toast = Toast.makeText(applicationContext, "Tourism Added", Toast.LENGTH_SHORT)
            toast.show()
        }

        setToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setToolbar(title: String = "") {
        setSupportActionBar(binding.toolbarAddTourism)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_close) // Use setHomeAsUpIndicator for the back icon
            setDisplayHomeAsUpEnabled(true)
            this.title = title
        }
    }
}