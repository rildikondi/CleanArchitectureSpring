package com.akondi.inmemorydb;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.akondi.inmemorydb.data")
public class TestConfiguration { }
