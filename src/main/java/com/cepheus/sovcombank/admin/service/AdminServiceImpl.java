package com.cepheus.sovcombank.admin.service;

import com.cepheus.sovcombank.admin.model.Admin;
import com.cepheus.sovcombank.admin.repository.AdminRepository;
import com.cepheus.sovcombank.exception.ConflictDataException;
import com.cepheus.sovcombank.exception.ForbiddenException;
import com.cepheus.sovcombank.exception.NotFoundException;
import com.cepheus.sovcombank.exception.ValidationException;
import com.cepheus.sovcombank.post.service.PostService;
import com.cepheus.sovcombank.user.model.User;
import com.cepheus.sovcombank.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;
    private final AdminCodeService adminCodeService;
    private final UserService userService;
    private final PostService postService;

    @Override
    @Transactional
    public void add(Admin admin, String code) {
        if(adminRepository.findByEmail(admin.getEmail()).isPresent()){
            throw new ConflictDataException("An administrator with such an email: "+admin.getEmail()+" already exists");
        }
        if(adminCodeService.find(code)) {
            adminRepository.save(admin);
            adminCodeService.delete(code);
        }else {
            throw new ForbiddenException("Invalid invitation code: "+code);
        }
    }

    @Override
    public void login(String email,String password){
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("The administrator with the mail: " + email + " was not found."));
        if(!admin.getPassword().equals(password)){
            throw new NotFoundException("Invalid password.");
        }
    }

    @Override
    public String getByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("The administrator with the mail: " + email + " was not found."));
        return admin.getName();
    }

    @Override
    @Transactional
    public void confirmationUser(String userEmail) {
        User user=userService.findByEmail(userEmail);
        if(user.getApproved()){
            throw new
                    ValidationException("The user with the mail " + userEmail + " has already been confirmed");
        }
        user.setApproved(true);
    }
    @Override
    public List<User> findUnconfirmedUsers(int from,int size){
        return userService.findAllUnconfirmed(from,size);
    }
    @Override
    @Transactional
    public void changingUserLock(String userEmail,Boolean banned){
        User user = userService.findByEmail(userEmail);
        user.setBanned(banned);
    }

    @Override
    public void deletePostById(Long postId) {
        postService.deleteById(postId);
    }
}
