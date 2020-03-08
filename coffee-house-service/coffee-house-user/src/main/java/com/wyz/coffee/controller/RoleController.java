package com.wyz.coffee.controller;

import com.wyz.coffee.entity.Role;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/24 20:34
 **/
@RestController
@RequestMapping("/role")
//@RequiresRoles("superAdmin")
public class RoleController {
    @Autowired
    @Lazy
    RoleService roleService;

    @GetMapping("/getPage")
    public Response<Pagination<Role>> getRolePage(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "20")int pageSize,
            @RequestParam(value = "roleName", defaultValue = "") String roleName) {
        Pagination<Role> rolePage = roleService.getRolePage(roleName, pageNum, pageSize);
        return Response.success(rolePage);
    }


    @GetMapping("/getAll")
    public Response<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return Response.success(roles);
    }

    @PostMapping("/insert")
    public Response insertRole(@RequestBody Role role) {
        roleService.createRole(role);
        return Response.success();
    }

    @DeleteMapping("/delete")
    public Response deleteRole(@RequestParam("id") Integer id) {
        roleService.deleteRole(id);
        return Response.success();
    }

    @PutMapping("/update")
    public Response updateRole(@RequestBody Role role) {
        roleService.updateRoleById(role);
        return Response.success();
    }
}
