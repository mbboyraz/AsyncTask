package com.androideduio.asynctask.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androideduio.asynctask.R;

import java.util.ArrayList;
import java.util.HashMap;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 24.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class KodluyoruzStudentAdapter extends RecyclerView.Adapter<KodluyoruzStudentViewHolder> {

    private ArrayList<HashMap<String, String>> contactList = null;

    public KodluyoruzStudentAdapter(ArrayList<HashMap<String, String>> contactList) {
        this.contactList = contactList;
    }

    @Override
    public KodluyoruzStudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_kodluyoruz_student, parent, false);

        return new KodluyoruzStudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(KodluyoruzStudentViewHolder holder, int position) {

        HashMap<String, String> contact = contactList.get(position);

        holder.txtName.setText(contact.get("name"));
        holder.txtEmail.setText(contact.get("email"));
        holder.txtPhone.setText(contact.get("phone"));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setListAndUpdateList(ArrayList<HashMap<String, String>> contactList) {

        this.contactList = contactList;
        notifyDataSetChanged();
    }
}