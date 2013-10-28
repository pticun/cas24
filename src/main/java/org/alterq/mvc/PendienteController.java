package org.alterq.mvc;

import org.alterq.domain.Member;
import org.alterq.repo.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/pendiente")
public class PendienteController {
    @Autowired
    private MemberDao memberDao;

    @RequestMapping(method = RequestMethod.GET)
    public String displaySortedMembers(Model model) {
	model.addAttribute("newMember", new Member());
	model.addAttribute("members", memberDao.findAllOrderedByName());
	return "pendiente";
    }

}
