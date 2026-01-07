package com.example.study_gather.location;

import com.example.study_gather.location.dto.CreateLocationRequest;
import com.example.study_gather.location.dto.CreateLocationResponse;
import com.example.study_gather.member.Member;
import com.example.study_gather.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CreateLocationResponse createLocation(CreateLocationRequest request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자가 없습니다."));
        member.validateAdminPermission();
        if (request.parentId() == null) {
            Location location = locationRepository.save(new Location(request.parentId(), request.name()));
            return new CreateLocationResponse(location.getId(), location.getParentId(), location.getName());
        } else {
            Location parentLocation = locationRepository.findById(request.parentId()).orElseThrow(
                    () -> new NoSuchElementException("해당하는 지역이 없습니다."));
            Location location = locationRepository.save(new Location(request.parentId(), request.name()));
            return new CreateLocationResponse(location.getId(), location.getParentId(), location.getName());
        }
    }

}
