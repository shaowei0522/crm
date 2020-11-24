import com.bjpowernode.crm.base.constants.ExceptionEnum;
import com.bjpowernode.crm.base.exception.CrmException;
import com.bjpowernode.crm.base.util.DateTimeUtil;
import com.bjpowernode.crm.base.util.MD5Util;
import com.bjpowernode.crm.base.util.UUIDUtil;
import org.junit.Test;


/**
 * @ProjectName: crm
 * @Package: PACKAGE_NAME
 * @Description: java类作用描述
 * @Author: 王少伟
 * @CreateDate: 2020/11/16 19:47
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class TestCrm {
    /*
    * 测试UUID生成32位字符串
    * */
    @Test
    public void test01(){
        String uuid = UUIDUtil.getUUID();
        System.out.println(uuid);
    }

    /*
    * 测试MD5生成加密密码
    * */
    @Test
    public void test02(){
        String admin = MD5Util.getMD5("admin");
        System.out.println(admin);

    }


    /*
    * 测试自定义异常
    * */
    @Test
    public void test03(){
        CrmException crmException = new CrmException(ExceptionEnum.LOGIN_ACCOUNT_ERROR);
        System.out.println(crmException.getMessage());


    }

    @Test
    public void test05(){
        String sysTime = DateTimeUtil.getSysTime();
        System.out.println(sysTime.compareTo("2021-10-10 10:10:10"));
        System.out.println(sysTime);
    }
}
