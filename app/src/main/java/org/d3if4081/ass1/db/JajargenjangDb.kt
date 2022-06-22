package org.d3if4081.ass1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [JajargenjangEntity::class], version = 1, exportSchema = false)
abstract class JajargenjangDb : RoomDatabase() {
    abstract val dao: JajargenjangDao
    companion object {
        @Volatile
        private var INSTANCE: JajargenjangDb? = null
        fun getInstance(context: Context): JajargenjangDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        JajargenjangDb::class.java,
                        "jajargenjang.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
