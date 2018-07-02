package first.nd.bbank;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NeedBloodFragment extends Fragment {

    Spinner groupChoice;
    Spinner cityChoice;
    Button need;

    public NeedBloodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_need_blood, container, false);
        cityChoice = (Spinner) rootView.findViewById(R.id.needCity);
        groupChoice = (Spinner) rootView.findViewById(R.id.needBlood);
        need = (Button) rootView.findViewById(R.id.startSearch);

        String[] citis = new String[]{"Bhopal","Dewas", "Indore", "Gwalior","Jabalpur", "Ratlam", "Khandwa", "Sagar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, citis);
        cityChoice.setAdapter(adapter);


        String[] group = new String[]{"O+","O-", "A+", "B+","A-", "B-", "AB+", "AB-"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, group);
        groupChoice.setAdapter(adapter1);


        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group = groupChoice.getSelectedItem().toString();
                String city = cityChoice.getSelectedItem().toString();

                Intent intent = new Intent(getActivity(), DonorList.class);
                intent.putExtra("group", group);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });




        return rootView;
    }

}
