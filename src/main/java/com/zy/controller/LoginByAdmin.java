package com.zy.controller;


import com.zy.entity.Employee;
import com.zy.service.ActivateempService;
import com.zy.service.DepartmentService;
import com.zy.service.EmployeeService;
import com.zy.util.JwtUtils;

import com.zy.vo.base.RespBean;
import com.zy.vo.request.AddEmployeerequest;
import com.zy.vo.request.LoginRequest;
import com.zy.vo.request.PaginationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.FileOutputStream;
import java.sql.Date;

import java.util.Enumeration;


/**
 * @author 执笔画倾颜and陈群
 * @college Yancheng Institute of Technology
 * @Description 登录
 * @class LoginByAdmin
 * @create 2020-02-15 16:28
 */
@Api("管理员端登录以及员工信息")
@RestController
@RequestMapping("/admin")
public class LoginByAdmin {

    @Autowired
    ActivateempService activateempService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    JwtUtils jwtUtils;


    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public RespBean login(@RequestBody LoginRequest loginRequest) throws Exception {
        return activateempService.login(loginRequest);
    }

    @ApiOperation(value = "获取侧边栏的操作")
    @GetMapping("/getOperationsByAdmin")
    public RespBean getOperations() {
        return activateempService.getOperations();
    }

    @ApiOperation(value = "获取用户数据")
    @PostMapping("/users")
    public RespBean getUsers(@RequestBody PaginationRequest paginationRequest) {
        //计算起始数据的下标并重新赋值
        paginationRequest.setPagenum((paginationRequest.getPagenum() - 1) * paginationRequest.getPagesize());
        return employeeService.getEmployees(paginationRequest);
    }


    @ApiOperation(value = "删除离职员工")
    @DeleteMapping("/deleteUser/{id}")
    public RespBean deleteUsers(@PathVariable long id, HttpServletRequest request) {
        Enumeration e = request.getHeaders("authorization");
        while (e.hasMoreElements()) {
            String headValue = (String) e.nextElement();
            int result = employeeService.deleteEmployee(id, new Date(System.currentTimeMillis()), "原因不详！", jwtUtils.parseJwtTest(headValue), new Date(System.currentTimeMillis()));
            if (result > 0) {
                return RespBean.okMessage("删除员工成功!");
            } else {
                return RespBean.error("删除员工失败!");
            }
        }
        return null;

    }

    @ApiOperation(value = "根据员工号查找员工")
    @GetMapping("/getUserByEmpId/{empId}")
    public RespBean getUserByEmpId(@PathVariable long empId) {
        Employee employee = employeeService.getEmployeeByEmpId(empId);
        if (employee != null) {
            return RespBean.ok("查询成功！", employee);
        } else {
            return RespBean.error("查询失败！");
        }
    }

    @ApiOperation(value = "获取部门和相应的职位")
    @GetMapping("/getDepartments")
    public RespBean getDepartments() {
        return departmentService.getDepartments();
    }

    @ApiOperation(value = "获取部门和相应的职位")
    @GetMapping("/getGrades")
    public RespBean getGrades() {
        return departmentService.getGrades();
    }



    @ApiOperation(value = "修改员工信息或者删除员工")
    @PutMapping("/updateEmployee")
    public RespBean updateOrDeleteEmployee(@RequestBody Employee employee, HttpServletRequest request) {
        //解析更新的操作人
        Enumeration e = request.getHeaders("authorization");
        while (e.hasMoreElements()) {
            String headValue = (String) e.nextElement();
            employee.setUpdateUser(jwtUtils.parseJwtTest(headValue));
            employee.setUpdateTime(new Date(System.currentTimeMillis()));
            if (employee.getLeaveDate() == null && employee.getTerminationReason() == null) {
                int result = employeeService.updateEmployeeNotDelete(employee);
                if (result > 0) {
                    return RespBean.okMessage("更新成功！");
                } else {
                    return RespBean.error("更新失败！");
                }
            } else {
                int result = employeeService.updateEmployeeAndDelete(employee);
                if (result > 0) {
                    return RespBean.okMessage("删除成功！");
                } else {
                    return RespBean.error("删除失败！");
                }
            }
        }
        return null;
    }

    @ApiOperation(value = "新增员工")
    @PostMapping("/addEmployee")
    public RespBean addEmployee(@RequestBody AddEmployeerequest addEmployeerequest, HttpServletRequest request) {
        //解析创建的操作人
        Enumeration e = request.getHeaders("authorization");
        while (e.hasMoreElements()) {
            String headValue = (String) e.nextElement();
            Long maxEmpId = employeeService.selectEmpId();
            System.out.println("hello" + maxEmpId);
            if (maxEmpId == null) {
                maxEmpId = new Long(1610701100);
            }
            addEmployeerequest.setEmpId(maxEmpId + 1);
            addEmployeerequest.setCreateUser(jwtUtils.parseJwtTest(headValue));
            addEmployeerequest.setCreateTime(new Date(System.currentTimeMillis()));
            int result = employeeService.addEmployee(addEmployeerequest);
            if (result > 0) {
                return RespBean.okMessage("创建新员工成功！");
            } else {
                return RespBean.error("创建失败！");
            }
        }
        return null;
    }


    @ApiOperation(value = "导出员工信息")
    @GetMapping("/excel/{empIDs}")
    public RespBean excel(@PathVariable String empIDs) throws Exception {
        //解析empIDs
        String[] excelEmpIDs = empIDs.split(",");
        FileOutputStream fileOut = new FileOutputStream("d:\\员工信息.xls");//文件输出位置
        Workbook wb = new HSSFWorkbook();//定义新工作簿
        Sheet sheet = wb.createSheet("第一个sheet页");//构造sheet页
        CellStyle cellStyle = wb.createCellStyle();//定义日期单元格的格式
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        Row row1 = sheet.createRow(0);//创建第一行,并创建第一列
        row1.createCell(0).setCellValue("编号");
        row1.createCell(1).setCellValue("姓名");
        row1.createCell(2).setCellValue("性别");
        row1.createCell(3).setCellValue("职位");
        row1.createCell(4).setCellValue("就职日期");
        row1.createCell(5).setCellValue("部门");
        row1.createCell(6).setCellValue("工作地点");
        row1.createCell(7).setCellValue("手机号码");
        row1.createCell(8).setCellValue("家庭住址");
        row1.createCell(9).setCellValue("邮箱");
        row1.createCell(10).setCellValue("毕业学校");
        row1.createCell(11).setCellValue("专业");
        row1.createCell(12).setCellValue("学位");
        row1.createCell(13).setCellValue("毕业时间");
        row1.createCell(14).setCellValue("离职日期");
        row1.createCell(15).setCellValue("离职原因");
        row1.createCell(16).setCellValue("生日");
        row1.createCell(17).setCellValue("创建人");
        row1.createCell(18).setCellValue("创建时间");
        row1.createCell(19).setCellValue("修改人");
        row1.createCell(20).setCellValue("修改时间");
        row1.createCell(21).setCellValue("是否有效(1:有效,0:无效)");
        //设置列宽
        sheet.setColumnWidth(0, 12 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 25 * 256);
        sheet.setColumnWidth(6, 35 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 35 * 256);
        sheet.setColumnWidth(9, 25 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 15 * 256);
        sheet.setColumnWidth(12, 12 * 256);
        sheet.setColumnWidth(13, 15 * 256);
        sheet.setColumnWidth(14, 15 * 256);
        sheet.setColumnWidth(15, 30 * 256);
        sheet.setColumnWidth(16, 15 * 256);
        sheet.setColumnWidth(17, 10 * 256);
        sheet.setColumnWidth(18, 15 * 256);
        sheet.setColumnWidth(19, 10 * 256);
        sheet.setColumnWidth(20, 15 * 256);
        sheet.setColumnWidth(21, 25 * 256);
        for (int i = 0; i < excelEmpIDs.length; i++) {
            Row row = sheet.createRow(i + 1);//长度为多少就创建多少行
            long empIds = Long.parseLong(excelEmpIDs[i]);
            Employee employee = employeeService.getEmployeeByEmpId(empIds);
            row.createCell(0).setCellValue(employee.getEmpId());//先创建单元格，再塞值*/
            row.createCell(1).setCellValue(employee.getChineseName());
            row.createCell(2).setCellValue(employee.getGender());
            row.createCell(3).setCellValue(employee.getGrade());
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(employee.getHireDate());
            cell4.setCellStyle(cellStyle);
            row.createCell(5).setCellValue(employee.getDepartment());
            row.createCell(6).setCellValue(employee.getWorkingLocation());
            row.createCell(7).setCellValue(employee.getPhone());
            row.createCell(8).setCellValue(employee.getAddress());
            row.createCell(9).setCellValue(employee.getEmail());
            row.createCell(10).setCellValue(employee.getSchool());
            row.createCell(11).setCellValue(employee.getMarjor());
            row.createCell(12).setCellValue(employee.getDegree());
            Cell cell13 = row.createCell(13);
            cell13.setCellValue(employee.getGraduationTime());
            cell13.setCellStyle(cellStyle);
            Cell cell14 = row.createCell(14);
            cell14.setCellStyle(cellStyle);
            if (employee.getLeaveDate() != null) {
                cell14.setCellValue(employee.getLeaveDate());
            } else {
                cell14.setCellValue(" ");
            }
            row.createCell(15).setCellValue(employee.getTerminationReason() != null ? employee.getTerminationReason() : " ");
            Cell cell16 = row.createCell(16);
            cell16.setCellValue(employee.getBirthday());
            cell16.setCellStyle(cellStyle);
            row.createCell(17).setCellValue(employee.getCreateUser());
            Cell cell18 = row.createCell(18);
            cell18.setCellValue(employee.getCreateTime());
            cell18.setCellStyle(cellStyle);
            row.createCell(19).setCellValue(employee.getUpdateUser() != null ? employee.getUpdateUser() : " ");
            Cell cell20 = row.createCell(20);
            cell20.setCellStyle(cellStyle);
            if (employee.getUpdateTime() != null) {
                cell20.setCellValue(employee.getUpdateTime());
            } else {
                cell20.setCellValue(" ");
            }
            row.createCell(21).setCellValue(employee.getIsDeleted());
        }
        wb.write(fileOut);
        fileOut.close();
        return RespBean.okMessage("导出成功！");
    }


}
