package view;

import java.util.concurrent.Semaphore;

import controller.ThreadFila;

public class Corredor {

	public static void main(String[] args) {
	
			int permissoes = 3;
			Semaphore semaforo = new Semaphore (permissoes);
			
			for (int idPessoa = 0; idPessoa <5; idPessoa ++){
				Thread tPessoa = new ThreadFila(idPessoa, semaforo);
				tPessoa.start();
			}
		
}
}
