package com.listou.listou.controller;

import java.util.List;
import java.util.Map;
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

import com.google.gson.Gson;
import com.listou.listou.entity.Lista;
import com.listou.listou.entity.UserListou;
import com.listou.listou.repository.ListaRepository;
import com.listou.listou.repository.UserListouRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Listou - Lista")
@CrossOrigin(origins = "*")
public class ListaController {
	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private UserListouRepository userListouRepository;
	
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
	@ApiOperation(value = "API POST Method - path:\"/api/user/lista\" adiciona lista")
    public ResponseEntity<Lista> Post(@RequestBody Map<String, Object>  requestBody)
    {
    	Gson gson = new Gson();
    	try {
    		Lista listaJson = gson.fromJson(String.valueOf(gson.toJson(requestBody.get("lista"))), Lista.class);
    		Lista lista = listaRepository.save(listaJson);
    		UserListou userListou = userListouRepository.getById(Long.parseLong(String.valueOf(requestBody.get("userId"))));
    		userListou.getListasId().add(lista.getId());
    		userListouRepository.save(userListou);
    		return new ResponseEntity<Lista>(lista, HttpStatus.OK);  		
    	}catch (Exception e){
    		System.out.println(e);
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	
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
