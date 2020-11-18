package onotolo.android.settings.markup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import onotolo.android.settings.VisibleSetting


@DslMarker
annotation class SettingsBuilderDomainClass

@SettingsBuilderDomainClass
interface SettingsMarkup<TViewGroup: ViewGroup> {

    val root: TViewGroup

    interface Adapter<TSettingType: Any, TConfig : Any> {

        val layoutRes: Int
            @LayoutRes
            get

        val bind: (view: View, setting: VisibleSetting<TSettingType, TConfig>, config: TConfig) -> Unit

        val getConfig: (context: Context) -> TConfig
    }
}

interface Invokable<T>: (T.() -> Unit) -> Unit {
    val root: T

    override fun invoke(init: T.() -> Unit) {
        root.init()
    }
}

fun<T: ViewGroup> markup(viewGroup: T) = object :
    SettingsMarkup<T> {
    override val root = viewGroup
}

interface ComplexSectionSettingsMarkup<
        TViewGroup: ViewGroup,
        TSectionMarkup: SettingsMarkup<*>>: SettingsMarkup<TViewGroup>,
    SectionMaker<TViewGroup, TSectionMarkup>


interface SectionSettingsMarkup<TViewGroup: ViewGroup>:
        SettingsMarkup<TViewGroup>,
    SectionMaker<TViewGroup, SettingsMarkup<TViewGroup>>


fun <TSettingType: Any, TConfig : Any> SettingsMarkup<*>.setting(
    setting: VisibleSetting<TSettingType, TConfig>,
    init: (TConfig.() -> Unit)? = null
): View {

    val adapter = setting.adapter
    val inflater = LayoutInflater.from(root.context)
    val config = adapter.getConfig(root.context)
    val view = inflater.inflate(adapter.layoutRes, root, false)

    if (init != null)
        config.init()

    adapter.bind(view, setting, config)

    root.addView(view)
    return view
}
