package com.example.subway.data.db.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubwayEntity(
    @PrimaryKey val subwayId: Int, // 몇 호선
)