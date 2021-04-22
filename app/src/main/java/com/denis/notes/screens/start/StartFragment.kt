package com.denis.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.denis.notes.R
import com.denis.notes.databinding.FragmentStartBinding
import com.denis.notes.utilits.APP_ACTIVITY
import com.denis.notes.utilits.TYPE_ROOM


class StartFragment : Fragment() {

    private var _binding:FragmentStartBinding? = null
    val binding get() = _binding!!
    private lateinit var mViewModel: StartFragmentViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
         initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        binding.btnRoom.setOnClickListener {
            mViewModel.initDataBase(TYPE_ROOM){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
}