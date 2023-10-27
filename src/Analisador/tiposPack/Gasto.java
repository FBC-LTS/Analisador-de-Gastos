package Analisador.tiposPack;

public class Gasto {
    public String nome;
    public int id;
    public double valor;
    public int tipo; // 0= passivo ; 1= ativo ;
    private Classificacao classificacao;

    // contrutor com subcategoria
    public Gasto(String nome, int id, double valor, int tipo, int codCategoria, int subCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo; // 0= passivo ; 1= ativo ;
        this.classificacao = new Classificacao(codCategoria, subCategoria);
        this.id = id;
    }

    public String leitor() {
        return "Nome: " + this.nome + ", Valor: " + this.valor + ", Tipo: " + (this.tipo == 0 ? "Ativo" : "Passivo")
                + ", Classificação: " + this.classificacao.nome;
    }

    public double getValor() {
        return this.valor;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getTipo() {
        return this.tipo;
    }

    public String getClassificacao() {
        return this.classificacao.nome;
    }
}