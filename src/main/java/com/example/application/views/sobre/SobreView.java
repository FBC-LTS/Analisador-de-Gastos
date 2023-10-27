package com.example.application.views.sobre;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Sobre")
@Route(value = "Sobre", layout = MainLayout.class)
@StyleSheet("./estilos/sobre.css")
public class SobreView extends VerticalLayout {

    public SobreView() {
        // criando uma div principal
        VerticalLayout container = new VerticalLayout();
            // adicionando a classe container no elemento para estilisar
        container.addClassName("container");
            // criando e adicionando o titulo
        H1 titulo = new H1("Sobre");
        titulo.addClassName("centraliza-titulo");
        container.add(titulo);
        // criando div horizontal para as informações ficarem lado a lado
        HorizontalLayout container_campos = new HorizontalLayout();

        VerticalLayout container_sobre = new VerticalLayout();
        VerticalLayout container_autores = new VerticalLayout();
        
        

        container_sobre = sobreProjeto(container_sobre);
        //container_autores = autoresProjeto(container_autores);

        // adicionando no container horizontal
        container_campos.add(container_sobre);
        container_campos.add(container_autores);
        
        // adicionando tudo na tela
        container.add(container_campos);
        
        add(container);
    }

    private VerticalLayout sobreProjeto(VerticalLayout container){
        H2 titulo_analisador = new H2("O Analisador de Gastos");
        Text texto_tst = new Text("é uma aplicação intuitiva e eficiente projetada para simplificar o processo de registro, \r\r\n" + //
                "categorização e análise de despesas financeiras pessoais ou comerciais. Essa ferramenta \r\r\n" + //
                "versátil oferece recursos essenciais para auxiliar os usuários a manter o controle de seus \r\n" + //
                "gastos, \rcategorizá-los e gerar relatórios detalhados em formato CSV.");
        H2 titulo_app = new H2("É uma aplicação feita");
        
        container.add(titulo_analisador);
        container.add(texto_tst);
        container.add(titulo_app);
        return container;
    }

    
}
