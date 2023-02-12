package au.edu.swin.sdmd.sdmdtask3a

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private val data:List<Country>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    val topThree =  getTopThreeElements(data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)

        if(item.getTotal() == topThree[0].getTotal())
        {
            holder.bind(item, 1)
        }
        else if(item.getTotal() == topThree[1].getTotal())
        {
            holder.bind(item, 2)
        }
        else if(item.getTotal() == topThree[2].getTotal())
        {
            holder.bind(item, 3)
        }
        else {
            holder.bind(item, 0)
        }
        holder.backgroundView.setOnClickListener {
            Toast.makeText(holder.backgroundView.context,
                "${item.countryName} has won ${item.getTotal()} medals" , Toast.LENGTH_SHORT).show()

            val pref:SharedPreferences = holder.backgroundView.context.getSharedPreferences("MyPref",
                Context.MODE_PRIVATE)
            val editor:SharedPreferences.Editor =  pref.edit()
            editor.putString("CName",item.countryName)
            editor.putInt("MedalCount", item.getTotal())
            editor.apply()

            val test = pref.getInt("MedalCount", 0)
            Log.i("pref", test.toString())
        }
    }

    private fun getTopThreeElements(listData:List<Country>): MutableList<Country> {
        val list = mutableListOf<Country>()
        var first = Country("a","",0, 0,0,0)
        var second = Country("b","",0, 0,0,0)
        var third = Country("c","",0, 0,0,0)

        listData.forEach {
            val test = it.getTotal()

            if (test > first.getTotal())
            {
                third = second
                second = first
                first = it
            }
            else if(test > second.getTotal() && it.countryName != first.countryName)
            {
                third = second
                second = it
            }
            else if(test > third.getTotal() && it.countryName != second.countryName)
            {
                third = it
            }
        }

        list.add(first)
        list.add(second)
        list.add(third)

        return list
    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        val country = v.findViewById<TextView>(R.id.country_TextView)
        val subText = v.findViewById<TextView>(R.id.sub_TextView)
        val medalCount=  v.findViewById<TextView>(R.id.medalCount_TextView)
        val backgroundView = v.findViewById<ConstraintLayout>(R.id.linearLayout)


        fun bind(item: Country, colorInt: Int)
        {
            country.text = item.countryName
            subText.text = item.countrySub
            val result = item.bronzeCount + item.silverCount + item.goldCount
            medalCount.text = result.toString()

            when (colorInt) {
                1 -> backgroundView.setBackgroundColor(Color.parseColor("#FFEB3B"))
                2 -> backgroundView.setBackgroundColor(Color.parseColor("#C8C8C7"))
                3 -> backgroundView.setBackgroundColor(Color.parseColor("#936B30"))
                else -> backgroundView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }
}