package com.example.instragramclone.auth.data

//FireAuth zahteva zbog praznog konstruktora da sve vrednosti budu nulluable
data class UserData(
    var userId: String? = null,
    var name: String? = null,
    var username: String? = null,
    var imageUrl: String? = null,
    var bio: String? = null,
    var following: List<String>? = null
) {
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "username" to username,
        "imageurl" to imageUrl,
        "bio" to bio,
        "following" to following
    )
}
