package com.example.kj.dibbits;

/**
 * Created by KJ on 11/12/15.
 */


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DibbitLab {

    private static DibbitLab sDibbitLab;
    private List<Dibbit> mDibbits;



    private DibbitLab(Context context){ //CONSTRUCTOR
        mDibbits = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Dibbit dibbit = new Dibbit();
            dibbit.setTitle("Dibbit #" + i);
            dibbit.setDone((i % 2) == 0); //Every other one is solved
            mDibbits.add(dibbit);

        }
    }


    public List<Dibbit> getDibbits() {
        return mDibbits;
    }


    public Dibbit getCrime(UUID id){
        for (Dibbit dibbit : mDibbits){
            if(dibbit.getId().equals(id)){
                return dibbit;
            }
        }
        return null;
    }


    public static DibbitLab get(Context context) {

        if(sDibbitLab ==null){
            sDibbitLab = new DibbitLab(context);
        }
        return sDibbitLab;
    }



}