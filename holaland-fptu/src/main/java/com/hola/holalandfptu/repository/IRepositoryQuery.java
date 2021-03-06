package com.hola.holalandfptu.repository;

public interface IRepositoryQuery {

    String CLUB_TYPE_GET_ALL = "SELECT * FROM fptu_club_type WHERE fptu_club_type_deleted = 0";
    String CLUB_TYPE_GET_ONE = "SELECT * FROM fptu_club_type WHERE fptu_club_type_id = ? AND fptu_club_type_deleted = 0";

    String CLUB_GET_ALL_BY_TYPE = "SELECT * FROM fptu_club WHERE fptu_club_type_id = ? AND fptu_club_deleted = 0";
    String CLUB_GET_ONE = "SELECT * FROM fptu_club WHERE fptu_club_id = ? AND fptu_club_deleted = 0";
}
