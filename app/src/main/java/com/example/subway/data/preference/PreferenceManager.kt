package com.example.subway.data.preference

interface PreferenceManager { // 설정 값을 저장하고 가져오는 기능을 추상화한 인터페이스

    fun getLong(key: String): Long? // 설정 값 가져오기

    fun putLong(key: String, value: Long) // 설정 값 저장하기
}