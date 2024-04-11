package com.backend.controller.user_login_logout_registration;

import lombok.AllArgsConstructor;

import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.backend.dto.UserDto;
import com.backend.entity.User;
import com.backend.service.UserService;

@Controller
@AllArgsConstructor
@SessionAttributes("userdto")
public class UserLoginController {
    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping("/login")
    public String GetLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute("userdto") UserDto loginDto, Model model, Principal principal) {
    if (userService.checkUserbyEmail(loginDto.getEmail()) == false) {
            return "redirect:/login?emailwrong";
        }
        User user = userService.getUserbyEmail(loginDto.getEmail());
        if (user.getRole().equals("admin")) {
            return "redirect:/admin_home";
        }
        if (userService.checkPasswordUser(loginDto.getEmail(), loginDto.getPassword())) {
            UserDto loggedInUserDto = convertUserToUserDto(user);
            model.addAttribute("userdto", loggedInUserDto);
            return "redirect:/home";
        }
        return "redirect:/login?passwordwrong";
    }
    private UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUserDisplayName(user.getUserDisplayName());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}