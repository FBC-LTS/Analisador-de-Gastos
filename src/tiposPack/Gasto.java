package tiposPack;

public class Gasto {
    public String nome;
    public int id;
    public double valor;
    public int tipo; // 0= passivo ; 1= ativo ; 
    private Classificacao classificacao;

    // contrutor com subcategoria
    public Gasto(String nome, int id, double valor, int tipo, int codCategoria, int subCategoria){
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo; // 0= passivo ; 1= ativo ; 
        this.classificacao = new Classificacao(codCategoria, subCategoria);
        this.id = id;
    }

    public double getValor() {
        return valor;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getTipo(){
        return tipo;
    }
    public String getClassificacao(){
        return this.classificacao.nome;
    }
}