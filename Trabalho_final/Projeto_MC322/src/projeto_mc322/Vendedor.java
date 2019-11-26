package projeto_mc322;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Vendedor {
	// Constantes
	public static int MODO_SUPERVISOR = 0;
	public static int MODO_CLIENTE = 1;
	public static int senha = 0;
	private static int VALOR = 14;

	// Atributos
	private ControladorIngresso controladorIngresso;
	private String filmes[] = { "The Joker", "Avengers" };
	private String sessao[] = { "14:00", "20:00" };
	private ArrayList<Integer> lugares1 = new ArrayList<Integer>();
	private ArrayList<Integer> lugares2 = new ArrayList<Integer>();
	private String tipoPagamento[] = { "Credito", "Debito", "Dinheiro" };
	private int quantIngresso;// Quantitades de ingressos
	private int modoAtual; // modo de operacao atual: 0=supervisor, 1=cliente
	private ArrayList<Ingresso> carrinho;
	private Sessao se;

	// Operacoes
	public Vendedor(int senhaCaixa) {
		this.controladorIngresso = new ControladorIngresso(senhaCaixa, 0);
		this.quantIngresso = 0;
		this.carrinho = new ArrayList<Ingresso>();
		lugares1.add(1);
		lugares1.add(2);
		lugares1.add(3);
		lugares2.add(1);
		lugares2.add(2);
		lugares2.add(3);
		
	}
	// Metodos Gets e sets

	public static int getMODO_SUPERVISOR() {
		return MODO_SUPERVISOR;
	}

	public static void setMODO_SUPERVISOR(int MODO_SUPERVISOR) {
		Vendedor.MODO_SUPERVISOR = MODO_SUPERVISOR;
	}

	public static int getMODO_CLIENTE() {
		return MODO_CLIENTE;
	}

	public static void setMODO_CLIENTE(int MODO_CLIENTE) {
		Vendedor.MODO_CLIENTE = MODO_CLIENTE;
	}

	public static int getVALOR() {
		return VALOR;
	}

	public static void setVALOR(int VALOR) {
		Vendedor.VALOR = VALOR;
	}

	public ControladorIngresso getControladorIngresso() {
		return controladorIngresso;
	}

	public void setControladorIngresso(ControladorIngresso controladorIngresso) {
		this.controladorIngresso = controladorIngresso;
	}

	public String[] getFilmes() {
		return filmes;
	}

	public void setFilmes(String[] filmes) {
		this.filmes = filmes;
	}

	public String[] getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String[] tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public int getQuantIngresso() {
		return quantIngresso;
	}

	public void setQuantIngresso(int quantIngresso) {
		this.quantIngresso += quantIngresso;
	}

	public int getModoAtual() {
		return modoAtual;
	}

	public void setModoAtual(int modoAtual) {
		this.modoAtual = modoAtual;
	}
	
	

	/*
	 * 1 - Qual filme 2 - Qual sessao 3 - Quanto ingressos 4 - Tipo de ingresso
	 * 
	 */

	public void iniciarOperacao() {
		// Pegando dados do teclado
		Scanner teclado = new Scanner(System.in);
		// Apresentacao
		System.out.println("---------------- Bem Vindo Ao Cinema Kinoplex  -------------\n");
		int valor = 0;
		System.out.print("Tudo bem? Serei Seu / Sua Atendente , qual seu nome? ");
		String nome = teclado.nextLine();
		this.controladorIngresso.getIngresso().setIdentificacao(nome);
		System.out.print("Quantos ingressos deseja comprar? ");
		int qtd = teclado.nextInt();
		this.setQuantIngresso(qtd);
		if(qtd>0) {
			System.out.println("Seja Bem vindo " + this.controladorIngresso.getIngresso().getIdentificacao() + " escolha uma das opcoes de filmes: ");
			for (int i = 0; i < filmes.length; i++) {
				System.out.println(i+1 + " --> " + filmes[i]);
			}
			// Depois por uma exption ou while para consertar erro de usuario burro
			System.out.print("Opção de filme: ");
			int op = teclado.nextInt();
			System.out.println("Voce escolheu o filme  " + filmes[op-1]);
			this.controladorIngresso.getIngresso().setFilmeEscolhido(filmes[op-1]);
			System.out.println("Para o filme " + filmes[op-1] + " temos um total de "
					+ controladorIngresso.getIngresso().getNUMEROSESSAO() + " Sessoes");
			for (int i = 0; i < sessao.length; i++) {
				System.out.println(i+1 + " --> " + sessao[i]);
			}
			System.out.print("Digite a sessao de sua escolha: ");
			int s = teclado.nextInt();
			this.controladorIngresso.getIngresso().setSessaoEscolhida(s);
			if (qtd > this.controladorIngresso.getIngresso().getSessao().getLugares_disponiveis()){
				System.out.println("Número de lugares disponíveis insulficientes para sua compra");
				System.out.println("------------------- Reiniciar compra -----------------------");
				this.iniciarOperacao();
			}else {
				System.out.println("Qual tipo de ingresso voce necessita?  (meia ou inteira) ");
				String tipoIngresso = teclado.next();
				if(tipoIngresso.equalsIgnoreCase("meia")) {
					valor += 7;
				}else {
					valor += 14;
				}
				this.controladorIngresso.getIngresso().setTipoIngresso(tipoIngresso);
				System.out.println("Voce escolheu o tipo de ingresso " + tipoIngresso);
				this.controladorIngresso.getIngresso().getSessao().setLugares_oculpados(this.controladorIngresso.getIngresso().getSessao().getLugares_oculpados() + 1);
				this.controladorIngresso.getIngresso().getSessao().setLugares_disponiveis(this.controladorIngresso.getIngresso().getSessao().getLugares_disponiveis() - 1);
				this.controladorIngresso.getIngresso().getSessao().imprimeModeloSessao();
				System.out.println("");
				System.out.print("Qual poltrona deseja sentar? ");
				int polt = teclado.nextInt();
				if (s == 1) {
					lugares1.remove(polt-1);
				}else if(s == 2){
					lugares2.remove(polt-1);
				}
				this.controladorIngresso.getIngresso().setPoltronaScolhida(polt-1);
				this.controladorIngresso.getIngresso().getSessao().adicionaLugar(senha, (polt-1));
				carrinho.add(this.controladorIngresso.getIngresso());
				qtd -= 1;
				
			}
			while (qtd>0) {
				controladorIngresso = new ControladorIngresso(senha, s);
				System.out.println("------------ Com mais gente é mais divertido -------------\n");
				System.out.print("Como é o nome do seu (sua) acompanhante? ");
				Scanner tecla = new Scanner(System.in);
				String novonome = tecla.nextLine();
				this.controladorIngresso.getIngresso().setIdentificacao(novonome);
				qtd -= 1;
				this.controladorIngresso.getIngresso().setFilmeEscolhido(filmes[op-1]);
				this.controladorIngresso.getIngresso().setSessaoEscolhida(s);
				System.out.println("Qual tipo de ingresso voce necessita?  (meia ou inteira) ");
				String tipoIngresso = teclado.next();
				if(tipoIngresso.equalsIgnoreCase("meia")) {
					valor += 7;
				}else {
					valor += 14;
				}
				this.controladorIngresso.getIngresso().setTipoIngresso(tipoIngresso);
				this.controladorIngresso.getIngresso().getSessao().setLugares_oculpados(this.controladorIngresso.getIngresso().getSessao().getLugares_oculpados() + 1);
				this.controladorIngresso.getIngresso().getSessao().setLugares_disponiveis(this.controladorIngresso.getIngresso().getSessao().getLugares_disponiveis() - 1);
				System.out.print("");
				System.out.println("Poltronas:");
				if(s == 1) {
					for (int i = 0; i < lugares1.size(); i++) {
						System.out.print(lugares1.get(i)+ " ");
					}
					System.out.println("Qual poltrona deseja sentar?");
					int polt = teclado.nextInt();
					if(!(lugares1.contains(polt))) {
						System.out.println("Poltrona inválida");
						iniciarOperacao();
					}
					if (s == 1) {
						lugares1.remove(polt-1);
					}else if(s == 2){
						lugares2.remove(polt-1);
					}
				}else {
					for (int i = 0; i < lugares2.size(); i++) {
						System.out.print(lugares2.get(i));
					}
					System.out.println("Qual poltrona deseja sentar?");
					int polt = teclado.nextInt();
					if(!(lugares2.contains(polt))) {
						System.out.println("Poltrona inválida");
					}
					if (s == 1) {
						lugares1.remove(polt-1);
					}else if(s == 2){
						lugares2.remove(polt-1);
					}
				}
				
			
				
			}
				
				
				
			}
			System.out.println("Formas de pagamento");
			for (int i = 0; i < tipoPagamento.length; i++) {
				System.out.println(i+1 + " --> " + tipoPagamento[i]);
			}
			System.out.println("Qual a forma de pagamento?");
			int pag = teclado.nextInt();
			switch (pag) {
			//private String tipoPagamento[] = { "Credito", "Debito", "Dinheiro" };
			case 1:
				System.out.println("Dados do cartão de crédito:");
				System.out.println("Valor: "+ valor);
				System.out.print("Nome do cartão:");
				String nome_c = teclado.nextLine();
				System.out.print("Digite o número do cartão:");
				String num_c = teclado.nextLine();
				System.out.print("Data de validade:");
				String data_c = teclado.nextLine();
				System.out.print("CVV:");
				String cvv_c = teclado.nextLine();
				break;
			case 2:
				System.out.println("Dados do cartão de débito:");
				System.out.println("Valor: "+ valor);
				System.out.print("Nome do cartão:");
				String nome_d = teclado.nextLine();
				System.out.print("Digite o número do cartão:");
				String num_d = teclado.nextLine();
				System.out.print("Data de validade:");
				String data_d = teclado.nextLine();
				System.out.print("CVV:");
				String cvv_d = teclado.nextLine();
				break;
			case 3:
				System.out.println("Dados do boleto:");
				System.out.println("Valor: "+ valor);
				System.out.println("Código de barras do boleto:");
				Random gerador = new Random();
		        //imprime sequência de 10 números inteiros aleatórios
		        System.out.println(gerador.nextInt());
				
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + pag);
			
			}
		
			System.out.println("-------------------Compra confirmada-----------------");
			System.out.println("Agradecemos a confiança em nosso cinema");
			System.out.println("Dados da sua compra:");
			for (int i = 0; i < carrinho.size(); i++) {
				System.out.println("Ingresso "+ (i+1));
				System.out.println("Filme escolhido: " + carrinho.get(i).getFilmeEscolhido());
				System.out.println("Nome: " + carrinho.get(i).getIdentificacao());
				System.out.println("Sessão escolhida: " + carrinho.get(i).getSessaoEscolhida());
				System.out.println("Poltrona escolhida: " + (carrinho.get(i).getPoltronaScolhida())+1);
				System.out.println("Tipo de ingresso:" + carrinho.get(i).getTipoIngresso());
			}
			System.out.println("Valor: " + valor);
			if ((pag-1) == 0) {
				System.out.println("Tipo de pagamento: Cartão de crédito");
			}else if ((pag-1) == 1) {
				System.out.println("Tipo de pagamento: Cartão de débito");
			}else {
				System.out.println("Tipo de pagamento: Boleto");
			}
			this.iniciarOperacao();
			
			
		}
	
	

	// Passando uma String como pergunta , Pede um valor ao usuario Ã© o retorna
	private int getInt(String str) {
		Reader r = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(r);

		System.out.println("Entre com " + str);
		try {
			st.nextToken();
		} catch (IOException e) {
			System.out.println("Erro na leitura do teclado");
			return (0);
		}
		return ((int) st.nval);
	}

	void alternarModo(int senhaSupervisor) {
		if (this.controladorIngresso.validarSenha(senhaSupervisor)) {
			if (this.modoAtual == Vendedor.MODO_SUPERVISOR)
				this.modoAtual = Vendedor.MODO_CLIENTE;
			else
				this.modoAtual = Vendedor.MODO_SUPERVISOR;
		}

	}
}
