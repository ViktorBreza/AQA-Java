package models;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Device {
    private static final Logger logger = Logger.getLogger(Device.class.getName());

    private String title;
    private String ip;
    private String port;
    private String password;
    private String contact;

    private Device(String title, String ip, String port, String password, String contact) {
        this.title = title;
        this.ip = ip;
        this.port = port;
        this.password = password;
        this.contact = contact;

        // Logging the device creation
        logger.log(Level.INFO, "New device info: " + this.toString());
    }

    public String getTitle() {
        return title;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getContact() {
        return contact;
    }

    public static class Builder {
        private String title;
        private String ip;
        private String port;
        private String password;
        private String contact;

        public Builder() {}

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder setPort(String port) {
            this.port = port;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setContact(String contact) {
            this.contact = contact;
            return this;
        }

        public Device build() {
            return new Device(title, ip, port, password, contact);
        }
    }

    @Override
    public String toString() {
        return "Device{" +
                "title='" + title + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
