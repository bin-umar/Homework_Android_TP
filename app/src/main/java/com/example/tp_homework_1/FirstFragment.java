package com.example.tp_homework_1;

import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private List<Integer> data;
    private GridLayoutManager layoutManager;
    private NumberListAdapter adapter;
    private int counter;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);

        data = new ArrayList<>();
        for (counter = 1; counter <= 100; counter++) {
            data.add(counter);
        }

        int spanCount = getResources().getInteger(R.integer.column_count);
        layoutManager = new GridLayoutManager(getActivity(), spanCount);
        adapter = new NumberListAdapter(data);

        RecyclerView listView = view.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setLayoutManager(layoutManager);

        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.insertElem(counter++);
            }
        });

        return view;
    }
}

class NumberViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;

    NumberViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.list_item);
//        textView.setOnClickListener(
//                v -> mElementOnClickCallback.accept(Integer.parseInt(textView.getText().toString()))
//        );
    }

    void SetValue(Integer value) {
        textView.setText(Integer.toString(value));
    }

    void SetTextColor(int color) {
        textView.setTextColor(color);
    }
}

class NumberListAdapter extends RecyclerView.Adapter<NumberViewHolder> {
    private List<Integer> data;
    private int oddColor;
    private int evenColor;

    NumberListAdapter(List<Integer> _data) {
        data = _data;
    }

    public void insertElem(int elem) {
        data.add(elem);
        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        Context context = parent.getContext();

        evenColor = ContextCompat.getColor(context, R.color.evenNumber);
        oddColor  = ContextCompat.getColor(context, R.color.oddNumber);

        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        Integer number = data.get(position);
        holder.SetValue(number);

        if (number % 2 == 0) {
            holder.SetTextColor(evenColor);
        } else {
            holder.SetTextColor(oddColor);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
