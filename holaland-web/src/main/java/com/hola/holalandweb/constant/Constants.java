package com.hola.holalandweb.constant;

public interface Constants {

    // Module works
    String STT_WORK_NAME_RECRUITMENT_FIND_JOB = "WORK_REQUEST_RECRUITMENT_FIND_JOB";
    int STT_WORK_CODE_PENDING_APPROVAL = 1;
    int STT_WORK_CODE_REJECT = 2;
    int STT_WORK_CODE_APPROVED = 3;
    int STT_WORK_CODE_COMPLETE = 4;
    int STT_WORK_CODE_EXPIRED = 5;
    int STT_WORK_CODE_SAVE_DRAFT = 6;

    String STT_WORK_NAME_REPORT = "WORK_REPORT";
    int STT_WORK_CODE_REPORT_CONSIDERING = 1;
    int STT_WORK_CODE_REPORT_CONFIRM = 2;
    int STT_WORK_CODE_REPORT_DENIED = 3;
    int STT_WORK_CODE_REPORT_CANCELED = 4;

    String STT_WORK_NAME_REQUEST_APPLY_BOOK = "WORK_REQUEST_APPLY_BOOK";
    int STT_WORK_CODE_WAITING_REPOSITORY = 1;
    int STT_WORK_CODE_REQUEST_APPLY_BOOK_AGREED = 2;
    int STT_WORK_CODE_REQUEST_APPLY_BOOK_DENIED = 3;

    // Module food
    String STT_FOOD_NAME_FOOD_ORDER = "FOOD_STUDENT_ORDER";
    int STT_FOOD_CODE_PENDING_APPROVAL = 1;
    int STT_FOOD_CODE_APPROVED = 2;
    int STT_FOOD_CODE_REJECT = 3;
    int STT_FOOD_CODE_COMPLETE = 4;
    int STT_FOOD_CODE_EXPIRED = 5;
}
