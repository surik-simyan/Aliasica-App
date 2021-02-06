package surik.simyan.aliasica.main.explore

data class FirebaseWordsetModel (
    val creator: String? = null,
    val language: String? = null,
    val name: String? = null,
    val rating: Float? = null,
    val tags: List<String>? = null,
    val words: List<String>? = null
)