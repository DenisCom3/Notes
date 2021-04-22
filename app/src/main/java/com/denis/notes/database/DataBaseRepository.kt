package com.denis.notes.database

import androidx.lifecycle.LiveData
import com.denis.notes.models.AppNote

interface DataBaseRepository {
    val allNotes: LiveData<List<AppNote>>

    suspend fun insert(note: AppNote, onSuccess:()->Unit)
    suspend fun delete(note: AppNote, onSuccess:()->Unit)
}