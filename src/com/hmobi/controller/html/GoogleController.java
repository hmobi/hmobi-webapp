package com.hmobi.controller.html;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hmobi.dao.user.UserLogin;
import com.hmobi.dao.user.UserPermissions;
import com.hmobi.httppad.HttpPad;
import com.hmobi.mvc.BaseController;
import com.hmobi.mvc.HMModelAndView;
import com.hmobi.utils.HttpClient;
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: manusrivastava
 * Date: 30/04/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoogleController extends BaseController
{
    private static Logger logger = LoggerFactory.getLogger(GoogleController.class);

    private String clientId = "706027833055.apps.googleusercontent.com";
    private String clientSecret = "xWRAu7nM80zKgmQt9FxwEG7J";

    private String redirectUri = "http://ec2-54-251-203-201.ap-southeast-1.compute.amazonaws.com:8080/hmobi/google.html";

    @Override
    protected HMModelAndView handleRequest(HttpPad pad) throws Exception
    {
        String state = pad.getParameter("state");
        if(state != null && !"".equals(state.trim()))
        {
            String sessionId = pad.getSessionId();
            if(!sessionId.equals(state))
            {
                logger.error("Invalid session Id [" + sessionId + "], [" + state + "]");
            }
            else
            {
                logger.info("Got valid session [" + sessionId + "]....logging in user");
                String code = pad.getParameter("code");

                StringBuffer buffer = new StringBuffer();
                buffer.append("code=").append(code);
                buffer.append("&client_id=").append(clientId);
                buffer.append("&client_secret=").append(clientSecret);
                buffer.append("&redirect_uri=").append(redirectUri + "&");
                buffer.append("&grant_type=").append("authorization_code");

                String response = HttpClient.postData("https://accounts.google.com/o/oauth2/token",null,
                                    null, true,"application/x-www-form-urlencoded", buffer.toString());

                logger.info("Got response -> " + response);

                JSONObject obj = (JSONObject)JSONValue.parse(response);

                String idToken = (String)obj.get("id_token");
                String deserializedIdToken = deserialize(idToken);
                logger.info("Got idJson -> " + deserializedIdToken);

                JSONObject userObj = (JSONObject)JSONValue.parse(deserializedIdToken);

                String userName = (String)userObj.get("email");

                UserLogin user = new UserLogin();
                user.setUsername(userName);
                UserPermissions permissions = new UserPermissions();
                permissions.setAuthority(("ROLE_USER"));
                user.setPermissions(new UserPermissions[]{permissions});
                Authentication auth = new UsernamePasswordAuthenticationToken(user,user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                redirectUserBasedOnRole(user,pad);
            }
            return null;

        }
        else
        {
            String sessionId = pad.getSessionId();
            String url = "https://accounts.google.com/o/oauth2/auth?" +
            "client_id=" + clientId + "&" +
            "response_type=code&" +
            "scope=openid%20email&" +
            "redirect_uri=" + redirectUri + "&" +
            "state=" + sessionId + "&";
            //"state=security_token%3D138r5719ru3e1%26url%3Dhttps://oa2cb.example.com/myHome&" +
            //"login_hint=" + username;
            pad.sendAbsoluteRedirect(url);

            return null;
        }
    }

    public String deserialize(String tokenString) {
        String[] pieces = splitTokenString(tokenString);
        String jwtPayloadSegment = pieces[1];
        JsonParser parser = new JsonParser();
        JsonElement payload = parser.parse(StringUtils.newStringUtf8(Base64.decodeBase64(jwtPayloadSegment)));
        return payload.toString();
    }

    /**
     * @param tokenString The original encoded representation of a JWT
     * @return Three components of the JWT as an array of strings
     */
    private String[] splitTokenString(String tokenString) {
        String[] pieces = tokenString.split(Pattern.quote("."));
        if (pieces.length != 3) {
            throw new IllegalStateException("Expected JWT to have 3 segments separated by '"
            + "." + "', but it has " + pieces.length + " segments");
        }
        return pieces;
    }

}
