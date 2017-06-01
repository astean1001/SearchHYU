package kr.ac.hanyang.searchhyu.domain.model

data class SearchPoiInfoRoot(
        val searchPoiInfo: SearchPoiInfo
)

data class SearchPoiInfo(
        val pois: Pois
)

data class Pois(
        val poi: List<Poi>
)

data class Poi(
        val name: String,
        val frontLat: Double,
        val frontLon: Double
)