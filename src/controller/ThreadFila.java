package controller;

import java.util.concurrent.Semaphore;

public class ThreadFila extends Thread {

	private int idPessoa;
	private static int posChegada;
	private static int posSaida;
	private Semaphore semaforo;

	public ThreadFila(int idPessoa, Semaphore semaforo) {
		this.idPessoa = idPessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		pessoaAndando();
		// ----- Inicio seçao critica -----
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		pessoaParada();
		semaforo.release();
		// ----- Fim seçao critica --------
		pessoaSaindo();
	}
	}

	private void pessoaAndando() {
		int distanciaTotal = 200; //(int) ((Math.random() * 1001) + 1000);
		int distanciaPercorrida = (int) ((Math.random() * 4001) + 2000);//0;
		int deslocamento = 100;
		int tempo = (int) ((Math.random() * 1001) + 2000);  //1000;

		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			// distanciaPercorrida = distancia percorrida + deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println("#" + idPessoa + "já andou " + distanciaPercorrida + " m. ");
		}
		posChegada++;
		System.out.println("#" + idPessoa + "foi o " + posChegada + "o. a chegar.");
	}

	private void pessoaParada() {
		//System.out.println("#" + idPessoa + " estacionou");
		int tempo = (int) ((Math.random() * 1001 + 1000));
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void pessoaSaindo() {
		posSaida++;
		System.out.println("#" + idPessoa + " foi o " + posSaida + "o. a sair");
	}
}
