package com.example.subway.data.api

import com.example.subway.data.db.entitiy.StationEntity
import com.example.subway.data.db.entitiy.SubwayEntity

interface StationApi {

    suspend fun getStationDataUpdatedTimeMillis(): Long // 업데이트된 시간을 밀리초 단위로 반환하는 메소드.

    suspend fun getStationSubways(): List<Pair<StationEntity, SubwayEntity>> // 역과 관련된 데이터를 가져오는 메소드.
}