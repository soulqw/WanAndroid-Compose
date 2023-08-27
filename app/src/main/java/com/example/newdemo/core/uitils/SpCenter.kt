package com.example.newdemo.core.uitils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object SpCenter {

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    var COOKIE by Delegates.string("")

    var USER by Delegates.string("")

    var DARK_MODE by Delegates.boolean(false)

    object Delegates {

        fun int(defaultValue: Int = 0) = object : ReadWriteProperty<SpCenter, Int> {

            override fun getValue(thisRef: SpCenter, property: KProperty<*>): Int {
                return preferences.getInt(property.name, defaultValue)
            }

            override fun setValue(
                thisRef: SpCenter,
                property: KProperty<*>,
                value: Int
            ) {
                preferences.edit().putInt(property.name, value).apply()
            }
        }

        fun long(defaultValue: Long = 0) =
            object : ReadWriteProperty<SpCenter, Long> {
                override fun getValue(
                    thisRef: SpCenter,
                    property: KProperty<*>
                ): Long {
                    return preferences.getLong(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SpCenter,
                    property: KProperty<*>,
                    value: Long
                ) {
                    preferences.edit().putLong(property.name, value).apply()
                }
            }


        fun float(defaultValue: Float = 0f) =
            object : ReadWriteProperty<SpCenter, Float> {
                override fun getValue(
                    thisRef: SpCenter,
                    property: KProperty<*>
                ): Float {
                    return preferences.getFloat(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SpCenter,
                    property: KProperty<*>,
                    value: Float
                ) {
                    preferences.edit().putFloat(property.name, value).apply()
                }
            }

        fun boolean(defaultValue: Boolean = false) =
            object : ReadWriteProperty<SpCenter, Boolean> {
                override fun getValue(
                    thisRef: SpCenter,
                    property: KProperty<*>
                ): Boolean {
                    return preferences.getBoolean(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: SpCenter,
                    property: KProperty<*>,
                    value: Boolean
                ) {
                    preferences.edit().putBoolean(property.name, value).apply()
                }
            }

        fun string(defaultValue: String = "") =
            object : ReadWriteProperty<SpCenter, String> {
                override fun getValue(
                    thisRef: SpCenter,
                    property: KProperty<*>
                ): String {
                    return preferences.getString(property.name, defaultValue)!!
                }

                override fun setValue(
                    thisRef: SpCenter,
                    property: KProperty<*>,
                    value: String
                ) {
                    preferences.edit().putString(property.name, value).apply()
                }
            }
    }
}