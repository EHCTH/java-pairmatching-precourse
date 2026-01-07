package pairmatching.application.service;

import pairmatching.application.port.inbound.MatchingUseCase;
import pairmatching.application.port.outbound.CrewRepository;
import pairmatching.application.port.outbound.PairRepository;
import pairmatching.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatchingService implements MatchingUseCase {
    private final List<Crews> matchedCrews = new ArrayList<>();
    private final ShufflePolicy shufflePolicy;
    private final CrewRepository crewRepository;
    private final PairRepository pairRepository;


    public MatchingService(ShufflePolicy shufflePolicy, CrewRepository crewRepository, PairRepository pairRepository) {
        this.shufflePolicy = shufflePolicy;
        this.crewRepository = crewRepository;
        this.pairRepository = pairRepository;
    }

    @Override
    public List<String> matching(DevelopType developType, Mission mission) {
        remove(developType, mission);
        Crews withMission = crewRepository.findByDevelopType(developType).withMission(mission);
        Crews crews = shuffle(withMission);
        pairRepository.save(crews);
        matchedCrews.add(crews);
        return crews.convertResponse();
    }

    @Override
    public List<String> search(DevelopType developType, Mission mission) {
        Crews byMissionAndDevelopType = pairRepository.findByMissionAndDevelopType(developType, mission);
        return byMissionAndDevelopType.convertResponse();
    }

    @Override
    public void clear() {
        matchedCrews.clear();
        pairRepository.clear();
    }

    @Override
    public boolean isContainsMatchingInfo(DevelopType developType, Mission mission) {
        return pairRepository.isProgressMission(developType, mission);
    }

    private void remove(DevelopType developType, Mission mission) {
        matchedCrews.removeIf(x -> x.getDevelopType().equals(developType) && x.getMission().equals(mission));
        pairRepository.removeIf(developType, mission);
    }

    private Crews shuffle(Crews withMission) {
        return Stream.generate(() -> withMission.shuffle(shufflePolicy))
                .limit(3)
                .filter(this::isNotMatched)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 매칭을 할수없습니다"));
    }

    private boolean isNotMatched(Crews crews) {
        return !matchedCrews.contains(crews);
    }

}
