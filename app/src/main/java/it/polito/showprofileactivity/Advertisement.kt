package it.polito.listapplication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.polito.showprofileactivity.R

data class Advertisement(val id: Int, val title:String, val description:String, val dateAndTime: String, val duration: String, val location: String )

class AdvertisementAdapter(val data:MutableList<Advertisement>): RecyclerView.Adapter<AdvertisementAdapter.AdvertisementViewHolder>() {
    var filter: Boolean = false
    var displayData = data.toMutableList()

    class AdvertisementViewHolder(v:View): RecyclerView.ViewHolder(v) {
        private val title: TextView = v.findViewById(R.id.slot_title)
        private val delete: ImageView = v.findViewById(R.id.delete)

        fun bind(advertisement: Advertisement, action: (v:View)->Unit) {
            title.text = advertisement.title
            delete.setOnClickListener(action)
        }
        fun unbind() {
            delete.setOnClickListener(null)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisementViewHolder {
        val vg = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.fragment_slot_details,parent, false)
        return AdvertisementViewHolder(vg)
    }
    override fun onBindViewHolder(holder: AdvertisementViewHolder, position: Int) {
        val item = displayData[position]
        holder.bind(item) {

            val pos = data.indexOf(item)
            if (pos!=-1) {
                data.removeAt(pos)
                val pos1 = displayData.indexOf(item)
                if (pos1!= -1) {
                    displayData.removeAt(pos1)
                    notifyItemRemoved(pos1)
                }
            }
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