package com.cinema.infra.db.postgres.helpers.entities;

public interface IEntityConverter<Source, Target> {
  Target convert(Source source);

  Source pgConverter(Target target);
}
