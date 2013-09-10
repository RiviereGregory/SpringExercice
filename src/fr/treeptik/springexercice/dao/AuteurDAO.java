package fr.treeptik.springexercice.dao;

import java.util.List;
import java.util.Map;

import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Auteur;

public interface AuteurDAO {
	Auteur save(Auteur auteur) throws DAOException;

	void remove(Auteur auteur) throws DAOException;

	List<Auteur> findAll() throws DAOException;

	Auteur findAuteurs(Integer id) throws DAOException;

	Map<Auteur, Integer> findNombreArticlesEnLigne() throws DAOException;

	List<Auteur> findArticlesMaxCommente() throws DAOException;

}
