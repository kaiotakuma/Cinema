package projeto_mc322;

import java.util.ArrayList;

public class Ingresso {
        
        private int NUMEROSESSAO ; //Quantidade de Sessoes
    //Quantidade de Sessoes
        
        //Atibutos
        private String identificacao; // nome do titular
        private String filmeEscolhido;
        private int sessaoEscolhida;//1,2 ou 3 ...
        private boolean sessaoCheia; //Verifica se a sessão esrta cheia
        private int poltronaScolhida;//1;1
        private String tipoIngresso;
        private Sessao sessao;
        private ArrayList<Integer> sessoes_escolhidas = new ArrayList<Integer>();
        //Contrutor 
        public Ingresso(int senhaSessao) {
        	this.NUMEROSESSAO = 2;
        }

        //Metodos acessores 

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public int getNUMEROSESSAO() {
        return NUMEROSESSAO;
    }

    public void setNUMEROSESSAO(int NUMEROSESSAO) {
        this.NUMEROSESSAO = NUMEROSESSAO;
    }
     
    
  

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getFilmeEscolhido() {
        return filmeEscolhido;
    }

    public void setFilmeEscolhido(String filmeEscolhido) {
        this.filmeEscolhido = filmeEscolhido;
    }

    public int getSessaoEscolhida() {
    	
        return sessaoEscolhida;
    }

    public int setSessaoEscolhida(int sessaoEscolhida) {
    	if(sessoes_escolhidas.size()<1) {
	    	this.sessao = new Sessao(sessaoEscolhida);
	        this.sessaoEscolhida = sessaoEscolhida;
	        sessoes_escolhidas.add(sessaoEscolhida);
        }else {
        	int j=0;
        	for (int i = 0; i < sessoes_escolhidas.size(); i++) {
        		j = sessoes_escolhidas.get(i);
				if(sessaoEscolhida == j) {
					return 0;
				}
			}
        }
        this.sessaoEscolhida = sessaoEscolhida;
        sessoes_escolhidas.add(sessaoEscolhida);
    	return 1;
    }

    public int getPoltronaScolhida() {
        return poltronaScolhida;
    }

    public void setPoltronaScolhida(int poltronaScolhida) {
        this.poltronaScolhida = poltronaScolhida;
    }

    public String getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public boolean isSessaoCheia() {
        return sessaoCheia;
    }

    public void setSessaoCheia(boolean sessaoCheia) {
        this.sessaoCheia = sessaoCheia;
    }
    
        
}
