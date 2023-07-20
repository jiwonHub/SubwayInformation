package com.example.subway.data.db.entitiy

import androidx.room.Entity

@Entity(primaryKeys = ["stationName", "subwayId"]) // 테이블의 기본 키 정의.
data class StationSubwayCrossRefEntity(
    val stationName: String, // 역 이름
    val subwayId: Int // 몇 호선
)
// Room 데이터베이스에서 역과 지하철 노선 간의 관계를 표현하는 데이터 클래스. stationName과 subwayId를 합쳐서 테이블의 기본 키로 사용하고 있으며
// 이를 통해 같은 역과 노선 간의 중복 관계를 허용하지 않는다. 이 클래스를 사용하여 역과 지하철 노선 간의 매핑 정보를 데이터베이스에 저장하고 관리 할 수 있다.