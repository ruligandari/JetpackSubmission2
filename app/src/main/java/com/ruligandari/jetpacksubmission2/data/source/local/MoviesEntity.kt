package com.ruligandari.jetpacksubmission2.data.source.local

data class MoviesEntity(
    var id: Int=0,
    var poster_path: String?=null,
    var title: String?=null,
    var release_date: String?=null,
    var runtime: Int?=null,
    var vote_average: String?=null,
    var overview: String?=null,
)
