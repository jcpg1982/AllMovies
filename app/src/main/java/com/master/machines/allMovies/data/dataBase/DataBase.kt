package com.master.machines.allMovies.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.master.machines.allMovies.data.dataBase.dao.DaoGenreId
import com.master.machines.allMovies.data.dataBase.dao.DaoMovie
import com.master.machines.allMovies.data.dataBase.entity.GenreIdsEntity
import com.master.machines.allMovies.data.dataBase.entity.MovieEntity
import com.master.machines.allMovies.framework.helpers.Constants

@Database(
    entities = [MovieEntity::class, GenreIdsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun movieDao(): DaoMovie
    abstract fun genreIdDao(): DaoGenreId

    companion object {

        @Volatile
        private var INSTANCE: DataBase? = null
        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, DataBase::class.java, Constants.NAME_DATA_BASE)
                        .addCallback(sRoomDatabaseCallback)
                        .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING).build()
                INSTANCE = instance
                instance
            }
        }

        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }
    }
}