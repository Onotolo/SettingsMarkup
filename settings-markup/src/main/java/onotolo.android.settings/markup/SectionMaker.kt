package onotolo.android.settings.markup

import android.view.ViewGroup

interface SectionMaker<TInViewGroup: ViewGroup, TOutMarkup: SettingsMarkup<*>> {

    val root: TInViewGroup

    fun section(header: String? = null, init: TOutMarkup.() -> Unit) {
        return createSection(header, root).init()
    }

    fun createSection(header: String?, root: TInViewGroup): TOutMarkup
}