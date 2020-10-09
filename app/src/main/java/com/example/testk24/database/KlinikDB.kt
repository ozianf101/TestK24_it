package com.example.testk24.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testk24.model.Admin
import com.example.testk24.model.Member

@Database(entities = [Admin::class,Member::class],version = 1,exportSchema = false)
abstract class KlinikDB: RoomDatabase() {
    companion object{
        @Volatile
        private var INSTANCE: KlinikDB? =null

        fun getDatabase(context: Context):KlinikDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        KlinikDB::class.java,
                        "klinik_db"
                )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getAdminDao() : AdminDAO

    abstract fun getMemberDao() : MemberDAO
}