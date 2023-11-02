package com.krupnov.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.krupnov.cryptoapp.R
import com.krupnov.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.krupnov.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.krupnov.cryptoapp.data.network.model.CoinInfoDto
import com.krupnov.cryptoapp.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity(R.layout.activity_coin_price_list) {

    //    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: CoinViewModel

    private val viewBinding by viewBinding(ActivityCoinPriceListBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        viewBinding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}