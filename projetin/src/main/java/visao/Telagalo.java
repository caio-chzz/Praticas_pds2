package visao;

import java.util.ArrayList;

import control.galosDAO;
import modelo.Galos;

public class Telagalo {

	public static void main(String[] args) {
		galosDAO dao = new galosDAO();
		
		ArrayList<Galos> galons = dao.listar();
		
		for (Galos galos : galons) {
			
		}
		
	}
	
	
}
