package com.example.showtracker.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.showtracker.model.Anime;
import com.example.showtracker.viewmodel.AnimeListViewModel;

import java.util.List;

public class example extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        vm.getAnimes().observe(this, new Observer<List<Anime>>() {
//            @Override
//            public void onChanged(@Nullable List<Anime> anime) {
//
//            }
//        });
//
//
//        AnimeListViewModel vm = ViewModelProviders.of(this).get(AnimeListViewModel.class);
//        vm.getAnimes().observe(this,(List<Anime> anime)->{
//            System.out.println(anime);
//        });
//        View v = new View(this);
//        v.setOnClickListener((view)->{
//            System.out.println(view);
//        });
//
//
//
//        View.OnClickListener l  = new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                System.out.println("asdas");
//            }
//        };
//
//
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("asdas");
//            }
//        });
//
//
//        Thread tl
    }
}