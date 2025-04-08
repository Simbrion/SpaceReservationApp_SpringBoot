package com.srasb.controller.admincontrollers;

import com.srasb.controller.AdminLogin;
import com.srasb.model.dto.SpaceDto;
import com.srasb.service.spaceservice.SpaceDtoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpaceSendListController {

    private final SpaceDtoService spaceDtoService;

    @GetMapping("/spaces/show")


    public ResponseEntity<List<SpaceDto>> sendListOfSpaces(HttpSession session) {

        Boolean isAdminSession = (Boolean) session.getAttribute(AdminLogin.ADMIN_SESSION_ATTRIBUTE);

        if (Boolean.FALSE.equals(isAdminSession)) {
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<SpaceDto> spaceDtoList = spaceDtoService.getDtoList();

        if (spaceDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(spaceDtoList, HttpStatus.OK);

    }


}
