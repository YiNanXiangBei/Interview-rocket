package org.yinan.rabbit.config;

/**
 * @author yinan
 * @date 19-3-28
 */
public class  Config {

    private String host;

    private long port;

    private String username;

    private String password;

    private String timeout;

    private String virtualHost;

    public String getHost() {
        return host;
    }

    void setHost(String host) {
        this.host = host;
    }

    public long getPort() {
        return port;
    }

    void setPort(long port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public String getTimeout() {
        return timeout;
    }

    void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Config{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", timeout='").append(timeout).append('\'');
        sb.append(", virtualHost='").append(virtualHost).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
