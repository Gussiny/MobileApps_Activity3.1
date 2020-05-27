package com.itesm.tarea3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class MainActivity : AppCompatActivity(), Handler.Callback {
    lateinit var dataHandler : Handler
    lateinit var foodList : ArrayList<Food>
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.listFood)
        recyclerView.hasFixedSize()
        foodList = ArrayList<Food>()
        dataHandler = Handler(Looper.getMainLooper(), this)
        //fetchJson()
        request()
    }

    fun request(){
        val r = Request(
            "https://raw.githubusercontent.com/Gussiny/MobileApps_Activity3.1/master/food.json",
            dataHandler
        )
        r.start()
    }

    override fun handleMessage(msg: Message): Boolean {
        val resultado = msg.obj as JSONArray
        Log.wtf("JSON", "{")
        try {
            for (i in 0 until resultado.length()) {
                var name: String
                var price: String
                var description: String
                val actual = resultado.getJSONObject(i)
                Log.wtf("JSON", "{")
                Log.wtf("JSON", actual.getString("name"))
                Log.wtf("JSON", actual.getString("price"))
                Log.wtf("JSON", actual.getString("description"))
                Log.wtf("JSON", "}")

                name = actual.getString("name")
                price = actual.getString("price")
                description = actual.getString("description")
                val food = Food(name, price, description)
                foodList.add(food)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val adapterFood = AdapterFood(foodList)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recyclerView.adapter = adapterFood
        Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show()

        return false
    }

}
