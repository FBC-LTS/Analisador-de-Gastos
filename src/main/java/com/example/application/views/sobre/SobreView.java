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
        VerticalLayout container = new VerticalLayout();
        HorizontalLayout container_campos = new HorizontalLayout();
        VerticalLayout container_sobre = new VerticalLayout();
        VerticalLayout container_autores = new VerticalLayout();
        H1 titulo = new H1("Sobre");
        H1 titulo_analisador = new H1("O Analisador de Gastos");
        Text texto_tst = new Text("é uma aplicação intuitiva e eficiente projetada para simplificar o processo de registro, \r\r\n" + //
                "categorização e análise de despesas financeiras pessoais ou comerciais. Essa ferramenta \r\r\n" + //
                "versátil oferece recursos essenciais para auxiliar os usuários a manter o controle de seus \r\n" + //
                "gastos, \rcategorizá-los e gerar relatórios detalhados em formato CSV.");

        container.addClassName("container");
        titulo.addClassName("centraliza-titulo");
        container_sobre.add(titulo_analisador);
        container_sobre.add(texto_tst);
        container.add(titulo);
        container_campos.add(container_sobre);
        container_campos.add(container_autores);
        container.add(container_campos);
        add(container);
    }

}
