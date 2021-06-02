package com.github.pagr0m.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.github.pagr0m.shared.Greeting
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.pagr0m.androidApp.model.User
import com.github.pagr0m.androidApp.model.UserRecyclerViewAdapter
import com.github.pagr0m.shared.CustomNetwork
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    private val url = "https://jsonplaceholder.typicode.com/users/"
    private val network = CustomNetwork()

    override fun onCreate(savedInstanceState: Bundle?) {
        val users = getUsers()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = UserRecyclerViewAdapter(users)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    private fun getUsers(): List<User> {
        val users = mutableListOf<User>()

        for (i in 1..10) {
            val rawResponse = network.get("$url$i") as String
            val user = Json.decodeFromString<User>(rawResponse)
            users.add(user)
            Log.i("LOX", user.toString())
        }

        return users
    }
}
