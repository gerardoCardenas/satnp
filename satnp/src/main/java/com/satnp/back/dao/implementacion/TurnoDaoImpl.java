/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.satnp.back.dao.implementacion;

import com.satnp.back.dao.TurnoDao;
import com.satnp.back.model.Turno;
import com.satnp.back.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gerardo
 */
@Repository
public class TurnoDaoImpl implements TurnoDao {
    
    @PersistenceContext()
    private EntityManager em;

    @Override
    public void turnoNuevo(Turno turno) {
        em.persist(turno);
    }

    @Override
    public String turnoMaximo(String letra) {
        return em.createQuery("SELECT substring(t.turno,2,3) "
                + "FROM Turno t "
                + "WHERE substring(t.turno,1,1) = :letra", String.class)
                .setParameter("letra", letra)
                .getResultList()
                .stream()
                .sorted((t1,t2) -> Integer.compare(Integer.parseInt(t2), Integer.parseInt(t1)))
                .findFirst()
                .orElse("0");
    }

    @Override
    public Integer accederSistema(String usuario, String contrasena) {
        return em.createQuery("select idUsuario "
                + "from Usuario u "
                + "where u.correo = :usuario and "
                + "u.contrasena =:contrasena", Integer.class)
                .setParameter("usuario", usuario)
                .setParameter("contrasena", contrasena)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Integer agregarUsuario(Usuario usuario) {
        em.persist(usuario);
        return usuario.getIdUsuario();  
    }
    
    @Override
    public Usuario consultarPorCorreo(String correo) {
                return em.createQuery("from Usuario u "
                + "where u.correo = :usuario", Usuario.class)
                .setParameter("usuario", correo)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
