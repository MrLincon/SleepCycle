package com.whitespace.sleepcycle.domain

data class InfoSectionModel(
    val title: String,
    val subtitle: String,
    val items: List<InfoCardModel>
)

data class InfoCardModel(val title: String, val body: String)

