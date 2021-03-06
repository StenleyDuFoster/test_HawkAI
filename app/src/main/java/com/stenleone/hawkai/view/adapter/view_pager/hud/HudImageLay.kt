package com.stenleone.hawkai.view.adapter.view_pager.hud

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.stenleone.hawkai.R
import kotlinx.android.synthetic.main.item_page_hud.view.*

class HudImageLay @JvmOverloads constructor(
    context: Context,
    startPosition: Int = 0,
    private val totalPage: Int = 1,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_page_hud, this)
        setBackgroundColor(Color.TRANSPARENT)
        update(startPosition)
    }

    fun update(page: Int) {
        textNumberPage.setText((page + 1).toString() + "/" + totalPage.toString())
    }
}