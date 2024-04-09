package fr.eni.tp.escaperooms.bll;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("trap")
public class Treasure1Service implements TreasureService {

	@Override
	public String ouvrir() {
		return "Perdu, vous êtes tombé dans un piège !";
	}
	
}
