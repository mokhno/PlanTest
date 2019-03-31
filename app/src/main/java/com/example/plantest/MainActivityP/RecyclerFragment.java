package com.example.plantest.MainActivityP;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.plantest.ApiUtils;
import com.example.plantest.Model.Launch;
import com.example.plantest.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RecyclerFragment extends Fragment {
    public static final String TAG = "tagg";

    private RecyclerView recycler;
    private final LaunchAdaper launchAdaper= new LaunchAdaper();
    private List<Launch> launches;
    private LaunchAdaper.OnItemClickListaner mListaner;

    private ProgressBar progressBar;
    public static RecyclerFragment newInstance() {

      return new RecyclerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LaunchAdaper.OnItemClickListaner){
            mListaner = (LaunchAdaper.OnItemClickListaner) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recycler = view.findViewById(R.id.recycler);
        progressBar = view.findViewById(R.id.progressBar);
recycler.setVisibility(View.GONE);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(launchAdaper);
        getAllLaunches();
        launchAdaper.setListaener(mListaner);

    }

    public void getAllLaunches() {
        ApiUtils.getApiService().getLaunches().enqueue(new retrofit2.Callback<List<Launch>>() {


            @Override
            public void onResponse(Call<List<Launch>> call, Response<List<Launch>> response) {
                recycler.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                launches = response.body();

                launchAdaper.addData(launches);
                Log.d(TAG, "onResponse: " + launches);
                Log.d(TAG, "resp: " + call.request());
            }

            @Override
            public void onFailure(Call<List<Launch>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + call.request());
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    @Override
    public void onDetach() {
        mListaner = null;
        super.onDetach();
    }
}
