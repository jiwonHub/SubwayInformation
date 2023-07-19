package com.example.subway.presentation

interface BaseView<PresenterT : BasePresenter> {
    val presenter: PresenterT
}