package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Privilege;
import com.aneeque.backendservice.data.repository.PrivilegeRepository;
import com.aneeque.backendservice.dto.request.PrivilegeRequest;
import com.aneeque.backendservice.exception.PrivilegeNotFoundException;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author B.O Okala III
 */

@Getter
@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;


    public Privilege create(PrivilegeRequest request) {
        boolean privilegeExists = privilegeRepository.existsByName((request.getName()));
        if (privilegeExists) {
            throw new IllegalArgumentException("privilege already exists");
        }

        Privilege privilege = new Privilege(request.getName(), request.getDescription(), request.getModule());
        return privilegeRepository.save(privilege);

    }

    public Privilege update(Long id, PrivilegeRequest request) {

        Privilege privilege = findPrivilegeById(id);
        boolean privilegeExists = privilege != null;

        if (privilegeExists) {
            throw new IllegalArgumentException("privilege Name already exists");
        }
        BeanUtils.copyProperties(request, privilege);
        return privilegeRepository.save(privilege);


    }

    public List<Privilege> getAllPrivileges(){
        return privilegeRepository.findAll();
    }

    public void delete(long id){
        privilegeRepository.deleteById(id);
    }


    public Privilege findPrivilegeByName(String name) {
        return privilegeRepository.findByName((name)).orElseThrow(() -> new PrivilegeNotFoundException("no privilege found"));
    }

    public Privilege findPrivilegeById(Long id) {
        return privilegeRepository.findById((id)).orElseThrow(() -> new PrivilegeNotFoundException("no privilege found"));
    }


}
