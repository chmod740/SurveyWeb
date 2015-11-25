package com.geetest.sdk.java.web.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geetest.sdk.java.GeetestLib;

/**
 * 浣跨敤Get鐨勬柟寮忚繑鍥烇細challenge鍜宑apthca_id 姝ゆ柟寮忎互瀹炵幇鍓嶅悗绔畬鍏ㄥ垎绂荤殑寮�彂妯″紡 涓撻棬瀹炵幇failback
 * 
 * @author zheng
 *
 */
public class StartCaptchaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Conifg the parameter of the geetest object
		GeetestLib gtSdk = new GeetestLib();
		gtSdk.setCaptchaId(GeetestConfig.getCaptcha_id());
		gtSdk.setPrivateKey(GeetestConfig.getPrivate_key());

		gtSdk.setGtSession(request);

		String resStr = "{}";

		if (gtSdk.preProcess() == 1) {
			// gt server is in use
			resStr = gtSdk.getSuccessPreProcessRes();
			gtSdk.setGtServerStatusSession(request, 1);

		} else {
			// gt server is down
			resStr = gtSdk.getFailPreProcessRes();
			gtSdk.setGtServerStatusSession(request, 0);
		}

		PrintWriter out = response.getWriter();
		out.println(resStr);
		response.setHeader("Access-Control-Allow-Origin", "*");
	}
}