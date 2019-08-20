package com.vz.uiam.onenet.vps.interfaces.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vz.uiam.onenet.vps.interfaces.VPSInterfacesServiceApplication;
import com.vz.uiam.onenet.vps.interfaces.jpa.dto.model.ClliSelectionDTO;
import com.vz.uiam.onenet.vps.interfaces.service.ClliSelectionService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VPSInterfacesServiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:10027")
@ActiveProfiles("development")
public class ServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);  

    @Autowired
    private ClliSelectionService clliSelectionService;

    @Test
    public void testGetClliSelectionExcecption() {
	try {
	    ClliSelectionDTO clliSelectionDTO = new ClliSelectionDTO();
	    clliSelectionService.getClliSelections(clliSelectionDTO);
	} catch (Exception e) {
	    LOGGER.error("Exception Occured as: ", e);
	}
    }

    @Test
    public void testCreateClliSelectionNull() {
	try {
	    ClliSelectionDTO clliSelectionDTO = null;
	    clliSelectionService.createClliSelection(clliSelectionDTO);
	} catch (Exception e) {
	    LOGGER.error("Exception Occured as: ", e);
	}
    }

    @Test
    public void testCreateClliSelectionExcecption() {
	try {
	    ClliSelectionDTO clliSelectionDTO = new ClliSelectionDTO();
	    clliSelectionService.createClliSelection(clliSelectionDTO);
	} catch (Exception e) {
	    LOGGER.error("Exception Occured as: ", e);
	}
    }

    @Test
    public void testUpdateClliSelectionExcecption() {
	try {
	    ClliSelectionDTO clliSelectionDTO = new ClliSelectionDTO();
	    clliSelectionService.updateClliSelection(clliSelectionDTO);
	} catch (Exception e) {
	    LOGGER.error("Exception Occured as: ", e);
	}
    }

    @Test
    public void testUpdateClliSelectionNull() {
	try {
	    ClliSelectionDTO clliSelectionDTO = null;
	    clliSelectionService.updateClliSelection(clliSelectionDTO);
	} catch (Exception e) {
	    LOGGER.error("Exception Occured as: ", e);
	}
    }

}