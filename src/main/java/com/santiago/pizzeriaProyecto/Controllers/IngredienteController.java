package com.santiago.pizzeriaProyecto.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santiago.pizzeriaProyecto.Entities.Ingrediente;
import com.santiago.pizzeriaProyecto.Services.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    private ResponseEntity<List<Ingrediente>> listarIngredientes(Model model){

        ResponseEntity<List<Ingrediente>> responseEntity = null;

        List<Ingrediente> pizzas = null;

        pizzas = ingredienteService.findAll();

        if (pizzas.size() > 0) {
            // si devuelve la lista == status OK
            responseEntity = new ResponseEntity<List<Ingrediente>>(pizzas, HttpStatus.OK);
        } else {
            // si el listado esta vacio == otro status
            responseEntity = new ResponseEntity<List<Ingrediente>>(HttpStatus.NO_CONTENT); //204
        }

        return responseEntity;
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> findById(@PathVariable(name = "id") Long id){

        ResponseEntity<Ingrediente> responseEntity = null;

        Ingrediente ingrediente = ingredienteService.findById(id);

        if(ingrediente != null){
            responseEntity = new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

    //Persistir un método en la BD mediante el método post y un ResponseEntity
    //Post porque es contenido a persisitir
    //RequestBody, body porque viene en el body al ser POST
    //@Valid para poder validar el JSON con las @annotaciones del Producto
    //BindingResult como objeto para validar el JSON
    @PostMapping()
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Ingrediente ingrediente, BindingResult result){

        //Para la respuesta del objeto persistido
        ResponseEntity<Map<String, Object>> responseEntity = null;

        //para la respuesta de los errores
        Map<String, Object> responseAsMap = new HashMap<>();

        //Comprobar si el objeto tiene errores, validar cada campo de la clase prodcuto
        if(result.hasErrors()){

            List<String> errores = new ArrayList<>();

            //Recorrer la colección para sacar los errores 
            //La colleción de result tiene los errores y devuelve una lista de todos los errores
            //Sacar el mensaje/String de la lista de errores, el defaultMessage es message de la entidad prodcuto
            for(ObjectError error : result.getAllErrors()){
                errores.add(error.getDefaultMessage());

            }

            //Devuelve la lista de errores
            responseAsMap.put("errores", errores);

            //Declaras dentro de la variable el responseMap con los errores y el http de error
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);

        }

        try {

            Ingrediente ingredienteDB = ingredienteService.guardarIngrediente(ingrediente);

            if(ingrediente!=null){
                responseAsMap.put("mensaje", "El ingrediente con id " + ingredienteDB.getId() 
                + " se ha creado exitosamente");
                responseAsMap.put("ingrediente", ingredienteDB);
                responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.CREATED);
            }
            else{
                responseAsMap.put("mensaje", "Error creando el ingrediente");
                responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.BAD_GATEWAY);
            }

        
        } catch (DataAccessException e) {
            //Excepcion de los datos
            responseAsMap.put("mensaje", "No se ha podido crear el ingrediente" + e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    

    }

    //Modificar un producto es practicamente similar al anterior
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modificar(@Valid @RequestBody Ingrediente ingrediente, BindingResult result, @PathVariable(name="id") Long id){

        //Para la respuesta del objeto persistido
        ResponseEntity<Map<String, Object>> responseEntity = null;

        //para la respuesta de los errores
        Map<String, Object> responseAsMap = new HashMap<>();

        //Comprobar si el objeto tiene errores, validar cada campo de la clase prodcuto
        if(result.hasErrors()){

            List<String> errores = new ArrayList<>();

            //Recorrer la colección para sacar los errores 
            //La colleción de result tiene los errores y devuelve una lista de todos los errores
            //Sacar el mensaje/String de la lista de errores, el defaultMessage es message de la entidad prodcuto
            for(ObjectError error : result.getAllErrors()){
                errores.add(error.getDefaultMessage());

            }

            //Devuelve la lista de errores
            responseAsMap.put("errores", errores);

            //Declaras dentro de la variable el responseMap con los errores y el http de error
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);

        }

        try {

            ingrediente.setId(id);

            Ingrediente ingredienteDB = ingredienteService.guardarIngrediente(ingrediente);

            if(ingredienteDB!=null){
                responseAsMap.put("mensaje", "El ingrediente con id " + ingredienteDB.getId() + " se ha modificado exitosamente");
                responseAsMap.put("ingrediente", ingredienteDB);
                responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.CREATED);
            }
            else {
                responseAsMap.put("mensaje", "Error actualizando el ingrediente");
                responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.BAD_GATEWAY);
            }

        
        } catch (DataAccessException e) {
            //Excepcion de los datos
            responseAsMap.put("mensaje", "No se ha podido actualizar el ingrediente" + e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(responseAsMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name="id") Long id){

        ResponseEntity<String> responseEntity = null;

        ingredienteService.deleteIngrediente(id);

        //Comprobar si se ha borrado
        Ingrediente ingredienteDB = ingredienteService.findById(id);

        if(ingredienteDB==null){
            //Se ha borrado correctamente
            responseEntity = new ResponseEntity<>("El ingrediente se ha borrado correctamente", HttpStatus.OK);
            
        }
        else responseEntity = new ResponseEntity<>("No se ha podido eliminar", HttpStatus.BAD_REQUEST);


        return responseEntity;

    }

}
