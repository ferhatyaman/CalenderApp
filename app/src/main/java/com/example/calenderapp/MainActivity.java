package com.example.calenderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EventAdapter.EventHandler {

    private static List<Event> eventList;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    static {

        try {
            Event [] events = {
                    new Event("CS310 Midterm1","Midterm at LO65 from 9am", "06/04/2019", 4, 1),
                    new Event("Room meeting","Meet Mr. Mbape before things get worse", "07/04/2019", 4, 1),
                    new Event("CS310 Midterm1 Objection","Room LO65 from 10am ", "06/05/2019", 3, 0),
                    new Event("CS308 Project presentation","4th sprint user-inteface design", "09/04/2019", 4, 1),
                    new Event("Judiy's Funeral","buy flowers", "31/03/2019", 3, 0),
                    new Event("Avengers release","Invite roommates to watch it", "02/04/2019", 2, 1),
                    new Event("Job application","Ware the black suit", "05/05/2019", 4, 0),
                    new Event("Google's interview","Focus on software engineering nothing else matters", "06/04/2019", 1, 1),
                    new Event("Summer school starts","Nothing special", "06/04/2019", 4, 1),
                    new Event("MS application deadline","now or never", "08/05/2019", 4, 1),
                    new Event("Make plane reservation","Most preferably Turkish Airlines", "29/03/2019", 3, 1),
                    new Event("Trip to Africa","Dont miss your plane", "06/04/2019", 4, 0),
                    new Event("See the dentist","Midterm at LO65 from 9am", "06/05/2019", 2, 1),
                    new Event("CS310 Midterm2","Midterm at LO65 from 9am", "04/04/2019", 4, 1),
                    new Event("Call Dad","Remind him of fees", "06/04/2019", 3, 1),
                    new Event("Shopping","With loved ones", "06/05/2019", 1, 0),
                    new Event("fundraiser","Give whatever you have", "01/04/2019", 4, 1),
                    new Event("CS310 Final","All topics includes", "28/05/2019", 1, 1),
                    new Event("Date with Juliet","Hilton hotel, Mecidiyekoy", "06/04/2019", 4, 0),
            };
            eventList = new ArrayList<>(Arrays.asList(events));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachViews();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
        RadioButton dateRadio = findViewById(R.id.radioButton_date);
        dateRadio.setOnClickListener(new Handler(this, 0));
        RadioButton priorityRadio = findViewById(R.id.radioButton_priority);
        priorityRadio.setOnClickListener(new Handler(this,1));


    }

    private void attachViews(){
        recyclerView = findViewById(R.id.recyclerView);
    }
    @Override
    public void onItemClick(int index) {
        eventAdapter.notifyItemRemoved(index);
        updateEventList();
        eventAdapter.notifyDataSetChanged();
    }
    @Override
    public void onCheckedChanged(int index, boolean checked){
        eventList.get(index).setStatus(checked);
        eventAdapter.notifyDataSetChanged();
    }

    public void onSeekBarChanged(int index, int progress){
        eventList.get(index).setPriority(progress);
        eventAdapter.notifyDataSetChanged();
    }

    private void updateEventList(){
        Iterator iter = eventList.iterator();
        while (iter.hasNext()){
            Event item = (Event) iter.next();
            if(item.getDeleted())
                iter.remove();
        }
    }

    public void sortEvent(int sortStyle) {
        if(sortStyle==0)
            Collections.sort(eventList,new EventDateComparator());
        else if (sortStyle==1)
            Collections.sort(eventList,new EventPriorityComparator());
        eventAdapter.notifyDataSetChanged();
    }
}
