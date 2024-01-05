package jpabook.jpabook.controller;

import jakarta.validation.Valid;
import jpabook.jpabook.entity.Address;
import jpabook.jpabook.entity.Member;
import jpabook.jpabook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //Register
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {
        if (result.hasErrors()){
            return "members/createMemberForm";
        }
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(new Address(memberForm.getCity(),
                memberForm.getStreet(),
                memberForm.getZipcode()));

        memberService.join(member);

        return "redirect:/";
    }

    //Retrieve
    @GetMapping("/members")
    public String list(Model model){
        List<Member>  members=memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
