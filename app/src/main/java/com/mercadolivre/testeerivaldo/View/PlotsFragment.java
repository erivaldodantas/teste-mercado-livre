package com.mercadolivre.testeerivaldo.View;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mercadolivre.testeerivaldo.R;
import com.mercadolivre.testeerivaldo.View.adapter.BanksAdapter;
import com.mercadolivre.testeerivaldo.View.adapter.PlotsAdapter;
import com.mercadolivre.testeerivaldo.ViewModel.BankViewModel;
import com.mercadolivre.testeerivaldo.ViewModel.PlotViewModel;
import com.mercadolivre.testeerivaldo.model.Bank;
import com.mercadolivre.testeerivaldo.model.Plot;
import com.mercadolivre.testeerivaldo.model.PlotBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlotsFragment extends Fragment {

    @BindView(R.id.rcvPlots)
    RecyclerView rcvPlots;

    @BindView(R.id.progressBar_plots)
    ProgressBar progressPlots;

    public PlotsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plots, container, false);

        ButterKnife.bind(this, view);

        rcvPlots.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcvPlots.getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        rcvPlots.addItemDecoration(dividerItemDecoration);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();
        observerViewModel(getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void observerViewModel(final Context context) {

        PlotViewModel model = ViewModelProviders.of(this).get(PlotViewModel.class);

        model.getPlots(
                ((MainActivity) getActivity()).getType(),
                ((MainActivity) getActivity()).getBank(),
                ((MainActivity) getActivity()).getValue()
        ).observe(this, (List<PlotBase> plotBase) -> {
                    Log.d("Observer", plotBase.toString());
                    rcvPlots.setAdapter(
                            new PlotsAdapter(
                                    context,
                                    plotBase.size()>0 ? plotBase.get(0).getPlots(): new ArrayList<Plot>(),
                                    (plot)-> this.setPlot(plot)
                            ));

                    progressPlots.setVisibility(View.GONE);
                    rcvPlots.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Selecione a parcela desejada", Toast.LENGTH_SHORT).show();
                }
        );
    }

    private void setPlot(Object plot){
        ((MainActivity) getActivity()).setPlot((Plot) plot);
    }

}
