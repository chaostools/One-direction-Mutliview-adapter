package `in`.curioustools.varabhaya.base.one_direction_mutliview_adapter

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_divider_text_and_button.view.*
import kotlinx.android.synthetic.main.item_image_and_bigtext.view.*
import kotlinx.android.synthetic.main.item_quicklink.view.*
import kotlinx.android.synthetic.main.item_zoom_meeting_details.view.*
import `in`.curioustools.varabhaya.R
import `in`.curioustools.varabhaya.base.one_direction_mutliview_adapter.ODItemType.*
import `in`.curioustools.varabhaya.base.one_direction_mutliview_adapter.ZoomMeetupStatus.UPCOMING

class OneDirectionViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    fun bind(itemType: ODItemType, listener: DashBoardItemClickListener?) {
        when (itemType) {
            is TitleHeader -> updateTitleHeaderUi(data = itemType ,listener)
            is QuickLink -> updateQuickLinkUi(data = itemType,listener)
            is RefreshDivider -> updateHeaderAndButtonUi(data = itemType,listener)
            is ZoomMeetupDetails -> updateZoomMeetupDetailsUi(data = itemType, listener)
            LoadingViewData -> updateLoadingViewUi(itemType,listener)
        }
    }



    @Suppress("UNUSED_PARAMETER")
    private fun updateTitleHeaderUi(data: TitleHeader, listener: DashBoardItemClickListener?) {
        with(itemView){
            header_tv_text?.text = data.title
            header_iv_logo?.setImageResource(data.titleIconRes)
        }
    }

    private fun updateQuickLinkUi(data: QuickLink, listener: DashBoardItemClickListener?) {
        with(itemView){
            quicklink_tv_title?.text = data.text
            quicklink_iv_logo?.setImageResource(data.iconRes)
            quicklink_ll_main?.setBackgroundResource(data.bgImageRes)

            setOnClickListener { listener?.onItemClick(data) }
        }

    }

    private fun updateHeaderAndButtonUi(data: RefreshDivider, l: DashBoardItemClickListener?) {
        with(itemView) {
            div_tv_title?.text = data.text
            div_tv_refresh?.text = data.buttonText
            div_tv_refresh?.setCompoundDrawablesWithIntrinsicBounds(0, 0, data.buttonIconRes, 0)

            div_tv_refresh?.setOnClickListener { l?.onItemClick(data) }
        }

    }

    private fun updateZoomMeetupDetailsUi(data: ZoomMeetupDetails, l: DashBoardItemClickListener?) {
        val catFgColor = if (data.category == UPCOMING) Color.WHITE else Color.BLACK
        val catBgTint = if (data.category == UPCOMING) R.color.orange_bright_ff8 else R.color.gray_c1c1

        with(itemView) {
            val catBgTintList = ContextCompat.getColorStateList(context, catBgTint)
            item_zoommeeting_tv_category?.setTextColor(catFgColor)
            item_zoommeeting_tv_category?.backgroundTintList = catBgTintList
            item_zoommeeting_tv_category?.text= data.category.pretty
            item_zoommeeting_tv_title?.text = data.topic
            item_zoommeeting_tv_details?.text = data.summary
            item_zoommeeting_tv_date?.text = data.dateTimePosted

            item_zoommeeting_bt_more?.setOnClickListener { l?.onItemClick(data) }
            setOnClickListener { l?.onItemClick(data) }
        }

    }

    private fun updateLoadingViewUi(data: ODItemType, listener: DashBoardItemClickListener?) {
        itemView.setOnClickListener { listener?.onItemClick(data) }

    }

}


interface DashBoardItemClickListener {
    fun onItemClick(data: ODItemType)
}
