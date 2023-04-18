package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class SelectNeighbourEvent {

    /**
     * Neighbour to select
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public SelectNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
