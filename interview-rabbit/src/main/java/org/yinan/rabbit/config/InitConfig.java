package org.yinan.rabbit.config;

import java.io.*;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author yinan
 * 初始化加载配置类
 * @date 19-3-28
 */
public class InitConfig {

    private Config config = null;

    public Config init(String path) {
        if (config == null) {
            config = new Config();
        }
        try {
            ResourceBundle bundle = load(path);
            setVal(config, bundle);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return config;
    }


    private ResourceBundle load(String path) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        return new PropertyResourceBundle(in);
    }

    private void setVal(Config config, ResourceBundle bundle) {
        config.setHost(bundle.getString("rabbitmq.host"));
        config.setPort(Integer.valueOf(bundle.getString("rabbitmq.port")));
        config.setPassword(bundle.getString("rabbitmq.password"));
        config.setUsername(bundle.getString("rabbitmq.username"));
        config.setVirtualHost(bundle.getString("rabbitmq.virtual-host"));
        config.setTimeout(bundle.getString("rabbitmq.connection-timeout"));
    }

}
