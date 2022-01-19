package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentExistsEmail() {
        //given
        String email = "jami@ja.com";
        Student student = new Student(
                "Jamila",
                email,
                Gender.FEMALE
        );
        underTest.save(student);

        //when
        boolean exists = underTest.selectExistsEmail(email);

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfStudentDoesNotExistsEmail() {
        //given
        String email = "jami@ja.com";

        //when
        boolean exists = underTest.selectExistsEmail(email);

        //then
        assertThat(exists).isFalse();
    }
}