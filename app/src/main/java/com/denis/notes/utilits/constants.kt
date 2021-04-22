package com.denis.notes.utilits

import com.denis.notes.MainActivity
import com.denis.notes.database.DataBaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DataBaseRepository
const val TYPE_DATA_BASE = "type_database"
const val TYPE_ROOM = "type_room"
const val CHANNEL_ID = "CHANNEL_ID"
const val NOTIFY_ID = 1