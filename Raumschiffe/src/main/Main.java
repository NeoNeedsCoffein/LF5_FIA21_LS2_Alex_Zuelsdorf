
package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * @author Alex Zuelsdorf
 * @version 1.0
 */
public class Main {
	
	/**
	 * The main Methods of the whole Programm
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		Cargo c1 = new Cargo("Ferengi Schneckensaft", 200);
		Cargo c2 = new Cargo("Borg-Schrott", 5);
		Cargo c3 = new Cargo("Rote Materie", 2);
		Cargo c4 = new Cargo("Forschungssonde", 35);
		Cargo c5 = new Cargo("Bat'leth Klingonen Schwert", 200);
		Cargo c6 = new Cargo("Plasma-Waffe", 50);
		
		Cargo[] cs1 = new Cargo[2];
		Cargo[] cs2 = new Cargo[3];
		Cargo[] cs3 = new Cargo[1];
		
		cs1[0] = c1;
		cs1[1] = c5;
		
		cs2[0] = c2;
		cs2[1] = c3;
		cs2[2] = c6;
		
		cs3[0] = c4;
		
		ArrayList<String> communicator = new ArrayList<String>();
		
		Random r = new Random();
		
		Spaceship klingonen = new Spaceship("IKS Hegh'ta", 100, 50, 100, 100, 0, 2, cs1, communicator);
		Spaceship romulaner = new Spaceship("IRW Khazara", 50, 50, 100, 100, 2, 2, cs2, communicator);
		Spaceship vulkanier = new Spaceship("Ni'Var", r.nextInt(100)+1, r.nextInt(100)+1, r.nextInt(100)+1, 100, 3, 5, cs1, communicator);
		
		klingonen.shootTorpedo(romulaner);
		romulaner.shootPhaser(klingonen);
		vulkanier.broadcastAll("Gewalt ist nicht logisch");
		System.out.println();
		klingonen.getStatus();
		System.out.println();
		klingonen.getCargo();
		System.out.println();
		vulkanier.useRepairAndroids(5, true, true, true);
		vulkanier.loadTorpedos(5);
		vulkanier.cleanCargo();
		klingonen.shootTorpedo(romulaner);
		klingonen.shootTorpedo(romulaner);
		System.out.println();
		klingonen.getStatus();
		System.out.println();
		klingonen.getCargo();
		System.out.println();
		romulaner.getStatus();
		System.out.println();
		romulaner.getCargo();
		System.out.println();
		vulkanier.getStatus();
		System.out.println();
		vulkanier.getCargo();
		System.out.println();
		System.out.println();
		
		for (Iterator iterator = communicator.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
	}

}
