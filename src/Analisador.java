import java.util.ArrayList;
import java.util.List;

import Analisador.Analise;
import Analisador.tiposPack.Gasto;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Analisador {
    public static void main(String[] args) {
        // Inicialização da lista de gastos
        List<Gasto> listaDeGastos = new ArrayList<>();

        // Criação de uma instância de Analise
        Analise analise = new Analise(1000.0, "Primeira Análise", listaDeGastos ); // Supondo que o construtor de Analise requer uma lista de gastos

        // Exemplo de registro de gastos
        analise.registrarGasto("Gasto 1", 100.0, 0, 1, 1);
        analise.registrarGasto("Gasto 2", 100.0, 0, 4, 1);
        analise.registrarGasto("Gasto 3", 200.0, 1, 3, 2);
        analise.registrarGasto("Gasto 4", 300.0, 1, 2, 2);
        analise.registrarGasto("Gasto 5", 150.0, 0, 1, 2);
        analise.registrarGasto("Gasto 6", 240.0, 0, 3, 2);
        analise.registrarGasto("Gasto 7", 355.5, 1, 4, 2);
        analise.registrarGasto("Gasto 8", 1000.0, 0, 4, 2);
        System.out.println(analise.getListaDeGastosString());
        // Exemplo de exclusão de gasto
        analise.excluirGasto(1); // Supondo que 1 seja o ID do gasto a ser excluído
        //Calculando o Patrimônio Líquido após os registros e exclusões
        analise.calcularPatLiq();
        // Exibir o total ativo, total passivo, e o patrimônio líquido
        System.out.println("Total Ativo: " + analise.getTotalAtivo());
        System.out.println("Total Passivo: " + analise.getTotalPassivo());
        System.out.println("Patrimônio Líquido: " + analise.getPatLiq());
        System.out.println(analise.getListaDeGastosString());
        
        //Exportador para CSV (StandardCharsets.UTF_8 para ler os carácteres especiais)
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("analisedegastos.csv"), StandardCharsets.UTF_8))) {
            writer.write("Nome, Valor, Tipo, Classificação\n");
        
            for (Gasto gasto : listaDeGastos) {
                writer.write(gasto.getNome() + ", " + gasto.getValor() + ", " + (gasto.getTipo() == 0 ? "Ativo" : "Passivo") + ", " + gasto.getClassificacao() + "\n");
            }
            String valorFormatado = String.format("Total de Ativos: %.2f, Total de Passivos: %.2f, Patrimônio Líquido: %.2f", analise.getTotalAtivo(), analise.getTotalPassivo(), analise.getPatLiq());
            writer.write(valorFormatado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

