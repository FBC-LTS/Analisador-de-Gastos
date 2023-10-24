import java.util.ArrayList;
import java.util.List;
import tiposPack.Gasto;

public class Analisador {
    public static void main(String[] args) {
        // Inicialização da lista de gastos
        List<Gasto> listaDeGastos = new ArrayList<>();

        // Criação de uma instância de Analise
        Analise analise = new Analise(1000.0, "Primeira Análise", listaDeGastos); // Supondo que o construtor de Analise requer uma lista de gastos

        // Exemplo de registro de gastos
        analise.registrarGasto("Gasto 1", 100.0, 0, 1, 1);
        analise.registrarGasto("Gasto 3", 100.0, 0, 4, 1); // Gasto ativo
        analise.registrarGasto("Gasto 2", 50.0, 1, 2, 2); // Gasto passivo

        // Exemplo de exclusão de gasto
        analise.excluirGasto(1); // Supondo que 1 seja o ID do gasto a ser excluído
        // Exibir o total ativo
        System.out.println("Total Ativo: " + analise.getTotalAtivo());
        System.out.println(analise.getListaDeGastosString());
    }

    void exportarCsv() {
        
    }
}
