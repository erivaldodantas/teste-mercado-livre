package com.mercadolivre.testeerivaldo.View;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mercadolivre.testeerivaldo.R;
import com.mercadolivre.testeerivaldo.View.adapter.BanksAdapter;
import com.mercadolivre.testeerivaldo.ViewModel.BankViewModel;
import com.mercadolivre.testeerivaldo.model.Bank;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 3;

    @BindView(R.id.rcvBanks)
    RecyclerView rcvBanks;

    @BindView(R.id.progressBar_bank)
    ProgressBar progressBar;

    @BindView(R.id.txvNotBanks)
    TextView txvNotBanks;

    public BankFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bank, container, false);

        ButterKnife.bind(this, view);


        final Context context = view.getContext();


        rcvBanks.setLayoutManager(new GridLayoutManager(context, mColumnCount));

//        observerViewModel(context);

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
        BankViewModel model = ViewModelProviders.of(this).get(BankViewModel.class);

        model.getBanks(((MainActivity) getActivity()).getType()).observe(this, (List<Bank> banksList) -> {
                    rcvBanks.setAdapter(
                            new BanksAdapter(
                                    context,
                                    banksList,
                                    (bank)-> this.setBank(bank)
                            ));
                    progressBar.setVisibility(View.GONE);
                    rcvBanks.setVisibility(View.VISIBLE);
                    if(banksList.size()==0){
                        txvNotBanks.setVisibility(View.VISIBLE);
                    }else
                        Toast.makeText(getContext(), "Selecione o banco para o pagamento", Toast.LENGTH_SHORT).show();
                }
        );
    }

    private void setBank(Object bank){
        ((MainActivity) getActivity()).setBank((Bank) bank);
    }

}
