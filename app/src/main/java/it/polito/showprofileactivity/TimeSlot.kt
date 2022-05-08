package it.polito.showprofileactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "items", indices = [Index("name")])
class TimeSlot {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var name:String = ""


    override fun toString() = "{ id:$id, title:\"$name\"}"
}


class TimeSlotAdapter(val data:MutableList<TimeSlot>): RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {
    var displayData = data.toMutableList()

    class TimeSlotViewHolder(v: View): RecyclerView.ViewHolder(v) {
        private val title: TextView = v.findViewById(R.id.slot_title)
        private val edit: ImageView = v.findViewById(R.id.edit)
        private val card: CardView = v.findViewById(R.id.card)


        fun bind(timeslot: TimeSlot, action: (v: View)->Unit) {
            title.text = timeslot.name
            card.setOnClickListener{
                //cliccando sulla card si apre il TimeSlotDetailFragment
            }
            edit.setOnClickListener(action)
        }

        fun unbind() {
            edit.setOnClickListener(null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val vg = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.time_slot,parent, false)

        return TimeSlotViewHolder(vg)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
