package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Alex Zuelsdorf
 * @version 1.0
 */
public class Spaceship {
	
	private String name;
	private int energy;
	private int shield;
	private int hull;
	private int life;
	private int torpedos;
	private int androids;
	private Cargo[] cargos;
	private ArrayList<String> broadcastCommunicator;
	
	/**
	 * Ein parameterloser Konstruktor
	 */
	public Spaceship() {}
	
	/**
	 * Ein voll-parametrisierten Konstruktor
	 * 
	 * @param name String
	 * @param energy Integer
	 * @param shield Integer
	 * @param hull Integer
	 * @param life Integer
	 * @param torpedos Integer
	 * @param androids Integer
	 * @param cargo Cargo[]
	 * @param communicator ArrayList of Type String
	 */
	public Spaceship(String name, int energy, int shield, int hull, int life, int torpedos, int androids, Cargo[] cargo, ArrayList<String> communicator) {
		this.androids = androids;
		this.energy = energy;
		this.hull = hull;
		this.life = life;
		this.shield = shield;
		this.torpedos = torpedos;
		this.cargos = cargo;
		this.broadcastCommunicator = communicator;
	}
	
	/**
	 * Alle Zustände (Attributwerte) des Raumschiffes auf der Konsole mit entsprechenden Namen ausgeben.
	 */
	public void getStatus() {
		System.out.println(this.name + ": Energieversorgung: " + this.energy + "%, Schild: " + this.shield + "%, H�lle: " + this.hull + "%, Lebenserhaltung: "+ this.life + "%\nTorpedos: "
				+ this.torpedos + ", Reperaturandoiden: " + this.androids);
	}
	
	/**
	 * Alle Ladungen (Cargo) eines Raumschiffes auf der Konsole mit Ladungsbezeichnung und Menge ausgeben.
	 */
	public void getCargo() {
		for (int i = 0; i < cargos.length; i++) {
			System.out.println("Index: " + i + ", Name: " + cargos[i].getName() + ", Menge: " + cargos[i].getAmount());
		}
	}
	
	/**
	 * 
     * Gibt es keine Torpedos, so wird als Nachricht an Alle -=*Click*=- ausgegeben.
     * Ansonsten wird die Torpedoanzahl um eins reduziert und die Nachricht an Alle Photonentorpedo abgeschossen gesendet. Außerdem wird die Methode Treffer aufgerufen.
	 *
	 * @param target Spaceship
	 */
	public void shootTorpedo(Spaceship target) {
		if (this.torpedos <= 0) {
			broadcastAll("-=*Click*=-");
		} else {
			this.torpedos--;
			broadcastAll("Photonentorpedo abgeschossen!");
			hit(target);
		}
	}
	
	/**
	 * Ist die Energieversorgung kleiner als 50%, so wird als Nachricht an Alle -=*Click*=- ausgegeben.
	 * Ansonsten wird die Energieversorgung um 50% reduziert und die Nachricht an Alle "Phaserkanone abgeschossen" gesendet. Ausserdem wird die Methode Treffer aufgerufen.
	 * 
	 * @param target Spaceship
	 */
	public void shootPhaser(Spaceship target) {
		if (this.energy <= 0) {
			broadcastAll("-=*Click*=-");
		} else {
			this.energy = this.energy - 50;
			broadcastAll("Phaserkanone abgeschossen!");
			hit(target);
		}
	}
	
	/**
	 * Die Nachricht [Schiffsname] wurde getroffen! wird in der Konsole ausgegeben. [Schiffsname] durch den Namen des Schiffes ersetzen.
	 * 
     * Die Schilde des getroffenen Raumschiffs werden um 50% geschwächt.
     * Sollte anschließend die Schilde vollständig zerstört worden sein, so wird der Zustand der Hülle und der Energieversorgung jeweils um 50% abgebaut.
     * Sollte danach der Zustand der Hülle auf 0% absinken, so sind die Lebenserhaltungssysteme vollständig zerstört und es wird eine Nachricht an Alle ausgegeben, dass die Lebenserhaltungssysteme vernichtet worden sind.
	 *
	 * @param target Spaceship
	 */
	public void hit(Spaceship target) {
		if (target.getName() == this.getName()) {
			if (this.shield >= 50) {
				this.shield = this.shield - 50;
			} else if (this.hull >= 51) {
				this.hull = this.hull - 50;
				this.energy = this.energy - 50;
			} else {
				this.life = 0;
				broadcastAll(this.name + "'s Lebenserhaltungssysteme wurden vollst�ndig zerst�rt!");
			}
		} else {
			broadcastAll(target.getName() + " wurde getroffen!");
			hit(target);
		}
	}
	
	/**
	 * Die Nachricht wird dem broadcastKommunikator hinzugefügt.
	 * 
	 * @param message String
	 */
	public void broadcastAll(String message) {
		broadcastCommunicator.add(message);
	}
	
	/**
	 * Gibt den broadcastKommunikator zurück.
	 */
	public void getLog() {
		for (Iterator iterator = broadcastCommunicator.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
	
	/**
	 * 
     * Gibt es keine Ladung Photonentorpedo auf dem Schiff, wird als Nachricht Keine Photonentorpedos gefunden! in der Konsole ausgegeben und die Nachricht an alle -=*Click*=- ausgegeben.
     * Ist die Anzahl der einzusetzenden Photonentorpedos größer als die Menge der tatsächlich Vorhandenen, so werden alle vorhandenen Photonentorpedos eingesetzt.
     * Ansonsten wird die Ladungsmenge Photonentorpedo über die Setter Methode vermindert und die Anzahl der Photonentorpedo im Raumschiff erhöht.
     * Konnten Photonentorpedos eingesetzt werden, so wird die Meldung [X] Photonentorpedo(s) eingesetzt auf der Konsole ausgegeben. [X] durch die Anzahl ersetzen.
	 * 
	 * @param amount int
	 */
	public void loadTorpedos(int amount) {
		boolean inCargo = false;
		for (int i = 0; i < cargos.length; i++) {
			if (cargos[i].getName() == "Photonentorpedo") {
				inCargo = true;
			}
		}
		
		if (inCargo) {
			boolean loadToMany = false;
			for (int i = 0; i < cargos.length; i++) {
				if (cargos[i].getName() == "Photonentorpedo" && cargos[i].getAmount() >= amount) {
					loadToMany= true;
					amount = cargos[i].getAmount();
					cargos[i].setAmount(cargos[i].getAmount() - amount);
					break;
				}
			}
			this.torpedos = this.torpedos + amount;
			System.out.println(amount + " Photonentorpedo(s) eingesetzt!");
		} else {
			System.out.println("Keine Photontorpedos gefunden!");
			broadcastAll("-=*Click*=-");
		}
	}
	
	/**
	 * Wenn die Menge einer Ladung 0 ist, dann wird das Objekt Ladung aus der Liste entfernt.
	 */
	public void cleanCargo() {
		for (int i = 0; i < cargos.length; i++) {
			if(cargos[i].getAmount() <= 0) {
				cargos[i] = null;
			}
			
		}
	}
	
	/**
	 * 
     * Die Methode entscheidet anhand der übergebenen Parameter, welche Schiffsstrukturen repariert werden sollen.
     * Es wird eine Zufallszahl zwischen 0 - 100 erzeugt, welche für die Berechnung der Reparatur benötigt wird.
     * Ist die übergebene Anzahl von Androiden größer als die vorhandene Anzahl von Androiden im Raumschiff, dann wird die vorhandene Anzahl von Androiden eingesetzt.
     * Prozentuale Berechnung der reparierten Schiffsstrukturen: 
     * Das Ergebnis wird den auf true gesetzten Schiffsstrukturen hinzugefügt.
	 *
	 * 
	 * @param androids Integer
	 * @param energy Boolean
	 * @param shield Boolean
	 * @param hull Boolean
	 */
	public void useRepairAndroids(int androids, Boolean energy, Boolean shield, Boolean hull) {
		int count = 0;
		if (energy && shield && hull) {
			count = 3;
		} else if (energy && shield) {
			count = 2;
		} else if (energy && hull) {
			count = 2;
		} else if (shield && hull) {
			count = 2;
		} else if (energy) {
			count = 1;
		} else if (shield) {
			count = 1;
		} else if (hull) {
			count = 1;
		}
		
		if (androids >= this.androids) {
			androids = this.androids;
		}
		
		Random ran = new Random();
		int r = ran.nextInt();
		
		int calc = (r * androids) / count;
		
		if (energy && shield && hull) {
			this.energy = this.energy + calc;
			this.shield = this.shield + calc;
			this.hull = this.hull + calc;
		} else if (energy && shield) {
			this.energy = this.energy + calc;
			this.shield = this.shield + calc;
		} else if (energy && hull) {
			this.energy = this.energy + calc;
			this.hull = this.hull + calc;
		} else if (shield && hull) {
			this.shield = this.shield + calc;
			this.hull = this.hull + calc;
		} else if (energy) {
			this.energy = this.energy + calc;
		} else if (shield) {
			this.shield = this.shield + calc;
		} else if (hull) {
			this.hull = this.hull + calc;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getHull() {
		return hull;
	}

	public void setHull(int hull) {
		this.hull = hull;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getTorpedos() {
		return torpedos;
	}

	public void setTorpedos(int torpedos) {
		this.torpedos = torpedos;
	}

	public int getAndroids() {
		return androids;
	}

	public void setAndroids(int androids) {
		this.androids = androids;
	}

	public Cargo[] getCargos() {
		return cargos;
	}

	public void setCargos(Cargo[] cargos) {
		this.cargos = cargos;
	}

	public ArrayList<String> getBroadcastCommunicator() {
		return broadcastCommunicator;
	}

	public void setBroadcastCommunicator(ArrayList<String> broadcastCommunicator) {
		this.broadcastCommunicator = broadcastCommunicator;
	}
}
