package `in`.curioustools.varabhaya.base.one_direction_mutliview_adapter

import android.app.Activity
import android.os.Parcelable
import androidx.annotation.LayoutRes
import kotlinx.android.parcel.Parcelize
import `in`.curioustools.varabhaya.R

sealed class ODItemType {
    data class TitleHeader(val titleIconRes: Int, val title: String) : ODItemType()

    data class QuickLink(
        val text: String,
        val iconRes: Int,
        val bgImageRes: Int,
        val targetClass: Class<out Activity>
    ) : ODItemType()

    data class RefreshDivider(
        val text: String,
        val buttonText: String,
        val buttonIconRes: Int,
    ) : ODItemType()

    @Parcelize
    data class ZoomMeetupDetails(
        var category: ZoomMeetupStatus,

        val topic: String = "fkjbg",
        val summary: String = "jai hanuman gyaan gun sagar",

        val timeZone: String = "21 jan 1980, 24:00",
        val duration: String = "2 hours",

        var zoomLink: String = "https://www.google.com",
        var siteLink: String = "",
        var password: String = "",

        var dateTimePosted: String = "20 jan 1980",
        var originalPostUrl: String = "www.google.com",
        var meetingStatus: String = "going on",
        var startDateTime: String = "21 jan 1980"


    ) : ODItemType(), Parcelable

    object LoadingViewData : ODItemType()


    companion object {
        val DEFAULT_DETAILS = UiClassInfo(999, android.R.layout.simple_list_item_1, 1)
        fun getDetailsForDashboardItem(item: ODItemType): UiClassInfo {
            return getAllSubclassDetailsMap()[item::class.java] ?: DEFAULT_DETAILS
        }

        const val DASHBOARD_MAX_WEIGHT = 2
        fun getAllSubclassDetailsMap(): Map<Class<out ODItemType>, UiClassInfo> {
            return mapOf(
                Pair(
                    TitleHeader::class.java,
                    UiClassInfo(101, R.layout.item_image_and_bigtext, 1)
                ),

                Pair(
                    QuickLink::class.java,
                    UiClassInfo(102, R.layout.item_quicklink, 2),
                ),
                Pair(
                    RefreshDivider::class.java,
                    UiClassInfo(103, R.layout.item_divider_text_and_button, 1),

                    ),
                Pair(
                    ZoomMeetupDetails::class.java,
                    UiClassInfo(104, R.layout.item_zoom_meeting_details, 1)

                ),
                Pair(
                    LoadingViewData::class.java,
                    UiClassInfo(105, R.layout.item_loading, 1)
                )


            )
        }


    }
}

enum class ZoomMeetupStatus(val pretty: String) {
    UPCOMING("Upcoming"), ARCHIVED("Archived")
}



data class UiClassInfo(
    val uniqueID: Int,
    @LayoutRes val layoutRes: Int,
    val itemsPerRow: Int
)


