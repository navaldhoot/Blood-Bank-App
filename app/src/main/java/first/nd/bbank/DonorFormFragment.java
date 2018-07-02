package first.nd.bbank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import static first.nd.bbank.MainActivity.database;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonorFormFragment extends Fragment {
    Spinner cityChoice;
    Spinner groupChoice;

    EditText Name;
    EditText Mobile;

    Button Save;

    ProgressBar progressBar;

    @Override
    public void onStop() {
        super.onStop();
    }

    public DonorFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_donor_form, container, false);

        cityChoice = (Spinner) rootView.findViewById(R.id.dropdownCity);

        String[] citis = new String[]{"Bhopal", "Dewas", "Indore", "Gwalior", "Jabalpur", "Ratlam", "Khandwa", "Sagar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, citis);
        cityChoice.setAdapter(adapter);


        groupChoice = (Spinner) rootView.findViewById(R.id.dropdownGroup);
        String[] group = new String[]{"O+", "O-", "A+", "B+", "A-", "B-", "AB+", "AB-"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, group);
        groupChoice.setAdapter(adapter1);

        Name = (EditText) rootView.findViewById(R.id.edt_name);
        Mobile = (EditText) rootView.findViewById(R.id.edt_mobileNumber);
        Save = (Button) rootView.findViewById(R.id.btn_saveDonor);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String city = cityChoice.getSelectedItem().toString();
                String group = groupChoice.getSelectedItem().toString();
                String mobile = Mobile.getText().toString();
                //    String lat = MainActivity.lat.toString();
                //  String lng = MainActivity.lng.toString();

                if (name.length() == 0) {
                    Toast.makeText(getActivity(), "Enter the Name", Toast.LENGTH_SHORT).show();
                } else if (mobile.length() != 10) {
                    Toast.makeText(getActivity(), "Enter Correct Mobile Number", Toast.LENGTH_SHORT).show();
                } else {

                    Donor donor = new Donor(name, mobile, group, city);//,lat, lng);
                    DatabaseReference myRef = database.getReference("donors");
                    myRef.child(city).child(group).push().setValue(donor);


                    Toast.makeText(getActivity(), "Donor Added Successfully", Toast.LENGTH_SHORT).show();


                }
            }
        });

        return rootView;
    }

    }


