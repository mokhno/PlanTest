package com.example.plantest;

import com.example.plantest.Model.Launch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiSpace {
    @GET ("launches/past")
    Call<List<Launch>> getLaunches();
}
