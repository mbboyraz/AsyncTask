package com.androideduio.asynctask.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.androideduio.asynctask.R;
import com.androideduio.asynctask.adapter.KodluyoruzStudentAdapter;
import com.androideduio.asynctask.asynctask.KodluyoruzStudentTask;

import java.util.ArrayList;
import java.util.HashMap;

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    //View Component
    private RecyclerView recyclerView = null;
    private Button btnGetStudentList = null;
    private KodluyoruzStudentAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        initView();
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.activity_async_task_recyclerView);
        btnGetStudentList = (Button) findViewById(R.id.activity_async_task_btnGetStudentList);

        initEvent();
    }

    private void initEvent() {

        btnGetStudentList.setOnClickListener(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new KodluyoruzStudentAdapter(new ArrayList<HashMap<String, String>>());

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        String url = "https://api.myjson.com/bins/m6po9";

        new KodluyoruzStudentTask(this, adapter).execute(url);
    }
}
