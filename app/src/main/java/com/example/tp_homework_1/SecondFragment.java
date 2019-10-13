package com.example.tp_homework_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    public static SecondFragment newInstance(String value, int color) {
        SecondFragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putString("value", value);
        bundle.putInt("color", color);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        String value = "";
        int color = 0;
        Bundle arguments = getArguments();

        if (arguments != null) {
            value = arguments.getString("value");
            color = arguments.getInt("color");
        }

        TextView textView = view.findViewById(R.id.number_view);
        textView.setText(value);
        textView.setTextColor(color);

        return view;
    }
}
