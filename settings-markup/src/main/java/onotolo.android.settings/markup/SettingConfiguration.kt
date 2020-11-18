package onotolo.android.settings.markup

import onotolo.android.settings.VisibleSetting

typealias CancelFunc = () -> Unit

typealias OnSettingChangeCallback<T> = (value: T, cancelFunc: CancelFunc) -> Unit

@SettingsBuilderDomainClass
open class SettingConfiguration<TSettingType: Any>(val setting: VisibleSetting<TSettingType, *>) {
    var onSettingChangeCallback: OnSettingChangeCallback<TSettingType> = { _, _ -> }

    fun withCallback(callback: OnSettingChangeCallback<TSettingType>) {
        onSettingChangeCallback = callback
    }
}