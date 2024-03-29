package com.briup.apps.ej.service.impl;

import com.briup.apps.ej.bean.*;
import com.briup.apps.ej.bean.extend.orderExtend;
import com.briup.apps.ej.dao.OrderMapper;
import com.briup.apps.ej.service.OrderService;
import com.briup.apps.ej.utils.MessageUtil;
import org.springframework.stereotype.Service;
import com.briup.apps.ej.service.CustomerService;
import com.briup.apps.ej.service.WaiterService;
import com.briup.apps.ej.service.AddressService;
//import com.briup.apps.ej.service.impl.CustomerServiceImpl;
//import com.briup.apps.ej.dao.CustomerMapper;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

//    @Resource
//    private CustomerMapper customerMapper;

    @Resource
    private CustomerService customerService;

    @Resource
    private WaiterService waiterService;

    @Resource
    private AddressService addressService;

    @Override
    public long countByExample(OrderExample example) {
        return orderMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(OrderExample example) {
        return orderMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) throws Exception {
        Order order = orderMapper.findAllOrderById(id);
        if (order.getId() == null) {
            throw new Exception("要删除的用户不存在");
        } else {
            return orderMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public int insert(Order record) throws Exception {
//        long time=new Date().getTime();//获取当前时间
//        record.setOrderTime(time);//设置当前时间
        int custom_ids=0, waiter_ids=0, address_ids=0;
//1.1        CustomerServiceImpl customerService=new CustomerServiceImpl();//返回空数据原因-接口编程问题，CustomerServiceImpl() 实现接口，父接口可以调用它，而它则无法进行
        //System.out.println("抛出数据"+record.getCustomerId());
        //1.1  Customer customer=customerService.selectByPrimaryKey(record.getCustomerId());//返回空数据原因(直接mapper调用不错误而通过这种就错)--接口编程问题
        //System.out.println("最后抛出数据"+customer.getId());
        //System.out.println("抛出数据"+record.getWaiterId());
       // Waiter waiter=waiterService.findById(record.getWaiterId());//数据流分析结果：数据通过WaiterServiceImpl waiterService=new WaiterServiceImpl();Waiter waiter=waiterService.findById(record.getWaiterId());不能抛出数据，而Waiter waiter=waiterService.findById(record.getWaiterId());可以抛出对象数据
        //System.out.println("最后抛出数据"+waiter.getId());
       // System.out.println("抛出数据"+record.getAddressId());
//        Address address=addressService.selectByPrimaryKey(record.getAddressId());
       // System.out.println("抛出数据"+address.getId());
        if(record.getId()==null){
            if (record.getCustomerId()!=null) {
                  Customer customer=customerService.selectByPrimaryKey(record.getCustomerId());
//                Customer customer=customerMapper.selectByPrimaryKey(record.getCustomerId());//通过DAO包的mapper可以调取数据
                if ((MessageUtil.success("", customer).getData()!=null)) {
                    custom_ids = 1;
                    //System.out.println("custom_ids是"+custom_ids);
                }
            }
            if(record.getWaiterId()!=null){
                Waiter waiter=waiterService.findById(record.getWaiterId());
                if ((MessageUtil.success("",waiter).getData()!=null)) {
                   waiter_ids=1;
                 // System.out.println("waiter_ids1111是"+(MessageUtil.success("",waiter)).getData());如果是MessageUtil.success("",waiter))则是返回一个message对象标示值，应该取其属性值通过MessageUtil.success("",waiter)).getData()
                   // System.out.println("waiter_ids是"+waiter_ids);
                 }
              }
            if(record.getAddressId()!=null){
                 Address address=addressService.selectByPrimaryKey(record.getAddressId());
                 if((MessageUtil.success("",address).getData()!=null)) {
                   address_ids=1;
                    // System.out.println("address_ids是"+address_ids);
                }
             }
       if((((record.getCustomerId()!=null)&&(custom_ids==1))||(record.getCustomerId()==null))&&(((record.getWaiterId()!=null)&&(waiter_ids==1))||(record.getWaiterId()==null))&&(((record.getAddressId()!=null)&&(address_ids==1))||(record.getAddressId()==null))){
           return orderMapper.insert(record);
       }
       else {
           throw new Exception("外键值有错误或者不存在");
       }
        } else {
            throw new Exception("请不要输入id值");
        }
    }

    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public List<Order> selectByExample(OrderExample example) {
        return orderMapper.selectByExample(example);
    }

    /**@Override
    public Order selectByPrimaryKey(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }**/

    @Override
    public int updateByExampleSelective(Order record, OrderExample example) {
        return orderMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Order record, OrderExample example) {
        return orderMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) throws Exception {
        int custom_ids=0, waiter_ids=0, address_ids=0;
        if(record.getId()!=null){
            if (record.getCustomerId()!=null) {
                Customer customer=customerService.selectByPrimaryKey(record.getCustomerId());
                if (MessageUtil.success("", customer).getData()!=null) {
                    custom_ids = 1;
                }
            }
            if(record.getWaiterId()!=null){
                Waiter waiter=waiterService.findById(record.getWaiterId());
                if (MessageUtil.success("",waiter).getData()!=null) {
                    waiter_ids=1;
                }
            }
            if(record.getAddressId()!=null){
                Address address=addressService.selectByPrimaryKey(record.getAddressId());
                if(MessageUtil.success("",address).getData()!=null) {
                    address_ids=1;
                }
            }
            if((((record.getCustomerId()!=null)&&(custom_ids==1))||(record.getCustomerId()==null))&&(((record.getWaiterId()!=null)&&(waiter_ids==1))||(record.getWaiterId()==null))&&(((record.getAddressId()!=null)&&(address_ids==1))||(record.getAddressId()==null))){
                return orderMapper.updateByPrimaryKey(record);
            }
            else {
                throw new Exception("外键值有错误或者不存在");
            }
        } else {
            throw new Exception("请输入id值");
        }
    }

    @Override
    public orderExtend findAllOrderById(Long id) {
        return orderMapper.findAllOrderById(id);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderMapper.findAllOrder();
    }

    @Override
    public List<Order> query(Order order) {
        return orderMapper.query(order);
    }

    @Override
    public void betchDelete(long[] ids) throws Exception {
        for(long id:ids){
            orderMapper.deleteByPrimaryKey(id);
        }
    }
}
