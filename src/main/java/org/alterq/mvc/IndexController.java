package org.alterq.mvc;

 import org.alterq.domain.Member;
import org.alterq.repo.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
    	log.debug("init index.jsp");
//       model.addAttribute("login", new Member());
//        model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public @ResponseBody String login( Member newMember) {
    	log.debug("login");
    	log.debug("newMember.name:"+newMember.getName());
    	log.debug("newMember.pwd:"+newMember.getPwd());
    	
        return "do login";
    	
    }
 }
