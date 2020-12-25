package com.example.itunessearch.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itunessearch.models.Result

@Database(
        entities = [Result::class],
version=1
)
abstract class ResultDatabase: RoomDatabase() {
    abstract fun getResultDao(): ResultDao

    companion object{
       @Volatile //other threads can easily when a thread changes its instance
        private var instance: ResultDatabase?=null
        private val LOCK=Any()

        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?: createDatabase(context).also{ instance=it}
        }

        private fun createDatabase(context: Context)=
                Room.databaseBuilder(
                        context.applicationContext,
                        ResultDatabase::class.java,
                        "result_db.db"
                ).fallbackToDestructiveMigration()
                        .build()
    }
}