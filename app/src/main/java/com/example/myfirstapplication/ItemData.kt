package com.example.myfirstapplication

data class ItemData(
    var id: Int,
    var price: Int,
    var oily_score: Int,
    var dry_score: Int,
    var sensitive_score: Int,
    var thumbnail_image: String,
    var title: String)