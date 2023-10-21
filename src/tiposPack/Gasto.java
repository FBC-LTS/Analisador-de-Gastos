package tiposPack;

public class Gasto {
    String nome;
    double valor;
    int tipo; // 0= passivo ; 1= ativo ; 
    private Classificacao classificacao;

    // contrutor com subcategoria
    public Gasto(String nome, double valor, int tipo, int codCategoria, int subCategoria){
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo; // 0= passivo ; 1= ativo ; 
        this.classificacao = new Classificacao(codCategoria, subCategoria);
    }

    public String getClassificacao(){
        return this.classificacao.nome;
    }
}