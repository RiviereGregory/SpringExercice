package fr.treeptik.springexercice.service;

import java.util.List;

import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Auteur;

public interface AuteurService {
	Auteur save(Auteur auteur) throws ServiceException;

	void remove(Auteur auteur) throws ServiceException;

	List<Auteur> findAll() throws ServiceException;
	
	Auteur findAuteurs(Integer id) throws ServiceException;

}
