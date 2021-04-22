package com.denis.notes.screens.add_new_note

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.denis.notes.R
import com.denis.notes.databinding.FragmentAddNewNoteBinding
import com.denis.notes.models.AppNote
import com.denis.notes.utilits.APP_ACTIVITY
import com.denis.notes.utilits.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddNewNoteFragment : Fragment() {
    private var _binding: FragmentAddNewNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: AddNewNoteFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    @SuppressLint("ShowToast")
    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(AddNewNoteFragmentViewModel::class.java)
        binding.btnAddNote.setOnClickListener {
            val name = binding.inputNameNote.text.toString()
            val text = binding.inputTextNote.text.toString()
            if(name.isEmpty()){
                showToast(getString(R.string.toast_enter_without_name))
            } else {
                mViewModel.insert(AppNote(name = name, text = text)){
                    lifecycleScope.launch(Dispatchers.Main){
                        APP_ACTIVITY.navController.navigate(R.id.action_addNewNoteFragment_to_mainFragment)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}