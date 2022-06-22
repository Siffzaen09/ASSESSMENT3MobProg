package org.d3if4081.ass1.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4081.ass1.db.JajargenjangDao
import org.d3if4081.ass1.db.JajargenjangEntity
import org.d3if4081.ass1.model.Hasil

class HitungViewModel(private val db: JajargenjangDao) : ViewModel() {

    private val hasilJg = MutableLiveData<Hasil?>()

    fun hitung(Alas: Float, Tinggi: Float) {
        val hasiljg = Alas * Tinggi
        hasilJg.value = Hasil(hasiljg)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val datajg = JajargenjangEntity(
                    alas = Alas,
                    tinggi = Tinggi,
                    hasil = hasiljg
                )
                db.insert(datajg)
            }
        }
    }
    fun getHasil(): LiveData<Hasil?> = hasilJg
}