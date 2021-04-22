package com.denis.notes.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.denis.notes.models.AppNote
import com.denis.notes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteFragmentViewModel(application: Application):AndroidViewModel(application) {

    fun insert(note:AppNote,onSuccess:()->Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note){
                onSuccess()
            }
        }
    }


}