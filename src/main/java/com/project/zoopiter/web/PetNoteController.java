package com.project.zoopiter.web;

import com.project.zoopiter.domain.entity.Member;
import com.project.zoopiter.domain.entity.PetNote;
import com.project.zoopiter.domain.member.svc.MemberSVC;
import com.project.zoopiter.domain.petNote.svc.PetNoteSVC;
import com.project.zoopiter.web.form.member.DetailForm;
import com.project.zoopiter.web.form.petNote.PetNoteForm;
import com.project.zoopiter.web.login.LoginMember;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/mynote")
@RequiredArgsConstructor
public class PetNoteController {

  private final PetNoteSVC petNoteSVC;

  private final MemberSVC memberSVC;

  @GetMapping("")
  public String findNoteAll(Model model, @SessionAttribute(
      name = SessionConst.LOGIN_MEMBER, required = false) LoginMember loginMember
  ) {
    if (loginMember == null) {
      // 로그인 안되어있으면 로그인화면으로 이동
      return "redirect:/login";
    }
    String userId = loginMember.getUserId();
    Optional<Member> member = memberSVC.findById(userId);
    model.addAttribute("member", member);

//    String userPw = member.map(Member::getUserPw).orElse("");
    String userEmail = member.map(Member::getUserEmail).orElse("");
    String userNick = member.map(Member::getUserNick).orElse("");

    DetailForm detailForm = new DetailForm();
    detailForm.setUserEmail(userEmail);
    detailForm.setUserNick(userNick);
    detailForm.setUserId(userId);
    model.addAttribute("detailForm", detailForm);

    return "mypage/mypage_scheduler";
  }

  // 목록
  @GetMapping("/calender")
  public String findByNoteAll(
//      @PathVariable("id") Long id,
      Model model,
      HttpServletRequest request
  ){

    String userId = null;
    HttpSession session = request.getSession(false);
    if(session != null) {
      LoginMember loginMember = (LoginMember)session.getAttribute(SessionConst.LOGIN_MEMBER);
      userId = loginMember.getUserId();
    }
    Optional<PetNote> petNoteOptional = petNoteSVC.findByNoteAll(userId);

//    Optional<PetNote> petNoteOptional = petNoteSVC.findByNoteAll(id);
    PetNote petNote = petNoteOptional.get();

    PetNoteForm petNoteForm = new PetNoteForm();
    petNoteForm.setNumber(petNote.getNoteNum());
    petNoteForm.setUserId(petNote.getUserId());
    petNoteForm.setPetName(petNote.getPetName());
    petNoteForm.setPetImg(petNote.getPetImg());
    petNoteForm.setPetType(petNote.getPetType());
    petNoteForm.setPetGender(petNote.getPetGender());
    petNoteForm.setPetBirth(petNote.getPetBirth());
    petNoteForm.setPetYn(petNote.getPetYn());
    petNoteForm.setPetInfo(petNote.getPetInfo());
    petNoteForm.setPetWeig(petNote.getPetWeig());
    petNoteForm.setPetHCheck(petNote.getPetHCheck());
    petNoteForm.setPetHName(petNote.getPetHName());
    petNoteForm.setPetHTeacher(petNote.getPetHTeacher());
    petNoteForm.setPetReason(petNote.getPetReason());
    petNoteForm.setPetStmp(petNote.getPetStmp());
    petNoteForm.setPetNextdate(petNote.getPetNextdate());
    petNoteForm.setPetVac(petNote.getPetVac());

    model.addAttribute("petNoteForm", petNoteForm);
    return "mypage/mypage_scheduler";
  }

  //조회
//  @GetMapping("/{id}/calener")
//  public String findNoteById(
//      @PathVariable("id") Long id,
//      Model model
//  ){
//    Optional<PetNote> findNoteInfo = petNoteSVC.findByNote(id);
//    PetNote petNote = findNoteInfo.orElseThrow(() -> new RuntimeException("PetNote not found for id:" + id));
//
//
//
//
//    return "mypage/scheduler";
//  }
  //목록
//  @ModelAttribute("petNoteInfos")
//  public List<PetNote> getPetNote(HttpServletRequest request){
//
//    List<PetNote> petNoteInfos = null;
//    HttpSession session = request.getSession(false);
//
//    if (session != null) {
//      Optional<LoginMember> loginMemberOpt = Optional.ofNullable((LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER));
//
//      if (loginMemberOpt.isPresent()) {
//        LoginMember loginMember = loginMemberOpt.get();
//        petNoteInfos = petNoteSVC.findNoteAll(loginMember.getUserId());
//      }
//    }
//    return petNoteInfos;
//  }

  //목록 양식
//  @GetMapping("/petscheduler")
//  public String saveNote(Model model){
//    PetNoteSaveForm petNoteSaveForm = new PetNoteSaveForm();
//    model.addAttribute("petNoteSaveForm", petNoteSaveForm);
//
//    return "mynote/mypage_schedule";
//  }


}
