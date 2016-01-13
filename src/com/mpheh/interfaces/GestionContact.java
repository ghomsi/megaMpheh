package com.mpheh.interfaces;

import com.mpheh.beans.Contact;

public interface GestionContact {

	public Contact ajouterContact(Contact contact);
	public void supprimerContact(Contact contact);
	public void modifierContact(Contact contact);
	public void afficher(Contact contact);
}
