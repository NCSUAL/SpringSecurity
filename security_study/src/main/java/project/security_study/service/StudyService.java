package project.security_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.security_study.repository.StudyRepository;
import project.security_study.model.StudyData;

@Service
public class StudyService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final StudyRepository studyRepository;
    //static을 쓰면 안되는 이유: static 선언된후 static 한 영역만 공유하기 때문에 데이터가 겹칠 수 있음.

    @Autowired
    public StudyService(StudyRepository studyRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.studyRepository = studyRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //나중에~
    public void sign(StudyData studyData){
        String get_password = studyData.getPassword();
        String encode_password = bCryptPasswordEncoder.encode(get_password);

        studyData.setPassword(encode_password);
        studyRepository.save(studyData);
    }



}
