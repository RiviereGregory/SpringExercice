package fr.treeptik.springexercice.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.springexercice.dao.AuteurDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Auteur;
import fr.treeptik.springexercice.service.AuteurService;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Service
public class AuteurServiceImpl implements AuteurService {
	@Autowired
	private AuteurDAO auteurDAO;

	@Autowired
	private JDBCUtil jdbcUtil;

	private Connection connection;

	@Override
	public Auteur save(Auteur auteur) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			auteur = auteurDAO.save(auteur);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return auteur;
	}

	@Override
	public void remove(Auteur auteur) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			auteurDAO.remove(auteur);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Auteur> findAll() throws ServiceException {
		List<Auteur> list = new ArrayList<>();
		try {
			list = auteurDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Auteur findAuteurs(Integer id) throws ServiceException {
		Auteur auteur = new Auteur();
		try {
			auteur = auteurDAO.findAuteurs(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return auteur;
	}

}
