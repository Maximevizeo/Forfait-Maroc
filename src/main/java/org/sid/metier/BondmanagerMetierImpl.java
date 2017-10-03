package org.sid.metier;

import java.util.List;

import org.sid.dao.CandidatRepository;
import org.sid.entities.Candidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BondmanagerMetierImpl implements IBondmanagerMetier{
	@Autowired
	private CandidatRepository candidatRepository;
	
	@Override
	public void saveCandidat(Candidat cdt) {
		candidatRepository.save(cdt);
		}
	
	@Override
	public List<Candidat> afficherCandidats() {
		List<Candidat> cdts=candidatRepository.findAll();
		return cdts;
	}
	
	@Override
	public void supprimerCandidat(Long id) {
		candidatRepository.delete(id);
	}
	
	@Override
	public Candidat editerCandidat(Candidat cdt,Long id) {
		cdt.setId(id);
		return candidatRepository.saveAndFlush(cdt);
	}
	
	@Override
	public Candidat findCandidat(Long id) {
		Candidat cdt = candidatRepository.findOne(id);
		return cdt;
	}
}
