package com.example.subway.domain

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.room.PrimaryKey

enum class Subway( // 지하철 노선에 대한 열거형 클래스
    @PrimaryKey
    val id: Int, // 지하철 노선에 대한 고유 Id
    val label: String, // 지하철 노선의 라벨(이름)
    @ColorInt val color: Int // 지하철 노선을 나타내는 색상
) {
    LINE_1(1001, "1호선", Color.parseColor("#FF0D3692")),

    LINE_2(1002, "2호선", Color.parseColor("#FF33A23D")),

    LINE_3(1003, "3호선", Color.parseColor("#FFFE5D10")),

    LINE_4(1004, "4호선", Color.parseColor("#FF00A2D1")),

    LINE_5(1005, "5호선", Color.parseColor("#FF8B50A4")),

    LINE_6(1006, "6호선", Color.parseColor("#FFC55C1D")),

    LINE_7(1007, "7호선", Color.parseColor("#FF54640D")),

    LINE_8(1008, "8호선", Color.parseColor("#FFF14C82")),

    LINE_9(1009, "9호선", Color.parseColor("#FFAA9872")),

    LINE_63(1063, "경의중앙", Color.parseColor("#FF73C7A6")),

    LINE_65(1065, "공항철도", Color.parseColor("#FF3681B7")),

    LINE_67(1067, "경춘", Color.parseColor("#FF32C6A6")),

    LINE_71(1071, "수인분당", Color.parseColor("#FFFF8C00")),

    LINE_75(1075, "수인분당", Color.parseColor("#FFFF8C00")),

    LINE_77(1077, "신분당", Color.parseColor("#FFC82127")),

    UNKNOWN(-1, "확인불가", Color.LTGRAY);

    companion object {
        fun findById(id: Int): Subway = values().find { it.id == id } ?: UNKNOWN // 주어진 ID에 해당하는 지하철 노선을 찾는 함수.
        // values() : 열거형의 모든 값들을 배열로 반환.
        // find { it.id == id } ?: UNKNOWN : id와 일치하는 지하철 노선을 찾으면 해당 노선을 반환하고, 일치하는 노선이 없을 경우 UNKNOWN을 반환.
    }
}