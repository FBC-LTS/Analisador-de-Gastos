package com.example.application.views.documentação;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Analisador de Gastos")
@Tag("div")
@Route(value = "Documentacao", layout = MainLayout.class)
@StyleSheet("./estilos/documentacao.css")
public class DocumentaçãoView extends VerticalLayout {

    public DocumentaçãoView() {
        VerticalLayout container = new VerticalLayout();
        container.setId("container-principal");
        container.getElement().getStyle().set("width", "80%");
        container.setClassName("base-container");
        container.getElement().getThemeList().clear();

        container = documentacao(container);

        add(container);
    }

    private VerticalLayout documentacao(VerticalLayout container) {
        H1 titulo = new H1("O Analisador de Gastos");
        titulo.setClassName("titulos-doc");

        Paragraph texto0 = new Paragraph("É uma aplicação que consegue:");
        texto0.setClassName("textos-padroes");

        
        OrderedList consegue = new OrderedList();
        consegue.setClassName("textos-padroes");

        consegue.add(new ListItem("Tipificar os gastos entre em ativos e passivos"));
        consegue.add(new ListItem("Receber informações sobre o gasto para poder categoriza-lo em:"));


        UnorderedList gastoList = new UnorderedList();
        gastoList.add(new ListItem("Despesa"));
        gastoList.add(new ListItem("Custo - é subdividido em Industrial ou Comercial"));
        gastoList.add(new ListItem("Investimento"));
        gastoList.add(new ListItem("Perda"));
        gastoList.add(new ListItem("Desembolso"));

        consegue.add(gastoList);
        consegue.add(new ListItem("Calcular os totais de ativos e passivos"));
        consegue.add(new ListItem("Calcular o total de patrimônio líquido (diferença entre total ativo e total passivo)"));
        consegue.add(new ListItem("Entregar um relatório com a possibilidade de baixar um arquivo CSV com os dados"));



        H2 tituloComoUsar = new H2("Como Usar");
        tituloComoUsar.setClassName("titulos-doc");

        Div textoComoUsar = new Div();
        textoComoUsar.setId("linha-link");
        Paragraph parte1 = new Paragraph("Para começar a registrar seus gastos e analisá-los, o primeiro passo é acessar a página inicial, ");
        Anchor anchor = new Anchor("/", "Home, ");
        anchor.setId("link");
        Paragraph parte2 = new Paragraph("e clicar em \"Iniciar\".");

        textoComoUsar.add(parte1, anchor, parte2);

        HorizontalLayout comoInicio = new HorizontalLayout();
        comoInicio = boxComoInicio(comoInicio);

        Paragraph texto1 = new Paragraph("Após teremos um formulário como esse:");
        texto1.setClassName("textos-padroes");
        Div containerVideo = new Div();
        containerVideo.setId("pre-resultadovideo");
        containerVideo.setClassName("base-container");
        IFrame video = new IFrame("https://www.youtube.com/embed/O3kY7KEX9FE?si=KupjEo_ybkPogP9c");
        video.setId("video");
        containerVideo.add(video);

        Paragraph paragraph1 = new Paragraph("Inicialmente, na tela de Registro de Gastos, temos 3 campos, seguindo a ordem da esquerda para a direita:");
        paragraph1.setClassName("textos-padroes");

        OrderedList orderedList1 = new OrderedList();
        orderedList1.add(new ListItem("O primeiro campo é o nome do gasto."));
        orderedList1.add(new ListItem("O segundo campo é o tipo, se ele é Ativo ou Passivo."));
        orderedList1.add(new ListItem("O terceiro campo é o valor."));
        orderedList1.setClassName("textos-padroes");

        Paragraph paragraph2 = new Paragraph("Se o gasto for do tipo passivo, o sistema solicitará uma classificação adicional, que pode ser:");
        paragraph2.setClassName("textos-padroes");

        UnorderedList unorderedList1 = new UnorderedList();
        unorderedList1.add(new ListItem("Desembolso"));
        unorderedList1.add(new ListItem("Perda"));
        unorderedList1.add(new ListItem("Retornável (caso o gasto seja um investimento que gerará retorno no futuro)"));
        unorderedList1.add(new ListItem("E a opção para indicar se o gasto está relacionado à produção."));
        unorderedList1.setClassName("textos-padroes");

        Paragraph paragraph3 = new Paragraph("Se a opção 'Relacionado à Produção?' estiver selecionada, o programa solicitará uma subclassificação que categorizará o gasto em:");
        paragraph3.setClassName("textos-padroes");

        UnorderedList unorderedList2 = new UnorderedList();
        unorderedList2.add(new ListItem("Indiretamente = Despesa"));
        unorderedList2.add(new ListItem("Diretamente Comercial = Custo Comercial"));
        unorderedList2.add(new ListItem("Diretamente Industrial = Custo Industrial"));
        unorderedList2.setClassName("textos-padroes");

        Paragraph paragraph4 = new Paragraph("Após categorizar e preencher os dados do gasto, clique em \"Salvar\" para registrar o gasto na análise.");
        paragraph4.setClassName("textos-padroes");

        Paragraph paragraph5 = new Paragraph("Abaixo do formulário, temos uma pré-visualização que vai listando os gastos registrados, com o nome, tipo, a classificação e o valor do gasto.");
        paragraph5.setClassName("textos-padroes");

        Div texto2 = new Div();
        texto2.add(paragraph1, orderedList1, paragraph2, unorderedList1, paragraph3, unorderedList2, paragraph4, paragraph5);
        
        Div containerPreResultado = new Div();
        containerPreResultado.setId("cont-pre-resultado");
        containerPreResultado.setClassName("base-container");
        Image imagem = new Image("./images/pre-resultado.png", "Exemplo pré resultado");
        imagem.setId("img-pre");
        containerPreResultado.add(imagem);

        Div texto3 = new Div();
        Paragraph paragraph6 = new Paragraph("Errou? Tudo bem! Caso o gasto não tenha ficado do jeito que imaginou, é só clicar no 'X' vermelho na linha do gasto para remover o gasto da análise.");
        paragraph6.setClassName("textos-padroes");

        Paragraph paragraph7 = new Paragraph("Por fim, temos dois botões finais. Clique em \"Cancelar\" para cancelar o registro ou \"Finalizar\" para concluir o registro dos gastos e ir para a tela final de resultados.");
        paragraph7.setClassName("textos-padroes");
        texto3.add(paragraph6, paragraph7);

        H2 tituloFim = new H2("Fim!!");
        tituloFim.setClassName("titulos-doc");

        Div containerResultado = new Div();
        containerResultado.setId("cont-resultado");
        containerResultado.setClassName("base-container");
        Image img = new Image("./images/resultado.png", "Exemplo resultado");
        img.setId("img-res");
        containerResultado.add(img);

        Div texto4 = new Div();
        Paragraph paragraph8 = new Paragraph("Por fim, teremos uma tela como essa, onde no topo aparece o título da análise, seguido pelos valores de faturamento, total de ativos, total de passivos e total de patrimônio líquido. Abaixo, estarão listados todos os gastos.");
        paragraph8.setClassName("textos-padroes");

        Paragraph paragraph9 = new Paragraph("Ao final da página, teremos os botões \"Voltar ao Início\" para retornar à tela inicial e um botão para baixar a análise no formato CSV, como mostrado na imagem abaixo:");
        paragraph9.setClassName("textos-padroes");

        texto4.add(paragraph8, paragraph9);

        HorizontalLayout containerDownload = new HorizontalLayout();
        containerDownload = boxDownload(containerDownload);

        container.add(titulo, texto0, consegue, 
        tituloComoUsar, textoComoUsar, comoInicio, texto1, containerVideo, texto2, containerPreResultado, texto3, 
        tituloFim, containerResultado, texto4, containerDownload
        );

        return container;
    }

    private HorizontalLayout boxDownload(HorizontalLayout containerDownload) {
        Div containerimg1 = new Div();
        containerimg1.setId("cont-download");
        containerimg1.setClassName("base-container");
        Image img = new Image("./images/download.png", "Arquivo baixado com mesmo nome do Titulo da analise");
        img.setId("img-download");
        containerimg1.add(img);

        Div containerimg2 = new Div();
        containerimg2.setId("cont-csv");
        containerimg2.setClassName("base-container");
        Image img2 = new Image("./images/csv-exemplo.png", "Arquivo csv");
        img2.setId("img-csv");
        containerimg2.add(img2);


        containerDownload.getElement().getStyle().set("width", "110%");
        containerDownload.getElement().getStyle().set("margin-bottom", "30px");
        containerDownload.add(containerimg1, containerimg2);

        return containerDownload;
    }

    private HorizontalLayout boxComoInicio(HorizontalLayout comoInicio) {
        VerticalLayout containerEsquerda = new VerticalLayout();
        containerEsquerda.getElement().getThemeList().clear();
        containerEsquerda.setId("box-esquerda");
        Paragraph texto = new Paragraph("Após teremos um formulário como esse:");
        texto.setId("Texto-alto");
        Paragraph texto1 = new Paragraph("No primeiro campo, você irá inserir o título da análise, que serve para identificá-la. Quando a análise estiver concluída, o nome do arquivo CSV será o mesmo que o título.");
        Paragraph texto2 = new Paragraph("No segundo campo, você irá inserir o valor do faturamento. Lembre-se de que só é possível inserir números com até duas casas decimais.");

        Span exemplos = new Span("Exemplos ");
        Span validos = new Span("validos");
        validos.setId("valido");
        Span textoExe = new Span(": 1200358.55 999999999.99");
        Div texto3 = new Div();
        texto3.add(exemplos, validos, textoExe);

        Span exemplos2 = new Span("Exemplos ");
        Span validos2 = new Span("invalidos");
        validos2.setId("invalido");
        Span textoExe2 = new Span(": 1,200,358.55 999999999999.990");
        Div texto4 = new Div();
        texto4.add(exemplos2, validos2, textoExe2);

        containerEsquerda.add(texto, texto1, texto2, texto3, texto4);
        VerticalLayout containerDireita = new VerticalLayout();
        containerDireita.setClassName("container-imagem");
        containerDireita.setClassName("base-container");
        containerDireita.setId("pre-resultadoinicio");
        containerDireita.getElement().getThemeList().clear();
        Image imagem = new Image("./images/inicio.png", "Tela de Inicio");
        imagem.setId("img-inicio");
        containerDireita.add(imagem);
        
        comoInicio.setId("cont-ini");
        comoInicio.add(containerEsquerda, containerDireita);
        return comoInicio;
    }

}

