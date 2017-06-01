package kr.ac.hanyang.searchhyu.domain.model

import com.google.gson.annotations.SerializedName

data class RestThemeRoot(
        @SerializedName("count")
        val count: Int,

        @SerializedName("list")
        val restThemeList: List<RestTheme>,

        @SerializedName("pageNo")
        val pageNo: Int,

        @SerializedName("numberOfRows")
        val numberOfRows: Int,

        @SerializedName("pageSize")
        val pageSize: Int
)