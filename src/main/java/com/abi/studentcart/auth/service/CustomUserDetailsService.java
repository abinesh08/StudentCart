package com.abi.studentcart.auth.service;

import com.abi.studentcart.auth.model.ShopAdmin;
import com.abi.studentcart.auth.model.Student;
import com.abi.studentcart.auth.repository.ShopAdminRepository;
import com.abi.studentcart.auth.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ShopAdminRepository shopAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Student student= studentRepository.findByRollNumber(username).orElse(null);
        if(student!=null){
            return new User(student.getRollNumber(), student.getPassword(), List.of(new SimpleGrantedAuthority("Role_"
            + student.getRole().name())));
        }

        ShopAdmin admin= shopAdminRepository.findByCollegeId(username).orElse(null);
        if(admin!=null){
            return new User(admin.getCollegeId(),
                    admin.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" +admin.getRole().name() )));
        }
        throw new UsernameNotFoundException("User not found");
    }

}
