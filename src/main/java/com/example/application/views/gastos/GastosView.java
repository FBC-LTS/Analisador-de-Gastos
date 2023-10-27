package com.example.application.views.gastos;

import com.example.application.Analisador.Analise;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("Gastos")
@Route(value = "Gastos", layout = MainLayout.class)
public class GastosView extends VerticalLayout {

    public GastosView() {
        // inicio a analise pegando ela da sessão
        Analise analise = (Analise) VaadinSession.getCurrent().getAttribute("analiseAtual");
        if (analise == null){
            UI.getCurrent().navigate("");
        }else{
        String texto = analise.getTitulo() + "|" + analise.getFaturamento();
        add(new Paragraph(texto));
        
        } 
    }

}
