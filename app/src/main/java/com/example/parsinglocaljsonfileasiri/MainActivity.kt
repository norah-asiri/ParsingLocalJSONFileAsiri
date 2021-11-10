package com.example.parsinglocaljsonfileasiri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    // Ex: https://www.youtube.com/watch?v=3LIXkNxUdhw
    var URLlist = arrayListOf<String>()
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter
    private lateinit var ivMain: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        read_json()
        ivMain= findViewById(R.id.ivMain)
        rvMain = findViewById(R.id.rvMain)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)
        ivMain.setOnClickListener { closeImg() }

    }

    fun read_json(){
        var json : String? = null
        try {
            val inputStream : InputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use{it.readText()}
            var jsonarr = JSONArray(json)
           for (i in 0..jsonarr.length()-1) {
               var jsonobj = jsonarr.getJSONObject(i)
               URLlist.add((jsonobj.getString("url")))
           }

            //var adapter = ArrayAdapter (this , android.R.layout.activity_list_item,URLlist)

            rvAdapter = RVAdapter(this, URLlist)

            //  json_text.text = json
        } catch (e: IOException){

        }
    }


    fun openImg(link: String){
        Glide.with(this).load(link).into(ivMain)
        ivMain.isVisible = true
        rvMain.isVisible = false
    }

    private fun closeImg(){
        ivMain.isVisible = false
        rvMain.isVisible = true
    }
}