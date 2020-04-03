package com.cts.telstrapoc.model

data class CanadaAPIDetail(
    val rows: List<CanadaAPIDetailInfo>,
    val title: String
)

data class CanadaAPIDetailInfo(
    val description: String,
    val imageHref: String,
    val title: String
)