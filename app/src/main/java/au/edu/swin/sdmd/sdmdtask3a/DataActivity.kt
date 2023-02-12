package au.edu.swin.sdmd.sdmdtask3a

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val country = findViewById<TextView>(R.id.last_TextView)
        val medal = findViewById<TextView>(R.id.medal_TextView)

        val pref: SharedPreferences = this.getSharedPreferences("MyPref" , Context.MODE_PRIVATE)
        val CountryText = pref.getString("CName", "")
        val MedalCount = pref.getInt("MedalCount", 0)

        country.text = "The last Country selected was $CountryText"
        medal.text =  "Their medal count is $MedalCount"
    }
}