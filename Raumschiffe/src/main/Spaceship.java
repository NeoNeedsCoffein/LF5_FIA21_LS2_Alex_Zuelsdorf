package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
	
	public Spaceship() {}
	
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
	
	public void getStatus() {
		System.out.println(this.name + ": Energieversorgung: " + this.energy + "%, Schild: " + this.shield + "%, Hülle: " + this.hull + "%, Lebenserhaltung: "+ this.life + "%\nTorpedos: "
				+ this.torpedos + ", Reperaturandoiden: " + this.androids);
	}
	
	public void getCargo() {
		for (int i = 0; i < cargos.length; i++) {
			System.out.println("Index: " + i + ", Name: " + cargos[i].getName() + ", Menge: " + cargos[i].getAmount());
		}
	}
	
	public void shootTorpedo(Spaceship target) {
		if (this.torpedos <= 0) {
			broadcastAll("-=*Click*=-");
		} else {
			this.torpedos--;
			broadcastAll("Photonentorpedo abgeschossen!");
			hit(target);
		}
	}
	
	public void shootPhaser(Spaceship target) {
		if (this.energy <= 0) {
			broadcastAll("-=*Click*=-");
		} else {
			this.energy = this.energy - 50;
			broadcastAll("Phaserkanone abgeschossen!");
			hit(target);
		}
	}
	
	public void hit(Spaceship target) {
		
		if (target.getName() == this.getName()) {
			if (this.shield >= 50) {
				this.shield = this.shield - 50;
			} else if (this.hull >= 51) {
				this.hull = this.hull - 50;
				this.energy = this.energy - 50;
			} else {
				this.life = 0;
				broadcastAll(this.name + "'s Lebenserhaltungssysteme wurden vollständig zerstört!");
			}
		} else {
			broadcastAll(target.getName() + " wurde getroffen!");
			hit(target);
		}
	}
	
	public void broadcastAll(String message) {
		broadcastCommunicator.add(message);
	}
	
	public void getLog() {
		for (Iterator iterator = broadcastCommunicator.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
	
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
	
	public void cleanCargo() {
		for (int i = 0; i < cargos.length; i++) {
			if(cargos[i].getAmount() <= 0) {
				cargos[i] = null;
			}
			
		}
	}
	
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
