package ru.bullfinchdev.criminalintentnew;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab crimeLab;
    private List<Crime> crimeList;

    public static CrimeLab get(Context context){
        if(crimeLab == null){
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    private CrimeLab(Context context){
        crimeList = new ArrayList<>();
        for (int i = 0; i<50; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crimeList.add(crime);
        }
    }

    public List<Crime> getCrimeList(){
        return crimeList;
    }

    public Crime getCrime(UUID id){
        for (Crime crime : crimeList){
            if (crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
