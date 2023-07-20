package com.example.subway.data.repository

import com.example.subway.domain.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository { // Station에 대한 Repository의 기본 인터페이스

    val stations: Flow<List<Station>> // Station의 데이터를 표현하는 Flow를 반환.

    suspend fun refreshStations() // 데이터 새로고침 함수
}