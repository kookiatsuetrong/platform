package web;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.security.MessageDigest;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

class Common {
	
	static EntityManager getManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("main");
		}
		return factory.createEntityManager();
	}
	
	private static EntityManagerFactory factory = null;

	static ZonedDateTime getTime() {
		return ZonedDateTime.now(ZoneId.of("UTC"));
	}
	
	static String encrypt(String s) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] data = digest.digest(s.getBytes());
			String t = "";
			for (byte b : data) {
				int k = b;
				if (k < 0) {
					k = 256 + k;
				}
				t += String.format("%02X", k);
			}
			return t;
		} catch (Exception e) { }
		return "Encryption Error";
	}
	
	static char[] pattern = "0123456789ABCEDF".toCharArray();
	static String random(int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			int index = (int)(Math.random() * pattern.length);
			s += pattern[index];
		}
		return s;
	}
}
