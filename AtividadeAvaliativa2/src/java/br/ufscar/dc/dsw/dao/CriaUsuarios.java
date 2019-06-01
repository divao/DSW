package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.pojo.Papel;
import br.ufscar.dc.dsw.pojo.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriaUsuarios {

    public static void main(String[] args) throws ClassNotFoundException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        PapelDAO papelDAO = new PapelDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Criando Usuario admin com papel ROLE_ADMIN
     
        Usuario u1 = new Usuario();
        u1.setEmail("admin@admin");
        u1.setSenha(encoder.encode("admin"));
        u1.setAtivo(true);
        usuarioDAO.save(u1);

        Papel p1 = new Papel();
        p1.setNome("ROLE_ADMIN");
        papelDAO.save(p1);

        u1.getPapel().add(p1);
        usuarioDAO.update(u1);

        // Criando Usuario user com papel ROLE_CLIENTE
        
        Usuario u2 = new Usuario();
        u2.setEmail("user@user");
        u2.setSenha(encoder.encode("user"));
        u2.setAtivo(true);
        usuarioDAO.save(u2);

        Papel p2 = new Papel();
        p2.setNome("ROLE_CLIENTE");
        papelDAO.save(p2);

        u2.getPapel().add(p2);
        usuarioDAO.update(u2);
        
        // Criando Usuario user com papel ROLE_LOCADORA
        
        Usuario u3 = new Usuario();
        u3.setEmail("locadora@locadora");
        u3.setSenha(encoder.encode("locadora"));
        u3.setAtivo(true);
        usuarioDAO.save(u3);

        Papel p3 = new Papel();
        p3.setNome("ROLE_LOCADORA");
        papelDAO.save(p3);

        u3.getPapel().add(p3);
        usuarioDAO.update(u3);
    }
}
