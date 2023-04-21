package com.project.zoopiter.domain.petNote.svc;

import com.project.zoopiter.domain.entity.PetNote;
import com.project.zoopiter.domain.petNote.dao.PetNoteDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetNoteSVCImpl implements PetNoteSVC {

  private final PetNoteDAO petNoteDAO;

  /**
   * @param petNote
   * @return
   */
  @Override
  public String saveNote(PetNote petNote) {
    return petNoteDAO.saveNote(petNote);
  }

  /**
   * 목록
   * @param userId
   * @return
   */
  @Override
  public Optional<PetNote> findByNoteAll(String userId) {
    return petNoteDAO.findByNoteAll(userId);
  }

  /**
   * @param noteNum
   * @param petNote
   * @return
   */
  @Override
  public int updateNote(Long noteNum, PetNote petNote) {
    return petNoteDAO.updateNote(noteNum, petNote);
  }

  /**
   * @param noteNum
   * @return
   */
  @Override
  public int deleteNote(Long noteNum) {
    return petNoteDAO.deleteNote(noteNum);
  }

  /**
   * 전체목록
   * @param userId
   * @return
   */
  @Override
  public List<PetNote> findNoteAll(String userId) {
    return petNoteDAO.findNoteAll(userId);
  }
}
