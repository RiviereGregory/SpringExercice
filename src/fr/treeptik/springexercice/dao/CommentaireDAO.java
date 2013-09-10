package fr.treeptik.springexercice.dao;

import java.util.List;

import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Commentaire;

public interface CommentaireDAO {
	Commentaire save(Commentaire commentaire) throws DAOException;

	void remove(Commentaire commentaire) throws DAOException;

	List<Commentaire> findAll() throws DAOException;

	Commentaire findCommentaires(Integer id) throws DAOException;
}
