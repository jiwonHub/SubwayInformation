package com.example.subway.data.db.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationEntity(
    @PrimaryKey val stationName: String, // 역 이름
    val isFavorited: Boolean = false // 즐겨찾기 on off
)