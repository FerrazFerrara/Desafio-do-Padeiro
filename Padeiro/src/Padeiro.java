import java.util.Random;

public class Padeiro extends Thread{
	Pao pao;
	Forno forno;
	
	public Padeiro(String nome, Forno forno){
		super(nome);
		this.forno = forno;
	}
	
	public void run() {
		while(true) {
			this.fazerMassa();
		}
	}
	
	public void fazerMassa() {
		System.out.println("+++++ " + this.getName() + " comecou a fazer a massa");
		this.pao = new Pao(new Random().nextInt(1000));
		try {
			sleep(pao.pesoG * 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("+++++ " + this.getName() + " terminou de fazer a massa");
		do {
			assarPao(this.pao);
		} while(!this.pao.assado);
	}
	
	public void assarPao(Pao pao) {
		this.forno.assarPao(pao, this);
	}
	
	public void rechearMassa() {
		System.out.println(this.getName() + " comecou a rechear o pao");
		try {
			sleep(pao.pesoG * 3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pao.tipo = 1;
		System.out.println(this.getName() + " terminou de rechear o pao");
	}
	
	public void confeitarMassa() {
		System.out.println(this.getName() + " comecou a confeitar o pao");
		try {
			sleep(pao.pesoG * 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pao.tipo = 2;
		System.out.println(this.getName() + " terminou de confeitar o pao");
	}
	
	public void esculpirMassa() {
		System.out.println(this.getName() + " comecou a esculpir o pao");
		try {
			sleep(pao.pesoG * 7);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pao.tipo = 3;
		System.out.println(this.getName() + " terminou de esculpir o pao");
	}
}
