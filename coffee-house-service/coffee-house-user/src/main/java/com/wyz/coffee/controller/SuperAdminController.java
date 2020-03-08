package com.wyz.coffee.controller;


import com.wyz.coffee.dto.UserRoleDto;
import com.wyz.coffee.entity.Permission;
import com.wyz.coffee.entity.RolePermission;
import com.wyz.coffee.entity.User;
import com.wyz.coffee.entity.UserRole;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/15 22:33
 **/
@RestController
@RequestMapping("/superAdmin")
//@RequiresRoles("superAdmin")
public class SuperAdminController {

    @Autowired
    @Lazy
    SuperAdminService superAdminService;
    @Autowired
    @Lazy
    UserRoleService userRoleService;
    @Autowired
    @Lazy
    RoleService roleService;
    @Autowired
    @Lazy
    PermissionService permissionService;
    @Autowired
    @Lazy
    RolePermissionService rolePermissionService;

    /**
     * 修改顾客信息
     */
    @PutMapping("/updateUser")
    public Response updateCustomer(@RequestBody User user) {
        superAdminService.updateUser(user);
        return Response.success();
    }

    @PutMapping("/updateUserRole")
    public Response updateUserRole(@RequestBody UserRoleDto userRoleDto) {
        superAdminService.updateUserRole(userRoleDto);
        return Response.success();
    }

//

    @GetMapping("/permission/get")
    public Response<List<Permission>> getPermission(@RequestParam("permissionName") String permissionName) {
        List<Permission> permissions = permissionService.getPermissionByName(permissionName);
        return Response.success(permissions);
    }


    @PostMapping("/permission/insert")
    public Response insertPermission(@RequestBody Permission permission) {
        permissionService.createPermission(permission);
        return Response.success();
    }

    @DeleteMapping("/permission/delete")
    public Response deletePermission(@RequestParam("permissionId") Integer permissionId) {
        permissionService.deletePermissionById(permissionId);
        return Response.success();
    }

    @PutMapping("/permission/update")
    public Response updatePermission(Permission permission) {
        permissionService.updatePermissionById(permission);
        return Response.success();
    }



//
    @PostMapping("/userRole/insert")
    public Response insertUserRole(@RequestBody UserRole userRole) {
        userRoleService.insert(userRole);
        return Response.success();
    }

    @PutMapping("/userRole/update")
    public Response updateUserRole(@RequestBody UserRole userRole) {
        userRoleService.updateById(userRole);
        return Response.success();
    }

//
    @PostMapping("/rolePermission/insert")
    public Response insertRolePermission(@RequestBody RolePermission rolePermission) {
        rolePermissionService.insert(rolePermission);
        return Response.success();
    }

    @PutMapping("/rolePermission/update")
    public Response updateRolePermission(@RequestBody RolePermission rolePermission) {
        rolePermissionService.update(rolePermission);
        return Response.success();
    }


    @PostMapping("/rolePermission/delete")
    public Response deleteRolePermission(@RequestBody RolePermission rolePermission) {
        rolePermissionService.deleteByRoleIdAndPermissionId(rolePermission);
        return Response.success();
    }

}
