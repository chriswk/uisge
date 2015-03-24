# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "DISTILLERY" ("DIST_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"NAME" VARCHAR NOT NULL,"ESTABLISHED" INTEGER NOT NULL,"CLOSED" INTEGER NOT NULL,"SPIRIT_STILLS" INTEGER NOT NULL,"WASH_STILLS" INTEGER NOT NULL,"OWNER" VARCHAR NOT NULL,"CAPACITY_PER_YEAR" DECIMAL(21,2) NOT NULL);
create table "WHISKEY" ("WHISKEY_ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,"NAME" VARCHAR NOT NULL,"VINTAGE" INTEGER NOT NULL,"BOTTLED" INTEGER NOT NULL,"ABV" DECIMAL(21,2) NOT NULL,"CASK_NR" VARCHAR NOT NULL,"SIZE" INTEGER NOT NULL,"DISTILLERY_ID" BIGINT NOT NULL);
alter table "WHISKEY" add constraint "DISTILLERY_FK" foreign key("DISTILLERY_ID") references "DISTILLERY"("DIST_ID") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "WHISKEY" drop constraint "DISTILLERY_FK";
drop table "WHISKEY";
drop table "DISTILLERY";

