package pairmatching.application.service;

import pairmatching.application.port.inbound.PairMatchUseCase;
import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.application.port.outbound.PairMatchRepository;
import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Mission;
import pairmatching.domain.ShufflePolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PairMatchService implements PairMatchUseCase {
    private final CrewRepository crewRepository;
    private final PairMatchRepository pairMatchRepository;
    private final ShufflePolicy shufflePolicy;
    private final List<Crews> matchedCrews;

    public PairMatchService(CrewRepository crewRepository, PairMatchRepository pairMatchRepository, ShufflePolicy shufflePolicy) {
        this.crewRepository = crewRepository;
        this.pairMatchRepository = pairMatchRepository;
        this.shufflePolicy = shufflePolicy;
        this.matchedCrews = new ArrayList<>();
    }

    @Override
    public List<String> match(Course course, Mission mission) {
        Crews crews = findByCourse(course);
        Crews withMission = crews.withMission(mission);
        Crews shuffle = Stream.generate(() -> withMission.shuffle(shufflePolicy))
                .limit(3)
                .filter(x -> !matchedCrews.contains(x))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 매칭 되는 조합이 존재하지 않습니다"));
        matchedCrews.add(shuffle);
        pairMatchRepository.save(shuffle);
        return shuffle.getPair();
    }

    @Override
    public List<String> findByMatched(Course course, Mission mission) {
        Crews byMatchingInfo = pairMatchRepository.findByMatchingInfo(course, mission);
        return byMatchingInfo.getPair();
    }

    @Override
    public boolean isMatch(Course course, Mission mission) {
        return pairMatchRepository.isMatch(course, mission);
    }

    @Override
    public void clear() {
        matchedCrews.clear();
        pairMatchRepository.clear();
    }

    private Crews findByCourse(Course course) {
        return crewRepository.findByCourse(course);
    }
}
