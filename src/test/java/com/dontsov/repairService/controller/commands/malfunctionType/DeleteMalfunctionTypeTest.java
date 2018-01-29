package com.dontsov.repairService.controller.commands.malfunctionType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dontsov.repairService.service.MalfunctionTypeService;

public class DeleteMalfunctionTypeTest {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session ;
	private MalfunctionTypeService malfunctionTypeService;
	private DeleteMalfunctionType deleteMalfunctionType;
	

	@Before
	public void init() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		malfunctionTypeService = mock(MalfunctionTypeService.class);
		deleteMalfunctionType = new DeleteMalfunctionType(malfunctionTypeService);
	}
	
	@Test
	public void test() throws Exception {
		when(request.getSession()).thenReturn(session);
		when(request.getSession()).thenReturn(session);
		when(request.getParameter(Mockito.anyString())).thenReturn("2");
		
		String page =  deleteMalfunctionType.execute(request, response);
		String expected = "/WEB-INF/view/home.jsp";
		assertEquals(expected, page);	
		}

}
