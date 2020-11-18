package onotolo.android.settings

import android.content.Context

interface VisibleArrayItemSetting<T: Any, TConfig: Any> :
    VisibleSetting<T, TConfig> {

    val array: Array<T>

    override fun get(context: Context?): T {
        val index =
            settingsProvider.getValue(
                context,
                id,
                array.indexOf(defaultValue)
            )
        return array[index]
    }

    override fun set(context: Context?, value: T) {
        settingsProvider.setValue(
            context,
            id,
            array.indexOf(value)
        )
    }
}