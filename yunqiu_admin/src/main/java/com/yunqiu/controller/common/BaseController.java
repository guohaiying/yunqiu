package com.yunqiu.controller.common;



import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


public class BaseController {

	/**
	 * 活取ServletOutputStream对象
	 * @param response
	 * @return
	 */
	public ServletOutputStream getOutputStream(HttpServletResponse response){
		ServletOutputStream outptStream  = null;
		try {
			outptStream =response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outptStream;
	}
	
}
