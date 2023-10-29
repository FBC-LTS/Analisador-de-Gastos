package com.example.application.views.sobre;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin.Horizontal;


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
        HorizontalLayout containerCampos = new HorizontalLayout();

        VerticalLayout containerSobre = new VerticalLayout();
        VerticalLayout containerAutores = new VerticalLayout();
        
        

        containerSobre = sobreProjeto(containerSobre);
        containerAutores = autoresProjeto(containerAutores);
        
        containerCampos.add(containerSobre);
        containerCampos.add(containerAutores);
        
        // adicionando tudo na tela
        container.add(containerCampos);
        
        add(container);
    }

    private VerticalLayout sobreProjeto(VerticalLayout container){
        H2 tituloAnalisador = new H2("O Analisador de Gastos");
        Text textoAnalisador = new Text("É uma aplicação intuitiva e eficiente projetada para simplificar o processo de registro, \r\r\n" + //
                "categorização e análise de despesas financeiras pessoais ou comerciais. Essa ferramenta \r\r\n" + //
                "versátil oferece recursos essenciais para auxiliar os usuários a manter o controle de seus \r\n" + //
                "gastos, \rcategorizá-los e gerar relatórios detalhados em formato CSV.");
        H2 tituloApp = new H2("É uma aplicação feita");
        Text textoApp = new Text("Por alunos da Universidade Estácio, que estão cursando Análise e Desenvolvimento de \r\r\n" + // 
                "Sistemas e Ciências da Computação. Eles estavam estudando a disciplina de Programação \r\r\n" + //
                "Orientada a Objetos em Java, ministrada pelo professor Almir Rogério de Macedo.");
        Text textoApp2 = new Text("Para a aluna Lara Guimarães, estudante de Ciências Contábeis, que desempenhou o papel \r\r\n" + //
                "de explicar como a aplicação deveria funcionar e acompanhar o progresso do projeto para \r\n" + //
                "garantir que estivesse alinhado com suas expectativas.");
        H2 tituloAluno = new H2("De alunos para alunos");
        Text textoAluno = new Text("Desenvolvida utilizando técnicas que aprendemos em outros semestres durante o curso,\r\r\n" + //
                "como Kanban, metodologia RAD, GIT, entre outros. Feita durante o segundo semestre de\r\n" + //
                "2023.");
        container.add(tituloAnalisador, textoAnalisador, tituloApp, textoApp, textoApp2, tituloAluno, textoAluno);            
        return container;
    }

    private VerticalLayout autoresProjeto(VerticalLayout container){
        H2 tituloAutores = new H2("Autores:");
        container.add(tituloAutores);
        
        VerticalLayout alunos = new VerticalLayout();
        alunos.addClassName("container");
        
        VerticalLayout fabricio = new VerticalLayout();
        fabricio = campoAutor(fabricio,
            " Fabrício Lustosa de Oliveira Alencar Cunha.",
            " 202109059831",
            " Análise e desenvolvimento de sistemas",
            " Líder e Documentação"
            );
        alunos.add(fabricio);

        VerticalLayout lucimar = new VerticalLayout();
        lucimar = campoAutor(lucimar,
            " Lucimar Barros de Oliveira",
            " 202109086758",
            " Análise e desenvolvimento de sistemas",
            " Backend / Frontend"
            );
        alunos.add(lucimar);

        VerticalLayout Nicolas = new VerticalLayout();
        Nicolas = campoAutor(Nicolas,
            " Nícolas Moniz Rodrigues.",
            "  201902180259",
            " Ciências da computação",
            " Backend"
            );
        alunos.add(Nicolas);

        VerticalLayout Bruno = new VerticalLayout();
        Bruno = campoAutor(Bruno,
            " Bruno Santana de Sousa.",
            " 202204064031",
            " Análise e desenvolvimento de sistemas",
            " Front-end e Design"
            );
        alunos.add(Bruno);
        container.add(alunos);
        return container;
    }

    private VerticalLayout campoAutor(VerticalLayout container, String nomeAutor, String matriculaAutor, String cursoAutor, String responsabilidadeAutor){

        
        Span nome = new Span("Nome:");
        nome.addClassName("negrito");
       
        Span matricula = new Span("Matrícula:");
        matricula.addClassName("negrito");

        Span curso = new Span("Curso:");
        curso.addClassName("negrito");

        Span responsabilidade = new Span("Responsabilidade:");
        responsabilidade.addClassName("negrito");

      
        Text nomeText = new Text(nomeAutor);
        Text matriculaText = new Text(matriculaAutor);
        Text cursoText = new Text(cursoAutor);
        Text responsabilidadeText = new Text(responsabilidadeAutor);

        HorizontalLayout containerNome = new HorizontalLayout();
        HorizontalLayout containerMatricula = new HorizontalLayout();
        HorizontalLayout containerCurso = new HorizontalLayout();
        HorizontalLayout containerResponsabilidade = new HorizontalLayout();
        
        containerNome.add(nome, nomeText);
        containerMatricula.add(matricula, matriculaText);
        containerCurso.add(curso, cursoText);
        containerResponsabilidade.add(responsabilidade, responsabilidadeText);
        container.add(containerNome,
            containerMatricula,
            containerCurso,
            containerResponsabilidade
        );
        
        return container;
    }

    
}
