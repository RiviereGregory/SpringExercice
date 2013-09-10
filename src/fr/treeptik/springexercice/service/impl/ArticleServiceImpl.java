package fr.treeptik.springexercice.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.springexercice.dao.ArticleDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Article;
import fr.treeptik.springexercice.service.ArticleService;
import fr.treeptik.springexercice.utils.AppMailSender;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDAO articleDAO;

	@Autowired
	private JDBCUtil jdbcUtil;
	
	@Autowired
	private AppMailSender appMailSender;

	private Connection connection;

	@Override
	public Article save(Article article) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			article = articleDAO.save(article);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return article;

	}

	@Override
	public void remove(Article article) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			articleDAO.remove(article);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Article> findAll() throws ServiceException {
		List<Article> list = new ArrayList<>();
		try {
			list = articleDAO.findAll();
			appMailSender.sendEmail("greg13@laposte.net", "Test findAll Articles", "Test envoi de message dans le service");
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Article findArticles(Integer id) throws ServiceException {
		Article article = new Article();
		try {
			article = articleDAO.findArticles(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return article;
	}

}
