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

    /**
     * Get my Neighbour id
     * @param id
     */
    Neighbour getByID(Long id);

    /**
     * Get all my Favorites Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavorites();

    /**
     * Set a neighbour as favorit or not
     * @param id
     * @param isFavorit
     */
    void setIsFavoritNeighbour(Long id, Boolean isFavorit);


}
