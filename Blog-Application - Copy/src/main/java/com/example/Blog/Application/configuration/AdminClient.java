package com.example.Blog.Application.configuration;

import com.example.Blog.Application.entities.DropdownFilterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Interview-service")
public interface AdminClient {

    @PostMapping("dropDownFilter")
    ResponseEntity<?> dropdownFilter(@RequestBody DropdownFilterRequest request);
}
