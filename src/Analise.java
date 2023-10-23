import java.util.ArrayList;
import java.util.List;
import tiposPack.Gasto;

public class Analise {
    private List<Gasto> listaDeGastos;
    private double faturamento;
    private String titulo;
    private double diferenca;
    public double totalAtivo;
    public double totalPassivo;
    
    
    public Analise(double faturamento, String titulo) {
        this.titulo = titulo;
        this.faturamento = faturamento;
        listaDeGastos = new ArrayList<>();

    }
    public void registrarGasto(String nome, int tipo, double valor) {
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
        System.out.println("Gasto registrado: R$" + valor + tipo + nome);
        registrarGasto rg = new registrarGasto();

    }

    public void excluirGasto(Gasto gasto, List<Gasto> listaGastos ) {
        listaGastos.remove(gasto);
        System.out.println("Gasto excluido!");

    }

    void calcularAtivo() {

    }

    void calcularPassivo() {

    }

    void calcularPatLiq() {

    }
    double getDiferenca(){
        return this.diferenca;
    }

    String getTitulo(){
        return this.titulo;
    }

    double getFaturamento() {
        return this.faturamento;
    }

    List<Gasto> getListaDeGastos() {
        return listaDeGastos;
    }
}
