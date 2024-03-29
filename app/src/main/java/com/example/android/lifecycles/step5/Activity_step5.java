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

package com.example.android.lifecycles.step5;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.codelabs.lifecycle.R;

/**
 * Shows two {@link Fragment_step5} fragments.
 */
public class Activity_step5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_step5);

        /**
         * 在这里要实现两个fragment共享数据。必须要把两个fragment分别实例出来，然后监听一个
         * Fragment的变化，实时更改另一个Fragment的变化
         * 使用了viewModel就简单一些请看ViewModel中的注释。
         */
    }
}
