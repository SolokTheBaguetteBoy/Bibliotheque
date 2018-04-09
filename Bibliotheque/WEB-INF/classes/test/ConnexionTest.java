package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;
import persistantdata.MediathequeData;

public class ConnexionTest {
	
	@Test
	public void test() throws ClassNotFoundException {
		Class.forName("persistantdata.MediathequeData");
		Utilisateur UserTest = Mediatheque.getInstance().getUser("JeanAdmin", "123");
		assertEquals(UserTest.getPassword(), "123");
	}

}
