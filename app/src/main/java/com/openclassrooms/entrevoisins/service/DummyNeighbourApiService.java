package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getByID(Long id){
        for (Neighbour n : neighbours) {
            if (n.getId() == id){
                return n;
            }
        }
        return null;
    }
    //Méthode de création et de listage des users favos
    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favorites = new ArrayList<>();
        for (Neighbour n : neighbours) {
            if (n.isFavorite()){
                favorites.add(n);
            }
        }
        return favorites;
    }

    @Override
    public void setIsFavoritNeighbour(Long id, Boolean isFavorit) {
        Neighbour n = getByID(id);
        if (n != null){
            n.setFavorite(isFavorit);
        }
    }


}
