package com.listou.listou.controller;

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
import com.listou.listou.entity.Item;
import com.listou.listou.entity.ItemLista;
import com.listou.listou.entity.Lista;
import com.listou.listou.repository.ItemListaRepository;
import com.listou.listou.repository.ItemRepository;
import com.listou.listou.repository.ListaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Listou - ItemLista")
@CrossOrigin(origins = "*")
public class itemListaController {
	@Autowired
	private ItemListaRepository itemListaRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ListaRepository listaRepository;
	
    @RequestMapping(value = "user/itemLista/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "API Get Method - path:\"user/itemLista/{id}\" devolve um itemLista do banco")
    public ResponseEntity<ItemLista> GetById(@PathVariable(value = "id") long id)
    {
        Optional<ItemLista> itemLista = itemListaRepository.findById(id);
        if(itemLista.isPresent())
            return new ResponseEntity<ItemLista>(itemLista.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "user/itemLista", method =  RequestMethod.POST)
    @ApiOperation(value = "API POST Method - path:\"/api/itemLista\" adiciona itemLista no banco")
    public ItemLista Post(@RequestBody ItemLista itemLista)
    {
        return itemListaRepository.save(itemLista);
    }

    @RequestMapping(value = "user/itemLista/{id}", method =  RequestMethod.PUT)
    @ApiOperation(value = "API PUT Method - path:\"/api/itemLista/{id}\" atualiza itemLista do banco")
    public ResponseEntity<ItemLista> Put(@PathVariable(value = "id") long id, @RequestBody ItemLista newItemLista)
    {
        Optional<ItemLista> oldItemLista = itemListaRepository.findById(id);
        if(oldItemLista.isPresent()){
        	return new ResponseEntity<ItemLista>( itemListaRepository.save(newItemLista), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "user/itemLista/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "API DELETE Method - path:\"/api/itemLista/{id}\" deleta itemLista do banco")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<ItemLista> itemLista = itemListaRepository.findById(id);
        if(itemLista.isPresent()){
        	itemListaRepository.delete(itemLista.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "user/add-item-Lista", method =  RequestMethod.POST)
    @ApiOperation(value = "API POST Method - path:\"/api/itemLista\" adiciona itemLista no banco")
    public ResponseEntity<Lista> Post(@RequestBody Map<String, Object>  requestBody)
    {
    	Gson gson = new Gson();
    	try {
	    	Lista lista = listaRepository.getById(Long.parseLong(String.valueOf(requestBody.get("listaId"))));
	    	Item itemJson = gson.fromJson(String.valueOf(requestBody.get("item")), Item.class);
	        Item item = itemRepository.save(itemJson);
	        ItemLista itemLista = new ItemLista();
	        itemLista.setItemId(item.getId());
	        itemLista.setQtdItems((int)requestBody.get("quantidade"));
	        itemLista = itemListaRepository.save(itemLista);
	        lista.getItemsListaId().add(itemLista.getId());
	        return new ResponseEntity<Lista>(listaRepository.save(lista),HttpStatus.OK);
    	}catch(Exception e) {
    		 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
        
    }
}
