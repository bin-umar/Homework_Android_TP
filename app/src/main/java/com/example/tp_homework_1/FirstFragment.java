package com.example.tp_homework_1;

import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class FirstFragment extends Fragment {

    private NumberListAdapter adapter = null;
    private int counter;
    private static final String LIST_DATA_BUNDLE = "listDataBundle";
    private static final String COUNTER_BUNDLE = "counterBundle";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);

        ArrayList<Integer> data;

        if (savedInstanceState == null) {
            if (adapter == null) {
                data = new ArrayList<>();
                for (counter = 1; counter <= 100; counter++) {
                    data.add(counter);
                }
            } else {
                data = adapter.getData();
            }
        } else {
            data = savedInstanceState.getIntegerArrayList(LIST_DATA_BUNDLE);
            counter = savedInstanceState.getInt(COUNTER_BUNDLE);
        }

        int spanCount = getResources().getInteger(R.integer.column_count);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        adapter = new NumberListAdapter(data, getActivity());

        RecyclerView listView = view.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setLayoutManager(layoutManager);

        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(counter++);
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(LIST_DATA_BUNDLE, adapter.getData());
        outState.putInt(COUNTER_BUNDLE, counter);
    }
}

class NumberViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    NumberViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.list_item);
    }

    void SetValue(Integer value) {
        textView.setText(String.format(Locale.getDefault(), "%d", value));
    }

    void SetTextColor(int color) {
        textView.setTextColor(color);
    }
}

class NumberListAdapter extends RecyclerView.Adapter<NumberViewHolder> {
    private ArrayList<Integer> data;
    private int oddColor;
    private int evenColor;
    private FragmentActivity activity;

    NumberListAdapter(ArrayList<Integer> _data, FragmentActivity _activity) {
        data = _data;
        activity = _activity;
    }

    void add(int elem) {
        data.add(elem);
        notifyItemInserted(data.size() - 1);
    }

    ArrayList<Integer> getData() {
        return data;
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
    public void onBindViewHolder(@NonNull final NumberViewHolder holder, int position) {
        Integer number = data.get(position);
        holder.SetValue(number);

        if (number % 2 == 0) {
            holder.SetTextColor(evenColor);
        } else {
            holder.SetTextColor(oddColor);
        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                String value = holder.textView.getText().toString();
                int color = holder.textView.getCurrentTextColor();

                SecondFragment fragment = SecondFragment.newInstance(value, color);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack("open_second_fragment");
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
