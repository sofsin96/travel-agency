package com.example.travelagency.controller;

import com.example.travelagency.dto.RoleDto;
import com.example.travelagency.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/roles") @RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public RoleDto createRole(@Valid @RequestBody RoleDto roleDto) {
        return roleService.createRole(roleDto);
    }

    @GetMapping("")
    public ResponseEntity<List<RoleDto>> getRoles() {
        return ResponseEntity.ok().body(roleService.getRoles());
    }
}