package com.example.subway.data.presentation

interface BaseView<PresenterT : BasePresenter> { // View의 기본 인터페이스 정의. 제너릭을 사용하여 PresenterT라는 Presenter 타입을 정의한다.
    val presenter: PresenterT // PresenterT타입의 읽기 전용 프로퍼티. 해당 View에 연결된 Presenter 인스턴스를 참조한다.
    // PresenterT는 BasePresenter 인터페이스를 구현한 Presenter형태로 제한된다. 따라서 해당 View는 BasePresenter 인터페이스를 상속받은 클래스인 Presenter와만 연결할 수 있다.
}