package dev.be.modulecommon.repositories

import dev.be.modulecommon.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>