package com.hola.holalandwork.service;

import com.hola.holalandwork.entity.WorkRequestBook;
import com.hola.holalandwork.repository.WorkRequestBookRepository;
import com.hola.holalandwork.service.impl.WorkRequestBookServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkRequestBookServiceTest {
    @InjectMocks
    WorkRequestBookServiceImpl workRequestBookService;

    @Mock
    WorkRequestBookRepository workRequestBookRepository;

    public WorkRequestBook genWorkRequestBook(){
        WorkRequestBook workRequestBook = WorkRequestBook.builder()
                .workRequestBookId(1)
                .userId(1)
                .workRequestFindJobId(1)
                .sttWorkCode(1)
                .build();
        return workRequestBook;
    }

    @Test
    public void getAllWorkRequestBook() {
        List<WorkRequestBook> workRequestBookList = new ArrayList<>();
        workRequestBookList.add(genWorkRequestBook());
        when(workRequestBookRepository.getAll()).thenReturn(workRequestBookList);
        workRequestBookService.getAll();
    }

    @Test
    public void getOneWorkRequestBook() {
        when(workRequestBookRepository.getOne(1)).thenReturn(genWorkRequestBook());
        workRequestBookService.getOne(1);
    }

    @Test
    public void createWorkRequestBook() {
        when(workRequestBookRepository.save(any())).thenReturn(true);
        workRequestBookService.save(genWorkRequestBook());
    }
}
