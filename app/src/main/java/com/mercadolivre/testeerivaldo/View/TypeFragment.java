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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mercadolivre.testeerivaldo.R;
import com.mercadolivre.testeerivaldo.View.adapter.TypesAdapter;
import com.mercadolivre.testeerivaldo.ViewModel.TypeViewModel;
import com.mercadolivre.testeerivaldo.model.Type;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TypeFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 3;

    @BindView(R.id.rcvTypes) RecyclerView rcvTypes;
    @BindView(R.id.progressType) ProgressBar progressBar;

    public TypeFragment() {
        super();
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
        View view = inflater.inflate(R.layout.fragment_type, container, false);

        ButterKnife.bind(this, view);


        final Context context = view.getContext();


        rcvTypes.setLayoutManager(new GridLayoutManager(context, mColumnCount));


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();

        observerViewModel(getContext());


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void observerViewModel(final Context context) {
        TypeViewModel model = ViewModelProviders.of(this).get(TypeViewModel.class);

        model.getTypes().observe(this, (List<Type> typesList) -> {
                    rcvTypes.setAdapter(
                            new TypesAdapter(
                                    context,
                                    typesList.stream().filter(t -> t.getPaymentTypeId().equals("credit_card")).collect(Collectors.toList()),
                                    (type)-> this.setType((Type) type)));

                    progressBar.setVisibility(View.GONE);
                    rcvTypes.setVisibility(View.VISIBLE);

                    Toast.makeText(getContext(), "Selecione o tipo do pagamento", Toast.LENGTH_SHORT).show();
                }
        );
    }

    private void setType(Type type){

            ((MainActivity) getActivity()).setType(type);


    }

}
