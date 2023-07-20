package com.example.subway.data.preference

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferenceManager(
    private val sharedPreferences: SharedPreferences // sharedPreferences 생성자를 통해 SharedPreferences 객체를 주입받는다.
) : PreferenceManager { // PreferenceManager 인터페이스를 구현하는 클래스.

    override fun getLong(key: String): Long? {
        val value = sharedPreferences.getLong(key, INVALID_LONG_VALUE) // sharedPreferences.getLong(key, INVALID_LONG_VALUE)를 호출하여 key에 해당하는 설정 값을 가져온다.
        // 만약 해당 키로 저장된 값이 없다면 INVALID_LONG_VALUE 상수를 반환한다.

        return if (value == INVALID_LONG_VALUE) { // 가져온 값이 INVALID_LONG_VALUE와 같으면 null, 아니면 실제 값을 반환.
            null
        } else {
            value
        }
    }

    override fun putLong(key: String, value: Long) =
        sharedPreferences.edit { putLong(key, value) } // sharedPreferences.edit { putLong(key, value) }를 호출하여 SharedPreferences.Editor를 가져온 뒤, putLong() 메서드를 사용하여 해당 키와 값을 저장한다.

    companion object {
        private const val INVALID_LONG_VALUE = Long.MIN_VALUE
    }
}
// PreferenceManager 인터페이스는 설정 값을 추상화하여 어떤 종류의 설정 값을 사용하더라도 구현을 교체할 수 있게 해준다.
// SharedPreferenceManager 클래스는 SharedPreferences를 사용하여 실제로 설정 값을 저장하고 가져오는데 사용된다.
// 이렇게 함으로써 앱에서 다른 설정 저장소를 사용하고자 할 때 코드 변경을 최소화할 수 있다.