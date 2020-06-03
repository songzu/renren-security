package io.renren.modules;

import com.alibaba.druid.util.StringUtils;
import io.renren.common.config.JwtConfig;
import io.renren.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p></p>
 *
 * @author 松竹
 * @version $$ Id: WechatController.java, V 0.1 2020-06-03 17:46 wanggengen Exp $$
 **/

@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Resource
    private JwtConfig jwtConfig;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;

        this.response = response;

        this.session = request.getSession();

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public R login(@RequestParam("code") String code) {
        String wxOpenId = "wxOpenId";
        String wxSesionKey = "wxSesionKey";

        String token = jwtConfig.createTokenByWxAccount(wxOpenId, wxSesionKey);
        TokenDTO tokenDTO = new TokenDTO(token, Boolean.TRUE);

        return R.ok("success").put("token", tokenDTO);
    }

    @RequestMapping(value = "/verifyLogin", method = RequestMethod.GET)
    public R verifyLogin() {

        String jwtToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwtToken)) {
            return R.error(110, "token is invalid , please check your token");
        }

        String wxOpenId = jwtConfig.getWxOpenIdByToken(jwtToken);
        String sessionKey = jwtConfig.getSessionKeyByToken(jwtToken);
        if (StringUtils.isEmpty(wxOpenId)) {
            return R.error(110, "user account not exits , please check your token");
        }
        if (StringUtils.isEmpty(sessionKey)) {
            return R.error(110, "sessionKey is invalid , please check your token");
        }
        if (!jwtConfig.verifyToken(jwtToken)) {
            return R.error(110, "token is invalid , please check your token");
        }

        return R.ok("登陆校验通过");
    }


}
