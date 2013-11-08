package org.alterq.mvc;

 import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.alterq.domain.UserAlterQ;
import org.alterq.dto.ResponseDto;
import org.alterq.repo.MemberDao;
import org.alterq.repo.SessionAlterQDao;
import org.alterq.repo.UserAlterQDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value={"/","/index","/login"})
public class IndexController
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private UserAlterQDao userDao;
    @Autowired
    private SessionAlterQDao sessionDao;

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
    	log.debug("init index.jsp");
//       model.addAttribute("login", new Member());
//        model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public @ResponseBody ResponseDto login( UserAlterQ user,HttpServletResponse response) {
    	log.debug("login");
    	log.debug("newMember.name:"+user.getName());
    	log.debug("newMember.pwd:"+user.getPwd());
    	UserAlterQ userValidate= userDao.validateLogin(user.getName(), user.getPwd());
    	ResponseDto dto=new ResponseDto();
    	if(userValidate!=null){
    		String sessionID = sessionDao.startSession(userValidate.getName());
    		log.debug("Session ID is:" + sessionID);
    		
    		response.addCookie(new Cookie("session", sessionID));
    		dto.setUserAlterQ(userValidate);
    	}
    	else{
    		dto.setUserAlterQ(null);
    	}
    	return dto;
    	
    	
    	
    }
 }
