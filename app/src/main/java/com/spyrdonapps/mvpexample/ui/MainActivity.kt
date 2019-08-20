package com.spyrdonapps.mvpexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.spyrdonapps.mvpexample.R
import com.spyrdonapps.mvpexample.data.YoutubeVideo
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private val youtubeVideoAdapter = YoutubeVideoAdapter()
    private var restoredInstanceState: Parcelable? = null

    @Inject
    internal lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this)
        setupRecyclerView()
        handleSavedInstanceStateIfNeeded(savedInstanceState)
        loadData()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(LAYOUT_MANAGER_INSTANCE_STATE_TAG, recyclerView.layoutManager?.onSaveInstanceState())
    }

    override fun onDestroy() {
        presenter.clear()
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = youtubeVideoAdapter
        }
    }

    private fun handleSavedInstanceStateIfNeeded(savedInstanceState: Bundle?) {
        savedInstanceState?.getParcelable<Parcelable>(LAYOUT_MANAGER_INSTANCE_STATE_TAG)?.let {
            restoredInstanceState = it
        }
    }

    private fun loadData() {
        progressBar.isVisible = true
        presenter.loadData()
    }

    override fun showList(list: List<YoutubeVideo>) {
        progressBar.isVisible = false
        youtubeVideoAdapter.setItems(list)
        restoreRecyclerPositionIfNeeded()
    }

    private fun restoreRecyclerPositionIfNeeded() {
        restoredInstanceState?.let {
            recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun showError() {
        progressBar.isVisible = false
        AlertDialog.Builder(this)
            .setTitle(R.string.error)
            .setMessage("")
            .setPositiveButton("OK", null)
            .show()
    }

    companion object {
        const val LAYOUT_MANAGER_INSTANCE_STATE_TAG = "LLM"
    }
}