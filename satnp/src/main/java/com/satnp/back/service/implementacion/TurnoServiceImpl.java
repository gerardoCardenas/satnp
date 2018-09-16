/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.satnp.back.service.implementacion;

import com.satnp.back.dao.TurnoDao;
import com.satnp.back.model.Turno;
import com.satnp.back.model.Usuario;
import com.satnp.back.service.TurnoService;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerardo
 */
@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoDao turnoDao;

    @Override
    @Transactional()
    public Turno turnoNuevo(String usuario) {
        Random random = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String letraTurno = abc.charAt(random.nextInt(abc.length())) + "";
        String numero = turnoDao.turnoMaximo(letraTurno);
        String relleno = "";
        if (Integer.parseInt(numero) +1 < 10) {
            relleno = "0";
        }
        Usuario usuarioModel = this.turnoDao.consultarPorCorreo(relleno);
        Turno turno = new Turno();
        turno.setUsuario(usuarioModel);
        turno.setTurno(letraTurno + relleno + Integer.toString(Integer.parseInt(numero) + 1));
        turnoDao.turnoNuevo(turno);
        return turno;
    }

    @Transactional(readOnly = true)
    @Override
    public Integer accederSistema(String usuario, String contrasena) {
        Integer idUsuario = this.turnoDao.accederSistema(usuario, contrasena);
        if(idUsuario == null) {
            throw new RuntimeException("El usuario y/o contraseña son invalidos.");
        }
        return idUsuario;
    }
    
    @Transactional
    @Override
    public Integer agregarUsuario(Usuario usuario) {
        if(turnoDao.consultarPorCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("El usuario ya existe porfavor ingrese otro.");
        }
        return this.turnoDao.agregarUsuario(usuario);
    }

}
