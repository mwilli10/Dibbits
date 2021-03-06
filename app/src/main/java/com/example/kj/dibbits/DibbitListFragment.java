package com.example.kj.dibbits;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KJ on 11/11/15.
 */
public class DibbitListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int mCrimePosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dibbit_list, container,false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        DibbitLab dibbitLab = DibbitLab.get(getActivity());
        List<Dibbit> dibbits = dibbitLab.getDibbits();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(dibbits);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyItemChanged(mCrimePosition);
        }
    }


    // NEW CLASS
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Dibbit mDibbit;

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);

        }

        public void bindCrime(Dibbit dibbit) {
            mDibbit = dibbit;
            mTitleTextView.setText(mDibbit.getTitle());
            mDateTextView.setText(mDibbit.getDate().toString());
            mSolvedCheckBox.setChecked(mDibbit.isDone());
        }

        @Override
        public void onClick(View v) {
            mCrimePosition = getAdapterPosition();
            Intent intent = DibbitActivity.newIntent(getActivity(), mDibbit.getId());
            startActivity(intent);
        }


    }


    // NEW CLASS
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Dibbit> mDibbits;

        public CrimeAdapter(List<Dibbit> dibbits){
            mDibbits = dibbits;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent , int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_dibbit, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Dibbit dibbit = mDibbits.get(position);
            holder.bindCrime(dibbit);
        }

        @Override
        public int getItemCount() {
            return mDibbits.size();
        }
    }

}