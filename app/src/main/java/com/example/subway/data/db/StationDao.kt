package com.example.subway.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.subway.data.db.entitiy.StationEntity
import com.example.subway.data.db.entitiy.StationSubwayCrossRefEntity
import com.example.subway.data.db.entitiy.StationWithSubwaysEntity
import com.example.subway.data.db.entitiy.SubwayEntity
import kotlinx.coroutines.flow.Flow

@Dao // Dao 어노테이션으로 Room라이브러리가 이 인터페이스를 DAO로 인식하게 한다.
interface StationDao {

    @Transaction // getStationWithSubways() 메서드가 하나의 트랜잭션으로 실행되어야 함을 나타낸다. 트랜잭션은 일련의 쿼리가 모두 성공적으로 실행되거나 모두 실패하는 것을 보장한다.
    @Query("SELECT * FROM StationEntity") // StationEntity테이블의 모든 레코드를 가져온다.
    fun getStationWithSubways(): Flow<List<StationWithSubwaysEntity>> // Flow는 비동기적인 데이터 스트림을 나타내며, 데이터베이스에서 데이터가 변경될 때마다 자동으로 업데이트를 수실할 수 있도록 한다.

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 데이터 삽입. onConflict옵션을 OnConflictStrategy.REPLACE로 설정함으로써 동일한 키가 이미 존재하는 경우 새로운 데이터로 교체한다.
    suspend fun insertStations(station: List<StationEntity>) // suspend키워드는 메서드가 코루틴에서 실행되어야 함을 나타낸다. 따라서 이들은 메인 스레드를 차단하지않고 비동기적으로 실행될 수 있다.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubways(subways: List<SubwayEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(reference: List<StationSubwayCrossRefEntity>)
}
// StationDao 인터페이스를 사용하여 데이터베이스의 쿼리를 실행하고, 데이터를 삽입, 조회, 업데이트, 삭제하는 등의 작업을 수행할 수 있다.
// Flow를 통해 데이터베이스에서 변경 사항을 관찰하고, suspend키워드를 이용하여 비동기적으로 데이터를 삽입하는 작업을 수행할 수 있다.