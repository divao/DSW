package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Locadora;
import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriaUsuarios {

    public static void main(String[] args) throws ClassNotFoundException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        PapelDAO papelDAO = new PapelDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        LocadoraDAO locadoraDAO = new LocadoraDAO();

        //Criando os papéis
        Papel p1 = new Papel();
        p1.setNome("ROLE_ADMIN");
        papelDAO.save(p1);

        Papel p2 = new Papel();
        p2.setNome("ROLE_CLIENTE");
        papelDAO.save(p2);

        Papel p3 = new Papel();
        p3.setNome("ROLE_LOCADORA");
        papelDAO.save(p3);

        // Criando Usuario admin com papel ROLE_ADMIN
        Usuario u1 = new Usuario();
        u1.setEmail("admin@admin.com");
        u1.setSenha(encoder.encode("admin123"));
        u1.setAtivo(true);
        usuarioDAO.save(u1);
        u1.getPapel().add(p1);
        usuarioDAO.update(u1);

        // Criando Usuarios com papel ROLE_CLIENTE
        Cliente c2 = new Cliente();
        c2.setNome("Cliente 1");
        c2.setCpf("200.177.890-28");
        c2.setEmail("cliente1@cliente.com");
        c2.setDataNascimento("28/06/1995");
        c2.setTelefone("(11) 95589-1256");
        c2.setSexo("Masculino");
        c2.setSenha("12345678");
        clienteDAO.save(c2);

        Usuario u2 = new Usuario();
        u2.setEmail(c2.getEmail());
        u2.setSenha(encoder.encode(c2.getSenha()));
        u2.setAtivo(true);
        usuarioDAO.save(u2);
        u2.getPapel().add(p2);
        usuarioDAO.update(u2);

        Cliente c3 = new Cliente();
        c3.setNome("Cliente 2");
        c3.setCpf("278.840.600-27");
        c3.setEmail("cliente2@cliente.com");
        c3.setDataNascimento("10/02/1996");
        c3.setTelefone("(11) 96589-5689");
        c3.setSexo("Feminino");
        c3.setSenha("12345678");
        clienteDAO.save(c3);

        Usuario u3 = new Usuario();
        u3.setEmail(c3.getEmail());
        u3.setSenha(encoder.encode(c3.getSenha()));
        u3.setAtivo(true);
        usuarioDAO.save(u3);
        u3.getPapel().add(p2);
        usuarioDAO.update(u3);

        Cliente c4 = new Cliente();
        c4.setNome("Cliente 3");
        c4.setCpf("564.887.130-94");
        c4.setEmail("cliente3@cliente.com");
        c4.setDataNascimento("28/10/1989");
        c4.setTelefone("(21) 98569-1257");
        c4.setSexo("Masculino");
        c4.setSenha("12345678");
        clienteDAO.save(c4);

        Usuario u4 = new Usuario();
        u4.setEmail(c4.getEmail());
        u4.setSenha(encoder.encode(c4.getSenha()));
        u4.setAtivo(true);
        usuarioDAO.save(u4);
        u4.getPapel().add(p2);
        usuarioDAO.update(u4);

        Cliente c5 = new Cliente();
        c5.setNome("Cliente 4");
        c5.setCpf("967.918.300-93");
        c5.setEmail("cliente4@cliente.com");
        c5.setDataNascimento("05/05/1990");
        c5.setTelefone("(16) 98546-0089");
        c5.setSexo("Feminino");
        c5.setSenha("12345678");
        clienteDAO.save(c5);

        Usuario u5 = new Usuario();
        u5.setEmail(c5.getEmail());
        u5.setSenha(encoder.encode(c5.getSenha()));
        u5.setAtivo(true);
        usuarioDAO.save(u5);
        u5.getPapel().add(p2);
        usuarioDAO.update(u5);

        Cliente c6 = new Cliente();
        c6.setNome("Cliente 5");
        c6.setCpf("526.511.860-82");
        c6.setEmail("cliente5@cliente.com");
        c6.setDataNascimento("01/02/2000");
        c6.setTelefone("(11) 95589-1002");
        c6.setSexo("Feminino");
        c6.setSenha("12345678");
        clienteDAO.save(c6);

        Usuario u6 = new Usuario();
        u6.setEmail(c6.getEmail());
        u6.setSenha(encoder.encode(c6.getSenha()));
        u6.setAtivo(true);
        usuarioDAO.save(u6);
        u6.getPapel().add(p2);
        usuarioDAO.update(u6);

        // Criando Usuario user com papel ROLE_LOCADORA
        Locadora l1 = new Locadora();
        l1.setCnpj("66.071.486/0001-67");
        l1.setNome("Locadora 1");
        l1.setEmail("locadora1@locadora.com");
        l1.setSenha("12345678");
        l1.setCidade("São Carlos");
        locadoraDAO.save(l1);

        Usuario u7 = new Usuario();
        u7.setEmail(l1.getEmail());
        u7.setSenha(encoder.encode(l1.getSenha()));
        u7.setAtivo(true);
        usuarioDAO.save(u7);
        u7.getPapel().add(p3);
        usuarioDAO.update(u7);

        Locadora l2 = new Locadora();
        l2.setCnpj("70.467.834/0001-97");
        l2.setNome("Locadora 2");
        l2.setEmail("locadora2@locadora.com");
        l2.setSenha("12345678");
        l2.setCidade("São Carlos");
        locadoraDAO.save(l2);

        Usuario u8 = new Usuario();
        u8.setEmail(l2.getEmail());
        u8.setSenha(encoder.encode(l2.getSenha()));
        u8.setAtivo(true);
        usuarioDAO.save(u8);
        u8.getPapel().add(p3);
        usuarioDAO.update(u8);

        Locadora l3 = new Locadora();
        l3.setCnpj("49.876.691/0001-80");
        l3.setNome("Locadora 3");
        l3.setEmail("locadora3@locadora.com");
        l3.setSenha("12345678");
        l3.setCidade("Araraquara");
        locadoraDAO.save(l3);

        Usuario u9 = new Usuario();
        u9.setEmail(l3.getEmail());
        u9.setSenha(encoder.encode(l3.getSenha()));
        u9.setAtivo(true);
        usuarioDAO.save(u9);
        u9.getPapel().add(p3);
        usuarioDAO.update(u9);

        Locadora l4 = new Locadora();
        l4.setCnpj("03.261.024/0001-92");
        l4.setNome("Locadora 4");
        l4.setEmail("locadora4@locadora.com");
        l4.setSenha("12345678");
        l4.setCidade("Araraquara");
        locadoraDAO.save(l4);

        Usuario u10 = new Usuario();
        u10.setEmail(l4.getEmail());
        u10.setSenha(encoder.encode(l4.getSenha()));
        u10.setAtivo(true);
        usuarioDAO.save(u10);
        u10.getPapel().add(p3);
        usuarioDAO.update(u10);

        Locadora l5 = new Locadora();
        l5.setCnpj("29.623.044/0001-50");
        l5.setNome("Locadora 5");
        l5.setEmail("locadora5@locadora.com");
        l5.setSenha("12345678");
        l5.setCidade("São Carlos");
        locadoraDAO.save(l5);

        Usuario u11 = new Usuario();
        u11.setEmail(l5.getEmail());
        u11.setSenha(encoder.encode(l5.getSenha()));
        u11.setAtivo(true);
        usuarioDAO.save(u11);
        u11.getPapel().add(p3);
        usuarioDAO.update(u11);
    }
}
