package AtividadeBusca;

import java.util.concurrent.Semaphore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Buscador extends Thread{
    private Semaphore sem = new Semaphore(2);
    private Explorador exp = new Explorador();


    public void buscar(String chave) throws FileNotFoundException {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean achou = false;
        String arquivoEncontrou = "";
        int k = 0;

        ArrayList<File> caminhos = exp.listar("C:\\Users\\mateu\\OneDrive\\Documentos\\BCC\\BCC-AltoDesempenho\\arquivosNomes", ".txt");
        String nome = "";
        for(int i = 0; i < caminhos.size(); i++) {
            if(!achou) {
                k = 0;
            }
            Scanner in = new Scanner(new FileReader(caminhos.get(i)));
            while (in.hasNextLine() && !achou) {
                k++;
                String line = in.nextLine();
                if(line.toLowerCase().contains(chave.toLowerCase())) {
                    achou = true;
                    arquivoEncontrou = caminhos.get(i).toString();
                    nome = line;
                    try {
                    	Thread.sleep(3000);
                    }catch (Exception e) {
                    	System.out.println(e);
					}
                }
            }
            in.close();
        }

        if(achou) {
            System.out.println("A chave "+chave+" encontrou "+nome+" no arquivo "+arquivoEncontrou+ " na linha "+k+ ".");
        } else {
            System.out.println("Não foi possível encontrar a chave.");
        }

        sem.release();
    }

}