package com.denis.notes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.denis.notes.database.room.AppRoomDataBase
import com.denis.notes.database.room.AppRoomRepo
import com.denis.notes.utilits.REPOSITORY
import com.denis.notes.utilits.TYPE_ROOM

class MainFragmentViewModel(application: Application):AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes




}