package com.mpheh.interfaces;

import com.mpheh.beans.Menu;

public interface GestionMenu {

	public Menu ajouterMenu(Menu menu);
	public void supprimerMenu(Menu menu);
	public void modifierMenu(Menu menu);
	public void publierMenu(Menu menu);
	public void depublierMenu(Menu menu);
	public void afficherMenu(Menu menu);
}
