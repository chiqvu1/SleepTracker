/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SleepDatabaseDao {
    //insert sleep night into database table
    //when call insert, room will create a row

    @Insert
    fun insert(night: sleepNight)

    @Update
    fun update(night: sleepNight)

    //create query
    //pass the value from the table. if pass in 10 as and 10 exist, return that data, if not, return null
    @Query("SELECT * FROM daily_sleep_quallity_table WHERE nightId = :key")
    fun get(key: Long): sleepNight?

    @Query("DELETE FROM daily_sleep_quallity_table")
    fun clear()

    @Query("SELECT * FROM daily_sleep_quallity_table ORDER BY nightId DESC")
    //live data will keep the data updated for us so we won't have to run the query again
    fun getAllNights(): LiveData<List<sleepNight>>

    @Query("SELECT * FROM daily_sleep_quallity_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): sleepNight?
}

