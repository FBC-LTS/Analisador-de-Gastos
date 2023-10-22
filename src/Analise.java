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
    void registrarGasto() {

    }

    void excluirGasto() {

    }

  void calcularAtivo() {
        totalAtivo = 0.0; // Inicializa o totalAtivo com zero

        for (Gasto gasto : listaDeGastos) {
            // Chama o método getTipo para obter o tipo de cada gasto
            int tipoDoGasto = gasto.getTipo();

            // Faça o que for necessário com o tipo do gasto
            // Por exemplo, você pode somar os gastos ativos
            if (tipoDoGasto == 1) { // 1 representa gasto ativo
                totalAtivo += gasto.valor;
            }
        }
    }

    void calcularPassivo() {
        totalPassivo = 0.0; // Inicializa o totalPassivo com zero

        for (Gasto gasto : listaDeGastos) {
            // Chama o método getTipo para obter o tipo de cada gasto
            int tipoDoGasto = gasto.getTipo();

            // Faça o que for necessário com o tipo do gasto
            // Por exemplo, você pode somar os gastos passivos
            if (tipoDoGasto == 2) { // 2 representa gasto passivo
                totalPassivo += gasto.valor;
            }
        }
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
