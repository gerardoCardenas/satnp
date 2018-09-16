/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.satnp.back.service;

import com.satnp.back.model.Turno;
import com.satnp.back.model.Usuario;

/**
 *
 * @author gerardo
 */
public interface TurnoService {
    public Turno turnoNuevo(String usuario);
    
    public Integer accederSistema(String usuario, String contrasena);
    
    public Integer agregarUsuario(Usuario usuario);
}
