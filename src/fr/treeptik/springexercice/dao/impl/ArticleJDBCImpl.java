package fr.treeptik.springexercice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.treeptik.springexercice.dao.ArticleDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Article;
import fr.treeptik.springexercice.model.Auteur;
import fr.treeptik.springexercice.model.Categorie;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Repository
public class ArticleJDBCImpl implements ArticleDAO {

	@Autowired
	private JDBCUtil jdbcUtil;

	@Override
	public Article save(Article article) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement(
							"INSERT INTO Articles "
									+ " (cat_id, art_titre , art_chapeau,art_contenu, art_date, aut_id ,art_en_ligne) "
									+ "VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, article.getCategories().getId());
			prepareStatement.setString(2, article.getTitre());
			prepareStatement.setString(3, article.getChapeau());
			prepareStatement.setString(4, article.getContenu());
			prepareStatement.setDate(5, new java.sql.Date(article.getDate().getTime()));
			prepareStatement.setInt(6, article.getAuteurs().getId());
			prepareStatement.setBoolean(7, article.getEnLigne());
			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			article.setId(keys.getInt(1));

		} catch (SQLException e) {

			throw new DAOException(e.getMessage(), e.getCause());

		}

		return article;
	}

	@Override
	public void remove(Article article) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Articles WHERE art_id=?");
			prepareStatement.setInt(1, article.getId());

			int nbModif = prepareStatement.executeUpdate();
			if (nbModif != 1) {
				throw new DAOException("Article inconnu id = " + article.getId());
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

	}

	@Override
	public List<Article> findAll() throws DAOException {
		Connection connection;
		List<Article> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Articles art INNER JOIN Auteurs aut ON aut.aut_id=art.aut_id "
							+ " INNER JOIN Categories cat ON cat.cat_id=art.cat_id ");
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Article article = new Article();
				article.setId(resultSet.getInt("art_id"));

				Auteur auteur = new Auteur();
				auteur.setId(resultSet.getInt("aut_id"));
				auteur.setEmail(resultSet.getString("aut_email"));
				auteur.setNom(resultSet.getString("aut_nom"));
				auteur.setPrenom(resultSet.getString("aut_prenom"));
				auteur.setUrl(resultSet.getString("aut_url"));
				article.setAuteurs(auteur);

				Categorie categorie = new Categorie();
				categorie.setId(resultSet.getInt("cat_id"));
				categorie.setName(resultSet.getString("cat_name"));
				categorie.setUrl(resultSet.getString("cat_url"));
				article.setCategories(categorie);

				article.setChapeau(resultSet.getString("art_chapeau"));
				article.setContenu(resultSet.getString("art_contenu"));
				article.setTitre(resultSet.getString("art_titre"));
				article.setEnLigne(resultSet.getBoolean("art_en_ligne"));
				article.setDate(new Date(resultSet.getDate("art_date").getTime()));

				list.add(article);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public Article findArticles(Integer id) throws DAOException {
		Connection connection;
		Article article = new Article();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Articles art INNER JOIN Auteurs aut ON aut.aut_id=art.aut_id "
							+ " INNER JOIN Categories cat ON cat.cat_id=art.cat_id  WHERE art_id=?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();

			resultSet.next();

			article.setId(resultSet.getInt("art_id"));

			Auteur auteur = new Auteur();
			auteur.setId(resultSet.getInt("aut_id"));
			auteur.setEmail(resultSet.getString("aut_email"));
			auteur.setNom(resultSet.getString("aut_nom"));
			auteur.setPrenom(resultSet.getString("aut_prenom"));
			auteur.setUrl(resultSet.getString("aut_url"));
			article.setAuteurs(auteur);

			Categorie categorie = new Categorie();
			categorie.setId(resultSet.getInt("cat_id"));
			categorie.setName(resultSet.getString("cat_name"));
			categorie.setUrl(resultSet.getString("cat_url"));
			article.setCategories(categorie);

			article.setChapeau(resultSet.getString("art_chapeau"));
			article.setContenu(resultSet.getString("art_contenu"));
			article.setTitre(resultSet.getString("art_titre"));
			article.setEnLigne(resultSet.getBoolean("art_en_ligne"));
			article.setDate(new Date(resultSet.getDate("art_date").getTime()));

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return article;
	}

	@Override
	public List<Article> findArticles(Date dateDebut, Date dateFin) throws DAOException {
		// Crée un Objet Article sans Auteurs et sans catégories
		Connection connection;
		List<Article> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Articles WHERE art_date BETWEEN ? AND ?");
			prepareStatement.setDate(1, new java.sql.Date(dateDebut.getTime()));
			prepareStatement.setDate(2, new java.sql.Date(dateFin.getTime()));
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Article article = new Article();
				article.setId(resultSet.getInt("art_id"));

				article.setChapeau(resultSet.getString("art_chapeau"));
				article.setContenu(resultSet.getString("art_contenu"));
				article.setTitre(resultSet.getString("art_titre"));
				article.setEnLigne(resultSet.getBoolean("art_en_ligne"));
				article.setDate(new Date(resultSet.getDate("art_date").getTime()));

				list.add(article);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

}
