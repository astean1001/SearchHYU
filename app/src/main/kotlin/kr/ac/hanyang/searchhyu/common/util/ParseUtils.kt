package kr.ac.hanyang.searchhyu.common.util

import android.location.Location
import java.net.HttpURLConnection
import java.net.URL
import com.github.salomonbrys.kotson.*
import com.google.gson.Gson
import android.content.Context
import android.util.Log
import kr.ac.hanyang.searchhyu.common.util.ActivityUtils.showToast
import kr.ac.hanyang.searchhyu.common.util.TmapUtils.routeTmap

/**
 * Created by jehyoung on 2017. 5. 30..
 */
object ParseUtils {

    fun parseKeywords(inputStr : String, curX:Double, curY:Double, context :Context) : String?
    {
        val restList = makeRest()
        if(inputStr.contains("맛")) {
            val keyword = callKeywordAPI(inputStr)
            for(item : Rest in restList)
            {
                if(item.batchMenu != null && item.batchMenu.contains(keyword)) {
                    routeTmap(context, curX, curY, item.x.toDouble(), item.y.toDouble())
                }
            }

            noResult(context)
            return ""
        }

        if(inputStr.contains("휘발유") || inputStr.contains("기름")) {
            var mostCheap : Int = 99999
            var mostCheapX : Float = 0F
            var mostCheapY : Float = 0F

            for(item : Rest in restList)
            {
                if (item.gasolinePrice != null &&calcDist(item.x.toDouble(), item.y.toDouble(), curX, curY) < 50 && item.gasolinePrice != null) {
                    if(item.gasolinePrice < mostCheap) {
                        mostCheap = item.gasolinePrice
                        mostCheapX = item.x
                        mostCheapY = item.y
                    }
                }
            }
            if(mostCheap == 99999) {
                noResult(context)
                return ""
            }
            routeTmap(context, curX, curY, mostCheapX.toDouble(), mostCheapY.toDouble())
            // 휘발유 정보 떼오면 됨 (30KM)

            noResult(context)
            return ""
        }

        if(inputStr.contains("경유")) {
            var mostCheap : Int = 99999
            var mostCheapX : Float = 0F
            var mostCheapY : Float = 0F

            for(item : Rest in restList)
            {
                if (item.diselPrice != null && calcDist(item.x.toDouble(), item.y.toDouble(), curX, curY) < 50 && item.diselPrice != null) {
                    if(item.diselPrice < mostCheap) {
                        mostCheap = item.diselPrice
                        mostCheapX = item.x
                        mostCheapY = item.y
                    }
                }
            }
            if(mostCheap == 99999) {
                noResult(context)
                return ""
            }
            routeTmap(context, curX, curY, mostCheapX.toDouble(), mostCheapY.toDouble())
            // 휘발유 정보 떼오면 됨 (30KM)

            noResult(context)
            return ""
        }

        if(inputStr.contains("lpg") || inputStr.contains("LPG")) {
            var mostCheap : Int = 99999
            var mostCheapX : Float = 0F
            var mostCheapY : Float = 0F

            for(item : Rest in restList)
            {
                if (item.lpgPrice != null && calcDist(item.x.toDouble(), item.y.toDouble(), curX, curY) < 50 && item.lpgYn.equals("Y")) {
                    if(item.lpgPrice.toInt() < mostCheap) {
                        mostCheap = item.lpgPrice.toInt()
                        mostCheapX = item.x
                        mostCheapY = item.y
                    }
                }
            }
            if(mostCheap == 99999) {
                noResult(context)
                return ""
            }
            routeTmap(context, curX, curY, mostCheapX.toDouble(), mostCheapY.toDouble())
            // 휘발유 정보 떼오면 됨 (30KM)

            noResult(context)
            return ""

        }

        if(inputStr.contains("가까운") || inputStr.contains("가까이") || inputStr.contains("빨리")) {
            val key = callKeywordAPI(inputStr)
            if(key == "ERROR") {
                // TMAP 검색 결과 ("고속도로 휴게소") 중에서 첫번째 인덱스를 반환하면 됨
            }
            else {
                val keyword = callKeywordAPI(inputStr)
                // TMap에서 key를 검색한 첫번째 인덱스를 시작점으로 잡고 "고속도로 휴게소를 검색하여 나온 첫번째 인덱스 반환
            }
            noResult(context)
            return ""
        }

        if(inputStr.contains("있는")) {
            // 편의시설 정보랑 여튼 가진 정보 다 떼와서 contains 처리하면 됨

            for(item : Rest in restList)
            {
                val keyword = callKeywordAPI(inputStr)
                if(item.convenience.contains(keyword)) {
                    if(calcDist(item.x.toDouble(), item.y.toDouble(), curX, curY) < 50) {
                        routeTmap(context, curX, curY, item.x.toDouble(), item.y.toDouble())
                    }
                }
            }
            noResult(context)
            return ""

        }

        if(inputStr.contains("휴게소")) {
            for(item : Rest in restList)
            {
                // TMAP에서 inputStr 검색해서 나온거 첫번째 인덱스로 안내
            }
            noResult(context)
            return ""
        }

        // 검색결과가 없습니다.
        noResult(context)
        return ""
    }

    fun callKeywordAPI(inputStr : String?) : String {
        var data = ""
        val connection = URL("http://api.datamixi.com/datamixiApi/keywordextract?request=post&request_id=0&text="+inputStr).openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        data = connection.inputStream.bufferedReader().readText()
        val gson = Gson()
        val list = gson.fromJson<List<Keyword>>(data)
        if(list.get(0).ro.get(0).t.contains("휴게소")) {
            if(list.get(0).ro.size <= 1) {
                return "ERROR"
            }
            return list.get(0).ro.get(1).t.split("|")[0]
        }
        return list.get(0).ro.get(0).t.split("|")[0]
    }

    class Keyword (val rt : String, val res : String, val rea : String, val ro : List<RO>, val ri: String){}

    class RO(val t:String, val w:Float) { }

    fun makeRest() : List<Rest> {
        val connection = URL("http://hepc.hycse.net/jsonlist.json").openConnection() as HttpURLConnection
        Log.d("",connection.toString())
        connection.inputStream.bufferedReader().use { reader ->
            val gson = Gson()
            val list = gson.fromJson<List<Rest>>(reader.readText())
            return list
        }
    }

    fun calcDist(latA : Double, lngA : Double, latB : Double, lngB : Double) : Float{
        var locationA = Location("Point A")
        locationA.latitude = latA
        locationA.longitude = lngA

        val locationB = Location("Point B")
        locationB.latitude = latB
        locationB.longitude = lngB

        return locationA.distanceTo(locationB)/1000;
    }

    fun noResult(context: Context) {
        showToast(context, "검색결과가 없습니다.")
    }

    class Rest(val areaCode : String, val areaName : String, val direction : String, val x : Float, val y : Float, val areaCodeB : String, val lpgYn: String, val gasolinePrice: Int?, val diselPrice: Int?, val lpgPrice: Int?, val batchMenu: String?, val convenience: String)
}