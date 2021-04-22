package com.denis.notes.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.denis.notes.models.AppNote
import com.denis.notes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application) : AndroidViewModel(application){

    fun delete(note: AppNote,onSuccess: ()->Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.delete(note){
                onSuccess()
            }
        }
    }

}