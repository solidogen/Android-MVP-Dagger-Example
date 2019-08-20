package com.spyrdonapps.mvpexample.ui

import com.spyrdonapps.mvpexample.data.YoutubeVideo

interface MainContract {

    interface View {

        fun showList(list: List<YoutubeVideo>)

        fun showError()
    }

    interface Presenter {

        fun setView(mainView: View)

        fun loadData()

        fun clear()
    }
}