package com.example.a4pokecards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.squareup.picasso.Picasso;

public class FullCardDialog extends DialogFragment {

    public static final String Tag = "Full_Card";
    private Card fullCard = new Card();


    public static FullCardDialog newInstance(Card displayCard) {
        FullCardDialog fragment = new FullCardDialog();
        Bundle args = new Bundle();

        args.putString("cardName", displayCard.getCardName());
        args.putString("cardID", displayCard.getCardID());
        args.putString("cardHP", displayCard.getHP());
        args.putString("cardNumber", displayCard.getCardNumber());
        args.putString("cardLargeImage", displayCard.getLargeImageLink());
        args.putInt("printedTotal", displayCard.getPrintedTotal());
        args.putString("hostSetSeries", displayCard.getHostSetSeries());
        args.putString("hostSetName", displayCard.getHostSetName());
        args.putString("flavorText", displayCard.getFlavorText());


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fullCard.setCardName(getArguments().getString("cardName"));
            fullCard.setCardID(getArguments().getString("cardID"));
            fullCard.setHP(getArguments().getString("cardHP"));
            fullCard.setCardNumber(getArguments().getString("cardNumber"));
            fullCard.setLargeImageLink(getArguments().getString("cardLargeImage"));
            fullCard.setPrintedTotal(getArguments().getInt("printedTotal"));
            fullCard.setHostSetSeries(getArguments().getString("hostSetSeries"));
            fullCard.setHostSetName(getArguments().getString("hostSetName"));
            fullCard.setFlavorText(getArguments().getString("flavorText"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //outState.putInt("enteredSize", R.id.diaTextName);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            //int enteredSize = savedInstanceState.getInt("enteredSize");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_dialog, container, false);
        TextView titleText = v.findViewById(R.id.diaTextName);

        ImageView imageView = v.findViewById(R.id.diaCardImage);

        //All our data entries
        TextView viewCardNum = v.findViewById(R.id.numberData);
        TextView viewHP = v.findViewById(R.id.hpData);
        TextView viewSeries = v.findViewById(R.id.seriesData);
        TextView viewHost = v.findViewById(R.id.setData);
        TextView viewFlavor = v.findViewById(R.id.diaFlavor);

        //Now we set everything
        titleText.setText(fullCard.getCardName());
        viewCardNum.setText(fullCard.getCardNumber() + "/" + fullCard.getPrintedTotal());
        viewHP.setText(fullCard.getHP());
        viewSeries.setText(fullCard.getHostSetSeries());
        viewHost.setText(fullCard.getHostSetName());
        viewFlavor.setText(fullCard.getFlavorText());

        Picasso.get().load(fullCard.getLargeImageLink()).into(imageView);

        Button ok = v.findViewById(R.id.okBut);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return v;
    }
}