package com.example.subway

import android.app.Application
import com.example.subway.di.appModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SubwayApplication: Application() { // Application 클래스 상속

    override fun onCreate() { // 앱이 시작될 때 자동으로 호출되는 메소드
        super.onCreate()      // 기존의 Application 클래스에서 수행되는 초기화 작업을 실행하도록함.
        startKoin{// Koin 라이브러리의 설정과 모듈 등록 정의 블록
            androidLogger( // Koin 라이브러리의 로그 레벨 설정
                if (BuildConfig.DEBUG){ // 디버그 모드인 경우
                    Level.DEBUG // 로그 레벨 디버그(디버그 모드에서 더 많은 로그 출력)
                } else { // 아닌 경우
                    Level.NONE // 로그 레벨 논
                }
            )
            androidContext(this@SubwayApplication) // Koin에서 앱의 컨텍스트를 활용하여 의존성 주입 처리
            modules(appModule) // Koin에서 활용할 모듈 등록.
        }
    }
}