package com.openclassrooms.entrevoisins.ui.neighbour_list;

import static android.icu.lang.UCharacter.toLowerCase;
import static android.icu.lang.UCharacter.toUpperCase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.user_avatar)
    public ImageView user_avatar;

    @BindView(R.id.user_name)
    public TextView user_name;

    @BindView(R.id.adress_label)
    public TextView adress_label;

    @BindView(R.id.phone_label)
    public TextView phone_label;

    @BindView(R.id.socials_label)
    public TextView socials_label;

    @BindView(R.id.about_label)
    public TextView about_label;

    @BindView(R.id.fav_button)
    public FloatingActionButton fav_button;

    @BindView(R.id.return_button)
    public ImageView return_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        Long id = intent.getLongExtra("voisin_id", 0);
        NeighbourApiService service = DI.getNeighbourApiService();
        Neighbour neighbour = service.getByID(id);

        //Bind les elements ui
        ButterKnife.bind(this);
        Glide.with(user_avatar.getContext()).load(neighbour.getAvatarUrl()).centerCrop().into(user_avatar);
        //Username
        user_name.setText(neighbour.getName());
        //Adresse
        adress_label.setText(neighbour.getAddress());
        //Tel
        phone_label.setText(neighbour.getPhoneNumber());
        //Socials
        socials_label.setText(getString(R.string.socials) + neighbour.getName().toLowerCase());
        //About
        about_label.setText(neighbour.getAboutMe());

        //Setting du retour en arrière au clic sur le bouton retour
        return_button.setOnClickListener(v -> finish());
        //finish() -> Ferme l'activité en cours

        //Setting des favoris au clic sur le bouton
        fav_button.setOnClickListener(v ->{
            //Appel de la méthode setIsFavoritNeighbour pour lui attribuer l'inverse de ce qu'il a (!neighbour.isFavorite())
            service.setIsFavoritNeighbour(neighbour.getId(), !neighbour.isFavorite());
            //Appel de la méthode de modification de l'icone favo en fonction de l'état de isFavorite
            setNeighbourFavoriteIconIfNeeded(neighbour.isFavorite());

        });
        //Appel de la méthode de modification de l'icone favo en fonction de l'état de isFavorite
        setNeighbourFavoriteIconIfNeeded(neighbour.isFavorite());



    }
    //Changement de l'icone Favo si nécessaire
    public void setNeighbourFavoriteIconIfNeeded(Boolean isFavorite) {
            fav_button.setImageResource(
                    !isFavorite ?
                    R.drawable.ic_star_border_white_24dp :
                    R.drawable.ic_star_white_24dp
            );
    }

    public static void navigate(FragmentActivity activity, Neighbour neighbour){
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra("voisin_id", neighbour.getId());
        // autre méthode : intent.putExtra("Voisin", (Serializable) neighbour ); (Opti java)
        // autre méthode : intent.putExtra("Voisin", (Parcelable) neighbour ); (Opti android)
        ActivityCompat.startActivity(activity, intent, null);
    }
}