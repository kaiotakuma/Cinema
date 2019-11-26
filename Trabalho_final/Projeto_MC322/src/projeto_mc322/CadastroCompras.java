package projeto_mc322;

import java.util.ArrayList;

public class CadastroCompras {
    	
        private Vendedor v[]; // vetor de contas
        private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
	// Operacoes

    public CadastroCompras(int senha) {
        this.v = new Vendedor[4];
        v[1] = new Vendedor(senha);
        v[1].iniciarOperacao();
        System.out.println("Inicializacao Concluida");
        
        v[2] = new Vendedor(senha);
        v[2] .iniciarOperacao();
        System.out.println("Inicializacao Concluida");
        
        v[3] = new Vendedor(senha);
        v[3].iniciarOperacao();
        System.out.println("Inicializacao Concluida");
        
    }
        
	

	
}
