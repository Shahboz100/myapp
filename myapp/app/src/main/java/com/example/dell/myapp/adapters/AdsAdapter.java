package com.example.dell.myapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.myapp.R;
import com.example.dell.myapp.data.AdsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahb on 29.05.2017.
 */

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.adsHolder> {

    List<AdsModel> adsModels = new ArrayList<>();
    View.OnClickListener listener;

    public AdsAdapter(View.OnClickListener listener) {
        super();
        this.listener = listener;
    }

    public void setDriverList(List<AdsModel> driverModels) {
        this.adsModels = driverModels;
        notifyDataSetChanged();
    }

    @Override
    public adsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_list_item, parent, false);
        adsHolder vh = new adsHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(adsHolder holder, int position) {
        holder.bindView(adsModels.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return adsModels.size();
    }

    public static class adsHolder extends RecyclerView.ViewHolder {
        TextView loadplace = (TextView) itemView.findViewById(R.id.loadplace);
        TextView uploadplace = (TextView) itemView.findViewById(R.id.uploadplace);
        TextView type = (TextView) itemView.findViewById(R.id.type);
        TextView loaddate =(TextView) itemView.findViewById(R.id.loaddate);
        TextView cena = (TextView) itemView.findViewById(R.id.cena);
        public adsHolder(View itemView) {
            super(itemView);
        }

        private void bindView(AdsModel model, View.OnClickListener listener) {
            itemView.setTag(model.loadplace);
            itemView.setOnClickListener(listener);
            loadplace.setText(model.loadplace);
            uploadplace.setText(model.uploadplace);
            type.setText(model.type);
            loaddate.setText(model.loaddate);
            cena.setText(Integer.toString(model.cena));
        }
    }


}
