package projeto_mc322;

public class Sessao {
	// Constantes Static
	private static final int TAMANHO_MAX = 10;
	// Atributos
	private int lugares_disponiveis;
	private int lugares_oculpados;
	private int senhaadmin;
	private int poltronas[] = { 0, 0, 0 };
	public int contSessao1;
	public int contSessao2;

	// Construtor

	public int getContSessao1() {
		return contSessao1;
	}

	public void setContSessao1(int contSessao1) {
		this.contSessao1 = contSessao1;
	}

	public int getContSessao2() {
		return contSessao2;
	}

	public void setContSessao2(int contSessao2) {
		this.contSessao2 = contSessao2;
	}

	public Sessao(int sessao) {
		this.lugares_disponiveis = 3;
		this.lugares_oculpados = 0;
		if (sessao == 1) {
			contSessao1 +=1;
		}else if (sessao == 2) {
			contSessao2 +=1;
		}else {
			System.out.println("Escolha uma sessão válida");
		}
	}

	// Metodos acessores
	public int getLugares_disponiveis() {
		return lugares_disponiveis;
	}

	public void setLugares_disponiveis(int lugares_disponiveis) {
		this.lugares_disponiveis = lugares_disponiveis;
	}

	public int getLugares_oculpados() {
		return lugares_oculpados;
	}

	public void setLugares_oculpados(int lugares_oculpados) {
		this.lugares_oculpados = lugares_oculpados;
	}

	public int getSenhaadmin() {
		return senhaadmin;
	}

	public void setSenhaadmin(int senhaadmin) {
		this.senhaadmin = senhaadmin;
	}

	public int[] getPoltronas() {
		return poltronas;
	}



	public static int getTAMANHO_MAX() {
		return TAMANHO_MAX;
	}

	// Metodos auxiliares
	public void imprimeModeloSessao() {
		for (int j = 0; j < 3; j++) {
			if(this.poltronas[j] == 0) {
				System.out.print((j+1) + " ");
			}
			// System.out.printf("%.2f\t",this.getPoltronas()[i][j]);
		}
	}

	public void liberarSessao(int pwd) {
		if (this.senhaadmin == pwd) {
			// ######Chama funcao libera matriz de assentos
			// Depois colocar o valor de 10 mutavel
			for (int j = 0; j < 3; j++) {
				this.getPoltronas()[j] = 0;
			}

			System.out.println("Sessao liberada!");
			imprimeModeloSessao();

		} else {
			System.out.println("Senha incorreta!");
		}
	}

	public void adicionaLugar(int pwd, int linha) {
		if (this.senhaadmin == pwd) {
			// ######Chama funcao libera matriz de assentos
			// Depois colocar o valor de 3 mutavel
			for (int j = 0; j < 3; j++) {
				if ((j == linha)) {
					this.poltronas[j] = 1;
				}

			}
		} else {
			System.out.println("Senha incorreta!");
		}
	}

	// Retorna true se senha correta
	public boolean validarSenha(int pwd) {
		return this.senhaadmin == pwd;
	}

	public int poltronalivre(int polt) {
		for (int i = 0; i < poltronas.length; i++) {
			if(getPoltronas()[i] == 1) {
				System.out.println("Poltrona ocupada");
			}
		}
		return 0;
	}
}
