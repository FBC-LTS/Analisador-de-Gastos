import tiposPack.Classificacao;

public class Gasto {
    String nome;
    double valor;
    int tipo; // 0= passivo ; 1= ativo ; 
    Classificacao classificacao;

    // contrutor com subcategoria
    public void Gasto(String nome, double valor, int tipo, int codCategoria, int subCategoria){
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo; // 0= passivo ; 1= ativo ; 
        this.classificacao = new Classificacao(codCategoria, subCategoria);
    }
    // construtor sem subcategoria
    public void Gasto(String nome, double valor, int tipo, int codCategoria){
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo; // 0= passivo ; 1= ativo ; 
        this.classificacao = new Classificacao(codCategoria);
    }

}