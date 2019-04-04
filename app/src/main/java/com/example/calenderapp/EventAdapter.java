package com.example.calenderapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private EventHandler eventHandler;
    private List<Event> eventList;

    public EventAdapter(EventHandler eventHandler, List<Event> eventList) {
        this.eventHandler = eventHandler;
        this.eventList = eventList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.reminder_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Event event = eventList.get(i);

        if(!event.getDeleted()) {


            myViewHolder.textViewTitle.setText(event.getTitle());
            myViewHolder.textViewNotes.setText(event.getNote());
            myViewHolder.editTextDate.setText(event.getDate());
            myViewHolder.seekBarPriority.setProgress(event.getPriority());
            myViewHolder.switchCheck.setChecked(event.getStatus());

            final int j = i;
            myViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    event.setDeleted(true);
                    eventHandler.onItemClick(j);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private View rowView;
        private TextView textViewTitle;
        private TextView textViewNotes;
        private TextView editTextDate;
        private SeekBar seekBarPriority;
        private Switch switchCheck;
        private ImageButton imageButton;

        MyViewHolder(View rowView) {
            super(rowView);
            this.rowView = rowView;
            textViewTitle = rowView.findViewById(R.id.textViewTitle);
            textViewNotes = rowView.findViewById(R.id.textViewNote);
            editTextDate = rowView.findViewById(R.id.textViewDate);
            seekBarPriority = rowView.findViewById(R.id.seekBarPriority);
            switchCheck = rowView.findViewById(R.id.switchActivated);
            imageButton = rowView.findViewById(R.id.imageButton);

        }
    }

    interface EventHandler {
        void onItemClick(int index);
    }
}