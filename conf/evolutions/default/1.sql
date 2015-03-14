# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "DISTILLERY" ("DIST_ID" BIGINT NOT NULL PRIMARY KEY,"NAME" VARCHAR NOT NULL,"ESTABLISHED" INTEGER NOT NULL);
create table "WHISKEY" ("WHISKEY_ID" BIGINT NOT NULL PRIMARY KEY,"NAME" VARCHAR NOT NULL,"AGE" INTEGER NOT NULL,"DISTILLERY_ID" BIGINT NOT NULL);

# --- !Downs

drop table "WHISKEY";
drop table "DISTILLERY";
