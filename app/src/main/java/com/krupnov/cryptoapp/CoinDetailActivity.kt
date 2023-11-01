package com.krupnov.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krupnov.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity(R.layout.activity_coin_detail) {

    private lateinit var viewModel: CoinViewModel

    private val binding by viewBinding(ActivityCoinDetailBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol!!).observe(this, Observer {
            with(binding) {
                tvPrice.text = it.price!!.toString()
                tvMinPrice.text = it.lowday!!.toString()
                tvMaxPrice.text = it.highday!!.toString()
                tvLastMarket.text = it.lastmarket!!.toString()
                tvLastUpdate.text = it.getFormattedTime()
                tvFromSymbol.text = it.fromsymbol
                tvToSymbol.text = it.tosymbol!!
                Picasso.get().load(it.getFullImageUrl()).into(ivLogocoin)
            }
        })
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}