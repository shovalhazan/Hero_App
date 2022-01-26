package com.main.hero_app.View.Adapters
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.hero_app.Model.HeroDetails.HeroDetailsModel
import com.main.hero_app.R


class HeroRowCustomAdapter:RecyclerView.Adapter<ViewHolder>() {
    /**
     * handles the binding of the row_item.xml layout to the RecyclerView.
     * creates ViewHolder objects as needed
     * takes list of items-heroes images & names - displays them to the user.
     */
    var dataSet = ArrayList<HeroDetailsModel>()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        Log.d("pttt", "onCreateViewHolder: ")
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("pttt", "onBindViewHolder: ")
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


}
