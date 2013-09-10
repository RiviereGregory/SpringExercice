package fr.treeptik.springexercice.service;

import java.util.List;

import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Commentaire;

public interface CommentaireService {
	Commentaire save(Commentaire commentaire) throws ServiceException;

	void remove(Commentaire commentaire) throws ServiceException;

	List<Commentaire> findAll() throws ServiceException;

	Commentaire findCommentaires(Integer id) throws ServiceException;

}
