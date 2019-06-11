package com.briup.apps.ej.web.controller;


import com.briup.apps.ej.bean.Customer;
import com.briup.apps.ej.service.CustomerService;
import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;


    @ApiOperation("查询所有顾客信息")
    @GetMapping("findAllCustomer")
    public Message findAllCustomer(){
        List<Customer> list = customerService.findAllcustomer();
        return MessageUtil.success("success",list);
    }

    @ApiOperation("通过主键查询")
    @GetMapping("selectById")
    public Message selectById(
            @ApiParam(value = "主键",required = true)
            @RequestParam(value = "id")long id){
        Customer customer=customerService.selectByPrimaryKey(id);
        return MessageUtil.success("success",customer);
    }
    @ApiOperation("通过id删除信息")
    @GetMapping("deleteById")
    public Message deleteById(@ApiParam(value = "主键",required = true) @RequestParam("id") Long id){
        try {
            customerService.deleteByPrimaryKey(id);
            return MessageUtil.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return MessageUtil.error(e.getMessage());
        }
    }
    @ApiOperation("添加顾客信息")
    @GetMapping("insert")
    public Message insert(Customer record){

        int insert=customerService.insert(record);
        return MessageUtil.success("success",insert);
    }

    @ApiOperation("更新顾客信息")
    @GetMapping("update")
    public Message updateByExampleSelective(Customer record){

        int updateByExampleSelective=customerService.insert(record);
        return MessageUtil.success("success",updateByExampleSelective);
    }

    @PostMapping("batchDelete")
    @ApiOperation("批量删除顾客信息")
    public Message batchDelete(@NotNull(message = "ids不能为空") long[] ids) throws Exception{
        customerService.batchDelete(ids);
        return MessageUtil.success("批量删除成功");
    }
}


