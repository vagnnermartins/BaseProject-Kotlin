package com.vagnnermartins.baseproject.user

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.ViewModelProvider
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.vagnnermartins.baseproject.*
import com.vagnnermartins.baseproject.model.UserItem
import javax.inject.Inject

class UserDialogFragment: DialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var callback: OnUserDialogListener

    companion object {
        private val NAME = "UserDialogFragment_name";
        fun newInstance(name: String?): UserDialogFragment{
            val args = Bundle()
            args.putSerializable(NAME, name)
            val fragment = UserDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    interface OnUserDialogListener{
        fun onUserDialogCallback(user: UserItem?)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppInjector().inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_user, null)
        val alert = AlertDialog.Builder(activity)
        val userName = view?.findViewById(R.id.dialog_user_username) as EditText

        alert.setView(view)
        alert.setTitle(R.string.dialog_user_title)
        alert.setMessage(R.string.dialog_user_message)
        alert.setNegativeButton(android.R.string.cancel, onDialogButton(null))
        alert.setPositiveButton(R.string.dialog_user_positive_button, onDialogButton(userName))
        return alert.show()
    }

    private fun onDialogButton(editTextUsername: EditText?): (DialogInterface, Int) -> Unit {
        return { _, which ->
            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    withViewModel<UserViewModel>(viewModelFactory) {
                        val typed = editTextUsername?.text.toString()
                        if(!typed.isNullOrEmpty()){
                            saveUser(UserItem(typed))
                            observe(user, ::onSaveUser)
                        }
                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> {}
            }
        }
    }

    private fun onSaveUser(data: Data<UserItem>?) {
        data?.let {
            when (it.dataState) {
                DataState.SUCCESS -> callback.onUserDialogCallback(it.data)
            }
        }
    }
}