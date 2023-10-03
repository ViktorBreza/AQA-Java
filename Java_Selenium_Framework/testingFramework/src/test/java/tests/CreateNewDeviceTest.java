package tests;

import models.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobjects.CreateDevicePage;
import pageobjects.DevicesPage;

import java.util.logging.Logger;

public class CreateNewDeviceTest extends BaseTest {
    private DevicesPage devicesPage;
    private static final Logger logger = Logger.getLogger(CreateNewDeviceTest.class.getName());

    @BeforeEach
    public void setUpLogger() {
        configureLogger(CreateNewDeviceTest.class);
    }

    @Test
    public void shouldCreateNewDevice() {
        logger.info("Creating a new device...");
        devicesPage = new DevicesPage(myDriver);

        devicesPage.openNewDevicePage();

        Device newDevice = new Device.Builder()
                .setTitle("My Device")
                .setIp("123456")
                .setPort("8080")
                .setPassword("password123")
                .setContact("Татьяна Алимова")
                .build();

        CreateDevicePage createDevicePage = new CreateDevicePage(myDriver);

        createDevicePage.createDevice(newDevice);

        logger.info("New device created");
    }

}
