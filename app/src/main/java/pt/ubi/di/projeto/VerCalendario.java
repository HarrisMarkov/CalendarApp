package pt.ubi.di.projeto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Calendar;


import java.util.ArrayList;

public class VerCalendario extends AppCompatActivity {

    private ListView list_eventos;
    private CalendarView calendarView;
    private String selectedDate;
    mySQLiteDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ver_calendario);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setShowWeekNumber(true);
        dbHandler = new mySQLiteDBHandler(this);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
                //makeText(selectedDate);

                list_eventos = (ListView) findViewById(R.id.listView_calendar);
                ArrayList<String> list = new ArrayList<>();
                Cursor data = dbHandler.getData();

                if(data.getCount() == 0) {
                    makeText("The database is empty...");
                } else {
                    while(data.moveToNext()){
                        String date = data.getString(2);
                        String[] arrOfStr = date.split("/", -2);

                        int data_day = Integer.parseInt(arrOfStr[0]);
                        int data_month = Integer.parseInt(arrOfStr[1]);
                        int data_year = Integer.parseInt(arrOfStr[2]);

                        // APRESENTA TODOS OS EVENTOS DO PRÓPRIO DIA OU FUTUROS
                        // SE SELECIONAR UM DIA SEM EVENTOS FUTUROS A LISTVIEW FICA VAZIA E NAO APRESENTA EVENTOS
                        if(data_year == year){
                            if(data_month > month + 1) {
                                String str = "EVENTO: " + data.getString(0) + "\nFIM: " + data_day + "/" + data_month + "/" + data_year;
                                list.add(str);
                            } else if (data_month == month + 1) {
                                if (data_day >= dayOfMonth) {
                                    String str = "EVENTO: " + data.getString(0) + "\nFIM: " + data_day + "/" + data_month + "/" + data_year;
                                    list.add(str);
                                }
                            }
                        } else if (data_year > year) {
                            String str = "EVENTO: " + data.getString(0) + "\nFIM: " + data_day + "/" + data_month + "/" + data_year;
                            list.add(str);
                        }
                        /*else {
                            String str = "De momento não tem eventos futuros.\n";
                            list.add(str);
                        }*/

                    }
                }

                ListAdapter listAdapter = new ArrayAdapter<>(VerCalendario.this, android.R.layout.simple_list_item_1, list);
                list_eventos.setAdapter(listAdapter);

            }
        });

    }

    private void makeText(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}