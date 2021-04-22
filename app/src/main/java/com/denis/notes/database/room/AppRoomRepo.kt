package com.denis.notes.database.room

import androidx.lifecycle.LiveData
import com.denis.notes.database.DataBaseRepository
import com.denis.notes.models.AppNote

class AppRoomRepo(private val appRoomDAO: AppRoomDAO):DataBaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDAO.getAllNotes()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDAO.insert(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDAO.delete(note)
        onSuccess()
    }
}