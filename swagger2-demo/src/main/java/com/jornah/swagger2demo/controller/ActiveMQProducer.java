package com.jornah.swagger2demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(description = "生产者进程API接口")
@RestController
@RequestMapping("/producer")
public class ActiveMQProducer {


    @ApiOperation(value = "1连接线路",
            notes = "向服务端发起连接线路请求，本接口只适用于客户端在不登陆的情况下连接免费线路；<br/>" +
                    "服务端成功返回线路的连接配置后客户端方可正式连接，其中config部分通过先AES加密，然后BASE64编码而成；<br/>" +
                    "客户端在得到这个配置后，应将data字段的值先然后BASE64解码，然后再用AES算法解密，解密的key请联系后端管理人员；<br/>" +
                    "注意，在正常返回时，Response中的meta元素可能包含如下节点：<ul>" +
                    "<li>protocol (required): 建议客户端应采用的协议，值SS为ShadowSocks协议，值OPENVPN为OpenVPN协议；</li>" +
                    "<li>source (optional): 代表当前发起连接请求的客户所在的国家码，这是根据用户的请求IP的计算出来的，如CN、US；</li>" +
                    "<li>target (optional): 线路所在的国家码，如CN；</li>" +
                    "</ul>",
            hidden = true)
    @PostMapping(value = {"/channels/{id:\\-1|\\d+}/connect/without-authorizing", "/channels/{id:\\-1|\\d+}/connect2"},
            produces = APPLICATION_JSON_VALUE, headers = "api-version=v1.0")
    public String sendText(@PathVariable String id) {

        return "SUCESS";
    }

    @ApiOperation(value = "123连接线路",
            notes = "向服务端发起连接线路请求，服务端会做一些验证以及数据状态改变和统计操作；<br/>" +
                    "当用户没有超过最大连接台数的时候，服务器端会返回线路的连接配置，否则只会返回meta里面的信息<br/>" +
                    "服务端成功返回线路的连接配置后客户端方可正式连接，其中config部分通过先AES加密，然后BASE64编码而成；<br/>" +
                    "客户端在得到这个配置后，应将data字段的值先然后BASE64解码，然后再用AES算法解密，解密的key请联系后端管理人员；<br/>" +
                    "注意，在正常返回时，Response中的meta元素可能包含如下节点：<ul>" +
                    "<li>protocol (required): 建议客户端应采用的协议，值SS为ShadowSocks协议，值OPENVPN为OpenVPN协议；</li>" +
                    "<li>source (optional): 代表当前发起连接请求的客户所在的国家码，这是根据用户的请求IP的计算出来的，如CN、US；</li>" +
                    "<li>target (optional): 线路所在的国家码，如CN；</li>" +
                    "<li>deviceName (optional)：最早连接的设备名称，如1435435435353545345；</li>" +
                    "<li>deviceToken (optional)：最早连接的设备token，能唯一标识一台设备；</li>" +
                    "<li>deviceType (optional)：最早连接的设备类型；</li>" +
                    "<li>deviceCount (required)：用户可同时登录设备的台数；</li>" +
                    "<li>activeDeviceCount (required)：用户可同时登录设备的台数；</li>" +
                    "<li>isReachMaxLimit (required)：是否达到最大连接连接台数；</li>" +
                    "<li>smartConnectionChannelId (required)：智能连接线路ID；</li>" +
                    "<li>smartConnectionChannelName (required)：智能连接线路名字；</li>" +
                    "<li>smartConnectionChannelFlag (required)：智能连接线路国旗；</li>" +
                    "<li>smartConnectionChannelSignalLevel (required)：智能连接线路信号级；</li>" +
                    "</ul>",
            authorizations = @Authorization("Bearer"))
    @PostMapping(value = "/channels/{id:\\-1|\\d+}/connect1", produces = APPLICATION_JSON_VALUE, headers = {"api-version=v1.0", "Authorization"})
    public String queryWordCount(@RequestBody String word) {

        return "SUCESS";
    }
}