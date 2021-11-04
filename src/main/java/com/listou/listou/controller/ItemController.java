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

import com.listou.listou.entity.Item;
import com.listou.listou.repository.ItemRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/api")
@Api(value="API REST Listou - Item")
@CrossOrigin(origins = "*")
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	
	@RequestMapping(value = "/item", method = RequestMethod.GET)
    @ApiOperation(value = "API Get Method - path:\"/api/item\" lista items do banco")
    public List<Item> ListarItems() {
        return itemRepository.findAll();
    }
	
    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "API Get Method - path:\"/api/item/{id}\" devolve um item do banco")
    public ResponseEntity<Item> GetById(@PathVariable(value = "id") long id)
    {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent())
            return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/item", method =  RequestMethod.POST)
    @ApiOperation(value = "API POST Method - path:\"/api/item\" adiciona item do banco")
    public Item Post(@RequestBody Item item)
    {
        return itemRepository.save(item);
    }

    @RequestMapping(value = "/item/{id}", method =  RequestMethod.PUT)
    @ApiOperation(value = "API PUT Method - path:\"/api/item/{id}\" atualiza item do banco")
    public ResponseEntity<Item> Put(@PathVariable(value = "id") long id, @RequestBody Item newItem)
    {
        Optional<Item> oldItem = itemRepository.findById(id);
        if(oldItem.isPresent()){
        	return new ResponseEntity<Item>( itemRepository.save(newItem), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "API DELETE Method - path:\"/api/item/{id}\" deleta item do banco")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
        	itemRepository.delete(item.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}