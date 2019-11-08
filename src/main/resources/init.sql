/*This is only for tests*/

CREATE SCHEMA `link_shortener`;

USE `link_shortener`;

create table short_url_statistics (uuid varchar(32) not null, link_creation_count bigint, redirect_amount bigint, primary key (uuid)) engine=MyISAM;
create table short_urls (uuid varchar(32) not null, original_url varchar(255) not null, primary key (uuid)) engine=MyISAM;