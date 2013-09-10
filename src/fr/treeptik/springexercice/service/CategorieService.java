package fr.treeptik.springexercice.service;

import java.util.List;

import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Categorie;

public interface CategorieService {
	Categorie save(Categorie categorie) throws ServiceException;

	void remove(Categorie categorie) throws ServiceException;

	List<Categorie> findAll() throws ServiceException;

	Categorie findCategories(Integer id) throws ServiceException;

}
