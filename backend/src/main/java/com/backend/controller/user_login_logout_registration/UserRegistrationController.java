package com.backend.controller.user_login_logout_registration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backend.dto.UserDto;
import com.backend.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserRegistrationController {
    private UserService userService;

    @ModelAttribute("userdto")
    public UserDto userResgistrationDto() {
        return new UserDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "sign-up";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("userdto") UserDto userDto,
            @RequestParam(required = false) String type) {

        if ("company".equals(type)) {
            userDto.setRole("company");
            if (userService.checkUserbyEmail(userDto.getEmail())) {
                return "redirect:/registration?type=comapny&emailexist";
            }
            userService.save(userDto);
            return "redirect:/registration?type=company&success";
        } else {
            userDto.setRole("user");
            if (userService.checkUserbyEmail(userDto.getEmail())) {
                return "redirect:/registration?emailexist";
            }
            userService.save(userDto);
        }
        return "redirect:/registration?success";
    }
}
