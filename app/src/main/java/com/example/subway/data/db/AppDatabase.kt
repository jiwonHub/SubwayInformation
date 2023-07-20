package com.example.subway.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.subway.data.db.entitiy.StationEntity
import com.example.subway.data.db.entitiy.StationSubwayCrossRefEntity
import com.example.subway.data.db.entitiy.SubwayEntity


@Database(
    entities = [StationEntity::class, SubwayEntity::class, StationSubwayCrossRefEntity::class], // 데이터베이스에 포함되는 엔티티들을 배열로 정의.
    version = 1, // 데이터베이스의 버전 지정. 데이터베이스 스키마를 업데이트 할 때 유용하기 위함.
)
abstract class AppDatabase : RoomDatabase() { // Room은 이 클래스를 사용하여 데이트베이스를 정의한다.

    abstract fun stationDao(): StationDao // stationDao() 메서드는 StationDao 인터페이스를 반환한다.
    // 이 인터페이스는 데이터베이스에 엑세스하는데 사용되는 DAO이다.
    // DAO는 데이터베이스 쿼리를 정의하는 메서드들을 가지고 있다.

    companion object {

        private const val DATABASE_NAME = "station.db" // 데이터베이스 파일 이름

        fun build(context: Context): AppDatabase = // 데이터베이스를 생성하는 함수.
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build() // Room.databaseBuilder()를 사용하여 데이터베이스를 빌드한다.
            // 이 함수는 앱의 컨텍스트와 데이터베이스를 생성하는 클래스를 전달받는다.
    }
}
// AppDatabase 클래스를 사용하여 데이터베이스 인스턴스를 생성하고, StationDao 인터페이스를 통해 데이터베이스에 엑세스 할 수 있다.
// 이를 통해 데이터를 저장, 수정, 삭제, 조회하는 등의 작업을 수행할 수 있다.