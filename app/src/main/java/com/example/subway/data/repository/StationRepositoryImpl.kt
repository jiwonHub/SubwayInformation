package com.example.subway.data.repository

import com.example.subway.data.api.StationApi
import com.example.subway.data.db.StationDao
import com.example.subway.data.db.entitiy.StationSubwayCrossRefEntity
import com.example.subway.data.db.entitiy.mapper.toStations
import com.example.subway.data.preference.PreferenceManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import com.example.subway.domain.Station

class StationRepositoryImpl(
    private val stationApi: StationApi,
    private val stationDao: StationDao,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : StationRepository { // Station 데이터에 대한 Repository 인터페이스를 구현하는 클래스

    override val stations: Flow<List<Station>> =
        stationDao.getStationWithSubways() // 데이터베이스로부터 Station과 Subway데이터를 가져오고
            .distinctUntilChanged() // 변경 사항이 있는 경우에만 데이터를 방출하도록 한다.
            .map { it.toStations() } // 데이터베이스 엔티티를 UI에 적합한 Station클래스로 변환
            .flowOn(dispatcher) // 코루틴 실행을 위해 지정된 디스패서를 사용하여 데이터를 방출하도록 한다.

    override suspend fun refreshStations() = withContext(dispatcher) { // refreshStations() 함수의 모든 작업이 지정된 디스패처에서 실행되도록 한다.
        val fileUpdatedTimeMillis = stationApi.getStationDataUpdatedTimeMillis() // stationApi.getStationDataUpdatedTimeMillis()를 통해 데이터 소스로부터 Station데이터가 최근 업데이트된 시간을 가져온다.
        val lastDatabaseUpdatedTimeMillis = preferenceManager.getLong( // 마지막으로 데이터베이스가 업데이트된 시간을 가져온다.
            KEY_LAST_DATABASE_UPDATED_TIME_MILLIS
        )

        if (lastDatabaseUpdatedTimeMillis == null || fileUpdatedTimeMillis > lastDatabaseUpdatedTimeMillis) {
            // 데이터베이스의 업데이트 시간이 존재하지 않거나, 데이터 소스의 업데이트 시간이 데이터베이스의 업데이트 시간보다 최신인 경우에만
            // 데이터를 새로고침하고 데이터베이스에 저장한다.
            val stationSubways = stationApi.getStationSubways() // stationApi를 통해 원격 데이터 소스로부터 stationSubways를 가져온다.
            stationDao.insertStations(stationSubways.map { it.first }) // stationSubways 리스트에서 각 원소의 첫 번째 값을 추출하여 stationDao를 통해 데이터베이스에 Station 데이터를 삽입한다.
            stationDao.insertSubways(stationSubways.map { it.second }) // stationSubways 리스트에서 각 원소의 두 번째 값을 추출하여 stationDao를 통해 데이터베이스에 Subway 데이터를 삽입한다.
            stationDao.insertCrossReferences(
                stationSubways.map { (station, subway) -> // stationSubways 리스트의 각 원소에 대해 Station과 Subway를 조합하여 StationSubwayCrossRefEntity 객체 생성.
                    StationSubwayCrossRefEntity( // 데이터베이스에 삽입
                        station.stationName,
                        subway.subwayId
                    )
                }
            )
            preferenceManager.putLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS, fileUpdatedTimeMillis) // fileUpdatedTimeMillis를 preferenceManager를 통해 설정 값으로 저장.
            // 데이터베이스의 업데이트 시간을 나타내며, 다음에 refreshStations()가 호출될 때 비교되어 데이터를 새로 고칠지를 결정하는데 사용된다.
        }
    }

    companion object {
        private const val KEY_LAST_DATABASE_UPDATED_TIME_MILLIS = "KEY_LAST_DATABASE_UPDATED_TIME_MILLIS" // 데이터베이스의 업데이트 시간을 저장하는 키
    }
}
// 데이터베이스의 업데이트 시간이 존재하지 않거나, 데이터 소스의 업데이트 시간이 데이터베이스의 업데이트 시간보다 최신인 경우에만 데이터를 새로 고치고 데이터베이스에 저장한다.
// 이를 통해 항상 최신의 Station데이터를 유지하고, 앱의 사용자들에게 업데이트된 정보를 제공할 수 있다.