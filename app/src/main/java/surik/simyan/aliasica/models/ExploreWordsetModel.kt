package surik.simyan.aliasica.models

import com.google.firebase.firestore.DocumentId

data class ExploreWordsetModel(
        val creator: String? = null,
        val language: String? = null,
        val name: String? = null,
        val tags: List<String>? = null,
        val words: List<String>? = null,
)