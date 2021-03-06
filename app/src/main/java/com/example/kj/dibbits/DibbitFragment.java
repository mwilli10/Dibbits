package com.example.kj.dibbits;


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

import java.text.DateFormat;
import java.util.UUID;


/**
 * Created by KJ on 11/11/15.
 */
public class DibbitFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private Dibbit mDibbit;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;


    public static DibbitFragment newInstance(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        DibbitFragment fragment = new DibbitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mDibbit = DibbitLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dibbit,container,false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mDibbit.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                //nothing here
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                mDibbit.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //nothing here either
            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date);

        mDateButton.setText(DateFormat.getDateInstance().format(mDibbit.getDate())); //return DateFormat.getDateInstance().format(date);
        mDateButton.setEnabled(false);
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mDibbit.isDone());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Set the crime's solved property
                mDibbit.setDone(isChecked);
            }
        });

        return v;
    }

}