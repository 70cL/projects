package EmployeeInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private String name = "";
    private String surName = "";
    private String birthDate = "";
    private String birthPlace = "";
    private String mail = "";
    private String phone = "";
    private String status = "";
    private String isWorking = "";
    private String university = "";

    public Person()
    {

    }

    public Person(String name, String surName, String birthDate, String birthPlace,
                  String mail, String phone, String status, String isWorking, String university) {
        this.name = name;
        this.surName = surName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.mail = mail;
        this.phone = phone;
        this.status = status;
        this.isWorking = isWorking;
        this.university = university;
    }
    public Person(String name, String surName, String birthDate, String mail, String phone, String status)
    {
        this(name, surName, birthDate, "", mail , phone, status, "", "");
    }

    public Person(String name, String surName, String birthDate, String birthPlace, String mail, String phone, String isWorking, String university)
    {
        this(name, surName, birthDate, birthPlace, mail , phone, "", isWorking, university);
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurName() {
        return surName;
    }

    public Person setSurName(String surName) {
        this.surName = surName;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Person setBirthDate(String birthDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(birthDate, formatter);

        this.birthDate = localDate.toString();

        return this;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Person setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Person setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Person setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getStatus()
    {
        return status;
    }

    public Person setStatus(String status)
    {
        this.status = status;
        return this;
    }

    public String getIsWorking() {
        return isWorking;
    }

    public Person setIsWorking(String isWorking) {
        this.isWorking = isWorking;
        return this;
    }

    public String getUniversity() {
        return university;
    }

    public Person setUniversity(String university) {
        this.university = university;
        return this;
    }

    public String [] getPersonalInfoAsArray()
    {
        String [] ID = {name, surName, birthDate, birthPlace, mail, phone, status, isWorking, university};

        return ID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
