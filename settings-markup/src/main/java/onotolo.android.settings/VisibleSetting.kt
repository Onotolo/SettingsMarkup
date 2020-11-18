package onotolo.android.settings

import android.content.Context
import onotolo.android.settings.markup.SettingsMarkup


interface VisibleSetting<T: Any, TConfig: Any> : Setting<T> {

    val settingNameResId: Int
    val descriptionResId: Int?

    fun getName(context: Context?): String? {
        return context?.getString(settingNameResId)
    }

    fun getDescription(context: Context?): String? {
        val resId = descriptionResId ?: return null
        return context?.getString(resId)
    }

    val adapter: SettingsMarkup.Adapter<T, TConfig>
}
