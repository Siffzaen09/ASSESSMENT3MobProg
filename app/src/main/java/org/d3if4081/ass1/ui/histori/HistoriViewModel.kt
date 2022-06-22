package org.d3if4081.ass1.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4081.ass1.db.JajargenjangDao

class HistoriViewModel(private val db: JajargenjangDao) : ViewModel() {
    val data = db.getLastJajargenjang()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}