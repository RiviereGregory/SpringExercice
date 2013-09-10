package fr.treeptik.springexercice.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.treeptik.springexercice.dao.CommentaireDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Commentaire;
import fr.treeptik.springexercice.service.CommentaireService;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Service
public class CommentaireServiceImpl implements CommentaireService {
	@Autowired
	@Qualifier("commentairesDAOFromFactory")
	private CommentaireDAO commentaireDAO;

	@Autowired
	private JDBCUtil jdbcUtil;

	private Connection connection;

	@Override
	public Commentaire save(Commentaire commentaire) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			commentaire = commentaireDAO.save(commentaire);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return commentaire;
	}

	@Override
	public void remove(Commentaire commentaire) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			commentaireDAO.remove(commentaire);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Commentaire> findAll() throws ServiceException {
		List<Commentaire> list = new ArrayList<>();
		try {
			list = commentaireDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Commentaire findCommentaires(Integer id) throws ServiceException {
		Commentaire commentaire = new Commentaire();
		try {
			commentaire = commentaireDAO.findCommentaires(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return commentaire;
	}

}
