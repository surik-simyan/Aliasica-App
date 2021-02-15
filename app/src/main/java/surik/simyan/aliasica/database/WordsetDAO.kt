package surik.simyan.aliasica.database

import androidx.room.*
import surik.simyan.aliasica.models.ExploreWordsetModel
import surik.simyan.aliasica.models.HomeWordsetModel

@Dao
interface WordsetDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordset: HomeWordsetModel)

    @Delete
    fun delete(wordset: HomeWordsetModel)

    @Query("DELETE FROM firebase_words_downloaded")
    suspend fun deleteAll()

    @Query("SELECT * FROM firebase_words_downloaded")
    fun getAllDownloaded(): List<HomeWordsetModel>
}