package ru.bullfinchdev.criminalintentnew;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class CrimeEditFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";

    private Crime crime;
    private EditText crimeTitleEditText;
    private Button crimeDateEditButton;
    private CheckBox crimeIsSolvedCheckBox;

    public static CrimeEditFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, id);
        CrimeEditFragment fragment  = new CrimeEditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        crime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_edit,container,false);

        crimeTitleEditText = (EditText) v.findViewById(R.id.crime_title_edit_text);
        crimeDateEditButton = (Button) v.findViewById(R.id.fragment_crime_edit_date_button);
        crimeIsSolvedCheckBox = (CheckBox) v.findViewById(R.id.fragment_crime_edit_solved_checkbox);

        crimeTitleEditText.setText(crime.getTitle());
        crimeDateEditButton.setText(crime.getDate().toString());
        crimeIsSolvedCheckBox.setChecked(crime.getIsSolved());

        crimeIsSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setIsSolved(isChecked);
            }
        });

        crimeTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }
}
