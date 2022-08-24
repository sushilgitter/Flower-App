package com.example.flowery.Models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FlowerModel(@DrawableRes val imageId:Int, @StringRes val imageNameId:Int,@StringRes val priceId:Int)
