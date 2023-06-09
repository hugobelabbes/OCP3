package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    //Rajouter commentaire ici basé sur le même modèle que ci dessus
    Neighbour getByID(Long id);

    //Récupérer liste des users favo
    List<Neighbour> getFavorites();

    void setIsFavoritNeighbour(Long id, Boolean isFavorit);


}
