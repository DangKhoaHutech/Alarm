package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnDunglai, btnDatgio; TextView txtHienThi; TimePicker time;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set id
        btnDunglai = (Button)findViewById(R.id.btnDungLai);
        btnDatgio= (Button)findViewById(R.id.btnDatGio);
        txtHienThi= (TextView)findViewById(R.id.txtHienThi);
        time = (TimePicker)findViewById(R.id.time);
        // truy cập hệ thống báo động
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        final Intent intent= new Intent(MainActivity.this,Alarm.class);
        // lấy thời gian
        calendar = Calendar.getInstance();
        // bắt sự kiện của button
        btnDatgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,time.getCurrentHour());
                calendar.set(Calendar.MINUTE,time.getCurrentMinute());

                int gio= time.getCurrentHour();
                int phut= time.getCurrentMinute();

                String string_gio=String.valueOf(gio);
                String string_phut=String.valueOf(phut);

                if(phut<10)
                    string_phut="0"+String.valueOf(phut);
                // PendingIntent tồn tại khi đã đóng ứng dụng

                intent.putExtra("extra","on");

                pendingIntent= PendingIntent.getBroadcast(MainActivity.this,
                        0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                txtHienThi.setText("Đã đặt "+string_gio+" giờ "+string_phut+" phút");
            }
        });
        btnDunglai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtHienThi.setText("Đã dừng lại !");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);


            }
        });
    }
}
