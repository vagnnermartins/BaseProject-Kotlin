package com.vagnnermartins.baseproject.projects

import android.arch.lifecycle.ViewModelProvider
import android.os.Build
import android.os.Bundle
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.vagnnermartins.baseproject.*
import com.vagnnermartins.baseproject.model.ProjectItem
import com.vagnnermartins.baseproject.model.UserItem
import com.vagnnermartins.baseproject.navigation.ProjectDetailNavigator
import com.vagnnermartins.baseproject.user.UserDialogFragment
import com.vagnnermartins.baseproject.user.UserViewModel
import kotlinx.android.synthetic.main.activity_projects.*
import kotlinx.android.synthetic.main.item_project.view.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class ProjectsActivity : AppCompatActivity(), UserDialogFragment.OnUserDialogListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var projectDetailNavigator: ProjectDetailNavigator

    private val adapter = ProjectListAdapter(this, ::onItemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)
        init()
    }

    private fun init() {
        getAppInjector().inject(this)
        projectsRecyclerView.adapter = adapter
        projectsUser.setOnClickListener { showUserDialogFragment(null)}
        withViewModel<UserViewModel>(viewModelFactory) {
            getUser()
            observe(user, ::onGetUser)
        }
    }

    private fun onGetUser(data: Data<UserItem>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> swipeRefreshLayout.startRefreshing()
                DataState.SUCCESS -> {
                    loadValues(it.data)
                    withViewModel<ProjectsViewModel>(viewModelFactory) {
                        swipeRefreshLayout.setOnRefreshListener {
                            get(app.hasInternet(), it.data?.name)
                        }
                        get(app.hasInternet(), it.data?.name)
                        observe(projects, ::onGetProjects)
                    }
                }
                DataState.ERROR -> {
                    swipeRefreshLayout.stopRefreshing()
                    toast(R.string.click_to_add_repository)
                }
            }
        }
    }

    private fun onGetProjects(data: Data<List<ProjectItem>>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> swipeRefreshLayout.startRefreshing()
                DataState.SUCCESS -> {
                    swipeRefreshLayout.stopRefreshing()
                    //TODO Load the datas
                    it.data?.let {
                        if(it.isEmpty()) toast(R.string.no_projects)
                        adapter.addItems(it)
                    }
                    it.message?.let { toast(it) }
                }
                DataState.ERROR ->toast(R.string.unknow_error)
            }
        }
    }

    private fun showUserDialogFragment(username: String?) {
        val dialogFragment = UserDialogFragment.newInstance(username)
        dialogFragment.callback = this
        dialogFragment.show(supportFragmentManager, "dialogFragment")
    }

    override fun onUserDialogCallback(user: UserItem?) {
        withViewModel<ProjectsViewModel>(viewModelFactory) {
            loadValues(user)
            get(true, user?.name)
            observe(projects, ::onGetProjects)
        }
    }

    private fun loadValues(userItem: UserItem?) {
        projectsUser.text = String.format(getString(R.string.repository_name), userItem?.name)
    }

    private fun onItemClickListener(projectItem: ProjectItem, itemView: View) {
        val views  = mutableListOf<Pair<View, String>>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            views.add(Pair(itemView.itemProjectAvatar, itemView.itemProjectAvatar.transitionName))
            views.add(Pair(itemView.itemProjectName, itemView.itemProjectName.transitionName))
            views.add(Pair(itemView.itemProjectLanguage, itemView.itemProjectLanguage.transitionName))
            views.add(Pair(itemView.itemProjectDescription, itemView.itemProjectDescription.transitionName))
        }
        projectDetailNavigator.navigate(this, projectItem, views)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_projects_edit -> {
                showUserDialogFragment(null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.projects_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
