package com.zicheng.dao;

/**
 * 子诚
 * Description：员工Dao
 * 时间：2020/1/7 16:12
 */

import com.zicheng.pojo.Department;
import com.zicheng.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * private Integer id;
 * private String lastName;
 * private String email;
 * private Integer gender;//0代表女，1代表男
 * private Department department;
 * private Date birth;
 */
@Repository
public class EmployeeDao {
    //模拟数据库中员工表的信息
    private static Map<Integer, Employee> employees = null;
    //员工表里面有所属的部门

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();//类似于创建一张员工表

        employees.put(1001, new Employee(1001, "A", "A523186180@qq.com", 1, new Department(101, "教学部")));
        employees.put(1002, new Employee(1002, "B", "B523186180@qq.com", 0, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "C", "C523186180@qq.com", 1, new Department(103, "教研部")));
        employees.put(1004, new Employee(1004, "D", "D523186180@qq.com", 0, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "E", "E523186180@qq.com", 1, new Department(105, "后勤部")));
    }
    //主键自增
    private static Integer initId=1006;
    //增加一个员工
    public void save(Employee employee){
        if(null == employee.getId()){
            employee.setId(initId++);
        }
        //根据employee实体类中的Department中的id，来获取
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
    //查询全部员工
    public Collection<Employee> getEmployees(){
        return employees.values();
    }
    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
    //删除员工通过id
    public void delete(Integer id){
        employees.remove(id);
    }
}
