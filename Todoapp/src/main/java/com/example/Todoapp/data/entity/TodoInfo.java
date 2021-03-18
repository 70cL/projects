package com.example.Todoapp.data.entity;

import java.time.LocalDate;


public class TodoInfo {
    private long m_id;
    private String m_username;
    private String m_title;
    private String m_description;
    private LocalDate m_insertDate = LocalDate.now();
    private LocalDate m_startDate;
    private LocalDate m_expectedEndDate;
    private LocalDate m_endDate;
    private boolean m_completed;

    public TodoInfo()
    {
    }

    public TodoInfo(String title, String username, String description, LocalDate startDate, LocalDate expectedEndDate, LocalDate endDate, boolean completed)
    {
        this(0, title, username, description, startDate, expectedEndDate, endDate, false);
    }

    public TodoInfo(long id, String title, String username, String description, LocalDate startDate, LocalDate insertDate, LocalDate expectedEndDate, LocalDate endDate, boolean completed)
    {
        m_id = id;
        m_title = title;
        m_username = username;
        m_description = description;
        m_startDate = startDate;
        m_insertDate = insertDate;
        m_expectedEndDate = expectedEndDate;
        m_endDate = endDate;
        m_completed = completed;
    }

    public TodoInfo(long id, String title, String username, String description, LocalDate startDate, LocalDate expectedEndDate, LocalDate endDate, boolean completed)
    {
        m_id = id;
        m_title = title;
        m_username = username;
        m_description = description;
        m_startDate = startDate;
        m_expectedEndDate = expectedEndDate;
        m_endDate = endDate;
        m_completed = completed;
    }

    public long getId(){
        return m_id;
    }

    public void setId(long id){
        m_id = id;
    }

    public String getUsername() {
        return m_username;
    }

    public void setUsername(String m_username) {
        this.m_username = m_username;
    }

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String m_title) {
        this.m_title = m_title;
    }

    public String getDescription() {
        return m_description;
    }

    public void setDescription(String m_description) {
        this.m_description = m_description;
    }

    public LocalDate getInsertDate() {
        return m_insertDate;
    }

    public void setInsertDate(LocalDate m_insertDate) {
        this.m_insertDate = m_insertDate;
    }

    public LocalDate getStartDate() {
        return m_startDate;
    }

    public void setStartDate(LocalDate m_startDate) {
        this.m_startDate = m_startDate;
    }

    public LocalDate getExpectedEndDate() {
        return m_expectedEndDate;
    }

    public void setExpectedEndDate(LocalDate m_expectedEndDate) {
        this.m_expectedEndDate = m_expectedEndDate;
    }

    public LocalDate getEndDate() {
        return m_endDate;
    }

    public void setEndDate(LocalDate m_endDate) {
        this.m_endDate = m_endDate;
    }

    public boolean isCompleted() {
        return m_completed;
    }

    public TodoInfo setCompleted(boolean m_completed) {
        this.m_completed = m_completed;
        return this;
    }
}
