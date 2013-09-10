package fr.treeptik.springexercice.dao;

import java.util.Date;
import java.util.List;

import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Article;

public interface ArticleDAO {
	Article save(Article article) throws DAOException;

	void remove(Article article) throws DAOException;

	List<Article> findAll() throws DAOException;

	Article findArticles(Integer id) throws DAOException;

	List<Article> findArticles(Date dateDebut, Date dateFin) throws DAOException;

}
