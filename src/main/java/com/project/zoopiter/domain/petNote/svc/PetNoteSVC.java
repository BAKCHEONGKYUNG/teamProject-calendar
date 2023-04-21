package com.project.zoopiter.domain.petNote.svc;

import com.project.zoopiter.domain.entity.PetNote;

import java.util.List;
import java.util.Optional;

public interface PetNoteSVC {

  //등록
  String saveNote(PetNote petNote);

  //목록
  Optional<PetNote> findByNoteAll(String userId);

  //수정
  int updateNote(Long NoteNum, PetNote petNote);

  //삭제
  int deleteNote(Long noteNum);

  //목록
  List<PetNote> findNoteAll(String userId);
}
