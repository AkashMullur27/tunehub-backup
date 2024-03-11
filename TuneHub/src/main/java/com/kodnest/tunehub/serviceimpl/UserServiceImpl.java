package com.kodnest.tunehub.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;


	public String addUser(User user) {
		// TODO Auto-generated method stub
		repository.save(user);
		return "Success";
	}

	//to check duplicate entries
	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		if(repository.findByEmail(email)!= null)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public boolean validateUser(String email, String password) {
		// TODO Auto-generated method stub
		User user=repository.findByEmail(email);
		String dbpw=user.getPassword();

		if(password.equals(dbpw))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String getRole(String email)
	{
		User user=repository.findByEmail(email);

		return user.getRole();
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return repository.findByEmail(email);
	}

	@Override
	public void updateuser(User user) {
		// TODO Auto-generated method stub
		repository.save(user);
	}

}
