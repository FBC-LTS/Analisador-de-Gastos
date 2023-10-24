import java.util.ArrayList;
import java.util.List;
import tiposPack.Gasto;


public class Analise {
    private List<Gasto> listaDeGastos;
    private double faturamento;
    private String titulo;
    public double totalAtivo;
    public double totalPassivo;
    public double patLiq;
    private int currentId;
    
    public Analise(double faturamento, String titulo) {
        this.titulo = titulo;
        this.faturamento = faturamento;
        listaDeGastos = new ArrayList<>();

    }

    void registrarGasto(String nome, double valor, int tipo, int codCategoria, int subCategoria) {
        this.currentId = this.currentId+1;
        Gasto novoGasto = new Gasto(nome, this.currentId, valor, tipo, codCategoria, subCategoria);
        listaDeGastos.add(novoGasto);
        somarAtivo(novoGasto);
        somarPassivo(novoGasto);
    }
    void excluirGasto(int id) { // método para encontrar e remover uma instância da gasto dentro da listaDeGastos utilizando a variável "id" da classe Gasto
        Gasto gastoToRemove = null;
        for (Gasto gasto : listaDeGastos) {
            if (gasto.getId()==(id)) {
                gastoToRemove = gasto;
                subtrairAtivo(gastoToRemove);
                subtrairPassivo(gastoToRemove);
                break;
            }
        }
        if (gastoToRemove != null) {
            listaDeGastos.remove(gastoToRemove);
        }
    }
 
    void somarAtivo(Gasto novoGasto) {
        if (novoGasto.getTipo() == 0) { // Verifica se o novo gasto é ativo
            totalAtivo += novoGasto.valor; // Soma o valor do novo gasto ao total ativo
        } // Se for um passivo, retira do total de ativo

    }
    void subtrairAtivo(Gasto gastoToRemove) {
        if (gastoToRemove.getTipo() == 0) { // Verifica se o novo gasto é ativo
            totalAtivo -= gastoToRemove.valor; // Subtrai o valor do gasto que está sendo removido do total ativo
        } 
    }   

    void somarPassivo(Gasto novoGasto) {
        if (novoGasto.getTipo() == 1) {
            totalPassivo += novoGasto.valor;
        }
    }
    void subtrairPassivo(Gasto gastoToRemove) {
        if (gastoToRemove.getTipo() == 1) {
            totalPassivo -= gastoToRemove.valor; 
        }
    }   

    public void calcularPatLiq() {
        patLiq = totalAtivo - totalAtivo;
    }
    
    // Getters e Setters 
    public String getTitulo(){
        return this.titulo;
    }
    public double getFaturamento() {
        return this.faturamento;
    }
    public List<Gasto> getListaDeGastos() {
        return listaDeGastos;
    }
    public double getTotalAtivo() {
        return totalAtivo;
    }
    public double getTotalPassivo() {
        return totalPassivo;
    }
    public double getPatLiq() {
        return patLiq;
    }
}
