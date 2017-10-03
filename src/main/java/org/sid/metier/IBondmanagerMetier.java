package org.sid.metier;

import java.util.List;

import org.sid.entities.Candidat;

public interface IBondmanagerMetier {
	public void saveCandidat(Candidat cdt);
	public List<Candidat> afficherCandidats();
	public void supprimerCandidat(Long id);
	public Candidat editerCandidat(Candidat cdt,Long id);
	public Candidat findCandidat(Long id);
}
