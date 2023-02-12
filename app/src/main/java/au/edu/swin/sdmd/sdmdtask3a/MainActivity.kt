package au.edu.swin.sdmd.sdmdtask3a

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<Country>()
        resources.openRawResource(R.raw.medallists).bufferedReader().forEachLine {
            val temp = it.split(",")

            if(temp[2].isDigitsOnly()) //check whether the 3rd column is only digits
            {
                list.add(Country(temp[0],temp[1],temp[2].toInt(),temp[3].toInt(), temp[4].toInt(), temp[5].toInt()))
            }

        }

        val recycler = findViewById<RecyclerView>(R.id.countryList_RecyclerView)
        recycler.adapter = CountryAdapter(list)
        recycler.layoutManager = LinearLayoutManager(this)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        var inflater = menuInflater
        inflater.inflate(R.menu.task3menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                val i = Intent(this, DataActivity::class.java)
                startActivity(i) // start the next activity
            }
        }

        return true
    }
}