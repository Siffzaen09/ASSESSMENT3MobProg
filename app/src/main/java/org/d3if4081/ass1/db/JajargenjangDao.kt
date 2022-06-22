package org.d3if4081.ass1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JajargenjangDao {
    @Insert
    fun insert(bmi: JajargenjangEntity)
    @Query("SELECT * FROM jajargenjang ORDER BY id DESC")
    fun getLastJajargenjang(): LiveData<List<JajargenjangEntity>>
    @Query("DELETE FROM jajargenjang")
    fun clearData()
}
