package com.srasb.controller.admincontrollers;

import com.srasb.controller.AdminLogin;
import com.srasb.service.spaceservice.SpaceEntityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SpaceDeleteController {

    private final SpaceEntityService spaceEntityService;



    @DeleteMapping("/spaces/delete/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable int id,
                                              HttpSession session) {

        Boolean isAdminSession = (Boolean) session.getAttribute(AdminLogin.ADMIN_SESSION_ATTRIBUTE);

        if (Boolean.FALSE.equals(isAdminSession)) {
            return ResponseEntity.badRequest().body("Admin login required!");
        }

        if (spaceEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no space with such id.");
        }

        spaceEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body("The space has been successfully deleted!");

    }


}
