/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.satnp.back.controller;

import com.satnp.back.model.Turno;
import com.satnp.back.model.Usuario;
import com.satnp.back.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gerardo
 */
@RestController
@RequestMapping("api/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @RequestMapping(value = "/nuevo/",
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            params = {"correo"})
    public Turno turnoNuevo(@RequestParam String correo) {
        return turnoService.turnoNuevo(correo);
    }

    @RequestMapping(value = "/accederSistema/",
            params = {"usuario", "contrasena"},
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public Integer accederSistema(@RequestParam String usuario, @RequestParam String contrasena) {
        return turnoService.accederSistema(usuario, contrasena);
    }

        @RequestMapping(value = "/agregarUsuario/",
            method = RequestMethod.POST,
            headers = "Accept=application/json")
    public Integer agregarUsuario(@RequestBody Usuario usuario) {
        return turnoService.agregarUsuario(usuario);
    }

}
