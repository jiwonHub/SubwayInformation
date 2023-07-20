package com.example.subway.data.db.entitiy

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StationWithSubwaysEntity(
    @Embedded val station: StationEntity, // @Embedded 어노테이션은 포함된 객체를 나타냄. 즉 StationEntity가 StationWithSubwaysEntity에 포함되어있음.
    @Relation( // @Relation 어노테이션은 관계형 데이터베이스의 테이블 간의 관계를 정의하는 데 사용됨.
        parentColumn = "stationName", // 역 이름
        entityColumn = "subwayId", // 몇 호선
        associateBy = Junction(StationSubwayCrossRefEntity::class) // Junction은 다대다 관계를 표현하는 데 사용됨.
    )
    val subways: List<SubwayEntity> // 역에 연결된 노선들을 저장하는 리스트.
)
// Room 데이터베이스에서 역과 해당 역에 연결된 지하철 노선들을 함께 가져오기 위해 사용되는 데이터 클래스.
// station필드에는 StationEntity가 포함되어 있으며 subway필드에는 SubwayEntity객체의 리스트가 저장됨.
// 두 테이블(StationEntity와 SubwayEntity)간의 다대다 관계는 StationSubwayCrossRefEntity 테이블을 사용하여 정의된다.