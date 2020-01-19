package com.example.myfirstapplication


import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

const val PRODUCT_PATH = "prod/product"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        viewManager = GridLayoutManager(this, 2)
        val datas = ArrayList<myDataset>()
        datas.add(myDataset(getDrawable(R.drawable.ic_launcher_foreground)!!, getString(R.string.temp)))

        val viewAdapter = MyAdapter(datas)
        items_view.adapter = viewAdapter
        }
    override fun onResume() {
        super.onResume()
        BirdviewRESTRequest().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,PRODUCT_PATH)
        }
    inner class BirdviewRESTRequest : AsyncTask<String, Unit,
            ItemData>(){
        var restTargetURL: String? = null
        override fun doInBackground(vararg params: String):
                ItemData? {

            //요청(URL)의 종류(현재날씨,미세먼지,자외선)
            restTargetURL = params[0]


            val toRESTServer = OkHttp3Manager.getOkHttpClient()

            //요청URL정보(질의문자열 포함)를 가져온다
            val httpURL: HttpUrl =
                OkHttp3Manager.makeHttpURL(params[0].trim())

            val request = Request.Builder().apply {
                url(httpURL)
            }.build()
            //요청보내고 결과를 가져온다
            val response: Response =
                toRESTServer.newCall(request).execute()

            //JSON을 Data 객체로 변환(JSON 직렬화)
            var itemData:ItemData? = null

            /*
             * OkHttp3의 Response는 닫아준다
             * use() 함수는 Closeable를 구현한 객체를 사용 후
             * 닫아주는 역할을 한다(try..catch..finally를 대신)
             */
            response.use {
                if (response.isSuccessful) {
                    val gson = Gson()
                    /*
                     * 넘어온 json을 gson을 이용해
                     * 객체화 한다
                     */
                    itemData =
                        gson.fromJson(response.body()?.string(),
                            ItemData::class.java)
                }
            }
            /*
             * GSON을 통해 변환된 객체를 리턴한다
             */
            return itemData
        }

        override fun onPostExecute(result: ItemData?) {
            super.onPostExecute(result)

            result?.let {
                //요청 URL정보에 따라 화면을 갱신하는 함수를 호출한다
                when (restTargetURL) {

                    PRODUCT_PATH ->
                        setUICurrentWeather(result)

                    else -> Unit
                }
            } ?: Toast.makeText(
                MyApplication.myApplication,
                "데이터를 받지 못했네요",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



}



// https://github.com/InsooPyo/KotlinAndroidOkHttp3/blob/master/app/src/main/java/com/pyoinsoo/kotlin/okhttp3/MainActivity.kt