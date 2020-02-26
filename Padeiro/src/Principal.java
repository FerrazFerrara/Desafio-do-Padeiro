import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Principal {
	public static void main(String[] args) {
		Lock lock = new ReentrantLock(true);
		Botijao botijao = new Botijao();
		Forno forno = new Forno(botijao, lock);
		
		new Gerente(botijao, forno).start();
		for(int i = 0; i < 3; i++)
			new Padeiro("Padeiro " + i,forno).start();
	}
}
