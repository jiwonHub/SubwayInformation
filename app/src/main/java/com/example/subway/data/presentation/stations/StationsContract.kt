package com.example.subway.data.presentation.stations

import com.example.subway.data.presentation.BasePresenter
import com.example.subway.data.presentation.BaseView
import com.example.subway.domain.Station

interface StationsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showStations(stations: List<Station>)
    }

    interface Presenter : BasePresenter {
        fun filterStations(query: String)
    }
}