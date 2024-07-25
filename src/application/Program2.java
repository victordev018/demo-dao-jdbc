package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1 : department findById =====");
        Department dep = departmentDao.findById(1);
        System.out.println(dep);

        System.out.println("\n=== TEST 2 : department findAll =====");
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);

        System.out.println("\n=== TEST 3 : department insert =====");
        Department newDepartment = new Department(null, "Music");
        departmentDao.insert(newDepartment);
        System.out.println("inserted! New Id: " + newDepartment.getId());

        System.out.println("\n=== TEST 4 : department update =====");
        Department dep2 = departmentDao.findById(1);
        dep2.setName("Food");
        departmentDao.update(dep2);
        System.out.println("Update completed!");

        System.out.println("\n=== TEST 5 : department delete =====");
        System.out.print("Enter id for delete: ");
        int id = in.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed!");

        in.close();
    }
}
