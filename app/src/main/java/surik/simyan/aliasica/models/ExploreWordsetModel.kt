package surik.simyan.aliasica.models

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

data class ExploreWordsetModel(
        @DocumentId
        val id: String? = null,
        val creator: String? = null,
        val language: String? = null,
        val name: String? = null,
        val tags: List<String>? = null,
        val words: List<String>? = null,
)

fun ExploreWordsetModel.toHomeWordset() = HomeWordsetModel(
        key = id.toString(),
        creator = creator,
        language = language,
        name = name,
        tags = tags,
        words = words
)