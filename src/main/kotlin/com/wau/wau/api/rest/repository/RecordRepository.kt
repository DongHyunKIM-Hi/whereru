package com.wau.wau.api.rest.repository

import com.wau.wau.api.rest.model.entity.Record
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecordRepository:JpaRepository<Record, Int> {
}