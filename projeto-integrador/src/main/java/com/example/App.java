package com.example;

import com.example.controllers.CadastraClienteController;
import com.example.controllers.CadastraPessoa;
import com.example.controllers.CadastraPorte;
import com.example.controllers.EditaPorteController;
import com.example.controllers.ExcluiClienteController;
import com.example.controllers.ExcluiPorteController;
import com.example.controllers.IndexController;
import com.example.controllers.ListaClienteController;
import com.example.controllers.ListaPorteController;
import com.example.daos.ClienteDAO;
import com.example.daos.JDBCClienteDAO;
import com.example.daos.JDBCPessoaDAO;
import com.example.daos.JDBCPorteDAO;
import com.example.daos.PessoaDAO;
import com.example.daos.PorteDAO;
import com.example.models.FabricaConexoes;
import com.example.repositories.ClienteRepository;
import com.example.repositories.PessoaRepository;
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
    PessoaDAO pessoaDAO = new JDBCPessoaDAO(FabricaConexoes.getInstance()); 
    ClienteDAO clienteDAO = new JDBCClienteDAO(FabricaConexoes.getInstance()); 
    ClienteRepository repositorioCliente = new ClienteRepository(clienteDAO, pessoaDAO); 
    CadastraClienteController cadastraClienteController = new CadastraClienteController(repositorioCliente); 
    PorteRepository repositorioPorte = new PorteRepository(porteDAO);
    PessoaRepository repositorioPessoa = new PessoaRepository(pessoaDAO);
    CadastraPorte porteController = new CadastraPorte(repositorioPorte);
    ListaPorteController listaPorteController = new ListaPorteController(repositorioPorte);
    ExcluiPorteController excluiPorteController = new ExcluiPorteController(repositorioPorte); 
    EditaPorteController editaPorteController = new EditaPorteController(repositorioPorte); 
    CadastraPessoa cadastraPessoaController = new CadastraPessoa(repositorioPessoa); 
    ListaClienteController listaClienteController = new ListaClienteController(repositorioCliente); 
    ExcluiClienteController excluiClienteController = new ExcluiClienteController(repositorioCliente); 
    

    IndexController indexController = new IndexController(); 

    //app.get("/", ctx -> ctx.result("Hello Javalin!"));
    app.get("/addporte", porteController.get);
    app.post("/addporte", porteController.post);
    app.get("/listporte", listaPorteController.get); 
    app.get("/excluiporte", excluiPorteController.get); 
    app.post("/excluiporte",excluiPorteController.post); 
    app.get("/editaporte", editaPorteController.get);
    app.post("/editaporte", editaPorteController.post);
    app.get("/addpessoa", cadastraPessoaController.get); 
    app.post("/addpessoa", cadastraPessoaController.post); 
    app.get("/addcliente", cadastraClienteController.get); 
    app.post("/addcliente", cadastraClienteController.post); 
    app.get("/listcliente", listaClienteController.get); 
    app.get("/excluicliente", excluiClienteController.get);
    app.post("/excluicliente", excluiClienteController.post); 
    app.get("/", indexController.get); 
    

  }

}
