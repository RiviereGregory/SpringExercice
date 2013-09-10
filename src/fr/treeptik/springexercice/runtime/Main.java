package fr.treeptik.springexercice.runtime;


public class Main {

	public static void main(String[] args) {
//		// Connection à Spring par le fichier xml
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				"applicationContext.xml");
//
//		// Récupération des annotations pour création des variables
//		ArticlesService articlesService = context.getBean(ArticlesService.class);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//		
//		// Utilistaion Fichier XML pour la lecture
//		JAXBContext jaxbcontext;
//		try {
//			jaxbcontext = JAXBContext.newInstance("fr.treeptik.springexercice.xml");
//			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
//			ListCommentaires listCommentaires = (ListCommentaires) unmarshaller.unmarshal(new File(
//					"commentaires.xml"));
//
//			System.out.println(listCommentaires.getCommentaires().size());
//			for (fr.treeptik.springexercice.xml.Commentaires commentaires : listCommentaires
//					.getCommentaires()) {
//				System.out.println("id : " + commentaires.getComId());
//				System.out.println("art_id : " + commentaires.getArtId());
//				System.out.println("Nom: " + commentaires.getComNom());
//				System.out.println("Email: " + commentaires.getComEmail());
//				System.out.println("Texte: " + commentaires.getComTexte());
//				System.out.println("date: "
//						+ dateFormat.format(commentaires.getComDate().toGregorianCalendar()
//								.getTime()));
//				System.out.println("Validation: " + commentaires.isComValidation());
//				System.out.println("");
//			}
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}

		// Utilisation SQL
//		 try {
//		 List<Articles> list = articlesService.findAll();
//		 System.out.println("NB Articles : " + list.size());
//		 for (Articles articles : list) {
//		
//		 System.out.println("art_id : " + articles.getId());
//		 System.out.println("cat_id : " + articles.getCategories().getId());
//		 System.out.println("art_titre : " + articles.getTitre());
//		 System.out.println("art_chapeau : " + articles.getChapeau());
//		 System.out.println("art_contenu : " + articles.getContenu());
//		 System.out.println("art_date : " + dateFormat.format(articles.getDate()));
//		 System.out.println("aut_id : " + articles.getAuteurs().getId());
//		 System.out.println("art_en_ligne : " + articles.getEnLigne());
//		 System.out.println("");
//		 }
//		
//		 } catch (ServiceException e) {
//		 e.printStackTrace();
//		 }
		//
		// AuteursService auteursService = context.getBean(AuteursService.class);
		// try {
		// List<Auteurs> list = auteursService.findAll();
		// System.out.println("//////////////////");
		// System.out.println("NB Auteurs : " + list.size());
		// for (Auteurs auteurs : list) {
		//
		// System.out.println("id : " + auteurs.getId());
		// System.out.println("Nom : " + auteurs.getNom());
		// System.out.println("Prenom : " + auteurs.getPrenom());
		// System.out.println("Url : " + auteurs.getUrl());
		// System.out.println("email : " + auteurs.getEmail());
		//
		// System.out.println("");
		// }
		//
		// } catch (ServiceException e) {
		// e.printStackTrace();
		// }
		//
		// CategoriesService categoriesService = context.getBean(CategoriesService.class);
		// try {
		// List<Categories> list = categoriesService.findAll();
		// System.out.println("//////////////////");
		// System.out.println("NB Categories : " + list.size());
		// for (Categories categories : list) {
		//
		// System.out.println("id : " + categories.getId());
		// System.out.println("Nom : " + categories.getName());
		// System.out.println("Url : " + categories.getUrl());
		//
		// System.out.println("");
		// }
		//
		// } catch (ServiceException e) {
		// e.printStackTrace();
		// }
		//
		// CommentairesService commentairesService = context.getBean(CommentairesService.class);
		// try {
		// List<Commentaires> list = commentairesService.findAll();
		// System.out.println("//////////////////");
		// System.out.println("NB Commentaires : " + list.size());
		// for (Commentaires commentaires : list) {
		//
		// System.out.println("id : " + commentaires.getId());
		// System.out.println("art_id : " + commentaires.getArticles().getId());
		// System.out.println("Nom: " + commentaires.getNom());
		// System.out.println("Email: " + commentaires.getEmail());
		// System.out.println("Texte: " + commentaires.getTexte());
		// System.out.println("date: " + dateFormat.format(commentaires.getDate()));
		// System.out.println("Validation: " + commentaires.getValidation());
		//
		// System.out.println("");
		// }
		//
		// } catch (ServiceException e) {
		// e.printStackTrace();
		// }
		//
		// Commentaires commentaire = new Commentaires();
		// try {
		//
		// commentaire.setArticles(articlesService.findArticles(1));
		//
		// commentaire.setDate(dateFormat.parse("25/12/2012"));
		// commentaire.setEmail("testMain@main.fr");
		// commentaire.setNom("TestMain");
		// commentaire.setTexte("Ceci est du texte");
		// commentaire.setValidation(true);
		//
		// commentairesService.save(commentaire);
		//
		// } catch (ServiceException | ParseException e) {
		// e.printStackTrace();
		// }
		//
		// try {
		// List<Commentaires> list = commentairesService.findAll();
		// System.out.println("/////Apres Ajout///////");
		// System.out.println("NB Commentaires : " + list.size());
		// for (Commentaires commentaires : list) {
		//
		// System.out.println("id : " + commentaires.getId());
		// System.out.println("art_id : " + commentaires.getArticles().getId());
		// System.out.println("Nom: " + commentaires.getNom());
		// System.out.println("Email: " + commentaires.getEmail());
		// System.out.println("Texte: " + commentaires.getTexte());
		// System.out.println("date: " + dateFormat.format(commentaires.getDate()));
		// System.out.println("Validation: " + commentaires.getValidation());
		//
		// System.out.println("");
		// }
		//
		// } catch (ServiceException e) {
		// e.printStackTrace();
		// }

		// context.close();

	}

}
