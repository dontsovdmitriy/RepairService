package com.dontsov.repairService.controller.commands.malfunctionType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dontsov.repairService.controller.validation.InputCheckingService;
import com.dontsov.repairService.controller.validation.InputCheckingServiceImpl;
import com.dontsov.repairService.service.MalfunctionTypeService;

public class AddMalfunctionTypeTest {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private InputCheckingService checkingService;
	private MalfunctionTypeService malfunctionTypeService;
	private AddMalfunctionType addMalfunctionType;
	

	@Before
	public void init() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		checkingService = mock(InputCheckingServiceImpl.class);
		malfunctionTypeService = mock(MalfunctionTypeService.class);
		addMalfunctionType = new AddMalfunctionType(malfunctionTypeService, checkingService);
	}
	
	@Test
	public void test() throws Exception {	
		when(request.getParameter(Mockito.anyString())).thenReturn("3");
		when(checkingService.checkName(Mockito.anyString())).thenReturn(true);
		when(checkingService.checkRepairDay(Mockito.anyString())).thenReturn(true);

		String page =  addMalfunctionType.execute(request, response);
		String expected = "/WEB-INF/view/home.jsp";
		assertEquals(expected, page);
	
	}

}
