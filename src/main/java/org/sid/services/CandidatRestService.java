package org.sid.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.sid.entities.Candidat;
import org.sid.metier.IBondmanagerMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/candidats")
public class CandidatRestService {
	@Autowired
	private IBondmanagerMetier bondmanagerMetier;
	
	@RequestMapping(method=RequestMethod.POST)
	public void saveCandidat(@ RequestBody Candidat cdt) {
		bondmanagerMetier.saveCandidat(cdt);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Candidat> afficherCandidats() {
		return bondmanagerMetier.afficherCandidats();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Candidat editerCandidat(@RequestBody Candidat cdt,@PathVariable Long id) {
		return bondmanagerMetier.editerCandidat(cdt,id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Candidat findCandidat(@PathVariable Long id) {
		return bondmanagerMetier.findCandidat(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void supprimerCandidat(@PathVariable Long id) {
		bondmanagerMetier.supprimerCandidat(id);
	}
	
	@RequestMapping(value="/getLogedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest){
		HttpSession httpSession=httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) 
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		List<String> roles=new ArrayList<>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String,Object> params=new HashMap<>();
		params.put("username",username);
		params.put("roles", roles);
		return params;
		
	}
	
	@RequestMapping(value="/uploadfile", method=RequestMethod.POST)
    public void uploadFileMulti(
            @RequestParam("uploadfile") MultipartFile file) throws Exception {
		
		if(!(file.isEmpty())) {
			try {
				file.transferTo(new File(System.getProperty("user.home")+"/upload/"+file.getOriginalFilename()));
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
    }

}
