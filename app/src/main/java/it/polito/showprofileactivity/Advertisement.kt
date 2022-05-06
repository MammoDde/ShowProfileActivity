package it.polito.listapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import it.polito.showprofileactivity.R
import it.polito.showprofileactivity.TimeSlotDetailsFragment

//(val id: Int, val title:String, val description:String, val dateAndTime: String, val duration: String, val location: String)

@Entity(tableName = "advertisement", indices = [Index("id")])
data class Advertisement(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name="title")
    val title:String,
    @ColumnInfo(name="description")
    val description:String,
    @ColumnInfo(name="dateAndTime")
    val dateAndTime: String,
    @ColumnInfo(name="duration")
    val duration: String,
    @ColumnInfo(name="location")
    val location: String
)

class AdvertisementAdapter(val data:MutableList<Advertisement>): RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder>() {
    var filter: Boolean = false
    var displayData = data.toMutableList()


    class AdvertisementViewHolder(v:View): RecyclerView.ViewHolder(v) {
        private val title: TextView = v.findViewById(R.id.slot_title)
        private val edit: ImageView = v.findViewById(R.id.edit)
        private val card: CardView = v.findViewById(R.id.card)



        fun bind(advertisement: Advertisement, action: (v:View)->Unit) {
            title.text = advertisement.title
            card.setOnClickListener{
                //cliccando sulla card si apre il TimeSlotDetailFragment
            }
            edit.setOnClickListener(action)
        }


        fun unbind() {
            edit.setOnClickListener(null)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val vg = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.time_slot,parent, false)


        return AdvertisementViewHolder(vg)
    }
    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        val item = displayData[position]
        holder.bind(item) {
            //cliccando sull'edit si apre il TimeSlotEditFragment

        /*val pos = data.indexOf(item)
            if (pos!=-1) {
                data.removeAt(pos)
                val pos1 = displayData.indexOf(item)
                if (pos1!= -1) {
                    displayData.removeAt(pos1)
                    notifyItemRemoved(pos1)
                }
            }*/
        }
    }

    override fun getItemCount(): Int = displayData.size

    fun addFilter(on: Boolean) {
        filter = on
        val newData = if (filter) {
            data.filter { it.id % 2 == 0 }.toMutableList()
        } else
            data.toMutableList()
        val diffs = DiffUtil.calculateDiff(MyDiffCallback(displayData, newData))
        displayData = newData
        diffs.dispatchUpdatesTo(this)
    }
}

class MyDiffCallback(val old: List<Advertisement>, val new: List<Advertisement>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size
    override fun getNewListSize(): Int = new.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === new[newItemPosition]
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}