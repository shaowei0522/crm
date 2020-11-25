package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.base.constants.ExceptionEnum;
import com.bjpowernode.crm.base.exception.CrmException;
import com.bjpowernode.crm.base.util.DateTimeUtil;
import com.bjpowernode.crm.base.util.UUIDUtil;
import com.bjpowernode.crm.settings.bean.User;
import com.bjpowernode.crm.settings.mapper.UserMapper;
import com.bjpowernode.crm.workbench.bean.*;
import com.bjpowernode.crm.workbench.mapper.*;
import com.bjpowernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/21 21:24
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("ClueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private ContactRemarkMapper contactRemarkMapper;
    @Autowired
    private ContactActivityRelationMapper contactActivityRelationMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;
    @Autowired
    private TransactionRemarkMapper transactionRemarkMapper;
    @Override
    public void addClue(Clue clue) {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        int i = clueMapper.insertSelective(clue);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CLUE_ADD);
        }

    }

    @Override
    public List<Clue> queryAllClues() {
//        此处需要将clue中的owner的uuid转换成为user的实际名称
        List<Clue> clueList = clueMapper.selectAll();
        for (Clue clue : clueList) {
            User user = userMapper.selectByPrimaryKey(clue.getOwner());
            clue.setOwner(user.getName());
        }
        return clueList;
    }

    @Override
    public Clue queryClueDetail(String id) {
        Clue clue = clueMapper.selectByPrimaryKey(id);

        User user = userMapper.selectByPrimaryKey(clue.getOwner());
        clue.setOwner(user.getName());

        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setClueId(id);
        List<ClueRemark> select = clueRemarkMapper.select(clueRemark);
        clue.setClueRemarkList(select);

        List<Activity> activityList = new ArrayList<>();
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(id);
        List<ClueActivityRelation> select1 = clueActivityRelationMapper.select(clueActivityRelation);
        for (ClueActivityRelation activityRelation : select1) {
            Activity activity = activityMapper.selectByPrimaryKey(activityRelation.getActivityId());
            User user1 = userMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwner(user1.getName());
            activityList.add(activity);
        }
        clue.setActivityList(activityList);

        return clue;
    }

    @Override
    public void deleteClueRemark(String id) {
        int i = clueRemarkMapper.deleteByPrimaryKey(id);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CLUE_REMARK_DELETE);
        }
    }

    @Override
    public void updateClueRemark(ClueRemark clueRemark) {

        clueRemark.setEditTime(DateTimeUtil.getSysTime());
        clueRemark.setEditFlag("1");
        int i = clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CLUE_REMARK_UPDATE);
        }

    }

    @Override
    public ClueRemark addClueRemark(ClueRemark clueRemark) {
        clueRemark.setId(UUIDUtil.getUUID());
        clueRemark.setCreateTime(DateTimeUtil.getSysTime());
        clueRemark.setEditFlag("0");
        int i = clueRemarkMapper.insertSelective(clueRemark);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CLUE_REMARK_ADD);
        }
        return clueRemark;
    }

    @Override
    public void deleteBindActivity(ClueActivityRelation clueActivityRelation) {
        int delete = clueActivityRelationMapper.delete(clueActivityRelation);
        if (delete == 0) {
            throw new CrmException(ExceptionEnum.CLUE_ACTIVITY_RELATION_DELETE);
        }
    }

    @Override
    public List<Activity> selectActivitiesUnbind(ClueActivityRelation clueActivityRelation, String name) {
        List<String> activityIds = new ArrayList<>();
        if (clueActivityRelation.getClueId() != null) {
            List<ClueActivityRelation> select = clueActivityRelationMapper.select(clueActivityRelation);
            for (ClueActivityRelation activityRelation : select) {
                activityIds.add(activityRelation.getActivityId());
            }
        }

        List<Activity> activityList = new ArrayList<>();
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if (activityIds.size() > 0) {
            criteria.andNotIn("id", activityIds);
        }
        if (name != null && name != "") {
            criteria.andLike("name", "%" + name + "%");
        }
        activityList = activityMapper.selectByExample(example);


//        此处对活动的所有者进行修正，显示真实的姓名而不是uuid
        for (Activity activity : activityList) {
            User user = userMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwner(user.getName());
        }
        return activityList;
    }

    @Override
    public void addClueActivityRelation(String ids, String clueId) {
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);
        String[] split = ids.split(",");
        for (String s : split) {
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setActivityId(s);
            int i = clueActivityRelationMapper.insertSelective(clueActivityRelation);
            if (i == 0) {
                throw new CrmException(ExceptionEnum.CLUE_ACTIVITY_RELATION_ADD);
            }

        }
    }

    @Override
    public List<Activity> queryAllClueActivities(String clueId) {
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);
        List<ClueActivityRelation> select = clueActivityRelationMapper.select(clueActivityRelation);
        List<Activity> activityList = new ArrayList<>();
        for (ClueActivityRelation activityRelation : select) {
            Activity activity = activityMapper.selectByPrimaryKey(activityRelation.getActivityId());
            User user = userMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwner(user.getName());
            activityList.add(activity);
        }
        return activityList;
    }

    @Override
    public Clue queryClueByPrimaryKey(String clueId) {
        Clue clue = clueMapper.selectByPrimaryKey(clueId);
        User user = userMapper.selectByPrimaryKey(clue.getOwner());
        clue.setOwner(user.getName());
        return clue;
    }

    /**
     * 将线索转换成为客户
     * */
    @Override
    public void saveConvert(String clueId, String username, Transaction transaction, String isCreateTransaction) {
//        查询出来要转换的线索的所有信息
        Clue clue = clueMapper.selectByPrimaryKey(clueId);
//        先将clue中的company取出来判断客户是否存在，如果不存在需要进行创建新的客户
        Example example = new Example(Customer.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", clue.getCompany());
        List<Customer> customers = customerMapper.selectByExample(example);
        Customer customer = null;
        if (customers.size() == 0) {
//            客户不存在，创建一个客户
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setAddress(clue.getAddress());
            customer.setContactSummary(clue.getContactSummary());

            customer.setCreateBy(username);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setName(clue.getCompany());
            customer.setNextContactTime(clue.getNextContactTime());
            customer.setPhone(clue.getPhone());
            customer.setWebsite(clue.getWebsite());

            customer.setOwner(clue.getOwner());
            int i = customerMapper.insertSelective(customer);
            if (i == 0) {
                throw new CrmException(ExceptionEnum.CUSTOMER_ADD);
            }

        } else {
            customer = customers.get(0);
        }

//        将线索中的联系人信息存在联系人表中
        Contact contact = new Contact();
        contact.setId(UUIDUtil.getUUID());
        contact.setAppellation(clue.getAppellation());
        contact.setCreateBy(username);
        contact.setCreateTime(DateTimeUtil.getSysTime());
        contact.setCustomerId(customer.getId());
        contact.setEmail(clue.getEmail());
        contact.setFullname(clue.getFullname());
        contact.setJob(clue.getJob());
        contact.setMphone(clue.getMphone());
        contact.setOwner(clue.getOwner());
        contact.setNextContactTime(clue.getNextContactTime());
        contact.setSource(clue.getSource());

        int i = contactMapper.insertSelective(contact);
        if (i == 0) {
            throw new CrmException(ExceptionEnum.CONTACT_ADD);
        }

        //线索中的备注信息取出来保存在客户备注和联系人备注中
        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setClueId(clue.getId());
        List<ClueRemark> select = clueRemarkMapper.select(clueRemark);
        for (ClueRemark remark : select) {
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setNoteContent(remark.getNoteContent());
            customerRemark.setCreateBy(username);
            customerRemark.setCreateTime(remark.getCreateTime());
            customerRemark.setCustomerId(customer.getId());
            int i1 = customerRemarkMapper.insertSelective(customerRemark);
            if (i1 == 0) {
                throw new CrmException(ExceptionEnum.CUSTOMER_REMARK_ADD);
            }

            ContactRemark contactRemark = new ContactRemark();
            contactRemark.setId(UUIDUtil.getUUID());
            contactRemark.setNoteContent(remark.getNoteContent());
            contactRemark.setCreateBy(username);
            contactRemark.setCreateTime(remark.getCreateTime());
            contactRemark.setContactsId(contact.getId());
            int i2 = contactRemarkMapper.insertSelective(contactRemark);
            if (i2 == 0) {
                throw new CrmException(ExceptionEnum.CONTACT_REMARK_ADD);
            }
        }
        //将"线索和市场活动的关系"转换到"联系人和市场活动的关系中"
        //先将当前线索对应的市场活动的id号查询出来
        Example example1 = new Example(ClueActivityRelation.class);
        example1.createCriteria().andEqualTo("clueId", clue.getId());
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.selectByExample(example1);
        for (ClueActivityRelation clueActivityRelation : clueActivityRelations) {
            ContactActivityRelation contactActivityRelation = new ContactActivityRelation();
            contactActivityRelation.setId(UUIDUtil.getUUID());
            contactActivityRelation.setContactsId(contact.getId());
            contactActivityRelation.setActivityId(clueActivityRelation.getActivityId());
            int i1 = contactActivityRelationMapper.insertSelective(contactActivityRelation);
            if (i1 == 0) {
                throw new CrmException(ExceptionEnum.CLUE_CONVERT);
            }
        }
        /**
         * 如果转换过程中发生了交易，创建一条新的交易，交易信息不全，后面可以通过修改交易来完善交易信息
         */
        if ("1".equals(isCreateTransaction)) {
            transaction.setId(UUIDUtil.getUUID());
            transaction.setCustomerId(customer.getId());
            transaction.setContactsId(contact.getId());
            transaction.setCreateBy(username);
            transaction.setCreateTime(clue.getCreateTime());
            transaction.setOwner(clue.getOwner());
            transaction.setSource(clue.getSource());
            int i1 = transactionMapper.insertSelective(transaction);
            if (i1 == 0) {
                throw new CrmException(ExceptionEnum.CLUE_CONVERT);
            }
            //创建该条交易对应的交易历史以及备注   交易-->交易历史 1-n
            //交易历史
            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setId(UUIDUtil.getUUID());
            transactionHistory.setCreateBy(username);
            transactionHistory.setCreateTime(transaction.getCreateTime());
            transactionHistory.setExpectedDate(transaction.getExpectedDate());
            transactionHistory.setMoney(transaction.getMoney());
            transactionHistory.setStage(transaction.getStage());
            transactionHistory.setTranId(transaction.getId());
            int i2 = transactionHistoryMapper.insertSelective(transactionHistory);
            if (i2 == 0) {
                throw new CrmException(ExceptionEnum.CLUE_CONVERT);
            }
            //交易备注
            TransactionRemark transactionRemark = new TransactionRemark();
            transactionRemark.setId(UUIDUtil.getUUID());
            transactionRemark.setCreateBy(transaction.getCreateBy());
            transactionRemark.setCreateTime(transaction.getCreateTime());
            transactionRemark.setTranId(transaction.getId());
            transactionRemark.setEditFlag("0");
            int i3 = transactionRemarkMapper.insertSelective(transactionRemark);
            if (i3 == 0) {
                throw new CrmException(ExceptionEnum.CLUE_CONVERT);
            }
        }
        //删除线索的备注信息
        Example example2 = new Example(ClueRemark.class);
        example2.createCriteria().andEqualTo("clueId", clue.getId());
        int i1 = clueRemarkMapper.deleteByExample(example2);
        if (i1 == 0) {
            throw new CrmException(ExceptionEnum.CLUE_CONVERT);
        }
        //删除线索和市场活动的关联关系
        Example example3 = new Example(ClueActivityRelation.class);
        example3.createCriteria().andEqualTo("clueId", clue.getId());
        int i2 = clueActivityRelationMapper.deleteByExample(example3);
        if (i2 == 0) {
            throw new CrmException(ExceptionEnum.CLUE_CONVERT);
        }

        //删除线索
        int i3 = clueMapper.deleteByPrimaryKey(clue);
        if (i3 == 0) {
            throw new CrmException(ExceptionEnum.CLUE_CONVERT);
        }
    }

    @Override
    public List<Activity> selectActivitiesByClueId(String clueId, String name) {
        List<String> activityIds = new ArrayList<>();
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);

        List<ClueActivityRelation> select = clueActivityRelationMapper.select(clueActivityRelation);
        for (ClueActivityRelation activityRelation : select) {
            activityIds.add(activityRelation.getActivityId());
        }

        List<Activity> activityList = new ArrayList<>();
        Example example = new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", activityIds);
        if (name != null && name != "") {
            criteria.andLike("name", "%" + name + "%");
        }
        activityList = activityMapper.selectByExample(example);


//        此处对活动的所有者进行修正，显示真实的姓名而不是uuid
        for (Activity activity : activityList) {
            User user = userMapper.selectByPrimaryKey(activity.getOwner());
            activity.setOwner(user.getName());
        }
        return activityList;
    }


}
