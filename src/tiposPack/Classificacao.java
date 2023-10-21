package tiposPack;

public class Classificacao {
    String nome;
    int codCategoria; // 0 = desembolso ; 1 = perda ; 2 = despesa ; 3 = investimento ; 4 = custo ; 
    int subCategoria; // 0 = none ; 1 = comercial ; 2 = industrial ;

    // Construtor com ambas
    public Classificacao(int codCategoria, int subCategoria){
        this.codCategoria = codCategoria; // 0 = desembolso ; 1 = perda ; 2 = despesa ; 3 = investimento ; 4 = custo ; 
        this.subCategoria = subCategoria; // 0 = none ; 1 = comercial ; 2 = industrial ;
        nomearClassifcacao();
    }

    void nomearClassifcacao() {
        System.out.println("nomeando.");
        // nomear a string nome de acordo com os valores de codCategoria e subCategoria
        if(this.codCategoria == 4){
            System.out.println("subclassificando.");
            // classicar o {nome} + {industrial ou comercial} dependendo do subCategoria
        }
    }
}