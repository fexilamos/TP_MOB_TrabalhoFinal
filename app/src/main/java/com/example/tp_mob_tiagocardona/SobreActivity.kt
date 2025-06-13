package com.example.tp_mob_tiagocardona

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_mob_tiagocardona.databinding.ActivitySobreBinding

class SobreActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySobreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySobreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
