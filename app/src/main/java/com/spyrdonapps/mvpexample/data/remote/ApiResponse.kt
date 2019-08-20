package com.spyrdonapps.mvpexample.data.remote


data class ApiResponse(val items: List<Item>)

data class Item(val snippet: Snippet)

data class Snippet(
    val description: String,
    val thumbnails: Thumbnails,
    val title: String
)

data class Thumbnails(val default: Default)

data class Default(val url: String)
