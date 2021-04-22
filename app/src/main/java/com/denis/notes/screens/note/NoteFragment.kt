package com.denis.notes.screens.note

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.denis.notes.R
import com.denis.notes.databinding.FragmentNoteBinding
import com.denis.notes.models.AppNote
import com.denis.notes.utilits.APP_ACTIVITY
import com.denis.notes.utilits.CHANNEL_ID
import com.denis.notes.utilits.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    private lateinit var notificationManager: NotificationManager




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater,container,false)
        mCurrentNote = arguments?.getSerializable("note") as AppNote
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {


        setHasOptionsMenu(true)
        super.onStart()
        initialization()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun initialization(){
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
        binding.noteText.text = mCurrentNote.text
        binding.noteName.text = mCurrentNote.name
        binding.btnNotification.setOnClickListener { showDateTimeDialog() }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_delete -> {
                mViewModel.delete(mCurrentNote){
                    lifecycleScope.launch(Dispatchers.Main){
                        APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                        showToast("Заметка удалена")
                }}
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat", "ShowToast")
    private fun showDateTimeDialog() {
        val calendar = Calendar.getInstance()



        val dateSetListener = DatePickerDialog.OnDateSetListener{ datePicker: DatePicker,
                                                                  year: Int,
                                                                  month: Int,
                                                                  dayOfMonth: Int ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            val onTimeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
                calendar.set(Calendar.MINUTE,minute)

                setTimeAndPush(calendar.timeInMillis)
                Toast.makeText(APP_ACTIVITY,"Напоминание установлено!", Toast.LENGTH_SHORT).show()



            }
            TimePickerDialog(APP_ACTIVITY,onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),false).show()

        }

        DatePickerDialog(APP_ACTIVITY,dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()

//        val simpleDateFormat = SimpleDateFormat("yy-MM-dd HH:mm")
//        val text: String? = simpleDateFormat.format(calendar.time)
//        if (text != null){ Toast.makeText(APP_ACTIVITY,text,Toast.LENGTH_SHORT).show()}

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setTimeAndPush(time:Long){

        notificationManager = APP_ACTIVITY.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createChannelIfNeeded(notificationManager)
        val intent = Intent(APP_ACTIVITY, Receiver::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getBroadcast(APP_ACTIVITY, 0, intent, 0)

        createChannelIfNeeded(notificationManager)

        val alarmManager:AlarmManager = APP_ACTIVITY.getSystemService(Context.ALARM_SERVICE) as AlarmManager



        alarmManager.set(AlarmManager.RTC_WAKEUP, time,pendingIntent)








    }

    private fun createChannelIfNeeded(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    
}