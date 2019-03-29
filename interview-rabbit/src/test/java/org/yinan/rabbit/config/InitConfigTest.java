package org.yinan.rabbit.config;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yinan
 * @date 19-3-28
 */
public class InitConfigTest {

    @Test
    public void init() {
        InitConfig initConfig = new InitConfig();
        Config config = initConfig.init("/home/laowang/gitwarehouse/Interview-rocket/interview-rabbit/src/test/resources/application-test.properties");
        Assert.assertEquals("127.0.0.1",config.getHost());
        Assert.assertEquals(5672,config.getPort());
        Assert.assertEquals("test",config.getUsername());
        Assert.assertEquals("test",config.getPassword());
        Assert.assertEquals("1500ms",config.getTimeout());
        Assert.assertEquals("/test",config.getVirtualHost());
    }
}