package com.project.zoopiter.web.form.petNote;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetNoteSaveForm {
  private Long number;        //순번
  private String userId;      //일반회원 아이디
  private String petName;     //반려동물 이름
  private byte[] petImg;      //반려동물 사진
  private String petType;     //반려동물 품종
  private String petGender;   //반려동물 성별
  private LocalDate petBirth; //반려동물 생일
  private String petYn;       //중성화 여부
  private String petInfo;     //기타 사항
  private Long petWeig;       //반려동물 몸무게
  private LocalDate petHCheck; //병원 방문 날짜
  private String petHName;    //방문한 병원이름
  private String petHTeacher; //담당 수의사
  private String petReason;   //병원내방 이유
  private String petStmp;     //동물 증상
  private LocalDate petNextdate;  //다음예약일
  private String petVac;      //기초 접종 여부

}
