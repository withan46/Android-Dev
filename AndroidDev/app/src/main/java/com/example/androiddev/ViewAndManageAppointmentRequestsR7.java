package com.example.androiddev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewAndManageAppointmentRequestsR7 extends AppCompatActivity {

    private String ip;
    private String clinicVatNumber;
    ArrayList<Appointment> appointments = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.ip = "192.168.2.4";
        this.clinicVatNumber = "10000";


        //Creating an OkhttpHandler object to manage any communication with the database.

        OkHttpHandler handler = new OkHttpHandler();
        try {
            String url = "https://" + ip +"/flexFitDBServices/fetchAppointmentDetailsR7.php?clinic_vat_number=10000";
            handler.fetchData(appointments,url);
        }catch(Exception e){
            e.printStackTrace();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_manage_appointment_requests_r7);

        // Fetching the parent of the appointment container, inside which an appointment's information will be visible.
        LinearLayout appointmentContainerParent = findViewById(R.id.patientsParent);

        for(int i = 0 ; i < appointments.size() ; i++){

            // Getting the layout needed, in order to add appointment containers inside the currently visible layout.
            LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout appointmentContainer = (LinearLayout) inflater.inflate(R.layout.appointment_container_layout_r7, appointmentContainerParent, false);





            // fetching the elements that vary and change dynamically based on the appointment.
            TextView name = appointmentContainer.findViewById(R.id.patientName);
            TextView date = appointmentContainer.findViewById(R.id.appDate);
            TextView time = appointmentContainer.findViewById(R.id.appTime);
            TextView note = appointmentContainer.findViewById(R.id.note);


            // getting all the necessary data and adding it to the appropriate elements.
            Appointment anAppointment = appointments.get(i);
            // setting the container's id as the appointment id. It is necessary in order to be able to perform any actions regarding that appointment, before removing it from the list.
            appointmentContainer.setTag(anAppointment.getAppointment_id());
            //Adding the necessary information inside the appointment container.
            name.setText(anAppointment.getPatient_name());
            date.setText(anAppointment.getDate());
            time.setText(anAppointment.getTime());
            note.setText(anAppointment.getTos());


            // Adding the layout containing an appointment's information to its parent layout.

            appointmentContainerParent.addView(appointmentContainer);

            //Getting the accept and deny appointment buttons, in order to add onClickListeners.
            Button acceptButton = appointmentContainer.findViewById(R.id.acceptButton);
            Button denyButton = appointmentContainer.findViewById(R.id.denyButton);

            acceptButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String url = "https://" + ip + "/flexFitDBServices/acceptAppointmentR7.php";
                    // Getting the patientBox layout (Buttons are each inside a relative layout, which is inside a linear layout, which is inside the patientBox)
                    LinearLayout parent = (LinearLayout) acceptButton.getParent().getParent().getParent();
                    //Calling the onAcceptClicked method of the handler object that changes the state of the appointment from false to true.
                    handler.onAcceptClicked((int) parent.getTag(), url );

                    //Removing the appointment container after the aforementioned action has been completed.
                    parent.setVisibility(View.GONE);
                }
            });

            denyButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String url = "https://" + ip + "/flexFitDBServices/denyAppointmentR7.php";
                    // Getting the patientBox layout (Buttons are each inside a relative layout, which is inside a linear layout, which is inside the patientBox)
                    LinearLayout parent = (LinearLayout) denyButton.getParent().getParent().getParent();
                    //Calling the onDenyClicked method of the handler object that deletes the appointment from the database.
                    handler.onDenyClicked((int) parent.getTag(), url);
                    //Removing the appointment container after the aforementioned action has been completed.
                    parent.setVisibility(View.GONE);
                }
            });

        }

    }


}