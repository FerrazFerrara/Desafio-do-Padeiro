import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Forno {
	boolean paoAssando;
	Botijao botijao;
	Lock lock;
	Condition podeAssar;
	Condition trocarBotijao;
	
	Forno(Botijao botijao, Lock lock){
		this.paoAssando = false;
		this.botijao = botijao;
		this.lock = lock;
		this.podeAssar = this.lock.newCondition();
		this.trocarBotijao = this.lock.newCondition();
	}
	
	public void assarPao(Pao pao, Padeiro padeiro) {
		if(this.lock.tryLock()) {
			try {
				while(this.botijao.tempoRestanteGas < pao.pesoG * 10) {
					pao.meioAssado = true;
					this.trocarBotijao.signal();
					this.podeAssar.await();
				}
				System.out.println("+++++ " + padeiro.getName() + " botou o pao pra assar");
				Thread.sleep(pao.pesoG * 10);
				this.botijao.tempoRestanteGas -= pao.pesoG * 10;
				pao.meioAssado = false;
				pao.assado = true;
			} catch(InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("+++++ " + padeiro.getName() + " terminou de assar o pao");
				this.lock.unlock();
			}
		} else {
			if(pao.tipo == 2 && !pao.assado)
				padeiro.esculpirMassa();
			if(pao.tipo == 1 && !pao.assado)
				padeiro.confeitarMassa();
			if(pao.tipo == 0 && !pao.assado) 
				padeiro.rechearMassa();
		}
	}
	
	public void trocarBotijao() {
		this.lock.lock();
		try {
			System.out.println(">>>[Gerente] Terminou de trocar o botijao");
			this.podeAssar.signalAll();
			this.trocarBotijao.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}
}
