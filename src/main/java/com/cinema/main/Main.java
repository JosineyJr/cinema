package com.cinema.main;

import java.util.ArrayList;

import com.cinema.application.controllers.users.CreateClientController;
import com.cinema.application.dtos.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.domain.usecases.users.CreateClientUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.PgClientRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new PgConnection().connect();

        ArrayList<String> genres = new ArrayList<String>();
        genres.add("action");
        genres.add("terror");

        Response result = new CreateClientController(new CreateClientUseCase(new PgClientRepository(), new PgClientRepository()))
                .handle(new CreateClientDTO("daniel", "lopes", "15018542675", genres));

        System.out.println(result.getStatusCode() + " " + result.getData());
    }
}