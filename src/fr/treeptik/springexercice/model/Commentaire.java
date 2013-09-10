package fr.treeptik.springexercice.model;

import java.io.Serializable;
import java.util.Date;

public class Commentaire implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Article article;
	private String nom;
	private String email;
	private String texte;
	private Date date;
	private Boolean validation;

	public Commentaire() {
	}

	public Commentaire(Integer id, Article article, String nom, String email, String texte,
			Date date, Boolean validation) {
		super();
		this.id = id;
		this.article = article;
		this.nom = nom;
		this.email = email;
		this.texte = texte;
		this.date = date;
		this.validation = validation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commentaire other = (Commentaire) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article getArticles() {
		return article;
	}

	public void setArticles(Article article) {
		this.article = article;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getValidation() {
		return validation;
	}

	public void setValidation(Boolean validation) {
		this.validation = validation;
	}

}
