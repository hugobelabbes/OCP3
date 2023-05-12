package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.Logger;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

//A commenter, faire un cours sur :
//-Comment Bind un element
//-Comment bind un element image (glide)
//-Refaire le point sur l'activity profile
//-Faire des tests ui

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.user_avatar)
    public ImageView user_avatar;

    @BindView(R.id.user_name)
    public TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Long id = intent.getLongExtra("voisin_id", 0);
        NeighbourApiService service = DI.getNeighbourApiService();
        Neighbour neighbour = service.getByID(id);

        System.out.println(neighbour.getName());

        //Bind les elements ui
        ButterKnife.bind(this);
        Glide.with(user_avatar.getContext()).load(neighbour.getAvatarUrl()).centerCrop().into(user_avatar);
        user_name.setText(neighbour.getName());
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