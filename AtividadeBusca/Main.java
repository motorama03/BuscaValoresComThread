package AtividadeBusca;

import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		String[] chave = {"GuiDO","mati","JOA","cur","Gilvan Valim"};
		Buscador b = new Buscador();
		for(String valor:chave) {
			new Thread(()->{
				try {
					b.buscar(valor);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			},"Thread-"+valor).start();
		}
	}

}
