package onotolo.android.settings.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import onotolo.android.settings.R
import onotolo.android.settings.markup.Invokable
import onotolo.android.settings.markup.SectionSettingsMarkup
import onotolo.android.settings.markup.SettingsMarkup
import onotolo.android.settings.markup.markup

open class SettingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.SettingsView
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes),
    SectionSettingsMarkup<LinearLayout>,
    Invokable<SectionSettingsMarkup<LinearLayout>> {

    override val root
        get() = this

    private val headerTextAppearance: Int
    private val sectionStyle: Int

    init {
        val arr = context.obtainStyledAttributes(
            attrs,
            ATTRS, defStyleAttr, defStyleRes
        )

        headerTextAppearance = arr.getResourceId(
            ATTRS.indexOf(
                R.attr.sectionHeaderTextAppearance
            ), R.style.SettingsView_Section_Header
        )

        sectionStyle = arr.getResourceId(
            ATTRS.indexOf(
                R.attr.sectionStyle
            ), R.style.SettingsView_Section
        )

        arr.recycle()
    }

    override fun createSection(header: String?, root: LinearLayout): SettingsMarkup<LinearLayout> {
        val section =
            markup(
                LinearLayout(
                    context,
                    null,
                    R.attr.sectionStyle,
                    sectionStyle
                )
            )
        if (header == null) {
            return section
        }
        val headerView = TextView(context).apply {
            setTextAppearance(context, headerTextAppearance)
            text = header
        }
        section.root.addView(headerView)
        return section
    }

    companion object {
        val ATTRS = intArrayOf(
            R.attr.sectionStyle,
            R.attr.sectionHeaderTextAppearance
        ).sortedArray()
    }
}
