package fr.treeptik.springexercice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.treeptik.springexercice.dao.CategorieDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Categorie;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Repository
public class CategorieJDBCImpl implements CategorieDAO {

	@Autowired
	private JDBCUtil jdbcUtil;

	@Override
	public Categorie save(Categorie categorie) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"INSERT INTO Categories " + " (cat_name, cat_url) " + "VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, categorie.getName());
			prepareStatement.setString(2, categorie.getUrl());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			categorie.setId(keys.getInt(1));

		} catch (SQLException e) {

			throw new DAOException(e.getMessage(), e.getCause());

		}

		return categorie;
	}

	@Override
	public void remove(Categorie categorie) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Categories WHERE cat_id=?");
			prepareStatement.setInt(1, categorie.getId());
			int nbModif = prepareStatement.executeUpdate();
			if (nbModif != 1) {
				throw new DAOException("Categories inconnu id = " + categorie.getId());
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Categorie> findAll() throws DAOException {
		Connection connection;
		List<Categorie> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Categories ");
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(resultSet.getInt("cat_id"));
				categorie.setName(resultSet.getString("cat_name"));
				categorie.setUrl(resultSet.getString("cat_url"));

				list.add(categorie);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public Categorie findCategories(Integer id) throws DAOException {
		Connection connection;
		Categorie categorie = new Categorie();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Categories WHERE cat_id=?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();

			resultSet.next();
			categorie.setId(resultSet.getInt("cat_id"));
			categorie.setName(resultSet.getString("cat_name"));
			categorie.setUrl(resultSet.getString("cat_url"));

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return categorie;
	}

	@Override
	public List<Categorie> findNotArticle() throws DAOException {

		Connection connection;
		List<Categorie> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Categories cat "
							+ " WHERE cat.cat_id NOT IN (SELECT cat.cat_id "
							+ " FROM Categories cat INNER JOIN Articles art "
							+ " ON art.cat_id=cat.cat_id)");

			ResultSet resultSet = prepareStatement.executeQuery();

			
			while (resultSet.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(resultSet.getInt("cat_id"));
				categorie.setName(resultSet.getString("cat_name"));
				categorie.setUrl(resultSet.getString("cat_url"));
				list.add(categorie);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;

	}

	@Override
	public List<Categorie> findMaxArticles() throws DAOException {
		Connection connection;
		List<Categorie> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT cat.cat_name, art.cat_id, cat.cat_url, "
							+" COUNT(art.cat_id) AS 'nbArticles' FROM Categories cat "
							+" LEFT JOIN Articles art ON cat.cat_id=art.cat_id "
							+" GROUP BY cat.cat_name HAVING COUNT(art.cat_id) = "
							+" (SELECT MAX(t.nbeArticles)FROM (SELECT cat.cat_name, "
							+" art.cat_id, COUNT(art.cat_id) AS 'nbeArticles' FROM "
							+" Categories cat LEFT JOIN Articles art ON "
							+" cat.cat_id=art.cat_id GROUP BY cat.cat_name "
							+" ORDER BY nbeArticles)t)");
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(resultSet.getInt("cat_id"));
				categorie.setName(resultSet.getString("cat_name"));
				categorie.setUrl(resultSet.getString("cat_url"));
				list.add(categorie);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public Map<Categorie, Integer> findNombreArticles() throws DAOException {
		Connection connection;
		Map<Categorie, Integer> map = new HashMap<>();

		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT cat.cat_name,cat.cat_url, art.cat_id, "
							+ " COUNT(art.cat_id) AS 'nbArticles' FROM Categories cat "
							+ " LEFT JOIN Articles art ON cat.cat_id=art.cat_id "
							+ " GROUP BY cat.cat_name");
			ResultSet resultSet = prepareStatement.executeQuery();
			//Categories categories = new Categories();
			while (resultSet.next()) {
				Categorie categorie = new Categorie();
				categorie.setId(resultSet.getInt("cat_id"));
				categorie.setName(resultSet.getString("cat_name"));
				categorie.setUrl(resultSet.getString("cat_url"));
				map.put(categorie, resultSet.getInt("nbArticles"));
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return map;
	}
}
