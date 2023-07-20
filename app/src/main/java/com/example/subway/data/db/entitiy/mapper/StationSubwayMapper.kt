package com.example.subway.data.db.entitiy.mapper

import com.example.subway.data.db.entitiy.StationWithSubwaysEntity
import com.example.subway.data.db.entitiy.SubwayEntity
import com.example.subway.domain.Station
import com.example.subway.domain.Subway

fun StationWithSubwaysEntity.toStation() = Station( // StationWithSubwaysEntity를 Station로 변환하는 함수.
    name = station.stationName, // StationWithSubwaysEntity에 있는 station 프로퍼티의 stationName값을 Station의 name프로퍼티에 할당.
    isFavorited = station.isFavorited, // StationWithSubwaysEntity에 있는 station프로퍼티의 isFavorited값을 Station의 isFavorited 프로퍼티에 할당.
    connectedSubways = subways.toSubways() // StationWithSubwaysEntity에 있는 subway리스트를 SubwayEntity 리스트에서 Subway 리스트로 변환하여, 이를 Station의 connectedSubways 프로퍼티에 할당.
)

fun List<StationWithSubwaysEntity>.toStations() = map { it.toStation() } // StationWithSubwaysEntity 리스트를 Station객체로 변환하여 새로운 리스트를 생성. map함수를 사용하여 StationWithSubwaysEntity를 toStations()함수를 통해 변환.

fun List<SubwayEntity>.toSubways(): List<Subway> = map { Subway.findById(it.subwayId) } // SubwayEntity리스트를 Subway객체로 변환하여 새로운 리스트를 생성. map함수를 사용하여 각 SubwayEntity를 Subway.findById(it.subwayId)함수를 통해 변환.
// SubwayEntity의 subwayId값을 사용하여 Subway객체를 찾아 반환.