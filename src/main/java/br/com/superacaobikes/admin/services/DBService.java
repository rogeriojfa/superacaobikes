package br.com.superacaobikes.admin.services;

import br.com.superacaobikes.admin.domain.*;
import br.com.superacaobikes.admin.domain.enums.EstadoPagamento;
import br.com.superacaobikes.admin.domain.enums.Perfil;
import br.com.superacaobikes.admin.domain.enums.TipoCliente;
import br.com.superacaobikes.admin.domain.enums.TipoPlano;
import br.com.superacaobikes.admin.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    private final CategoriaRepository catRep;
    private final ProdutoRepository prdRep;
    private final EstadoRepository estRep;
    private final CidadeRepository cddRep;
    private final ClienteRepository cliRep;
    private final EnderecoRepository endRep;
    private final PlanoContasRepository plcRep;
    private final FornecedorRepository forRep;
    private final PedidoRepository pedRep;
    private final PagamentoRepository pagRep;
    private final ItemPedidoRepository itpRep;
    private final BCryptPasswordEncoder pe;

    public DBService(CategoriaRepository catRep,
                     ProdutoRepository prdRep,
                     EstadoRepository estRep,
                     CidadeRepository cddRep,
                     ClienteRepository cliRep,
                     EnderecoRepository endRep,
                     PlanoContasRepository plcRep,
                     FornecedorRepository forRep,
                     PedidoRepository pedRep,
                     PagamentoRepository pagRep,
                     ItemPedidoRepository itpRep, BCryptPasswordEncoder pe) {
        this.catRep = catRep;
        this.prdRep = prdRep;
        this.estRep = estRep;
        this.cddRep = cddRep;
        this.cliRep = cliRep;
        this.endRep = endRep;
        this.plcRep = plcRep;
        this.forRep = forRep;
        this.pedRep = pedRep;
        this.pagRep = pagRep;
        this.itpRep = itpRep;
        this.pe = pe;
    }

    public void InstantiateTesteDatabse() throws ParseException {
        //Categorias
        Categoria cat1 = new Categoria(null,"Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Vestuário");
        Categoria cat4 = new Categoria(null,"Ciclismo");
        Categoria cat5 = new Categoria(null, "Personalizados");
        Categoria cat6 = new Categoria(null, "Sports");
        Categoria cat7 = new Categoria(null,"Casual");
        Categoria cat8 = new Categoria(null, "Eletrônicos");
        Categoria cat9 = new Categoria(null, "Sinalização");

        //Produtos
        Produto p1 = new Produto(null, "Computador", 2000.00, 2500.00, "U", "PC", "Teste", 40.00);
        Produto p2 = new Produto(null, "Impressora", 800.00, 1500.00, "U", "PC", "Teste", 40.00);
        Produto p3 = new Produto(null, "Mouse", 80.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p4 = new Produto(null, "Mesa de escritório", 300.00, 450.00, "U", "PC", "Teste", 40.00);
        Produto p5 = new Produto(null, "Toalha", 50.00, 100.00, "U", "PC", "Teste", 40.00);
        Produto p6 = new Produto(null, "Colcha", 200.00, 350.00, "U", "PC", "Teste", 40.00);
        Produto p7 = new Produto(null, "TV true color", 1200.00, 2000.00,"U", "PC", "Teste", 40.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00, 1500.00, "U", "PC", "Teste", 40.00);
        Produto p9 = new Produto(null, "Abajour", 100.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p10 = new Produto(null, "Pendente", 180.00, 320.00, "U", "PC", "Teste", 40.00);
        Produto p11 = new Produto(null, "Shampoo", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p12 = new Produto(null, "Produto12", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p13 = new Produto(null, "Produto13", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p14 = new Produto(null, "Produto14", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p15 = new Produto(null, "Produto15", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p16 = new Produto(null, "Produto16", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p17 = new Produto(null, "Produto17", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p18 = new Produto(null, "Produto18", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p19 = new Produto(null, "Produto19", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p20 = new Produto(null, "Produto20", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p21 = new Produto(null, "Produto21", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p22 = new Produto(null, "Produto22", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p23 = new Produto(null, "Produto23", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p24 = new Produto(null, "Produto24", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p25 = new Produto(null, "Produto25", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p26 = new Produto(null, "Produto26", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p27 = new Produto(null, "Produto27", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p28 = new Produto(null, "Produto28", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p29 = new Produto(null, "Produto29", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p30 = new Produto(null, "Produto30", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p31 = new Produto(null, "Produto31", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p32 = new Produto(null, "Produto32", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p33 = new Produto(null, "Produto33", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p34 = new Produto(null, "Produto34", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p35 = new Produto(null, "Produto35", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p36 = new Produto(null, "Produto36", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p37 = new Produto(null, "Produto37", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p38 = new Produto(null, "Produto38", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p39 = new Produto(null, "Produto39", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p40 = new Produto(null, "Produto40", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p41 = new Produto(null, "Produto41", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p42 = new Produto(null, "Produto42", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p43 = new Produto(null, "Produto43", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p44 = new Produto(null, "Produto44", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p45 = new Produto(null, "Produto45", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p46 = new Produto(null, "Produto46", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p47 = new Produto(null, "Produto47", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p48 = new Produto(null, "Produto48", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p49 = new Produto(null, "Produto49", 90.00, 150.00, "U", "PC", "Teste", 40.00);
        Produto p50 = new Produto(null, "Produto50", 90.00, 150.00, "U", "PC", "Teste", 40.00);

        cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        p12.getCategorias().add(cat1);
        p13.getCategorias().add(cat1);
        p14.getCategorias().add(cat1);
        p15.getCategorias().add(cat1);
        p16.getCategorias().add(cat1);
        p17.getCategorias().add(cat1);
        p18.getCategorias().add(cat1);
        p19.getCategorias().add(cat1);
        p20.getCategorias().add(cat1);
        p21.getCategorias().add(cat1);
        p22.getCategorias().add(cat1);
        p23.getCategorias().add(cat1);
        p24.getCategorias().add(cat1);
        p25.getCategorias().add(cat1);
        p26.getCategorias().add(cat1);
        p27.getCategorias().add(cat1);
        p28.getCategorias().add(cat1);
        p29.getCategorias().add(cat1);
        p30.getCategorias().add(cat1);
        p31.getCategorias().add(cat1);
        p32.getCategorias().add(cat1);
        p33.getCategorias().add(cat1);
        p34.getCategorias().add(cat1);
        p35.getCategorias().add(cat1);
        p36.getCategorias().add(cat1);
        p37.getCategorias().add(cat1);
        p38.getCategorias().add(cat1);
        p39.getCategorias().add(cat1);
        p40.getCategorias().add(cat1);
        p41.getCategorias().add(cat1);
        p42.getCategorias().add(cat1);
        p43.getCategorias().add(cat1);
        p44.getCategorias().add(cat1);
        p45.getCategorias().add(cat1);
        p46.getCategorias().add(cat1);
        p47.getCategorias().add(cat1);
        p48.getCategorias().add(cat1);
        p49.getCategorias().add(cat1);
        p50.getCategorias().add(cat1);



        //Associações produto X categoria
        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));
        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));


        //grava produto X categoria no banco
        catRep.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
        prdRep.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        prdRep.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3));
        estRep.saveAll(Arrays.asList(est1, est2));
        cddRep.saveAll(Arrays.asList(c1,c2,c3));


        Cliente cli1 = new Cliente(null, "Maria Silva", "rogeriojfa@gmail.com", "36985236547", TipoCliente.PESSOAFISICA, pe.encode("123456") );
        cli1.getTelefones().addAll(Arrays.asList("32155050", "988095654"));


        Cliente cli2 = new Cliente(null, "Ana Costa", "rogerio.silva@ntendeicia.com.br", "36985236547", TipoCliente.PESSOAFISICA, pe.encode("123456") );
        cli2.getTelefones().addAll(Arrays.asList("33225588", "988885654"));
        cli2.addPerfil(Perfil.ADMIN);

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "jardim", "25809620", cli1, c1);
        Endereco e2 = new Endereco(null, "Av. Matos", "105", "Sala 800", "Centro", "89745895", cli1, c2);
        Endereco e3 = new Endereco(null, "Av. Floriano", "963", "Fundos", "Linhares", "89745895", cli2, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));

        cliRep.saveAll(Arrays.asList(cli1,cli2));
        endRep.saveAll(Arrays.asList(e1, e2, e3));

        PlanoContas plc1 = new PlanoContas(null, "pagamento", TipoPlano.DEBITO);
        PlanoContas plc2 = new PlanoContas(null, "Venda", TipoPlano.CREDITO);
        plcRep.saveAll(Arrays.asList(plc1, plc2));

        Fornecedor for1 = new Fornecedor(null, "VEST", "André");
        for1.getTelefones().addAll(Arrays.asList("32155050","988885454"));
        forRep.saveAll(Arrays.asList(for1));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e1);

        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pgto1);
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pgto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedRep.saveAll(Arrays.asList(ped1,ped2));
        pagRep.saveAll(Arrays.asList(pgto1, pgto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itpRep.saveAll(Arrays.asList(ip1,ip2,ip3));
    }
}
