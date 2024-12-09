package com.cinema.main;

import com.cinema.infra.db.postgres.helpers.PgConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main  {
    public static void main(String[] args) {
        PgConnection.getInstance();

        SpringApplication.run(Main.class, args);
    }
}
