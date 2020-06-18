package com.jornah.util.json;

import com.alibaba.fastjson.JSONObject;
import com.jornah.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

public class FastJsonDemo {
    public static void main(String[] args) {
        String str1="{\"disconnect_reason\":0,\"element_id\":62271,\"int_product_inst\":\"204103\",\"product_start\":\"2020-01-07 08:00:00.0\",\"tracking_id\":0,\"tracking_id_serv\":0}";
        String str2="{\"act_nrc_tracking_id\":0,\"act_nrc_tracking_id_serv\":0,\"ala_carte_ind\":\"\\u0000\",\"contract_id\":0,\"contract_level\":1,\"contract_type\":207379,\"distribute_payback\":\"0\",\"grace_period\":0,\"int_customer_contract_inst\":\"475410565\",\"term_nrc_tracking_id\":0,\"term_nrc_tracking_id_serv\":0,\"tracking_id\":0,\"tracking_id_serv\":0,\"waive_act_nrc_ind\":\"N\",\"waive_term_nrc_ind\":\"N\"}";
        // {'AccountInternalId':70545829,'ActiveDt':'2019-12-12 00:00:00','CorridorPlanId':801464,'CountryCodeOrigin':-9999,'CountryCodeTarget':-9999,'DiscountId':-9999,'IsDefault':false,'IsIcbCorridor':false,'IsInternal':false,'Key':{'LanguageCode':1,'TrackingId':-9999,'TrackingIdServ':-1,'languageCode':1,'trackingId':-9999,'trackingIdServ':-1},'OriginIsXact':false,'OriginRestriction':90,'OwningAccountExternalId':'70545829','OwningAccountInternalId':-9999,'PlanLevel':90,'PlanType':50,'PointCategory':0,'ServiceInternalIdResets':-9999,'TargetIsXact':false,'accountInternalId':70545829,'activeDt':'2019-12-12 00:00:00','corridorPlanId':801464,'countryCodeOrigin':-9999,'countryCodeTarget':-9999,'discountId':-9999,'isDefault':false,'isIcbCorridor':false,'isInternal':false,'key':{'$ref':'$.Key'},'languageCode':1,'originIsXact':false,'originRestriction':90,'owningAccountExternalId':'70545829','owningAccountInternalId':-9999,'planLevel':90,'planType':50,'pointCategory':0,'serviceInternalIdResets':-9999,'targetIsXact':false,'trackingId':-9999,'trackingIdServ':-1}
        // String str="{'active_dt':'','association_id':-9999,'association_id_serv':0,'association_type':'Z','component_id':-9999,'component_instance_id':0,'component_instance_id_serv':0,'connect_reason':-9999,'inactive_dt':'','package_id':-9999,'package_status':'Z','prev_end_dt':'','vtDBHandle':{'active_dt':'20191022','external_id':'70545829','external_id_type':1,'objectType':{}},'vtServerID':-1}";
        Map<String,String> map1 = JSONObject.parseObject(str1, Map.class);
        Map<String,String> map2 = JSONObject.parseObject(str2, Map.class);
        Map<String,String> hashMap=new HashMap<>();

        for (Map.Entry<String, String> entry : map1.entrySet()) {
           if(!MapUtil.containsIgnoreCase(hashMap,entry.getKey())){
               hashMap.put(entry.getKey(),entry.getValue());
           }
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry);
        }
        hashMap.clear();
        System.out.println("--licg---  ------------ :-----");
        System.out.println("--licg---  ------------ :-----");
        System.out.println("--licg---  ------------ :-----");
        System.out.println("--licg---  ------------ :-----");
        System.out.println("--licg---  ------------ :-----");
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            if(!MapUtil.containsIgnoreCase(hashMap,entry.getKey())){
                hashMap.put(entry.getKey(),entry.getValue());
            }
        }

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println(entry);
        }
    }

}
