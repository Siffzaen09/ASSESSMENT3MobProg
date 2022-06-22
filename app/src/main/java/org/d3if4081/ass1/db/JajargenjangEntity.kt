package org.d3if4081.ass1.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jajargenjang")
data class JajargenjangEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var alas: Float,
    var tinggi: Float,
    var hasil : Float
)
