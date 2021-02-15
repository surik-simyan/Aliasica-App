package surik.simyan.aliasica.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import surik.simyan.aliasica.models.Converters
import surik.simyan.aliasica.models.HomeWordsetModel

@Database(entities = [HomeWordsetModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DownloadedWordsetDatabase : RoomDatabase() {
    abstract fun getWordsetDao(): WordsetDAO


    companion object {
        private var INSTANCE: DownloadedWordsetDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?:
        Room.databaseBuilder(context, DownloadedWordsetDatabase::class.java, "firebase_words_downloaded")
                // .allowMainThreadQueries()
                .build().also { INSTANCE = it }
    }
}