package org.d3if4081.ass1.model

import org.d3if4081.ass1.db.JajargenjangEntity

fun JajargenjangEntity.hitungJajargenjang(): Hasil {
    val hasil = alas * tinggi

    return Hasil(hasil)
}