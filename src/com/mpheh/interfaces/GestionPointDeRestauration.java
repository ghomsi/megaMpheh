package com.mpheh.interfaces;

import com.mpheh.beans.PointDeRestauration;

public interface GestionPointDeRestauration {

	public PointDeRestauration ajouterPDR(PointDeRestauration pointDR);
	public void supprimerPDR(PointDeRestauration pointDR);
	public void modifierPDR(PointDeRestauration pointDR);
	public void afficherPDR(PointDeRestauration pointDR);
}
