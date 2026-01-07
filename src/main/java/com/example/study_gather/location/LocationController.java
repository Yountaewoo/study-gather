package com.example.study_gather.location;

import com.example.study_gather.common.security.JwtProvider;
import com.example.study_gather.location.dto.CreateLocationRequest;
import com.example.study_gather.location.dto.CreateLocationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public CreateLocationResponse createLocation(@RequestBody CreateLocationRequest request,
                                                 @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        return locationService.createLocation(request, memberId);
    }
}
