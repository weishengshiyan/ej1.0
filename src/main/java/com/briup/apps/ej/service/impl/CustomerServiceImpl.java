
package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.Customer;
import com.briup.apps.ej.bean.CustomerExample;
import com.briup.apps.ej.dao.CustomerMapper;
import com.briup.apps.ej.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public long countByExample(CustomerExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CustomerExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) throws Exception {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Customer record)throws  Exception {
        return customerMapper.insert(record);
    }

    @Override
    public int insertSelective(Customer record) {
        return 0;
    }

    @Override
    public List<Customer> selectByExample(CustomerExample example) {
        return null;
    }

    @Override
    public Customer selectByPrimaryKey(Long id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Customer record, CustomerExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Customer record, CustomerExample example) {
        return 0;
    }

    //查询所有顾客信息
    @Override
    public List<Customer> findAllcustomer() throws Exception {
        return customerMapper.findAllcustomer();
    }

    //模糊查询顾客信息
    @Override
    public List<Customer> query(Customer customer) throws Exception {
        return customerMapper.query(customer);
    }

    @Override
    public int updateByPrimaryKeySelective(Customer record) throws Exception {
        return customerMapper.updateByPrimaryKey(record);
    }
    //更新顾客信息数据

    @Override
    public int updateByPrimaryKey(Customer record) throws Exception {
        return customerMapper.updateByPrimaryKey(record);
    }

    public void batchDelete(long[] ids) throws Exception {

        for (long id : ids) {
            customerMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void regist(Customer customer) throws Exception {

        customerMapper.insert(customer);
    }

        @Override
        public Customer findUserByNameAndPwd(String realname, String password) {
            return customerMapper.findUserByNameAndPwd(realname,password);
        }

        @Override
        public Customer findByCustromName(String realname) {

            return customerMapper.findByCustromName(realname);
        }



}
