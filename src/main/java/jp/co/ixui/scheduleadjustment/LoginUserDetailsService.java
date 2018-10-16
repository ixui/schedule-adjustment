package jp.co.ixui.scheduleadjustment;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.ixui.scheduleadjustment.domain.Emp;
import jp.co.ixui.scheduleadjustment.mapper.EmployeeMapper;




@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeMapper empMapper;

	@Override
	public UserDetails loadUserByUsername(String empNum) throws UsernameNotFoundException {

		Emp emp = empMapper.selectUser(empNum);
		if (Objects.isNull(emp)) {
			throw new UsernameNotFoundException("ユーザーが見つかりませんでした。");
		}
		return new LoginUserDetails(emp);
		
	}
	
	
}


