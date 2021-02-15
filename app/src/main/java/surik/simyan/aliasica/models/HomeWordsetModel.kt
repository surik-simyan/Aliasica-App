package surik.simyan.aliasica.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

@Entity(tableName = "firebase_words_downloaded")
data class HomeWordsetModel(
        @PrimaryKey val key: String,
        val creator: String? = null,
        val language: String? = null,
        val name: String? = null,
        val tags: List<String>? = null,
        val words: List<String>? = null
)