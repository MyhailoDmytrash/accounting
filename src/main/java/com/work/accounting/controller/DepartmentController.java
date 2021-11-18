package com.work.accounting.controller;

import com.work.accounting.models.dtos.DepartmentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/department")
public interface DepartmentController
{
    @GetMapping("/get-all")
    List<DepartmentDTO> getAllDepartments();
}
