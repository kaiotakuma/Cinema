package projeto_mc322;

public class ControladorIngresso {

    // Atributos
    //private CadastroContas dbContas; // Banco de dados das contas
    private Ingresso ingresso;

    // Construtor
    public ControladorIngresso(int senhaSessao, int sessao) {
        //dbContas = new CadastroContas();
        ingresso = new Ingresso(senhaSessao);
    }
    //Metodos gets e sets

    public Ingresso getIngresso() {
        return ingresso;
    }
    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }
    

    /*
	public ControladorCaixa(int senhaCaixa) {
		dbContas = new CadastroContas();
		caixa = new Caixa(senhaCaixa);
	}
     */
    public int verificaSessao() {
        if (ingresso.getSessaoEscolhida() == 1) {
            if (((ingresso.getSessao()).getContSessao1() >= 1) && ((ingresso.getSessao()).getContSessao1() < 3)) {
            	(ingresso.getSessao()).setContSessao1(((ingresso.getSessao()).getContSessao1() + 1));
                System.out.println("Sessao escolhida com sucesso");
                return 0;
            } else {
                System.out.println("Sessao esgotada !! ");
                return 1;
            }
        } else if (ingresso.getSessaoEscolhida() == 2) {
            if (((ingresso.getSessao()).getContSessao2() >= 1) && ((ingresso.getSessao()).getContSessao2() < 3)) {
            	(ingresso.getSessao()).setContSessao2(((ingresso.getSessao()).getContSessao2() + 1));
                System.out.println("Sessao escolhida com sucesso");
                return 0;
            } else {
                System.out.println("Sessao esgotada !! ");
                return 1;
            }
        }
        return 0;

    }

    public int consultarPoltrona(int pwd, int posLinha, double sessao) {

        if (ingresso.getSessao().validarSenha(pwd)) {
            System.out.println("Entrouuuu");
            if (ingresso.getSessao().getPoltronas()[posLinha] == 0) {
                System.out.println("Valor --> " + ingresso.getSessao().getPoltronas()[posLinha]);
                System.out.println("Sessao " + sessao + ":" + posLinha + " --> Disponivel");
                return 0;
            } else if (ingresso.getSessao().getPoltronas()[posLinha] == 1) {
                System.out.println("Sessao " + sessao + ":" + posLinha + " --> Oculpada");
                return 1;
            }

        } else {
            System.out.println("Senha incorreta! erro na verificacao");
        }
        return 0;
    }

    /*
        ##################Depois de implementar contaCor
        
	public void adicionar(int num, int pwd, float val) {
		ContaCor cta;
		if (val < 0 || (val % 10) != 0 || val > 200 || val > caixa.obterSaldoCaixa())
			return (false);
		cta = dbContas.buscarConta(num); // obtem a referencia para o objeto que
		// representa a conta 'num'
		if (cta == null) // se número de conta invalido ...

		if (cta.debitarValor("Saque Automatico", val, pwd) == false) // se saque
																		// recusado
			return (false); // retorna false
		caixa.liberarNotas((int) (val / 10)); // libera pagamento
		return (true);
	}
     */
    public void liberar(int pwd) {
        ingresso.getSessao().liberarSessao(pwd); // Sessao desoculpada para uso
    }

    public boolean validarSenha(int pwd) {
        return ingresso.getSessao().validarSenha(pwd);
    }

}
