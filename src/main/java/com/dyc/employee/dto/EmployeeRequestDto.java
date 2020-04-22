package com.dyc.employee.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
@ApiModel
public class EmployeeRequestDto {

    private String organizationId;

    @ApiModelProperty(value = "部门id",required = true)
    @NotEmpty(message = "部门id不能为空")
    private String departmentId;

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @Max(value = 120, message = "年龄不能超过120岁")
    private Integer age;

    @NotNull(message = "职位不能为空")
    @Size(max = 10, min = 3, message = "职位长度需要在1-10之间")
    private String position;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
