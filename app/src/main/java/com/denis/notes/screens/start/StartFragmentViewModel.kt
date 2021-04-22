package com.denis.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.denis.notes.database.room.AppRoomDataBase
import com.denis.notes.database.room.AppRoomRepo
import com.denis.notes.utilits.REPOSITORY
import com.denis.notes.utilits.TYPE_ROOM

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val mContext = application

    fun initDataBase(type:String, onSuccess:()->Unit){
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDataBase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepo(dao)
                onSuccess()
            }
        }


    }
}