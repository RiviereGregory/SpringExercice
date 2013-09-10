package fr.treeptik.springexercice.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.treeptik.springexercice.dao.impl.CommentaireJDBCImpl;
import fr.treeptik.springexercice.dao.impl.CommentaireXMLImpl;

@Configuration
public class DAOFactory {

	// Permet d'utiliser le fichier properties configuré dans le fichier applicationContext.xml
	// Et d'affecter la valeur de la properties configuration.commentaire.dao dans confCommentaire
	private @Value("#{jdbcConfiguration['configuration.commentaire.dao']}")
	String confCommentaires;

	// Permet d'utiliser les qualifiers dans le service après @Autowired
	@Bean(name = "commentairesDAOFromFactory")
	public CommentaireDAO getCommentairesDAO() {
		// Comparer la value xml à celle contenu dans confCommentaire
		if (confCommentaires.equals("xml")) {
			return new CommentaireXMLImpl();
		} else if (confCommentaires.equals("jdbc")) {

			return new CommentaireJDBCImpl();
		} else {
			return null;
		}
	}

}
