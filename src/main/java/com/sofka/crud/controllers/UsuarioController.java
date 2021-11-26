package com.sofka.crud.controllers;

import com.sofka.crud.models.UsuarioModel;
import com.sofka.crud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @GetMapping
    public ArrayList<UsuarioModel> obtenerUsuario(){
        return usuarioService.obtenerUsuario();
    }

    @PostMapping
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Object obtenerUsuarioPorId(@PathVariable("id")Long id){
        Optional<UsuarioModel> usuarioModel = this.usuarioService.obtenerPorId(id);
        if(usuarioModel.isEmpty()) {
            return "El usuario con id: " + id + " no se encuentra registrado en la base de datos.";
        }else {
            return usuarioModel;
        }
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @GetMapping("/nombre")
    public ArrayList<UsuarioModel> obtenerUsuarioPorNombre(@RequestParam("nombre") String nombre){
        return this.usuarioService.obtenerPorNombre(nombre);
    }

    @GetMapping("/email")
    public Object obtenerUsuarioPorEmail(@RequestParam("email") String email){
        Optional<UsuarioModel> usuarioModel = this.usuarioService.obtenerPorEmail(email);
        if(usuarioModel.isEmpty()){
            return "El usuario con email: " + email + " no se encuentra registrado en la base de datos.";
        }else{
            return usuarioModel;
        }
    }

    @DeleteMapping(path = "/{id}")
    public String elimiarUsuarioPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuarioPorId(id);
        if(ok){
            return "Se elimino el usuario con id: " + id;
        }else{
            return "No se puedo eliminar el usuario con id: " + id;
        }
    }

}
