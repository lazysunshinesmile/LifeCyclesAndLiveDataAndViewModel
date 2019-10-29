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

package com.example.android.lifecycles.step5_solution;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.android.codelabs.lifecycle.R;
import com.example.android.lifecycles.step5.SeekBarViewModel;

/**
 * Shows a SeekBar that is synced with a value in a ViewModel.
 */
public class Fragment_step5 extends Fragment {

    private SeekBar mSeekBar;

    private SeekBarViewModel mSeekBarViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_step5, container, false);
        mSeekBar = root.findViewById(R.id.seekBar);

        mSeekBarViewModel = ViewModelProviders.of(getActivity()).get(SeekBarViewModel.class);

        subscribeSeekBar();

        return root;
    }

    private void subscribeSeekBar() {

        // Update the ViewModel when the SeekBar is changed.
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    Log.d("Step5", "Progress changed!");
                    mSeekBarViewModel.seekbarValue.setValue(progress);

                    //这样也达不到效果，因为在这个Fragment实例，mSeekBar就是这个实例本身。
//                    mSeekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Update the SeekBar when the ViewModel is changed.
        //两个不同的Fragment实例持有了同一个SeekBarViewModel变量，
        // 因为mSeekBarViewModel 初始化是根据Activity获取的
        //ComponentActivity.java 中的getViewModelStore()方法中可看出ViewModel是一个变量。


        //下面加了两个监听，都能收到回调，说明回调的实例可以有多个，这样在两个Fragment中，
        //实例出来了两个Observer，都加入了一个Observer数组里面了。所以回调会让另一个seekbar
        // 同时发生变化，这样两个Fragment就有了共享数据的现象。

        Log.d("xiangsun_test", "subscribeSeekBar: F");
        mSeekBarViewModel.seekbarValue.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer value) {
                //两个不同的实例调用了他。
                Log.d("xiangsun_test", "onChanged: seek bar:" + mSeekBarViewModel);
                Log.d("xiangsun_test", "onChanged: nihao1：" + this);
                if (value != null) {
                    Log.d("xiangsun_test", "onChanged: nihao2:" );
                    mSeekBar.setProgress(value);
                }
            }
        });

        mSeekBarViewModel.seekbarValue.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.d("xiangsun_test", "onChanged: xiangsun_test2");
            }
        });
    }
}
