package com.example.subway.data.presentation

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

interface BasePresenter { // Presenter의 기본 인터페이스

    val scope: CoroutineScope // scope는 CoroutineScope 타입의 읽기 전용 프로퍼티이다. CoroutineScope는 코루틴을 실행하기 위한 범위를 정의하는데 사용된다.
    // 이를 통해 코루틴의 생명주기를 관리하고 취소할 수 있다.

    fun onViewCreated() // View가 생성될 때 호출되는 함수. 이 메서드를 사용하여 Presenter가 View의 초기화 또는 설정을 수행할 수 있다.

    fun onDestroyView() // View가 파기될 때 호출되는 함수. 이 메서드를 사용하여 Presenter가 View와 관련된 정리 작업을 수행할 수 있다.

    @CallSuper // 해당 메서드가 오버라이드된 경우, 상위 클래스의 메서드를 호출하도록 강제한다. onDestroy()가 오버라이드 되는 경우, 상위 클래스인 BasePresenter의 onDestroy() 메서드를 반드시 호출하도록 한다.
    fun onDestroy(){ // Presenter의 수명 주기가 끝날 대 호출되는 함수.
        scope.cancel() // Presenter의 CoroutineScope에서 실행 중인 모든 코루틴을 취소한다. 이렇게 함으로써 뷰가 파기되었을 때, Presenter의 코루틴들이 계속 동작하지 않도록 한다.
    }
}
// Presenter에서 View의 초기화 및 해제, 코루틴 관리 등의 작업을 정의한다.
// 이를 통해 Presenter의 생명 주기를 관리하고 메모리 누수를 방지하여 앱의 성능과 안정성을 향상시키는데 도움 된다.
// 프로젝트에 따라 이 인터페이스를 구현하여 실제로 Presenter를 구현할 수 있다.