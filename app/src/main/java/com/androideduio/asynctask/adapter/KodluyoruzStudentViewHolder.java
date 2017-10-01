package com.androideduio.asynctask.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.androideduio.asynctask.R;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 24.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class KodluyoruzStudentViewHolder extends RecyclerView.ViewHolder {

    TextView txtName = null, txtEmail = null, txtPhone;

    KodluyoruzStudentViewHolder(View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.adapter_item_kodluyoruz_student_txtName);
        txtEmail = itemView.findViewById(R.id.adapter_item_kodluyoruz_student_txtEmail);
        txtPhone = itemView.findViewById(R.id.adapter_item_kodluyoruz_student_txtPhone);
    }
}
