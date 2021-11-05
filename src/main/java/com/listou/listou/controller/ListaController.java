package com.listou.listou.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.listou.listou.entity.Lista;
import com.listou.listou.repository.ListaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Listou - Lista")
@CrossOrigin(origins = "*")
public class ListaController {
	@Autowired
	private ListaRepository listaRepository;
	
	@RequestMapping(value = "admin/listar-tudo-lista", method = RequestMethod.GET)
	@ApiOperation(value = "API Get Method - path:\"/api/lista\" retorna lista de todos as Listas banco")
    public List<Lista> ListarItems() {
        return listaRepository.findAll();
    }
	
    @RequestMapping(value = "user/lista/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "API Get Method - path:\"/api/lista/{id}\" retorna lista com o id")
    public ResponseEntity<Lista> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Lista> lista = listaRepository.findById(id);
        if(lista.isPresent())
            return new ResponseEntity<Lista>(lista.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "user/lista", method =  RequestMethod.POST)
	@ApiOperation(value = "API POST Method - path:\"/api/lista\" adiciona lista")
    public Lista Post(@RequestBody Lista lista)
    {
        return listaRepository.save(lista);
    }

    @RequestMapping(value = "user/lista/{id}", method =  RequestMethod.PUT)
    @ApiOperation(value = "API PUT Method - path:\"/api/lista/{id}\" atualiza lista")
    public ResponseEntity<Lista> Put(@PathVariable(value = "id") long id, @RequestBody Lista newLista)
    {
        Optional<Lista> oldLista = listaRepository.findById(id);
        if(oldLista.isPresent()){
        	 return new ResponseEntity<Lista>(listaRepository.save(newLista), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "user/lista/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "API DELETE Method - path:\"/api/lista/{id}\" deleta lista")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Lista> lista = listaRepository.findById(id);
        if(lista.isPresent()){
        	listaRepository.delete(lista.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
