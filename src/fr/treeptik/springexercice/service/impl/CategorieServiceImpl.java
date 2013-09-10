package fr.treeptik.springexercice.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.springexercice.dao.CategorieDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.exception.ServiceException;
import fr.treeptik.springexercice.model.Categorie;
import fr.treeptik.springexercice.service.CategorieService;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Service
public class CategorieServiceImpl implements CategorieService {
	@Autowired
	private CategorieDAO categorieDAO;

	@Autowired
	private JDBCUtil jdbcUtil;

	private Connection connection;

	@Override
	public Categorie save(Categorie categorie) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			categorie = categorieDAO.save(categorie);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return categorie;
	}

	@Override
	public void remove(Categorie categorie) throws ServiceException {
		try {
			connection = jdbcUtil.getConnection();
			categorieDAO.remove(categorie);
			connection.commit();
		} catch (DAOException | SQLException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Categorie> findAll() throws ServiceException {
		List<Categorie> list = new ArrayList<>();
		try {
			list = categorieDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return list;
	}

	@Override
	public Categorie findCategories(Integer id) throws ServiceException {
		Categorie categorie = new Categorie();
		try {
			categorie = categorieDAO.findCategories(id);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
		return categorie;
	}

}
