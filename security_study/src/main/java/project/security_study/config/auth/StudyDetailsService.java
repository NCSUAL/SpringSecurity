package project.security_study.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.security_study.model.StudyData;
import project.security_study.repository.StudyRepository;

import java.util.Optional;


// 시큐리티 설정에서 loginProcessUrl("login");
// /login 요청이 오면 자동으로 StudyDetailsService 타입으로 IOC 되어 있는 loadUserByUsername가 실행
@Service
public class StudyDetailsService implements UserDetailsService {

    private final StudyRepository studyRepository;

    @Autowired
    public StudyDetailsService(StudyRepository studyRepository) {
        this.studyRepository =  studyRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<StudyData> find = studyRepository.findByUsername(username);

        if(find.isPresent()){
            StudyData data = find.get(); //optional 벗겨내기
            return new StudyDetails(data);
        }
        else{
            return null;
        }
    }
}
