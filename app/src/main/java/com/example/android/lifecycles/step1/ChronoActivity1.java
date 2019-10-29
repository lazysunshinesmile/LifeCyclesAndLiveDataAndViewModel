/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.lifecycles.step1;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.codelabs.lifecycle.R;

/**
 * 旋转屏幕计时器会重新计时
 */
public class ChronoActivity1 extends AppCompatActivity {

    private static String TAG = "ChronoActivity1";
    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        Chronometer chronometer = findViewById(R.id.chronometer);


        /**
         * 记录基础时间，Chronometer会根据基础时间和当前时间，显示已经计时了多久。
         */
        Log.d(TAG, "onCreate: gettime:" + myViewModel.getTime());
        if(myViewModel.getTime() == 0) {
            long time = SystemClock.elapsedRealtime();
            myViewModel.setTime(time);
            chronometer.setBase(myViewModel.getTime());
        } else{
            chronometer.setBase(myViewModel.getTime());
        }


        chronometer.start();
    }


}
