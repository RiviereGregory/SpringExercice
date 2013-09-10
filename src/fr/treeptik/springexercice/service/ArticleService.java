package fr.treeptik.springexercice.service;

import java.util.List;

import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Article;

public interface ArticleService {
	Article save(Article article) throws ServiceException;

	void remove(Article article) throws ServiceException;

	List<Article> findAll() throws ServiceException;

	Article findArticles(Integer id) throws ServiceException;
}
