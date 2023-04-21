package com.project.zoopiter.domain.petNote.dao;

import com.project.zoopiter.domain.entity.PetNote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PetNoteDAOImpl implements PetNoteDAO{

  private final NamedParameterJdbcTemplate template;
  /**
   * 등록
   *
   * @param petNote
   * @return
   */
  @Override
  public String saveNote(PetNote petNote) {
    StringBuffer sb = new StringBuffer();

    sb.append("insert into pet_note(NOTE_NUM , USER_ID, PET_NAME, PET_TYPE, PET_GENDER, PET_BIRTH, PET_YN, PET_WEIG, PET_H_CHECK, \n" +
        "    PET_H_NAME, PET_H_TEACHER, PET_REASON, PET_STMP, PET_SIGNICE,PET_DATE, PET_NEXTDATE, PET_VAC ");
    sb.append(") values( ");
    sb.append("PET_NOTE_NOTE_NUM_seq.nextval, ");
    sb.append(":userId, ");
    sb.append(":petName, ");
    sb.append(":petImg, ");
    sb.append(":petType, ");
    sb.append(":petGender, ");
    sb.append(":petBirth, ");
    sb.append(":petYn, ");
    sb.append(":petInfo, ");
    sb.append(":petWeig, ");
    sb.append(":petHCheck, ");
    sb.append(":petHName, ");
    sb.append(":petHTeacher, ");
    sb.append(":petStmp, ");
    sb.append(":petSignice, ");
    sb.append(":petNextdate, ");
    sb.append("'p0101' ");
    sb.append(" ) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(petNote);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sb.toString(), param, keyHolder, new String[]{"note_num"});

    Long key = keyHolder.getKey().longValue();

    return String.valueOf(key);
  }

  /**
   * 목록
   * @param id
   * @return
   */
  @Override
  public Optional<PetNote> findByNoteAll(String userId) {
    StringBuffer sb = new StringBuffer();
    sb.append(" select * ");
    sb.append(" from pet_note ");
    sb.append(" where user_id = :userId ");

    Map<String, String> param = Map.of("userId", userId);

    PetNote petNote = template.queryForObject(
            sb.toString(),
            param,
            BeanPropertyRowMapper.newInstance(PetNote.class)
    );
      return Optional.of(petNote);
  }

  /**
   * 조회
   *
   * @param noteNum
   * @return
   */
//  @Override
//  public Optional<PetNote> findNoteInfo(Long noteNum) {
//
//    StringBuffer sb = new StringBuffer();
//    sb.append(" select * ");
//    sb.append(" from pet_note ");
//    sb.append(" where note_num = :id ");
//
//    try {
//      Map<String, Long> param = Map.of("id", noteNum);
//
//      PetNote petNote = template.queryForObject(sb.toString(), param,
//          BeanPropertyRowMapper.newInstance(PetNote.class));
//      return Optional.of(petNote);
//    } catch (EmptyResultDataAccessException e){
//      return Optional.empty();
//    }
//  }

  /**
   * 수정
   *
   * @param NoteNum
   * @param petNote
   * @return
   */
  @Override
  public int updateNote(Long NoteNum, PetNote petNote) {

    StringBuffer sb = new StringBuffer();

    sb.append(" update pet_note ");
    sb.append(" set ");
    sb.append(" pet_weig= :petWeig ");
    sb.append(" pet_h_check= :petHCheck ");
    sb.append(" pet_h_name= :petHName ");
    sb.append(" pet_h_teacher= :petHTeacher ");
    sb.append(" pet_reason= :petReason ");
    sb.append(" pet_stmp= :petStmp ");
    sb.append(" pet_signice= :petSignice ");
    sb.append(" pet_nextdate= :petNextdate ");
    sb.append(" where note_num= :id ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("petWeig", petNote.getPetWeig())
        .addValue("petHCheck", petNote.getPetHCheck())
        .addValue("petHName", petNote.getPetHName())
        .addValue("petHTeacher", petNote.getPetHTeacher())
        .addValue("petReason", petNote.getPetReason())
        .addValue("petStmp", petNote.getPetStmp())
        .addValue("petSingnice", petNote.getPetSignice())
        .addValue("petNextdate", petNote.getPetNextdate())
        .addValue("id", NoteNum);

    return template.update(sb.toString(), param);
  }

  /**
   * 삭제
   *
   * @param NoteNum
   * @return
   */
  @Override
  public int deleteNote(Long NoteNum) {

    String sql = "delete from pet_note where note_num= :noteNum ";

    return template.update(sql, Map.of("noteNum", NoteNum));
  }

  /**
   * 목록
   *
   * @param userId
   * @return
   */
  @Override
  public List<PetNote> findNoteAll(String userId) {

    StringBuffer sb = new StringBuffer();
    sb.append(" select * ");
    sb.append(" from pet_note ");
    sb.append(" where user_id= :userId ");

    Map<String, String> param = Map.of("userId", userId);

    List<PetNote> list = template.query(
        sb.toString(),
        param,
        BeanPropertyRowMapper.newInstance(PetNote.class)
    );

    log.info("list={}", list);
    return list;
  }
}
