package com.vagnnermartins.baseproject.projectDetail

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.vagnnermartins.baseproject.*
import com.vagnnermartins.baseproject.model.PROJECT_ITEM
import com.vagnnermartins.baseproject.model.ProjectItem
import kotlinx.android.synthetic.main.activity_project_detail.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class ProjectDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)
        init()
    }

    private fun init() {
        getAppInjector().inject(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        withViewModel<ProjectDetailViewModel>(viewModelFactory) {
            get(intent.getSerializableExtra(PROJECT_ITEM) as ProjectItem)
            observe(project, ::onGetProject)
        }
    }

    private fun onGetProject(data: Data<ProjectItem>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> Log.d("loading", "loading")
                DataState.SUCCESS -> loadValues(it.data)
                DataState.ERROR -> toast(R.string.unknow_error)
            }
        }
    }

    private fun loadValues(data: ProjectItem?){
        projectDetailAvatar.load(data?.ownerAvatar)
        projectDetailName.text = data?.name
        projectDetailLanguage.text = data?.language
        projectDetailOwner.text = String.format(getString(R.string.project_detail_owner), data?.ownerName)
        itemProjectFullName.text = String.format(getString(R.string.project_detail_full_name), data?.fullName)
        itemProjectDateCreated.text = String.format(getString(R.string.project_detail_date_created),
                data?.dateCreated!!.dateFormat("dd/MM/yyyy HH:mm"))
        itemProjectLastUpdated.text = String.format(getString(R.string.project_detail_last_updated),
                data?.lastUpdated!!.dateFormat("dd/MM/yyyy HH:mm"))
        itemProjectDescription.text = data.description
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
