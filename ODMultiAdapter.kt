package `in`.curioustools.varabhaya.base.one_direction_mutliview_adapter

import `in`.curioustools.varabhaya.base.one_direction_mutliview_adapter.ODItemType
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/*
* Goal is not to make an adapter that could be extended to provide easy adapter addition in other screens
* Goal is to make a giant complex multiple ui , single direction adapter in a simpler manner
* */

class ODMultiAdapter(
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<OneDirectionViewHolder>() {
    var dataList: MutableList<ODItemType> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var listener: DashBoardItemClickListener? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): OneDirectionViewHolder {
        val allChildrenDetails = ODItemType.getAllSubclassDetailsMap().values.toList()
        val layoutRes: Int = allChildrenDetails
            .firstOrNull { it.uniqueID == itemType }
            ?.layoutRes
            ?: ODItemType.DEFAULT_DETAILS.layoutRes

        val view = inflater.inflate(layoutRes, parent, false)
        return OneDirectionViewHolder(view)

    }

    override fun onBindViewHolder(holder: OneDirectionViewHolder, position: Int) {
        holder.bind(dataList[position], listener)
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int {
        val currItem = dataList[position]
        return ODItemType.getDetailsForDashboardItem(currItem).uniqueID
    }

}
