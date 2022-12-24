package com.techeer.cokkiri.domain.study.service;

import com.techeer.cokkiri.domain.study.dto.StudyDto;
import com.techeer.cokkiri.domain.study.entity.Study;
import com.techeer.cokkiri.domain.study.exception.StudyNotFoundException;
import com.techeer.cokkiri.domain.study.mapper.StudyMapper;
import com.techeer.cokkiri.domain.study.repository.StudyRepository;
import com.techeer.cokkiri.domain.study.repository.UserStudyRepository;
import com.techeer.cokkiri.domain.user.entity.User;
import com.techeer.cokkiri.domain.user.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyService {

  private final StudyRepository studyRepository;
  private final UserStudyRepository userStudyRepository;
  private final StudyMapper studyMapper;
  private final UserMapper userMapper;

  public Study createStudy(StudyDto.Request requestDto, User loginUser) { // 스터디 등록
    Study study = studyMapper.toEntity(requestDto, loginUser);
    Study createdStudy = studyRepository.save(study);

    return createdStudy;
  }

  public boolean isDuplicatedStudy(String studyName) {
    return studyRepository.existsByStudyName(studyName);
  }

  public Study findByStudyId(Long studyId) {
    Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);

    return study;
  }

  public StudyDto.FindResponse findStudyDtoById(Long studyId) {

    Study study = studyRepository.findById(studyId).orElseThrow(StudyNotFoundException::new);
    List<User> studyMembers = userStudyRepository.findByStudyId(studyId);
    StudyDto.FindResponse studyFindResponse = studyMapper.toStudyDto(study, studyMembers);

    return studyFindResponse;
  }
}
