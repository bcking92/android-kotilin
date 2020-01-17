package com.example.myfirstapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var items_view: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        viewManager = GridLayoutManager(this, 2)
        val datas = ArrayList<myDataset>()
        datas.add(myDataset(getDrawable(R.drawable.ic_launcher_foreground)!!, getString(R.string.temp)))

        datas.plus(datas)

        viewAdapter = MyAdapter(this, datas)


        items_view.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
//            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }
}
