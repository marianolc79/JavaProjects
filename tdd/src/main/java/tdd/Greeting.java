package tdd;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class Greeting {

	private static final int HEURE_DEBUT_JOUR = 6;
	private static final int HEURE_DEBUT_SOIR = 18;
	private static final int HEURE_DEBUT_NUIT = 23;
	private IDateHelper dateHelper;

	public Greeting(IDateHelper dateHelper) {
		this.dateHelper = dateHelper;
	}

	public String hi(String firstName) {

		LocalTime now = LocalDateTime.ofInstant(dateHelper.getDate().toInstant(), ZoneId.systemDefault()).toLocalTime();

		String greeting = "";
		if (now.getHour() >= HEURE_DEBUT_JOUR && now.getHour() < HEURE_DEBUT_SOIR) {
			greeting = "Bonjour";
		} else if (now.getHour() >= HEURE_DEBUT_SOIR && now.getHour() < HEURE_DEBUT_NUIT) {
			greeting = "Bonsoir";
		} else if (now.getHour() == 23 || now.getHour() >= 0 && now.getHour() < HEURE_DEBUT_JOUR) {
			greeting = "Bonne nuit";
		}

		return greeting + " " + String.valueOf(firstName.trim().charAt(0)).toUpperCase()
				+ String.valueOf(firstName.trim().substring(1)).toLowerCase();
	}
}
