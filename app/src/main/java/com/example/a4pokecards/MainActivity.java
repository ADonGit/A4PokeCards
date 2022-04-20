package com.example.a4pokecards;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.squareup.picasso.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NetworkingService.NetworkingListener, CardDBService.dbListener
{

    Set setMain = new Set();
    Favorites favCards = new Favorites();


    NetworkingService networkingService;
    JsonService JSONService;

    TextView mainText;
    Spinner spinner;
    ImageView cardImageView;
    NumberPicker cardNumPicker;
    Button faveButton;

    int prevSpinSelection = 0;
    int spinPosition = 0;
    int numPickPos = 0;

    FragmentManager fm = getSupportFragmentManager();

    CardDBService dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkingService = new NetworkingService();
        networkingService.listener = this;

        JSONService = new JsonService();

        mainText = findViewById(R.id.testText);
        cardImageView = (ImageView) findViewById(R.id.cardImage);
        cardNumPicker = (NumberPicker) findViewById(R.id.cardNumber);
        cardNumPicker.setMinValue(numPickPos);

        faveButton = (Button) findViewById(R.id.favoriteButton);

        //DB Stuff
        dbManager = ((MyApp)getApplication()).dbManager;
        dbManager.getDB(this);


        //Spinner Creation
        spinner = (Spinner) findViewById(R.id.setSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sets_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //int spinPosition = 0;
                spinPosition = spinner.getSelectedItemPosition();

                //We check for a duplicate prevSpinSelection as we want to minimize API calls and data usage
                switch(spinPosition) {
                    case 0:
                        prevSpinSelection = 0;
                        mainText.setText("");
                        cardImageView.setImageResource(0);
                        setMain.cardList.clear();

                        cardNumPicker.setMinValue(0);
                        cardNumPicker.setMaxValue(0);
                        break;
                    case 1:
                        if (prevSpinSelection != 1) {
                            prevSpinSelection = 1;
                            getData("hgss1", "1");
                            cardNumPicker.setMinValue(1);
                            cardNumPicker.setMaxValue(123);
                            cardNumPicker.setValue(1);

                        }
                        break;
                    case 2:
                        if (prevSpinSelection != 2) {
                            prevSpinSelection = 2;
                            getData("hgss2", "1");
                            cardNumPicker.setMinValue(1);
                            cardNumPicker.setMaxValue(95);
                            cardNumPicker.setValue(1);
                        }
                        break;
                    case 3:
                        if (prevSpinSelection != 3) {
                            prevSpinSelection = 3;
                            getData("hgss3", "1");
                            cardNumPicker.setMinValue(1);
                            cardNumPicker.setMaxValue(90);
                            cardNumPicker.setValue(1);
                        }
                        break;
                    case 4:
                        if (prevSpinSelection != 4) {
                            prevSpinSelection = 4;
                            getData("hgss4", "1");
                            cardNumPicker.setMinValue(1);
                            cardNumPicker.setMaxValue(102);
                            cardNumPicker.setValue(1);
                        }
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //
            }
        });



        cardNumPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                String selectedValue = String.valueOf(numberPicker.getValue());

                spinPosition = spinner.getSelectedItemPosition();

                switch(spinPosition) {
                    case 0:
                        prevSpinSelection = 0;
                        mainText.setText("");
                        cardImageView.setImageResource(0);
                        setMain.cardList.clear();

                        cardNumPicker.setMinValue(0);
                        cardNumPicker.setMaxValue(0);
                        break;
                    case 1:
                        //if (prevSpinSelection != 1) {
                        prevSpinSelection = 1;
                        getData("hgss1", selectedValue);
                        cardNumPicker.setMinValue(1);
                        cardNumPicker.setMaxValue(123);

                        //}
                        break;
                    case 2:
                        // if (prevSpinSelection != 2) {
                        prevSpinSelection = 2;
                        getData("hgss2", selectedValue);
                        cardNumPicker.setMinValue(1);
                        cardNumPicker.setMaxValue(95);
                        //}
                        break;
                    case 3:
                        //if (prevSpinSelection != 3) {
                        prevSpinSelection = 3;
                        getData("hgss3", selectedValue);
                        cardNumPicker.setMinValue(1);
                        cardNumPicker.setMaxValue(90);
                        //}
                        break;
                    case 4:
                        //if (prevSpinSelection != 4) {
                        prevSpinSelection = 4;
                        getData("hgss4", selectedValue);
                        cardNumPicker.setMinValue(1);
                        cardNumPicker.setMaxValue(102);
                        // }
                        break;
                }

            }
        });

        //This button will add the current card to the favorites
        faveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setMain.cardList.size() >= 1) {
                    if (!hasCard(favCards.cardList, getCurrentCard())) {
                        favCards.setFavorite(getCurrentCard());

                        Toast.makeText(MainActivity.this, "Added to Favorites!", Toast.LENGTH_SHORT).show();

                        dbManager.listener = MainActivity.this;
                        dbManager.saveNewFavorite(getCurrentCard());


                    } else {
                        Toast.makeText(MainActivity.this, "Already added!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Select a Card!", Toast.LENGTH_LONG).show();
                }
            }
        });


        //Clicking on the card will open up the Full Card View
        cardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialog();
            }
        });
    }


    //Get Data from the API
    public void getData(String rSet, String rCard) {
        networkingService.getCardData(rSet, rCard);
    }

    //Option Menu creation and inflation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    //Option Menu selections
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case(R.id.viewFavorites):
                openFavorites();
                break;
        }
        return true;
    }


    //This is where our final JSON string is sent, we need to read the JSON here
    @Override
    public void dataListener(String JSONString) {
        Card cardEntry = new Card();

        cardEntry = JSONService.getCardData(JSONString);

        if (!setMain.cardList.contains(cardEntry)) {
            setMain.addCardToSet(cardEntry);
        }

        mainText.setText(
                cardEntry.getCardName() + "\nCard #: " + cardEntry.getCardNumber() + "\n" + cardEntry.getFlavorText()
        );

        Picasso.get().load(cardEntry.getSmallImageLink()).into(cardImageView);
    }



    public void OpenDialog() {
        FullCardDialog dia = FullCardDialog.newInstance(getCurrentCard());
        dia.show(fm, FullCardDialog.Tag);
    }

    public void openFavorites() {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }


    public Card getCurrentCard() {
        return setMain.cardList.get(setMain.cardList.size() - 1);
    }

    public boolean hasCard(ArrayList<Card> all, Card find) {
        boolean ret = false;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getCardID().equals(find.getCardID())) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    @Override
    public void onListReady(List<Card> faveList) {
        Log.d("list", faveList.size() + "");
    }

    @Override
    public void onAddDone() {
        dbManager.getAllCards();
    }

    @Override
    public void CardDeleted() {
        //
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("prevSpinSelection", prevSpinSelection);
        savedInstanceState.putInt("spinnerPosition", spinPosition);
        savedInstanceState.putInt("numberPickerPosition", cardNumPicker.getValue());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        prevSpinSelection = savedInstanceState.getInt("prevSpinSelection");
        spinPosition = savedInstanceState.getInt("spinnerPosition");
        numPickPos = savedInstanceState.getInt("numberPickerPosition");
    }

}


