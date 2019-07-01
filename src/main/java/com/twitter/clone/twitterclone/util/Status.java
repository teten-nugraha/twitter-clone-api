package com.twitter.clone.twitterclone.util;

import java.util.Arrays;
import java.util.List;

public class Status {

    public static String CREATED    = "created";
    public static String ERROR      = "error";
    public static String GET_ALL    = "get_all";
    public static String GET_ONE    = "get_one";
    public static String DELETE     = "delete";
    public static String SUCCESS    = "success";

    private static final String[] success_statuses = new String[]{CREATED, GET_ALL, DELETE, GET_ONE, SUCCESS};
    private static final String[] error_statuses = new String[]{ERROR};

    public static List<String> listSuccessResponse  = Arrays.asList(success_statuses);
    public static List<String> listErrorResponse  = Arrays.asList(error_statuses);

}
