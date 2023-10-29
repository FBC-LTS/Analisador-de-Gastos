package com.example.application.views.sobre;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
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

    private Component novo_titulo_tst;
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
<<<<<<< Updated upstream
        //container_autores = autoresProjeto(container_autores);

        // adicionando no container horizontal
=======
        container_autores = autoresProjeto(container_autores);
        
>>>>>>> Stashed changes
        container_campos.add(container_sobre);
        container_campos.add(container_autores);
        
        // adicionando tudo na tela
        container.add(container_campos);
        
        add(container);
    }

    private VerticalLayout sobreProjeto(VerticalLayout container){
        H2 titulo_analisador = new H2("O Analisador de Gastos");
        Text texto_tst = new Text("É uma aplicação intuitiva e eficiente projetada para simplificar o processo de registro, \r\r\n" + //
                "categorização e análise de despesas financeiras pessoais ou comerciais. Essa ferramenta \r\r\n" + //
                "versátil oferece recursos essenciais para auxiliar os usuários a manter o controle de seus \r\n" + //
                "gastos, \rcategorizá-los e gerar relatórios detalhados em formato CSV.");
        H2 titulo_app = new H2("É uma aplicação feita");
<<<<<<< Updated upstream
        
        container.add(titulo_analisador);
        container.add(texto_tst);
        container.add(titulo_app);
=======
        Text texto_ts = new Text("Por alunos da Universidade Estácio, que estão cursando Análise e Desenvolvimento de \r\r\n" + // 
                "Sistemas e Ciências da Computação. Eles estavam estudando a disciplina de Programação \r\r\n" + //
                "Orientada a Objetos em Java, ministrada pelo professor Almir Rogério de Macedo.");
        Text texto_ts2 = new Text("Para a aluna Lara Guimarães, estudante de Ciências Contábeis, que desempenhou o papel \r\r\n" + //
                "de explicar como a aplicação deveria funcionar e acompanhar o progresso do projeto para \r\n" + //
                "garantir que estivesse alinhado com suas expectativas.");
        H2 outra_titulo_app = new H2("De alunos para alunos");
        Text texto_ts3 = new Text("Desenvolvida utilizando técnicas que aprendemos em outros semestres durante o curso,\r\r\n" + //
                "como Kanban, metodologia RAD, GIT, entre outros. Feita durante o segundo semestre de\r\n" + //
                "2023.");
        container.add(titulo_analisador, texto_tst, titulo_app, texto_ts, texto_ts2, outra_titulo_app, texto_ts3);            
        return container;
    }

    private VerticalLayout autoresProjeto(VerticalLayout container){
        H2 titulo_autores = new H2("Autores:");
        Text novo_texto_tst = new Text("Nome: Fabrício Lustosa de Oliveira Alencar Cunha.\n" + 
        "Matrícula:	202109059831\n" + 
        "Curso:	Análise e desenvolvimento de sistemas\n" + 
        "Responsabilidades: Líder e Documentação\n" + 
        
        "Nome:	Lucimar Barros de Oliveira.\n" + 
        "Matrícula:	202109086758\n" + 
        "Curso:	Análise e desenvolvimento de sistemas\n" + 
        "Responsabilidades: Back-end / Front-end\n" + 
        
        "Nome:	Nícolas Moniz Rodrigues.\n" + 
        "Matrícula: 201902180259\n" + 	
        "Curso:	Ciências da computação\n" + 
        "Responsabilidades: Back-end\n" + 
            
        "Nome:	Bruno Santana de Sousa.\n" + 
        "Matrícula:	202204064031\n" + 
        "Curso:	Análise e desenvolvimento de sistemas\n" + 
        "Responsabilidades: Front-end e Design");
        container.add(titulo_autores);
        container.add(novo_texto_tst);
        
>>>>>>> Stashed changes
        return container;
    }

    
}
