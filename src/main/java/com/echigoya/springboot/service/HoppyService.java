package com.echigoya.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echigoya.springboot.domain.EventList;
import com.echigoya.springboot.mapper.hoppyMapper;

@Service
public class HoppyService {

	@Autowired
	hoppyMapper hoppyMapper;


	/*@Transactional
	public void createUser(SignupForm signupDTO) {
		EmployeeMaster employee = new EmployeeMaster();
		employee.setEmpNo(signupDTO.getEmpNo());
		employee.setName(signupDTO.getName());
		employee.setMail(signupDTO.getMailaddress());
		employee.setPass(signupDTO.getPass());
		this.hoppyMapper.create(employee);
	}
	*/

	public List<EventList> getEventList() {
		List<EventList> eventList = this.hoppyMapper.selectEventListNotDevision();
		return eventList;
	}


}
