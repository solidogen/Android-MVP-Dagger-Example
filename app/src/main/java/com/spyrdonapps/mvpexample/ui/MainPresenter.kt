package com.spyrdonapps.mvpexample.ui

import com.spyrdonapps.mvpexample.data.YoutubeVideo
import com.spyrdonapps.mvpexample.data.remote.ApiResponse
import com.spyrdonapps.mvpexample.data.remote.ApiService
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(private val apiService: ApiService) : MainContract.Presenter {

    private var view: MainContract.View? = null
    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    override fun setView(mainView: MainContract.View) {
        view = mainView
    }

    override fun loadData() {
        scope.launch {
            launchLoadData()
        }
    }

    private suspend fun launchLoadData() {
        try {
            val items = withContext(Dispatchers.IO) {
                apiService.getResponse()
            }
            view?.showList(items.toUiModel().also { list ->
                Timber.d(list.toString())
            })
        } catch (e: Exception) {
            Timber.e(e)
            view?.showError()
        }
    }

    private fun ApiResponse.toUiModel(): List<YoutubeVideo> {
        return items
            .map { item ->
                YoutubeVideo(
                    item.snippet.title,
                    item.snippet.description,
                    item.snippet.thumbnails.default.url
                )
            }
            .sortedBy { it.description.count() }
    }

    override fun clear() {
        job.cancel()
        view = null
    }
}