package com.example.application.views.documentação;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style.Display;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;

@PageTitle("Analisador de Gastos")
@Route(value = "Documentacao", layout = MainLayout.class)
@StyleSheet("./estilo/documentacao.css")
public class DocumentaçãoView extends VerticalLayout {

    public DocumentaçãoView() {
        VerticalLayout container = new VerticalLayout();
        

        container = documentacao(container);

        add(container);
    }

    private VerticalLayout documentacao(VerticalLayout container) {
        H1 titulo = new H1("O Analisador de Gastos");
        Paragraph texto0 = new Paragraph("É uma aplicação que consegue:");

        // Criando uma lista numerada
        OrderedList consegue = new OrderedList();
        consegue.add(new ListItem("Tipificar os gastos entre em ativos e passivos"));
        consegue.add(new ListItem("Receber informações sobre o gasto para poder categoriza-lo em:"));

        // Criando uma lista não ordenada (bulleted list) dentro da lista numerada
        UnorderedList gastoList = new UnorderedList();
        gastoList.add(new ListItem("Despesa"));
        gastoList.add(new ListItem("Custo - é subdividido em Industrial ou Comercial"));
        gastoList.add(new ListItem("Investimento"));
        gastoList.add(new ListItem("Perda"));
        gastoList.add(new ListItem("Desembolso"));

        // Adicionando itens à lista numerada
        consegue.add(gastoList);
        consegue.add(new ListItem("Calcular os totais de ativos e passivos"));
        consegue.add(new ListItem("Calcular o total de patrimônio líquido (diferença entre total ativo e total passivo)"));
        consegue.add(new ListItem("Entregar um relatório com a possibilidade de baixar um arquivo CSV com os dados"));



        H2 tituloComoUsar = new H2("Como Usar");
        
        String htmlContent = "Para começar a registrar seus gastos e analisá-los, o primeiro passo é acessar a página inicial, <a href='/home'>Home</a>, e clicar em \"Iniciar\".";
        Html textoComoUsar = new Html(htmlContent);

        HorizontalLayout comoInicio = new HorizontalLayout();
        comoInicio = boxComoInicio(comoInicio);
        Paragraph texto1 = new Paragraph("Após teremos um formulário como esse:");

        Div containerVideo = new Div();


        Paragraph paragraph1 = new Paragraph("Inicialmente, na tela de Registro de Gastos, temos 3 campos, seguindo a ordem da esquerda para a direita:");

        OrderedList orderedList1 = new OrderedList();
        orderedList1.add(new ListItem("O primeiro campo é o nome do gasto."));
        orderedList1.add(new ListItem("O segundo campo é o tipo, se ele é Ativo ou Passivo."));
        orderedList1.add(new ListItem("O terceiro campo é o valor."));

        Paragraph paragraph2 = new Paragraph("Se o gasto for do tipo passivo, o sistema solicitará uma classificação adicional, que pode ser:");

        UnorderedList unorderedList1 = new UnorderedList();
        unorderedList1.add(new ListItem("Desembolso"));
        unorderedList1.add(new ListItem("Perda"));
        unorderedList1.add(new ListItem("Retornável (caso o gasto seja um investimento que gerará retorno no futuro)"));
        unorderedList1.add(new ListItem("E a opção para indicar se o gasto está relacionado à produção."));

        Paragraph paragraph3 = new Paragraph("Se a opção 'Relacionado à Produção?' estiver selecionada, o programa solicitará uma subclassificação que categorizará o gasto em:");

        UnorderedList unorderedList2 = new UnorderedList();
        unorderedList2.add(new ListItem("Indiretamente = Despesa"));
        unorderedList2.add(new ListItem("Diretamente Comercial = Custo Comercial"));
        unorderedList2.add(new ListItem("Diretamente Industrial = Custo Industrial"));

        Paragraph paragraph4 = new Paragraph("Após categorizar e preencher os dados do gasto, clique em \"Salvar\" para registrar o gasto na análise.");

        Paragraph paragraph5 = new Paragraph("Abaixo do formulário, temos uma pré-visualização que vai listando os gastos registrados, com o nome, tipo, a classificação e o valor do gasto.");
        Div texto2 = new Div();
        texto2.add(paragraph1, orderedList1, paragraph2, unorderedList1, paragraph3, unorderedList2, paragraph4, paragraph5);
        

        container.add(titulo, texto0, consegue, tituloComoUsar, textoComoUsar, comoInicio, texto1, containerVideo, texto2);

        return container;
    }

    private HorizontalLayout boxComoInicio(HorizontalLayout comoInicio) {
        VerticalLayout containerEsquerda = new VerticalLayout();
        Paragraph texto = new Paragraph("Após teremos um formulário como esse:");
        Paragraph texto1 = new Paragraph("No primeiro campo, você irá inserir o título da análise, que serve para identificá-la. Quando a análise estiver concluída, o nome do arquivo CSV será o mesmo que o título.");
        Paragraph texto2 = new Paragraph("No segundo campo, você irá inserir o valor do faturamento. Lembre-se de que só é possível inserir números com até duas casas decimais.");

        Span exemplos = new Span("Exemplos ");
        Span validos = new Span("validos");
        Span textoExe = new Span(": 1200358.55 999999999.99");
        Div texto3 = new Div();
        texto3.add(exemplos, validos, textoExe);

        Span exemplos2 = new Span("Exemplos ");
        Span validos2 = new Span("invalidos");
        Span textoExe2 = new Span(": 1,200,358.55 999999999999.990");
        Div texto4 = new Div();
        texto4.add(exemplos2, validos2, textoExe2);

        containerEsquerda.add(texto, texto1, texto2, texto3, texto4);
        VerticalLayout containerDireita = new VerticalLayout();
        Image imagem = new Image("./images/inicio.png", "Tela de Inicio");
        containerDireita.add(imagem);

        comoInicio.add(containerEsquerda, containerDireita);
        return comoInicio;
    }

}
