package com.example;

import com.example.controllers.CarroController;
import com.example.controllers.ClienteController;
import com.example.controllers.PessoaController;
import com.example.controllers.PorteController;
import com.example.controllers.TicketController;
import com.example.controllers.IndexController;
import com.example.controllers.PagamentoController;
import com.example.daos.CarroDAO;
import com.example.daos.ClienteDAO;
import com.example.daos.JDBCCarroDAO;
import com.example.daos.JDBCClienteDAO;
import com.example.daos.JDBCPagamentoDAO;
import com.example.daos.JDBCPessoaDAO;
import com.example.daos.JDBCPorteDAO;
import com.example.daos.JDBCTicketDAO;
import com.example.daos.JDBCVagaDAO;
import com.example.daos.PagamentoDAO;
import com.example.daos.PessoaDAO;
import com.example.daos.PorteDAO;
import com.example.daos.TicketDAO;
import com.example.daos.VagaDAO;
import com.example.models.FabricaConexoes;
import com.example.repositories.CarroRepository;
import com.example.repositories.ClienteRepository;
import com.example.repositories.PagamentoRepository;
import com.example.repositories.PessoaRepository;
import com.example.repositories.PorteRepository;
import com.example.repositories.TicketRepository;
import com.example.repositories.VagaRepository;
import com.example.utils.JavalinUtils;

import io.javalin.Javalin;

public class App {
  public static void main(String[] args) {
    var app = JavalinUtils.makeApp(7000);

    PorteDAO porteDAO = new JDBCPorteDAO(FabricaConexoes.getInstance());
    PessoaDAO pessoaDAO = new JDBCPessoaDAO(FabricaConexoes.getInstance());
    ClienteDAO clienteDAO = new JDBCClienteDAO(FabricaConexoes.getInstance());
    CarroDAO carroDAO = new JDBCCarroDAO(FabricaConexoes.getInstance()); 
    TicketDAO ticketDAO = new JDBCTicketDAO(FabricaConexoes.getInstance()); 
    VagaDAO vagaDAO = new JDBCVagaDAO(FabricaConexoes.getInstance()); 
    PagamentoDAO pagamentoDAO = new JDBCPagamentoDAO(FabricaConexoes.getInstance());
    PagamentoRepository pagamentoRepository = new PagamentoRepository(ticketDAO, pagamentoDAO); 
    VagaRepository vagaRepository = new VagaRepository(vagaDAO); 
    TicketRepository ticketRepository = new TicketRepository(ticketDAO, carroDAO); 
    ClienteRepository repositorioCliente = new ClienteRepository(clienteDAO, pessoaDAO);
    ClienteController clienteController = new ClienteController(repositorioCliente);
    CarroRepository carroRepository = new CarroRepository(carroDAO, porteDAO, clienteDAO); 
    PorteRepository repositorioPorte = new PorteRepository(porteDAO);
    PessoaRepository repositorioPessoa = new PessoaRepository(pessoaDAO);
    PorteController porteController = new PorteController(repositorioPorte);
    PessoaController pessoaController = new PessoaController(repositorioPessoa);
    CarroController carroController = new CarroController(carroRepository); 
    TicketController ticketController = new TicketController(ticketRepository, carroRepository,vagaRepository); 
    PagamentoController pagamentoController = new PagamentoController(pagamentoRepository, ticketRepository); 
    IndexController indexController = new IndexController();


    app.get("/addporte", porteController.cadastrarGet);
    app.post("/addporte", porteController.cadastrarPost);
    app.get("/listporte", porteController.listar);
    app.get("/excluiporte", porteController.excluirGet);
    app.post("/excluiporte", porteController.excluirPost);
    app.get("/editaporte", porteController.editarGet);
    app.post("/editaporte", porteController.editarPost);
    app.get("/addpessoa", pessoaController.cadastrarGet);
    app.post("/addpessoa", pessoaController.cadastrarPost);
    app.get("/addcliente", clienteController.cadastrarGet);
    app.post("/addcliente", clienteController.cadastrarPost);
    app.get("/listcliente", clienteController.listarGet);
    app.get("/excluicliente", clienteController.excluirGet);
    app.post("/excluicliente", clienteController.excluirPost);
    app.get("/editacliente", clienteController.editarGet); 
    app.post("/editacliente", clienteController.editarPost); 
    app.get("/addcarro", carroController.cadastraGet); 
    app.post("/addcarro", carroController.cadastraPost); 
    app.get("/listcarro", carroController.listarGet); 
    app.get("/editcarro", carroController.editarGet); 
    app.post("/editcarro", carroController.editarPost); 
    app.get("/excluicarro", carroController.excluirGet); 
    app.post("/excluicarro", carroController.excluirPost); 
    app.get("/addticket", ticketController.cadastrarGet); 
    app.post("/addticket", ticketController.cadastrarPost); 
    app.get("/saidaticket", ticketController.registraSaidaGet); 
    app.post("/saidaticket", ticketController.registraSaidaPost); 
    app.get("/addpag", pagamentoController.registrarGet); 
    app.post("/addpag", pagamentoController.registrarPost);
    app.get("/tickets", ticketController.listarGet); 
    app.get("/", indexController.get);

  }

}
