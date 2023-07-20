package com.example.subway.data.api

import com.example.subway.data.db.entitiy.StationEntity
import com.example.subway.data.db.entitiy.SubwayEntity
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class StationStorageApi(
    firebaseStorage: FirebaseStorage
) : StationApi {

    private val sheetReference = firebaseStorage.reference.child(STATION_DATA_FILE_NAME) // Firebase에 저장된 CSV파일 가리키기

    override suspend fun getStationDataUpdatedTimeMillis(): Long =
        sheetReference.metadata.await().updatedTimeMillis // Firebase 스토리지에서 CSV파일의 메타데이터를 가져와서 업데이트된 시간을 밀리초 단위로 반환.

    override suspend fun getStationSubways(): List<Pair<StationEntity, SubwayEntity>> { // CSV파일에서 데이터를 가져와 리스트로 변환 후 반환.

        val downloadSizeBytes = sheetReference.metadata.await().sizeBytes // Firebase 스토리지에서 파일의 메타데이터 가져오기
        // sheetReference.metadata = 해당 파일의 메타데이터 접근. 파일 크기, 업데이트된 시간 등 포함
        // .await() = 코루틴에서 비동기 처리를 위해 사용됨. 해당 작업이 완료될 때 까지 기다리도록함.
        // sizeBytes = 메타데이터에서 파일의 크기

        val byteArray = sheetReference.getBytes(downloadSizeBytes).await() // 파일의 데이터를 바이트 배열로 다운로드
        // downloadSizeBytes만큼의 데이터를 바이트 배열로 가져온다.

        return byteArray.decodeToString() // decodeToString()를 사용하여 문자열로 변환 후 각 줄을 파싱하여 StationEntity,SubwayEntity의 쌍으로 묶어 리스트 생성
            .lines() // 문자열을 줄 단위로 나누어 리스트로 반환.
            .drop(1) // 리스트의 첫번 째 줄, 즉 CSV파일의 헤더 행은 제외하고 나머지 행들만 남긴다.
            .map { it.split(",") } // 각 행을 ','로 분리하여 리스트의 리스트로 변환.
            .map { StationEntity(it[1]) to SubwayEntity(it[0].toInt()) } // 각 행에서 StationEntity,SubwayEntity의 쌍으로 이루어진 리스트 생성
    }

    companion object {
        private const val STATION_DATA_FILE_NAME = "station_data.csv" // CSV파일명
    }
}