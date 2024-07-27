package com.joguco.segundocursokotlin.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.joguco.segundocursokotlin.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {
    companion object{
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_DARKMODE = "key_darkmode"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var firstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch{
            getSettings().filter{ firstTime }.collect{settingsModel ->
                if(settingsModel != null){
                    runOnUiThread{
                        binding.sVibration.isChecked = settingsModel.vibration
                        binding.sDarkmode.isChecked = settingsModel.darkMode
                        binding.sBluetooth.isChecked = settingsModel.bluetooth
                        binding.rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime = false
                    }
                }
            }
        }

        initUI()
    }

    private fun initUI(){
        binding.rsVolume.addOnChangeListener{_, value, _ ->
            CoroutineScope(Dispatchers.IO).launch{
                saveVolume(value.toInt())
            }
        }
        binding.sBluetooth.setOnCheckedChangeListener{ _, value ->
            CoroutineScope(Dispatchers.IO).launch{
                saveOptions(KEY_BLUETOOTH, value)
            }
        }

        binding.sVibration.setOnCheckedChangeListener{ _, value ->
            CoroutineScope(Dispatchers.IO).launch{
                saveOptions(KEY_VIBRATION, value)
            }
        }

        binding.sDarkmode.setOnCheckedChangeListener{ _, value ->
            if(value)
                enableDarkMode()
            else
                disableDarkMode()
            CoroutineScope(Dispatchers.IO).launch{
                saveOptions(KEY_DARKMODE, value)
            }
        }
    }

    private suspend fun saveVolume(value: Int){
        dataStore.edit{ preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean){
        dataStore.edit{preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map{ preferences->
            SettingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: true,
                darkMode = preferences[booleanPreferencesKey(KEY_DARKMODE)] ?: false
            )
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}