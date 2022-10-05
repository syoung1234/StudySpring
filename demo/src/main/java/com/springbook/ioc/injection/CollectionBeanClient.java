package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.springbook.ioc.injection.CollectionBean;

public class CollectionBeanClient {
    public static void main(String[] args){
        AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

        CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
        // List 가장 많이 쓰임
        List<String> addressList = bean.getAddresList();
        for (String address : addressList) {
            System.out.println(address.toString());
        }

        // Set 중복 값을 허용하지 않는 객체를 사용할 때
        Set<String> addressList2 = bean.getAddresList2();
        for (String address2 : addressList2) {
            System.out.println(address2.toString());
        }

        // 특정 key로 데이터를 등록하고 사용할 때
        Map<String, String> addressList3 = bean.getAddressList3();
        System.out.println(addressList3.toString());
        for( String strKey : addressList3.keySet() ){
            String strValue = addressList3.get(strKey);
            System.out.println( strKey +":"+ strValue );
        }

        // key=value 형태의 데이터를 등록하고 사용할 때
        Properties addressList4 = bean.getAddressList4();
        System.out.println(addressList4);
        for (String address4 : addressList4.stringPropertyNames()) {
            System.out.println(String.format("키:%s, 값:%s", address4, addressList4.get(address4)));
        }

        factory.close();
    }
}
