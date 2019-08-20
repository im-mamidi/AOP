package com.vz.uiam.onenet.vps.interfaces.test.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import javax.servlet.Filter;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vz.uiam.onenet.vps.interfaces.VPSInterfacesServiceApplication;
import com.vz.uiam.onenet.vps.interfaces.constants.Constants;
import com.vz.uiam.onenet.vps.interfaces.controller.ClliSelectionController;
import com.vz.uiam.onenet.vps.interfaces.jpa.dto.model.ClliSelectionDTO;

/**
 * @author Lavanya Komarasamy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = VPSInterfacesServiceApplication.class)
@IntegrationTest("server.port:8000")
@ActiveProfiles("development")
public class ServiceControllerTest {

    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ServiceControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    ClliSelectionController clliSelectionController;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
		.defaultRequest(get("/").with(httpBasic("IVAPP", "ivapp"))).addFilters(springSecurityFilterChain)
		.build();
    }

    @Test
    @SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:Before.sql"),
	    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:After.sql") })
    public final void testGetClliSelection() {
	try {
	    ClliSelectionDTO clliSelectionRequest = null;

	    URI url = new URI("/vpsInterfaces/getCLLISelections");
	    LOGGER.debug(
		    "==================================Entering to ServiceControllerTest.testGetClliSelection================================");
	    MvcResult result = this.mockMvc
		    .perform(post(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().isOk()).andExpect(content().contentType(Constants.APPLICATION_JSON))
		    .andReturn();

	    LOGGER.debug("Result : " + result.getResponse().getContentAsString());
	    LOGGER.debug(
		    "==================================Exiting to ServiceControllerTest.testGetClliSelection==================================");

	} catch (Exception e) {
	    LOGGER.error("Exception Occured as in the line: ", e);
	}
    }

    @Test
    @SqlGroup({ @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:After.sql") })
    public void testCreateClliSelection() {
	try {

	    ClliSelectionDTO clliSelectionRequest = getClliSelectionRequestObject();

	    URI url = new URI("/vpsInterfaces/createCLLISelection");
	    LOGGER.debug(
		    "==================================Entering to ServiceControllerTest.testCreateFalloutItem================================");
	    MvcResult result = this.mockMvc
		    .perform(post(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().isOk()).andExpect(content().contentType(Constants.APPLICATION_JSON))
		    .andReturn();

	    LOGGER.debug("Result : " + result.getResponse().getContentAsString());
	    LOGGER.debug(
		    "==================================Exiting to ServiceControllerTest.testCreateFalloutItem==================================");
	} catch (Exception e) {
	    LOGGER.error("Exception Occured in CreateCLLISelection: ", e);
	}
    }

    @Test
    public final void testGetClliSelectionException() {
	try {
	    ClliSelectionDTO clliSelectionRequest = getClliSelectionRequestObject();

	    URI url = new URI("/vpsInterfaces/getCLLISelections");
	    LOGGER.debug(
		    "==================================Entering to ServiceControllerTest.testGetClliSelectionException================================");
	    this.mockMvc
		    .perform(post(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().is(HttpStatus.EXPECTATION_FAILED.value()));
	    LOGGER.debug(
		    "=========================Exiting to ServiceControllerTest.testGetClliSelectionException==================================");

	} catch (Exception e) {
	    LOGGER.error("Exception Occured as in the line: ", e);
	}
    }

    @Test
    public final void testCreateClliSelectionException() {
	try {
	    ClliSelectionDTO clliSelectionRequest = null;

	    URI url = new URI("/vpsInterfaces/createCLLISelection");
	    LOGGER.debug(
		    "==================================Entering to ServiceControllerTest.testCreateClliSelectionException================================");
	    this.mockMvc
		    .perform(post(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().is(HttpStatus.EXPECTATION_FAILED.value()));
	    LOGGER.debug(
		    "=========================Exiting to ServiceControllerTest.testCreateClliSelectionException==================================");

	} catch (Exception e) {
	    LOGGER.error("Exception Occured as in the line: ", e);
	}
    }

    @Test
    @SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:Before.sql"),
	    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:After.sql") })
    public final void testUpdateClliSelection() {
	try {
	    ClliSelectionDTO clliSelectionRequest = getClliSelectionRequestUpdateObject();
	    clliSelectionRequest.setRequestSource("VPS");
	    clliSelectionRequest.setProjectId("PROJCFE64");
	    clliSelectionRequest.setLocationInd("A");

	    URI url = new URI("/vpsInterfaces/updateCLLISelection");
	    LOGGER.debug(
		    "==================================Entering to ServiceControllerTest.testUpdateClliSelection================================");
	    MvcResult result = this.mockMvc
		    .perform(put(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().isOk()).andExpect(content().contentType(Constants.APPLICATION_JSON))
		    .andReturn();

	    LOGGER.debug("Result : " + result.getResponse().getContentAsString());
	    LOGGER.debug(
		    "==================================Exiting to ServiceControllerTest.testUpdateFalloutItem==================================");

	} catch (Exception e) {
	    LOGGER.error("Exception Occured as in the line: ", e);
	}

    }

    @Test
    public final void testUpdateClliSelectionError() {
	try {
	    ClliSelectionDTO clliSelectionRequest = getClliSelectionRequestUpdateObject();

	    URI url = new URI("/vpsInterfaces/updateCLLISelection");
	    LOGGER.debug(
		    "==================================Entering to ServiceControllerTest.testUpdateClliSelection================================");
	    this.mockMvc
		    .perform(put(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().is(HttpStatus.EXPECTATION_FAILED.value()));

	    LOGGER.debug(
		    "==================================Exiting to ServiceControllerTest.testUpdateFalloutItem==================================");

	} catch (Exception e) {
	    LOGGER.error("Exception Occured as in the line: ", e);
	}
    }

    @Test
    public final void testGetClliSelectionError() {
	try {
	    ClliSelectionDTO clliSelectionRequest = getClliSelectionRequestObject();

	    URI url = new URI("/vpsInterfaces/getCLLISelections");
	    LOGGER.debug(
		    "==================================Entering to ServiceControllerTest.testUpdateClliSelection================================");
	    this.mockMvc
		    .perform(post(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().is(HttpStatus.EXPECTATION_FAILED.value()));

	    LOGGER.debug(
		    "==================================Exiting to ServiceControllerTest.testUpdateFalloutItem==================================");

	} catch (Exception e) {
	    LOGGER.error("Exception Occured as in the line: ", e);
	}
    }

    @Test
    public final void testUpdateClliSelectionExcecption() {
	try {
	    ClliSelectionDTO clliSelectionRequest = new ClliSelectionDTO();

	    URI url = new URI("/vpsInterfaces/updateCLLISelection");
	    LOGGER.debug(
		    "==============Entering to ServiceControllerTest.testUpdateClliSelectionExcecption===================");
	    this.mockMvc
		    .perform(put(url).contentType(MediaType.APPLICATION_JSON)
			    .content(convertObjectToJsonBytes(clliSelectionRequest)))
		    .andExpect(status().is(HttpStatus.EXPECTATION_FAILED.value()));
	    LOGGER.debug(
		    "=============Exiting to ServiceControllerTest.testUpdateClliSelectionExcecption====================");

	} catch (Exception e) {
	    LOGGER.error("Exception Occured as in the line: ", e);
	}
    }

    private ClliSelectionDTO getClliSelectionRequestObject() {
	ClliSelectionDTO clliSelectionDTO = new ClliSelectionDTO();
	clliSelectionDTO.setCaseId("12589");
	clliSelectionDTO.setCircuitId("28/KGAK/111106//CM");
	clliSelectionDTO.setClli("DLLSTX87");
	clliSelectionDTO.setLocationInd("A");
	clliSelectionDTO.setOrderNumber("CKA111108");
	clliSelectionDTO.setOrderRefId(109L);
	clliSelectionDTO.setOrderVersion("111");
	clliSelectionDTO.setProjectId("PROJCFE64");
	clliSelectionDTO.setProjectStatus("Pending");
	clliSelectionDTO.setRequestSource("VRD");
	clliSelectionDTO.setSupType("S2");
	clliSelectionDTO.setVzid("JUNIT_TEST");
	clliSelectionDTO.setAacClliCode("aac_clli_code");
	clliSelectionDTO.setAacTid("aac_tid");
	clliSelectionDTO.setAacType("aac_type");
	clliSelectionDTO.setPremiseType("premise_type");
	
	return clliSelectionDTO;
    }

    private ClliSelectionDTO getClliSelectionRequestUpdateObject() {
	ClliSelectionDTO clliSelectionDTO = new ClliSelectionDTO();
	clliSelectionDTO.setCaseId("12589");
	clliSelectionDTO.setCircuitId("28/KGAK/111106//CM");
	clliSelectionDTO.setClli("DLLSTX87");
	clliSelectionDTO.setLocationInd("A");
	clliSelectionDTO.setOrderNumber("CKA111108");
	clliSelectionDTO.setOrderRefId(109L);
	clliSelectionDTO.setOrderVersion("111");
	clliSelectionDTO.setProjectId("PROJCFE64");
	clliSelectionDTO.setProjectStatus("Pending");
	clliSelectionDTO.setRequestSource("VRD");
	clliSelectionDTO.setBuildType("Build Type");
	clliSelectionDTO.setNidType("Nid Type");
	clliSelectionDTO.setSiteCode("Site Code");
	clliSelectionDTO.setSupType("S2");
	clliSelectionDTO.setVzid("JUNIT_TEST");
	clliSelectionDTO.setAacClliCode("aac_clli_code");
	clliSelectionDTO.setAacTid("aac_tid");
	clliSelectionDTO.setAacType("aac_type");
	clliSelectionDTO.setPremiseType("premise_type");

	return clliSelectionDTO;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws JsonProcessingException {
	ObjectMapper mapper = new ObjectMapper();
	mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	return mapper.writeValueAsBytes(object);
    }
}
