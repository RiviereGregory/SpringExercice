package fr.treeptik.springexercice.dao;

import java.util.List;
import java.util.Map;

import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Categorie;

public interface CategorieDAO {
	Categorie save(Categorie categorie) throws DAOException;

	void remove(Categorie categorie) throws DAOException;

	List<Categorie> findAll() throws DAOException;

	Categorie findCategories(Integer id) throws DAOException;

	List<Categorie> findNotArticle() throws DAOException;

	List<Categorie> findMaxArticles() throws DAOException;

	Map<Categorie,Integer> findNombreArticles() throws DAOException;
}
