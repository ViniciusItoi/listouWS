package com.listou.listou.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.listou.listou.entity.UserListou;
import com.listou.listou.repository.UserListouRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Listou - Usuário Listou")
@CrossOrigin(origins = "*")
public class UserListouController {
	@Autowired
	private UserListouRepository userListouRepository;
	
	@RequestMapping(value = "admin/get-user/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "API Get Method - path:\"admin/get-user/{id}\" retorna usuario com o id")
    public ResponseEntity<UserListou> GetById(@PathVariable(value = "id") long id)
    {
        Optional<UserListou> user = userListouRepository.findById(id);
        if(user.isPresent())
            return new ResponseEntity<UserListou>(user.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "API POST Method - path:\"/login\" retorna boolean Login")
    public ResponseEntity<Boolean> Login(@RequestBody UserListou userFront)
    {
        UserListou userDB = userListouRepository.findByUsername(userFront.getUsername());
        if(userDB!=null)
            if(new BCryptPasswordEncoder().matches(userFront.getPassword(), userDB.getPassword())) {
            	return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }else {
            	return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
            }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(value = "create-user", method =  RequestMethod.POST)
	@ApiOperation(value = "API POST Method - path:\"create-user\" adiciona usuario")
    public ResponseEntity<UserListou> Post(@RequestBody UserListou userListou)
    {
		if(userListou.getPassword() == null && userListou.getPassword().isBlank() 
				|| userListou.getUsername() != null && userListou.getUsername().isBlank()
 				||userListou.getName() != null && userListou.getName().isBlank()) {
			return new ResponseEntity<UserListou>(userListou ,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		//encoding password
		userListou.setPassword(encode(userListou.getPassword()));
        return new ResponseEntity<UserListou>(userListouRepository.save(userListou), HttpStatus.OK);
    }

    @RequestMapping(value = "update-user/{id}", method =  RequestMethod.PUT)
    @ApiOperation(value = "API PUT Method - path:\"update-user/{id}\" atualiza usuario")
    public ResponseEntity<UserListou> Put(@PathVariable(value = "id") long id, @RequestBody UserListou newUserListou){
        Optional<UserListou> oldUserListou = userListouRepository.findById(id);
        if(oldUserListou.isPresent()){
        	//encoding password
            newUserListou.setPassword(encode(newUserListou.getPassword()));
            newUserListou.setId(id);
        	return new ResponseEntity<UserListou>(userListouRepository.save(newUserListou), HttpStatus.OK);
        }	
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "admin/delete-user/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "API DELETE Method - path:\"admin/delete-user/{id}\" deleta usuario")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<UserListou> userListou = userListouRepository.findById(id);
        if(userListou.isPresent()){
        	userListouRepository.delete(userListou.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    private String encode(String string) {
    	return new BCryptPasswordEncoder().encode(string);
    }
}
