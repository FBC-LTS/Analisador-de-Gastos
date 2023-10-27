package Analisador.tiposPack;

public class Classificacao {
    public String nome;
    public int codCategoria; // 0 = desembolso ; 1 = perda ; 2 = despesa ; 3 = investimento ; 4 = custo ; 
    public int subCategoria; // 0 = none ; 1 = comercial ; 2 = industrial ;

    // Construtor com ambas
    public Classificacao(int codCategoria, int subCategoria){
        this.codCategoria = codCategoria; // 0 = desembolso ; 1 = perda ; 2 = despesa ; 3 = investimento ; 4 = custo ; 
        this.subCategoria = subCategoria; // 0 = none ; 1 = comercial ; 2 = industrial ;
        nomearClassifcacao();
    }

    public void nomearClassifcacao() {
        if(this.codCategoria == 0){
            this.nome = "Desembolso";
        }
        else if(this.codCategoria == 1){
            this.nome = "Perda";
        }
        else if(this.codCategoria == 2){
            this.nome = "Despesa";
        }
        else if(this.codCategoria == 3){
            this.nome = "Investimento";
        }
        else if(this.codCategoria == 4){
            this.nome = "Custo";
            subClassifcar();
        }
    }
    
    private void subClassifcar(){
        if(this.subCategoria == 1){
            this.nome = this.nome + " comercial";
        }
        else if(this.subCategoria == 2){
            this.nome = this.nome + " industrial";
        }
    }

    public String getNome() {
        return this.nome;
    }

}