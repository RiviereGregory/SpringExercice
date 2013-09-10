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

import fr.treeptik.springexercice.dao.AuteurDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Auteur;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Repository
public class AuteurJDBCImpl implements AuteurDAO {
	@Autowired
	private JDBCUtil jdbcUtil;

	@Override
	public Auteur save(Auteur auteur) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO Auteurs "
					+ " (aut_nom , aut_prenom ,aut_url , aut_email) " + "VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setString(1, auteur.getNom());
			prepareStatement.setString(2, auteur.getPrenom());
			prepareStatement.setString(3, auteur.getUrl());
			prepareStatement.setString(4, auteur.getEmail());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			auteur.setId(keys.getInt(1));

		} catch (SQLException e) {

			throw new DAOException(e.getMessage(), e.getCause());

		}

		return auteur;
	}

	@Override
	public void remove(Auteur auteur) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Auteurs WHERE aut_id=?");
			prepareStatement.setInt(1, auteur.getId());
			int nbModif = prepareStatement.executeUpdate();
			if (nbModif != 1) {
				throw new DAOException("Auteurs inconnu id = " + auteur.getId());
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Auteur> findAll() throws DAOException {
		Connection connection;
		List<Auteur> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Auteurs ");
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Auteur auteur = new Auteur();
				auteur.setId(resultSet.getInt("aut_id"));
				auteur.setEmail(resultSet.getString("aut_email"));
				auteur.setNom(resultSet.getString("aut_nom"));
				auteur.setPrenom(resultSet.getString("aut_prenom"));
				auteur.setUrl(resultSet.getString("aut_url"));

				list.add(auteur);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public Auteur findAuteurs(Integer id) throws DAOException {
		Connection connection;
		Auteur auteur = new Auteur();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Auteurs WHERE aut_id=?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();

			resultSet.next();

			auteur.setId(resultSet.getInt("aut_id"));
			auteur.setEmail(resultSet.getString("aut_email"));
			auteur.setNom(resultSet.getString("aut_nom"));
			auteur.setPrenom(resultSet.getString("aut_prenom"));
			auteur.setUrl(resultSet.getString("aut_url"));

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return auteur;
	}

	@Override
	public Map<Auteur, Integer> findNombreArticlesEnLigne() throws DAOException {
		Connection connection;
		Map<Auteur, Integer> map = new HashMap<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT aut.aut_id, aut.aut_email, "
							+ " aut.aut_nom,aut.aut_prenom, aut.aut_url, "
							+ " art.art_en_ligne, COUNT(art.cat_id) AS 'nbArticles' "
							+ " FROM Articles art RIGHT JOIN Auteurs aut "
							+ " ON aut.aut_id=art.aut_id GROUP BY aut.aut_nom, "
							+ " art.art_en_ligne HAVING art.art_en_ligne IN "
							+ " (SELECT ar.art_en_ligne FROM Articles ar LEFT JOIN "
							+ " Auteurs au ON au.aut_id=ar.aut_id "
							+ " HAVING ar.art_en_ligne <> 0); ");

			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Auteur auteur = new Auteur();
				auteur.setId(resultSet.getInt("aut_id"));
				auteur.setEmail(resultSet.getString("aut_email"));
				auteur.setNom(resultSet.getString("aut_nom"));
				auteur.setPrenom(resultSet.getString("aut_prenom"));
				auteur.setUrl(resultSet.getString("aut_url"));

				map.put(auteur, resultSet.getInt("nbArticles"));
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return map;
	}

	@Override
	public List<Auteur> findArticlesMaxCommente() throws DAOException {
		Connection connection;
		List<Auteur> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT *, COUNT(com.art_id) AS 'nbcommentaires' "
							+ " FROM Commentaires com JOIN Articles art "
							+ " ON com.art_id=art.art_id JOIN Auteurs aut "
							+ " ON art.aut_id=aut.aut_id GROUP BY aut.aut_nom,art.art_id "
							+ " HAVING COUNT(com.art_id) = (SELECT MAX(t.nbcommentaires) "
							+ " FROM (SELECT com.art_id,art.aut_id, COUNT(com.art_id) "
							+ " AS 'nbcommentaires' FROM Commentaires com  "
							+ " LEFT JOIN Articles art ON com.art_id=art.art_id "
							+ " LEFT JOIN Auteurs aut ON art.aut_id=aut.aut_id "
							+ " GROUP BY aut.aut_nom,art.art_id)t); ");

			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Auteur auteur = new Auteur();
				auteur.setId(resultSet.getInt("aut_id"));
				auteur.setEmail(resultSet.getString("aut_email"));
				auteur.setNom(resultSet.getString("aut_nom"));
				auteur.setPrenom(resultSet.getString("aut_prenom"));
				auteur.setUrl(resultSet.getString("aut_url"));

				list.add(auteur);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}
}
