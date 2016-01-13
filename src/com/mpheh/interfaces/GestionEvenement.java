package com.mpheh.interfaces;

import com.mpheh.beans.Reservation;

public interface GestionEvenement {

	public Reservation ajouterReservation(Reservation reservation);
	public void supprimerReservation(Reservation reservation);
	public void modifierReservation(Reservation reservation);
	public void afficherResrvation(Reservation reservation);
}
