package com.zicheng.controller;

import com.zicheng.dao.DepartmentDao;
import com.zicheng.dao.EmployeeDao;
import com.zicheng.pojo.Department;
import com.zicheng.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * 子诚
 * Description：
 * 时间：2020/1/9 13:41
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao   employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    //查询员工的所有信息
    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //跳转到添加用户页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    //添加用户方法
    @PostMapping("/emp")
    public String addEpm(Employee employee) {
        //添加操作
        System.out.println("添加用户页面传递过来的==》" + employee);
        employeeDao.save(employee);//调用底层业务方法，保存员工信息
        return "redirect:/emps";
    }

    //跳转到修改用户页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model) {
        //查出员工原来的信息
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);
        //查出所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }
    //修改用户的方法
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除用户的方法
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
