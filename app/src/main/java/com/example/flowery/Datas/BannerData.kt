package com.example.flowery.Datas

import com.example.flowery.Models.BannerModel
import com.example.flowery.R

class BannerData {
    fun bannerList(): List<BannerModel> {
        return listOf(
            BannerModel(R.drawable.ic_sale_banner),
            BannerModel(R.drawable.summersale),
            BannerModel(R.drawable.summersale50),
            BannerModel(R.drawable.flowersummersale),
            BannerModel(R.drawable.ic_sale_banner),
            BannerModel(R.drawable.summersale),
            BannerModel(R.drawable.summersale50),
            BannerModel(R.drawable.flowersummersale),

        )
    }
}