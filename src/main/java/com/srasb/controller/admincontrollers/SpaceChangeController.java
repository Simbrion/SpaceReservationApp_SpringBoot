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
public class SpaceChangeController {

    private final SpaceEntityService spaceEntityService;

    @PutMapping("/spaces/change/{id}")
    public ResponseEntity<String> changeSpace(@PathVariable int id,
                                              @Valid @RequestBody SpaceDto spaceDto,
                                              BindingResult bindingResult,
                                              HttpSession session) {


        Boolean isAdminSession = (Boolean) session.getAttribute(AdminLogin.ADMIN_SESSION_ATTRIBUTE);

        if (Boolean.FALSE.equals(isAdminSession)) {
            return ResponseEntity.badRequest().body("Admin login required!");
        }

        if (spaceEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no space with such id.");
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors().toString());
        }

        if (spaceEntityService.changeSpaceBasedOn(spaceDto, id)) {
            return ResponseEntity.badRequest().body("The space has been successfully changed!");
        }

        return ResponseEntity.badRequest().body("Something went wrong when trying to space the changed space to the database");
    }


}
