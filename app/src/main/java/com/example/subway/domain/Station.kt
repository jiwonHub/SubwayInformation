package com.example.subway.domain

data class Station(
    val name: String, // 역 이름
    val isFavorited: Boolean, // 역 즐겨찾기 유무
    val connectedSubways: List<Subway> // 해당 지하철 역과 연결된 지하철 노선들을 나타내는 Subway객체들의 리스트이다.
)