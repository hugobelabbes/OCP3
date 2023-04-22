package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.Logger;

import java.io.Serializable;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Long id = intent.getLongExtra("voisin_id", 0);
        NeighbourApiService service = DI.getNeighbourApiService();
        //Rajouter une méthode dans le NeighbourApiService pour récupérer un voisin par son ID
        //Utiliser les BindView et les SetText
    }

    public static void navigate(FragmentActivity activity, Neighbour neighbour){
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra("voisin_id", neighbour.getId());
        // autre méthode : intent.putExtra("Voisin", (Serializable) neighbour ); (Opti java)
        // autre méthode : intent.putExtra("Voisin", (Parcelable) neighbour ); (Opti android)
        ActivityCompat.startActivity(activity, intent, null);
    }
}