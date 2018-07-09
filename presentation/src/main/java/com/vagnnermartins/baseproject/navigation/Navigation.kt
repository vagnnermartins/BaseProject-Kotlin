package com.vagnnermartins.baseproject.navigation

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.vagnnermartins.baseproject.model.PROJECT_ITEM
import com.vagnnermartins.baseproject.model.ProjectItem
import com.vagnnermartins.baseproject.projectDetail.ProjectDetailActivity
import com.vagnnermartins.baseproject.projects.ProjectsActivity
import javax.inject.Inject

class ProjectNavigator @Inject constructor() {

    fun navigate(activity: Activity){
        val intent = Intent(activity, ProjectsActivity::class.java)
        activity.startActivity(intent)
    }
}

class ProjectDetailNavigator @Inject constructor() {

    fun navigate(activity: Activity, project: ProjectItem, views: List<Pair<View, String>>){
        val intent = Intent(activity, ProjectDetailActivity::class.java)
        intent.putExtra(PROJECT_ITEM, project)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, views.get(0), views.get(1), views.get(2), views.get(3)
            )
            ActivityCompat.startActivity(activity, intent, options.toBundle())
        }else{
            activity.startActivity(intent)
        }
    }
}