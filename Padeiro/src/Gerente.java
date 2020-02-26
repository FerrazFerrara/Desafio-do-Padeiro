
public class Gerente extends Thread{
	Botijao botijao;
	Forno forno;
	
	public Gerente(Botijao botijao, Forno forno){
		this.botijao = botijao;
		this.forno = forno;
	}
	
	public void run() {
		while(true) {
			this.encherBotijao();
		}
	}
	
	public void encherBotijao() {
		this.botijao.encherBotijao();
		this.forno.trocarBotijao();
	}
}
