package com.whitespace.sleepcycle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class BottomSheet extends BottomSheetDialogFragment {

    TextView title, hour, min, alarmTime, alarmAmPm, extraInfo;
    Calendar calendar;
    int calHourIn24, calHour, calMinute;
    String am_pm;
    LottieAnimationView alarm;

    View powerNapSelector, firstCycleSelector, secondCycleSelector, thirdCycleSelector, fourthCycleSelector, fifthCycleSelector, sixthCycleSelector;

    public BottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        title = view.findViewById(R.id.title);
        hour = view.findViewById(R.id.hour);
        min = view.findViewById(R.id.min);
        alarmTime = view.findViewById(R.id.alarm_time);
        alarmAmPm = view.findViewById(R.id.alarm_am_pm);
        extraInfo = view.findViewById(R.id.extra_info);
        alarm = view.findViewById(R.id.alarm_start);

        MainActivity activity = (MainActivity) getActivity();
        String sheetTitle = activity.getSheetTitle();
        int sheetHour = activity.getSheetHour();
        int sheetMin = activity.getSheetMin();
        powerNapSelector = activity.getPowerNapSelector();
        firstCycleSelector = activity.getFirstCycleSelector();
        secondCycleSelector = activity.getSecondCycleSelector();
        thirdCycleSelector = activity.getThirdCycleSelector();
        fourthCycleSelector = activity.getFourthCycleSelector();
        fifthCycleSelector = activity.getFifthCycleSelector();
        sixthCycleSelector = activity.getSixthCycleSelector();

        title.setText(sheetTitle);
        hour.setText("" + sheetHour);

        if (sheetMin == 0) {
            min.setText("00");
        } else {
            min.setText("" + sheetMin);
        }

        calendar = Calendar.getInstance();
        calHour = calendar.get(Calendar.HOUR_OF_DAY);
        calMinute = calendar.get(Calendar.MINUTE);

        calHourIn24 = calHour;

        if (sheetMin == 20) {
            calMinute = calMinute + sheetMin;
            extraInfo.setVisibility(View.INVISIBLE);
        } else {
            calMinute = calMinute + sheetMin + 15;
        }

        if (calMinute > 60) {
            calHour = calHour + sheetHour + 1;
            calMinute = calMinute - 60;
        } else {
            calHour = calHour + sheetHour;
        }

        if (calHour == 0) {
            calHour = calHour + 12;
            am_pm = "AM";
        } else if (calHour == 12) {
            am_pm = "PM";
        } else if (calHour > 24) {
            calHour = calHour - 24;
            am_pm = "AM";
        } else if (calHour > 12) {
            calHour = calHour - 12;
            am_pm = "PM";
        } else {
            am_pm = "AM";
        }

        alarmTime.setText(String.format("%02d:%02d", calHour, calMinute));
        alarmAmPm.setText(am_pm);

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent alarmActivity = new Intent(getContext(),AlarmActivity.class);
//                startActivity(alarmActivity);

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, calHour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, calMinute);
                if (am_pm.equals("PM")) {
                    intent.putExtra(AlarmClock.EXTRA_HOUR, calHour+12);
                }
                startActivity(intent);

//                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
//
//                } else {
//                    Toast.makeText(getContext(), "There is no app that support this action", Toast.LENGTH_SHORT).show();
//                }
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        powerNapSelector.setVisibility(View.GONE);
        firstCycleSelector.setVisibility(View.GONE);
        secondCycleSelector.setVisibility(View.GONE);
        thirdCycleSelector.setVisibility(View.GONE);
        fourthCycleSelector.setVisibility(View.GONE);
        fifthCycleSelector.setVisibility(View.GONE);
        sixthCycleSelector.setVisibility(View.GONE);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        powerNapSelector.setVisibility(View.GONE);
        firstCycleSelector.setVisibility(View.GONE);
        secondCycleSelector.setVisibility(View.GONE);
        thirdCycleSelector.setVisibility(View.GONE);
        fourthCycleSelector.setVisibility(View.GONE);
        fifthCycleSelector.setVisibility(View.GONE);
        sixthCycleSelector.setVisibility(View.GONE);
    }
}
