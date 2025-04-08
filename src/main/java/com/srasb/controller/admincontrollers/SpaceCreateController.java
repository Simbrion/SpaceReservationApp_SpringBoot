package com.srasb.controller.admincontrollers;

import com.srasb.controller.AdminLogin;
import com.srasb.model.dto.SpaceDto;
import com.srasb.service.spaceservice.SpaceEntityService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SpaceCreateController {

    private final SpaceEntityService spaceEntityService;


    @PostMapping("/spaces/create")
    public ResponseEntity<String> createSpace(@Valid @RequestBody SpaceDto spaceDto,
                                              BindingResult bindingResult,
                                              HttpSession session) {

        Boolean isAdminSession = (Boolean) session.getAttribute(AdminLogin.ADMIN_SESSION_ATTRIBUTE);

        if (Boolean.FALSE.equals(isAdminSession)) {
            return ResponseEntity.badRequest().body("Admin login required!");
        }


        if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors().toString());
        }

        if (spaceEntityService.isSaved(spaceDto)) {
            return ResponseEntity.badRequest().body("Validation failed: the space with this name already exists!");
        }

        spaceEntityService.addEntityBasedOn(spaceDto);
            return ResponseEntity.ok().body("The space has been successfully saved!");
    }


}
