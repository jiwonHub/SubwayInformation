package com.example.subway.di

import android.app.Activity
import com.example.subway.data.api.StationApi
import com.example.subway.data.api.StationStorageApi
import com.example.subway.data.db.AppDatabase
import com.example.subway.data.preference.PreferenceManager
import com.example.subway.data.preference.SharedPreferenceManager
import com.example.subway.data.presentation.stations.StationsContract
import com.example.subway.data.presentation.stations.StationsFragment
import com.example.subway.data.presentation.stations.StationsPresenter
import com.example.subway.data.repository.StationRepository
import com.example.subway.data.repository.StationRepositoryImpl
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Dispatchers.IO } // IO 디스패처 정의
    // 주로 네트워크 요청 & 데이터베이스 엑세스 등 비동기 작업에 사용

    scope<StationsFragment> {
        scoped<StationsContract.Presenter> { StationsPresenter(getSource()!!, get()) }
    }

    // Database
    single { AppDatabase.build(androidApplication()) } // androidApplication()함수로 Context를 얻고 이를 기반으로 데이터베이스 빌드
    single { get<AppDatabase>().stationDao() } // 데이터베이스에 접근하는 데 사용되는 메서드를 정의한 인터페이스

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) } // SharedPreference생성, 이름 : "preference", SharedPreference 가 앱의 개인 데이터로 사용됨을 의미
    single<PreferenceManager> { SharedPreferenceManager(get()) } // SharedPreferenceManager클래스를 사용하여 SharedPreferences를 래핑하는 방식으로 PreferenceManager구현.

    // Api
    single<StationApi> { StationStorageApi(Firebase.storage) } //  StationStorageApi클래스를 사용하여 Firebase Storage를 래핑하는 방식으로 StationApi구현.

    // Repository
    single<StationRepository> { StationRepositoryImpl(get(), get(), get(), get()) } // StationRepositoryImpl클래스를 사용하여 데이터베이스, 네트워크, SharedPreferences, Dispatchers.IO등의 의존성 객체를 이용하여 StationRepository구현.
}