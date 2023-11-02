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

class CoinPriceListActivity : AppCompatActivity(R.layout.activity_coin_price_list) {

    //    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: CoinViewModel

    private val viewBinding by viewBinding(ActivityCoinPriceListBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfoDto) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        viewBinding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })
        //////////////////////////////////////////////////////////////////////
//        viewModel.loadData()
//        viewModel.priceList.observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
//        })
//        viewModel.getDetailInfo("BTC").observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
//        })

        /////////////////////////////////////////////////////////////////////
//        val disposable = ApiFactory.apiService.getFullPriceList(fSyms = "BNC,ETH,EOS")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("TEST_OF_LOADING_DATA", it.toString())
//            },{
//                Log.d("TEST_OF_LOADING_DATA", it.message ?: "Warning")
//            })
//        compositeDisposable.add(disposable)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        compositeDisposable.dispose()
//    }
}