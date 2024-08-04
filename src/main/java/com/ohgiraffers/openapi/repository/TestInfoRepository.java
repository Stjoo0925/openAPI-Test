package com.ohgiraffers.openapi.repository;

import com.ohgiraffers.openapi.entity.TestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestInfoRepository extends JpaRepository<TestInfo, Long> {
}
