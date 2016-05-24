package ru.bullfinchdev.criminalintentnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CrimeAdapter crimeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.crime_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimeList();
        crimeAdapter = new CrimeAdapter(crimes);
        recyclerView.setAdapter(crimeAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle, textViewDate;
        private CheckBox checkBox;
        private Crime crime;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                    startActivity(intent);
                }
            });
            textViewTitle =(TextView) itemView.findViewById(R.id.list_item_crime_title_textview);
            textViewDate =(TextView) itemView.findViewById(R.id.items_list_crime_date_textview);
            checkBox = (CheckBox) itemView.findViewById(R.id.item_list_checkbox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    crime.setIsSolved(isChecked);
                }
            });
        }

        public void bindCrime(Crime getCrime){
            crime = getCrime;
            textViewTitle.setText(crime.getTitle());
            textViewDate.setText(crime.getDate().toString());
            checkBox.setChecked(crime.getIsSolved());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> crimeList;

        public CrimeAdapter(List<Crime> crimes){
            crimeList = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.item_list_crime, parent, false);
            return new CrimeHolder(v);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = crimeList.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return crimeList.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
