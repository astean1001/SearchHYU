package kr.ac.hanyang.searchhyu.domain.model

import com.google.gson.annotations.SerializedName

data class RestTheme(
        @SerializedName("detail")
        val detail: String,

        @SerializedName("stdRestCd")
        val stdRestCd: String,

        @SerializedName("stdRestNm")
        val stdRestName: String,

        @SerializedName("itemNm")
        val itemName: String
)