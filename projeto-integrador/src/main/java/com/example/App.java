package com.example;

import com.example.controllers.CadastraPorte;
import com.example.controllers.IndexController;
import com.example.controllers.ListaPorteController;
import com.example.daos.JDBCPorteDAO;
import com.example.daos.PorteDAO;
import com.example.models.FabricaConexoes;
import com.example.repositories.PorteRepository;
import com.example.utils.JavalinUtils;

import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    var app = JavalinUtils.makeApp(7000);
    
    PorteDAO porteDAO = new JDBCPorteDAO(FabricaConexoes.getInstance());
    PorteRepository repositorioPorte = new PorteRepository(porteDAO);
    CadastraPorte porteController = new CadastraPorte(repositorioPorte);
    ListaPorteController listaPorteController = new ListaPorteController(repositorioPorte);
    IndexController indexController = new IndexController(); 

    //app.get("/", ctx -> ctx.result("Hello Javalin!"));
    app.get("/addporte", porteController.get);
    app.post("/addporte", porteController.post);
    app.get("/listporte", listaPorteController.get); 
    app.get("/", indexController.get); 

  }

}
