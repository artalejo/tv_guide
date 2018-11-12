package artalejo.com.epg.ui.channelDetail.customViews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import artalejo.com.epg.R

class LiveTag : ConstraintLayout {

    private var attrs: AttributeSet? = null
    private var defStyleAttr: Int = 0

    constructor(context: Context): super(context) {
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs) {
        this.attrs = attrs
        initialize()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)  {
        this.attrs = attrs
        this.defStyleAttr = defStyleAttr
        initialize()
    }

    private fun initialize() {
        LayoutInflater.from(context).inflate(R.layout.custom_live_tag, this, true)
    }
}