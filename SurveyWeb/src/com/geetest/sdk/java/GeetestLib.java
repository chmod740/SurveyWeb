package com.geetest.sdk.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Java SDK
 * 
 * @author Zheng
 * @time 2014��7��10�� ����3:29:09
 */
public class GeetestLib {

    /**
     * SDK�汾���
     */
    // private final int verCode = 8;

    /**
     * SDK�汾����
     */
    protected final String verName = "2.15.10.9.1";
    protected final String sdkLang = "java";// SD����������

    protected final static String gt_session_key = "geetest";// geetest����洢��session��keyֵ(��ʵ��)
    protected final static String gt_server_status_session_key = "gt_server_status";// ���������״̬keyֵ����ʵ����

    protected final String baseUrl = "api.geetest.com";
    protected final String api_url = "http://" + baseUrl;
    protected final String https_api_url = "https://" + baseUrl;// һЩҳ����https
    protected final int com_port = 80;// ͨѶ�˿ں�

    protected final int defaultIsMobile = 0;
    // private final int defaultMobileWidth = 260;// the default width of the
    // mobile id

    // һЩ����
    public static final String success_res = "success";
    public static final String fail_res = "fail";
    public static final String forbidden_res = "forbidden";

    // ǰ����֤�ı�ֵ--���ڽӿڣ��������޸�
    protected final String fn_geetest_challenge = "geetest_challenge";
    protected final String fn_geetest_validate = "geetest_validate";
    protected final String fn_geetest_seccode = "geetest_seccode";

    protected Boolean debugCode = true;// ���Կ��أ��Ƿ����������־
    protected String validateLogPath = "";// �������˱�����־��Ŀ¼//var/log/����ȷ���пɶ�дȨ��

    /**
     * ��Կ
     */
    private String captchaId = "";

    /**
     * ˽Կ
     */
    private String privateKey = "";

    /**
     * the challenge
     */
    private String challengeId = "";

    /**
     * set the own private pictures,default is ""
     */
    private String picId = "";

    /**
     * he captcha product type,default is 'embed'
     */
    private String productType = "embed";

    /**
     * is secure
     */
    private Boolean isHttps = false;

    public Boolean getIsHttps() {
        return isHttps;
    }

    public void setIsHttps(Boolean isHttps) {
        this.isHttps = isHttps;
    }

    /**
     * when the productType is popup,it needs to set the submitbutton
     */
    private String submitBtnId = "submit-button";

    public String getSubmitBtnId() {
        return submitBtnId;
    }

    public void setSubmitBtnId(String submitBtnId) {
        this.submitBtnId = submitBtnId;
    }

    /**
     * �Ƿ����ƶ��˵�
     */
    private int isMobile = defaultIsMobile;// 1--true,0-false

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public final Boolean getDebugCode() {
        return debugCode;
    }

    public final void setDebugCode(Boolean debugCode) {
        this.debugCode = debugCode;
    }

    /**
     * ��ȡ�汾���
     * 
     * @author Zheng
     * @email dreamzsm@gmail.com
     * @time 2014��7��11�� ����11:07:11
     * @return
     */
    public String getVersionInfo() {
        return verName;
    }

    public String getValidateLogPath() {
        return validateLogPath;
    }

    public void setValidateLogPath(String validateLogPath) {
        this.validateLogPath = validateLogPath;
    }

    // public void setCaptcha_id(String captcha_id) {
    // this.captcha_id = captcha_id;
    // }

    /**
     * һ���޲ι��캯��
     */
    public GeetestLib() {
    }

    // public static GeetestLib createGtInstance() {
    // GeetestLib geetestSdk = new GeetestLib();
    // geetestSdk.setCaptchaId(GeetestConfig.getCaptcha_id());
    // geetestSdk.setPrivateKey(GeetestConfig.getPrivate_key());
    //
    // return geetestSdk;
    // }

    /**
     * ����ǰʵ�����õ�session��
     * 
     * @param request
     */
    public void setGtSession(HttpServletRequest request) {
        request.getSession().setAttribute(gt_session_key, this);// set session
        this.gtlog("set session succeed");
    }

    /**
     * ͬһ�Ự��ʵ��ʱ������session
     * 
     * @param request
     * @param gt_instance_session_key
     *            ��ͬ��֤ʵ�����õ�key
     */
    public void setGtSession(HttpServletRequest request,
            String gt_instance_session_key) {
        request.getSession().setAttribute(gt_instance_session_key, this);// set
                                                                            // session
        this.gtlog("set session succeed");
    }

    /**
     * �����������gt-server״ֵ̬
     * 
     * @param request
     */
    public void setGtServerStatusSession(HttpServletRequest request,
            int statusCode) {
        request.getSession().setAttribute(gt_server_status_session_key,
                statusCode);// set session
    }

    /**
     * �����������gt-server״ֵ̬����ʵ����
     * 
     * @param request
     * @param statusCode
     * @param gt_instance_server_status_session_key
     */
    public void setGtServerStatusSession(HttpServletRequest request,
            int statusCode, String gt_instance_server_status_session_key) {
        request.getSession().setAttribute(
                gt_instance_server_status_session_key, statusCode);// set
                                                                    // session
    }

    /**
     * ��ȡsession
     * 
     * @param request
     * @return
     */
    public static GeetestLib getGtSession(HttpServletRequest request) {
        return (GeetestLib) request.getSession().getAttribute(gt_session_key);
    }

    /**
     * ��ȡsession(����ͬһ�Ự��ʵ��ģʽ�£���������)
     * 
     * @param request
     * @param gt_instance_session_key
     * @return
     */
    public static GeetestLib getGtSession(HttpServletRequest request,
            String gt_instance_session_key) {
        return (GeetestLib) request.getSession().getAttribute(
                gt_instance_session_key);
    }

    /**
     * 0��ʾ��������1��ʾ����
     * 
     * @param request
     * @return
     */
    public static int getGtServerStatusSession(HttpServletRequest request) {
        return (Integer) request.getSession().getAttribute(
                gt_server_status_session_key);
    }

    /**
     * ��ȡsession(����ͬһ�Ự��ʵ��ģʽ�£���������)
     * 
     * @param request
     * @param gt_instance_server_status_session_key
     * @return
     */
    public static int getGtServerStatusSession(HttpServletRequest request,
            String gt_instance_server_status_session_key) {
        return (Integer) request.getSession().getAttribute(
                gt_instance_server_status_session_key);
    }

    /**
     * Ԥ����ʧ�ܺ�ķ��ظ�ʽ��
     * 
     * @return
     */
    public String getFailPreProcessRes() {
        // return String.format("{\"success\":%s}", 0);

        Long rnd1 = Math.round(Math.random() * 100);
        Long rnd2 = Math.round(Math.random() * 100);
        String md5Str1 = md5Encode(rnd1 + "");
        String md5Str2 = md5Encode(rnd2 + "");
        String challenge = md5Str1 + md5Str2.substring(0, 2);
        this.setChallengeId(challenge);

        return String.format(
                "{\"success\":%s,\"gt\":\"%s\",\"challenge\":\"%s\"}", 0,
                this.getCaptchaId(), this.getChallengeId());
    }

    /**
     * Ԥ����ɹ���ı�׼��
     * 
     * @return
     */
    public String getSuccessPreProcessRes() {
        return String.format(
                "{\"success\":%s,\"gt\":\"%s\",\"challenge\":\"%s\"}", 1,
                this.getCaptchaId(), this.getChallengeId());
    }

    /**
     * ������֤����־����������ͼ�����һЩ��������,���ڿ�����ǰ����֤ͨ�������Ǻ�����֤ʧ�ܵ����
     * 
     * @param challenge
     * @param validate
     * @param seccode
     * @param gtUser
     *            �û�ҳ���cookie��ʶ
     * @param sdkResult
     */
    public void saveValidateLog(String challenge, String validate,
            String seccode, String sdkResult) {

        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd   hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());

        String logFormat = String.format(
                "date:%s,challenge:%s,validate:%s,seccode:%s,sdkResult:%s",
                date, challenge, validate, seccode, sdkResult);

        gtlog(logFormat);

    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(int isMobile) {
        this.isMobile = isMobile;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public GeetestLib(String privateKey) {
        this.privateKey = privateKey;
    }

    // public GeetestLib(String privateKey, String captcha_id) {
    // this.privateKey = privateKey;
    // this.captcha_id = captcha_id;
    // }

    // public int getVerCode() {
    // return verCode;
    // }

    public String getVerName() {
        return verName;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    /**
     * processing before the captcha display on the web front
     * 
     * @return
     */
    public int preProcess() {

        // first check the server status , to handle failback
        // if (getGtServerStatus() != 1) {
        // return 0;
        // }

        // just check the server side register
        if (registerChallenge() != 1) {
            return 0;
        }

        return 1;

    }

    /**
     * generate the dynamic front source
     * 
     * @param different
     *            product display mode :float,embed,popup
     * @return
     */
    public String getGtFrontSource() {

        String base_path = "";
        if (this.isHttps) {
            base_path = this.https_api_url;
        } else {
            base_path = this.api_url;
        }

        String frontSource = String.format(
                "<script type=\"text/javascript\" src=\"%s/get.php?"
                        + "gt=%s&challenge=%s", base_path, this.captchaId,
                this.challengeId);

        if (this.productType.equals("popup")) {
            frontSource += String.format("&product=%s&popupbtnid=%s",
                    this.productType, this.submitBtnId);
        } else {
            frontSource += String.format("&product=%s", this.productType);
        }

        frontSource += "\"></script>";

        return frontSource;
    }

    /**
     * ��ȡ����ķ�����״̬
     * 
     * @author Zheng
     * @email dreamzsm@gmail.com
     * @time 2014��7��10�� ����7:12:38
     * @return
     */
    public int getGtServerStatus() {

        try {
            final String GET_URL = api_url + "/check_status.php";
            if (readContentFromGet(GET_URL).equals("ok")) {
                return 1;
            } else {
                System.out.println("gServer is Down");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * generate a random num
     * 
     * @return
     */
    public int getRandomNum() {

        int rand_num = (int) (Math.random() * 100);
        // System.out.print(rand_num);
        return rand_num;
    }

    /**
     * Register the challenge
     * 
     * @return
     */
    public int registerChallenge() {
        try {
            String GET_URL = api_url + "/register.php?gt=" + this.captchaId;

            // if (this.productType.equals("popup")) {
            // GET_URL += String.format("&product=%s&popupbtnid=%s",
            // this.productType, this.submitBtnId);
            // } else {
            // GET_URL += String.format("&product=%s", this.productType);
            // }

            // System.out.print(GET_URL);
            String result_str = readContentFromGet(GET_URL);
            // System.out.println(result_str);
            if (32 == result_str.length()) {
                this.challengeId = result_str;
                return 1;
            } else {
                System.out.println("gServer register challenge failed");
                return 0;
            }
        } catch (Exception e) {
            gtlog("exception:register api:");
            // e.printStackTrace();
        }
        return 0;
    }

    /**
     * ��ȡ������
     * 
     * @author Zheng dreamzsm@gmail.com
     * @time 2014��7��10�� ����7:11:11
     * @param getURL
     * @return
     * @throws IOException
     */
    private String readContentFromGet(String getURL) throws IOException {

        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();

        connection.setConnectTimeout(2000);// ��������������ʱ����λ�����룩
        connection.setReadTimeout(2000);// ���ô�������ȡ���ݳ�ʱ����λ�����룩

        // ����������������ӣ���δ��������

        connection.connect();
        // �������ݵ���������ʹ��Reader��ȡ���ص�����
        StringBuffer sBuffer = new StringBuffer();

        InputStream inStream = null;
        byte[] buf = new byte[1024];
        inStream = connection.getInputStream();
        for (int n; (n = inStream.read(buf)) != -1;) {
            sBuffer.append(new String(buf, 0, n, "UTF-8"));
        }
        inStream.close();
        connection.disconnect();// �Ͽ�����

        return sBuffer.toString();
    }

    /**
     * �ж�һ��������ֵ�Ƿ�Ϊ��
     * 
     * @time 2014��7��10�� ����5:54:25
     * @param gtObj
     * @return
     */
    protected boolean objIsEmpty(Object gtObj) {
        if (gtObj == null) {
            return true;
        }

        if (gtObj.toString().trim().length() == 0) {
            return true;
        }
        // && gtObj.toString().trim().length() > 0

        return false;
    }

    /**
     * ���ͻ��˵������Ƿ�Ϊ��--����ֻҪ��һ��Ϊ�գ����жϲ��Ϸ�
     * 
     * @time 2014��7��10�� ����5:46:34
     * @param request
     * @return
     */
    public boolean resquestIsLegal(HttpServletRequest request) {

        if (objIsEmpty(request.getParameter(this.fn_geetest_challenge))) {
            return false;
        }

        if (objIsEmpty(request.getParameter(this.fn_geetest_validate))) {
            return false;
        }

        if (objIsEmpty(request.getParameter(this.fn_geetest_seccode))) {
            return false;
        }

        return true;
    }

    /**
     * ������֤���� ����Ĳ���Ϊrequest--vCode 8֮���ٸ���,���Ƽ�ʹ��
     * 
     * @time 2014��7��10�� ����6:34:55
     * @param request
     * @return
     */
    public boolean validateRequest(HttpServletRequest request) {

        boolean gtResult = this.validate(
                request.getParameter(this.fn_geetest_challenge),
                request.getParameter(this.fn_geetest_validate),
                request.getParameter(this.fn_geetest_seccode));

        return gtResult;
    }

    /**
     * failbackʹ�õ���֤��ʽ
     * 
     * @param request
     * @return
     */
    public String failbackValidateRequest(HttpServletRequest request) {

        gtlog("in failback validate");

        if (!resquestIsLegal(request)) {
            return GeetestLib.fail_res;
        }

        String challenge = request.getParameter(this.fn_geetest_challenge);
        String validate = request.getParameter(this.fn_geetest_validate);
        // String seccode = request.getParameter(this.fn_geetest_seccode);

        if (!challenge.equals(this.getChallengeId())) {
            return GeetestLib.fail_res;
        }

        String[] validateStr = validate.split("_");
        String encodeAns = validateStr[0];
        String encodeFullBgImgIndex = validateStr[1];
        String encodeImgGrpIndex = validateStr[2];

        gtlog(String.format(
                "encode----challenge:%s--ans:%s,bg_idx:%s,grp_idx:%s",
                challenge, encodeAns, encodeFullBgImgIndex, encodeImgGrpIndex));

        int decodeAns = decodeResponse(this.getChallengeId(), encodeAns);
        int decodeFullBgImgIndex = decodeResponse(this.getChallengeId(),
                encodeFullBgImgIndex);
        int decodeImgGrpIndex = decodeResponse(this.getChallengeId(),
                encodeImgGrpIndex);

        gtlog(String.format("decode----ans:%s,bg_idx:%s,grp_idx:%s", decodeAns,
                decodeFullBgImgIndex, decodeImgGrpIndex));

        String validateResult = validateFailImage(decodeAns,
                decodeFullBgImgIndex, decodeImgGrpIndex);

        if (!validateResult.equals(GeetestLib.fail_res)) {
            // ʹ��һ�����ʶ���������˴���֤����ֹ�ط�
            Long rnd1 = Math.round(Math.random() * 100);
            String md5Str1 = md5Encode(rnd1 + "");
            this.setChallengeId(md5Str1);
        }

        return validateResult;
    }

    /**
     *
     * @param ans
     * @param full_bg_index
     * @param img_grp_index
     * @return
     */
    private String validateFailImage(int ans, int full_bg_index,
            int img_grp_index) {
        final int thread = 3;// �ݲ�ֵ

        String full_bg_name = md5Encode(full_bg_index + "").substring(0, 9);
        String bg_name = md5Encode(img_grp_index + "").substring(10, 19);

        String answer_decode = "";

        // ͨ�������ַ���������ż��λƴ�Ӳ�����λ
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                answer_decode += full_bg_name.charAt(i);
            } else if (i % 2 == 1) {
                answer_decode += bg_name.charAt(i);
            } else {
                gtlog("exception");
            }
        }

        String x_decode = answer_decode.substring(4, answer_decode.length());

        int x_int = Integer.valueOf(x_decode, 16);// 16 to 10

        int result = x_int % 200;
        if (result < 40) {
            result = 40;
        }

        if (Math.abs(ans - result) <= thread) {
            return GeetestLib.success_res;
        } else {
            return GeetestLib.fail_res;
        }
    }

    /**
     * �������λ���������,�����ƫ����
     * 
     * @param randStr
     * @return
     */
    public int decodeRandBase(String challenge) {

        String base = challenge.substring(32, 34);
        ArrayList<Integer> tempArray = new ArrayList<Integer>();

        for (int i = 0; i < base.length(); i++) {
            char tempChar = base.charAt(i);
            Integer tempAscii = (int) (tempChar);

            Integer result = (tempAscii > 57) ? (tempAscii - 87)
                    : (tempAscii - 48);

            tempArray.add(result);
        }

        int decodeRes = tempArray.get(0) * 36 + tempArray.get(1);
        return decodeRes;

    }

    /**
     * �����������
     * 
     * @param encodeStr
     * @param challenge
     * @return
     */
    public int decodeResponse(String challenge, String string) {
        if (string.length() > 100) {
            return 0;
        }

        int[] shuzi = new int[] { 1, 2, 5, 10, 50 };
        String chongfu = "";
        HashMap<String, Integer> key = new HashMap<String, Integer>();
        int count = 0;

        for (int i = 0; i < challenge.length(); i++) {
            String item = challenge.charAt(i) + "";

            if (chongfu.contains(item) == true) {
                continue;
            } else {
                int value = shuzi[count % 5];
                chongfu += item;
                count++;
                key.put(item, value);
            }
        }

        int res = 0;

        for (int j = 0; j < string.length(); j++) {
            res += key.get(string.charAt(j) + "");
        }

        res = res - decodeRandBase(challenge);

        return res;

    }

    /**
     * ��ǿ�����֤��Ϣ,�ṩ�˸������֤���ؽ����Ϣ�����ÿͻ����������в�ͬ�����ݴ���
     * 
     * @param challenge
     * @param validate
     * @param seccode
     * @return
     */
    public String enhencedValidateRequest(HttpServletRequest request) {

        if (!resquestIsLegal(request)) {
            return GeetestLib.fail_res;
        }

        String challenge = request.getParameter(this.fn_geetest_challenge);
        String validate = request.getParameter(this.fn_geetest_validate);
        String seccode = request.getParameter(this.fn_geetest_seccode);
        // String gtuser = "";

        // Cookie[] cookies = request.getCookies();
        //
        // if (cookies != null) {
        // for (int i = 0; i < cookies.length; i++) {
        // Cookie cookie = cookies[i];
        // if ("GeeTestUser".equals(cookie.getName())) {
        // gtuser = cookie.getValue();
        // gtlog(String.format("GeeTestUser:%s", gtuser));
        // }
        // }
        // }

        String host = baseUrl;
        String path = "/validate.php";
        int port = 80;
        // String query = "seccode=" + seccode + "&sdk=" + this.sdkLang + "_"
        // + this.verName;

        String query = String.format("seccode=%s&sdk=%s", seccode,
                (this.sdkLang + "_" + this.verName));

        String response = "";

        gtlog(query);
        try {
            if (validate.length() <= 0) {
                return GeetestLib.fail_res;
            }

            if (!checkResultByPrivate(challenge, validate)) {
                return GeetestLib.fail_res;
            }

            response = postValidate(host, path, query, port);

            gtlog("response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gtlog("md5: " + md5Encode(seccode));

        if (response.equals(md5Encode(seccode))) {
            return GeetestLib.success_res;
        } else {
            return response;
        }

    }

    /**
     * the old api use before version code 8(not include)
     * 
     * @param challenge
     * @param validate
     * @param seccode
     * @return
     * @time 2014122_171529 by zheng
     */
    private boolean validate(String challenge, String validate, String seccode) {
        String host = baseUrl;
        String path = "/validate.php";
        int port = 80;
        if (validate.length() > 0 && checkResultByPrivate(challenge, validate)) {
            String query = "seccode=" + seccode;
            String response = "";
            try {
                response = postValidate(host, path, query, port);
                gtlog(response);
            } catch (Exception e) {
                e.printStackTrace();
            }

            gtlog("md5: " + md5Encode(seccode));

            if (response.equals(md5Encode(seccode))) {
                return true;
            }
        }
        return false;

    }

    /**
     * Print out log message Use to Debug
     * 
     * @time 2014122_151829 by zheng
     * 
     * @param message
     */
    public void gtlog(String message) {
        if (debugCode) {
            System.out.println("gtlog: " + message);
        }
    }

    protected boolean checkResultByPrivate(String challenge, String validate) {
        String encodeStr = md5Encode(privateKey + "geetest" + challenge);
        return validate.equals(encodeStr);
    }

    /**
     * fuck��ò�Ʋ���Post��ʽ�������ع�ʱ�޸�����
     * 
     * @param host
     * @param path
     * @param data
     * @param port
     * @return
     * @throws Exception
     */
    protected String postValidate(String host, String path, String data,
            int port) throws Exception {
        String response = "error";
        // data=fixEncoding(data);
        InetAddress addr = InetAddress.getByName(host);
        Socket socket = new Socket(addr, port);
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream(), "UTF8"));
        wr.write("POST " + path + " HTTP/1.0\r\n");
        wr.write("Host: " + host + "\r\n");
        wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
        wr.write("Content-Length: " + data.length() + "\r\n");
        wr.write("\r\n"); // �Կ�����Ϊ�ָ�
        // ��������
        wr.write(data);
        wr.flush();
        // ��ȡ������Ϣ
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                socket.getInputStream(), "UTF-8"));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
            response = line;
        }
        wr.close();
        rd.close();
        socket.close();
        return response;
    }

    // /**
    // * תΪUTF8����
    // *
    // * @time 2014��7��10�� ����3:29:45
    // * @param str
    // * @return
    // * @throws UnsupportedEncodingException
    // */
    // private String fixEncoding(String str) throws
    // UnsupportedEncodingException {
    // String tempStr = new String(str.getBytes("UTF-8"));
    // return URLEncoder.encode(tempStr, "UTF-8");
    // }

    /**
     * md5 ����
     * 
     * @time 2014��7��10�� ����3:30:01
     * @param plainText
     * @return
     */
    public String md5Encode(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

}
