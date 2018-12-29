package com.example.anoopm.jsontoroom.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = arrayOf(AlbumItem::class), version = 1)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albumDAO(): AlbumDAO

    companion object {

        private var INSTANCE: AlbumDatabase? = null


        internal fun getDatabase(context: Context): AlbumDatabase {
            if (INSTANCE == null) {
                synchronized(AlbumDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlbumDatabase::class.java, "albums_database")
                            .build()

                    }
                }
            }
            return INSTANCE!!
        }
    }

}