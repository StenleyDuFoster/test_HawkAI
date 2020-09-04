package com.stenleone.hawkai.util.pop_many

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import com.stenleone.hawkai.R
import com.stenleone.hawkai.App
import com.stenleone.hawkai.util.easyInfo.makeToast

class TextCopyPopMany (view: View) {

    init {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.pop_many)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.copy -> {
                    val clipboard: ClipboardManager =
                        App.appContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("", (view as TextView).text.toString())
                    clipboard.setPrimaryClip(clip)
                    makeToast("text copied")
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}