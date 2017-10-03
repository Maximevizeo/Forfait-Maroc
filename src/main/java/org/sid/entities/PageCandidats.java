package org.sid.entities;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PageCandidats implements Serializable{
	private List<Candidat> candidats;
	private int page;
	private int nombreCandidats;
	private int totalPages;
	public List<Candidat> getCandidats() {
		return candidats;
	}
	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNombreCandidats() {
		return nombreCandidats;
	}
	public void setNombreCandidats(int nombreCandidats) {
		this.nombreCandidats = nombreCandidats;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
