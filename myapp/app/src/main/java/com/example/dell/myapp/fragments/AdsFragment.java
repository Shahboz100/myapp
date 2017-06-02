package com.example.dell.myapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.myapp.R;
import com.example.dell.myapp.adapters.AdsAdapter;
import com.example.dell.myapp.data.AdsModel;

import java.util.ArrayList;
import java.util.List;


public class AdsFragment extends Fragment {


    RecyclerView driversRecyclerView;
    AdsAdapter adsAdapter;


    public AdsFragment() {
    }


    public static AdsFragment newInstance() {
        AdsFragment fragment = new AdsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adsAdapter = new AdsAdapter(clickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        driversRecyclerView = (RecyclerView) view.findViewById(R.id.listview);
        driversRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        driversRecyclerView.setAdapter(adsAdapter);
        List<AdsModel> driverModels = new ArrayList<>();
        driverModels.add(new AdsModel("Красноярск", "Новосибирск", "Рефрижатор","10.11.17",20000));
        driverModels.add(new AdsModel("Новосибирск", "Красноярск", "Тент", "11.11.17",10000));
        driverModels.add(new AdsModel("tg43", "4512d1d6", "123ed","11.11.11",5000));
        adsAdapter.setDriverList(driverModels);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), v.getTag().toString(), Toast.LENGTH_SHORT).show();
        }
    };


}
