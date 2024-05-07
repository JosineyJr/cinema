package com.cinema.main;

import java.util.ArrayList;

import com.cinema.domain.usecases.users.CreateClient;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.PgClientRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new PgConnection().connect();

        ArrayList<String> genres = new ArrayList<String>();
        genres.add("action");

        new CreateClient(new PgClientRepository()).execute("daniel", "lopes", "15018542675", genres);
    }
}