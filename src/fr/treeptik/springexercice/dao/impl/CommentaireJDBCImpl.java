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

import fr.treeptik.springexercice.dao.CommentaireDAO;
import fr.treeptik.springexercice.exception.DAOException;
import fr.treeptik.springexercice.model.Article;
import fr.treeptik.springexercice.model.Auteur;
import fr.treeptik.springexercice.model.Categorie;
import fr.treeptik.springexercice.model.Commentaire;
import fr.treeptik.springexercice.utils.JDBCUtil;

@Repository
public class CommentaireJDBCImpl implements CommentaireDAO {

	@Autowired
	private JDBCUtil jdbcUtil;

	@Override
	public Commentaire save(Commentaire commentaire) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement(
							"INSERT INTO Commentaires "
									+ " (art_id , com_nom , com_email ,com_texte, com_date , com_validation) "
									+ "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			prepareStatement.setInt(1, commentaire.getArticles().getId());
			prepareStatement.setString(2, commentaire.getNom());
			prepareStatement.setString(3, commentaire.getEmail());
			prepareStatement.setString(4, commentaire.getTexte());
			prepareStatement.setDate(5, new java.sql.Date(commentaire.getDate().getTime()));
			prepareStatement.setBoolean(6, commentaire.getValidation());

			prepareStatement.executeUpdate();

			ResultSet keys = prepareStatement.getGeneratedKeys();
			keys.next();
			commentaire.setId(keys.getInt(1));

		} catch (SQLException e) {

			throw new DAOException(e.getMessage(), e.getCause());

		}

		return commentaire;
	}

	@Override
	public void remove(Commentaire commentaire) throws DAOException {
		Connection connection;
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("DELETE FROM Commentaires WHERE com_id=?");
			prepareStatement.setInt(1, commentaire.getId());
			int nbModif = prepareStatement.executeUpdate();
			if (nbModif != 1) {
				throw new DAOException("Commentaires inconnu id = " + commentaire.getId());
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Commentaire> findAll() throws DAOException {
		Connection connection;
		List<Commentaire> list = new ArrayList<>();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Commentaires com INNER JOIN Articles art ON art.art_id=com.art_id "
							+ "INNER JOIN Auteurs aut ON aut.aut_id=art.aut_id "
							+ " INNER JOIN Categories cat ON cat.cat_id=art.cat_id ");
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Commentaire commentaire = new Commentaire();
				commentaire.setId(resultSet.getInt("com_id"));

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

				commentaire.setArticles(article);

				commentaire.setNom(resultSet.getString("com_nom"));
				commentaire.setEmail(resultSet.getString("com_email"));
				commentaire.setTexte(resultSet.getString("com_texte"));
				commentaire.setDate(new Date(resultSet.getDate("com_date").getTime()));
				commentaire.setValidation(resultSet.getBoolean("com_validation"));

				list.add(commentaire);
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return list;
	}

	@Override
	public Commentaire findCommentaires(Integer id) throws DAOException {
		Connection connection;
		Commentaire commentaire = new Commentaire();
		try {
			connection = jdbcUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("SELECT * FROM Commentaires com INNER JOIN Articles art ON art.art_id=com.art_id "
							+ "INNER JOIN Auteurs aut ON aut.aut_id=art.aut_id "
							+ " INNER JOIN Categories cat ON cat.cat_id=art.cat_id WHERE com_id=?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();

			resultSet.next();

			commentaire.setId(resultSet.getInt("com_id"));

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

			commentaire.setArticles(article);

			commentaire.setNom(resultSet.getString("com_nom"));
			commentaire.setEmail(resultSet.getString("com_email"));
			commentaire.setTexte(resultSet.getString("com_texte"));
			commentaire.setDate(new Date(resultSet.getDate("com_date").getTime()));
			commentaire.setValidation(resultSet.getBoolean("com_validation"));

		} catch (SQLException e) {
			throw new DAOException(e.getMessage(), e.getCause());
		}

		return commentaire;
	}

}
